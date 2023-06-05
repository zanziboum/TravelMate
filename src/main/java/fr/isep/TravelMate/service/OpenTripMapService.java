package fr.isep.TravelMate.service;

import com.fasterxml.jackson.databind.JsonNode;
import fr.isep.TravelMate.Entity.AttractionKindEntity;
import fr.isep.TravelMate.Entity.CityEntity;
import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import fr.isep.TravelMate.model.City;
import fr.isep.TravelMate.repository.AttractionKindRepository;
import fr.isep.TravelMate.repository.AttractionsRepository;
import fr.isep.TravelMate.repository.CityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
 @RequiredArgsConstructor
 public class OpenTripMapService {

     private static final String apiKey = "5ae2e3f221c38a28845f05b63a2d5c94a9a2733e6841db3e6c20d838";

     private final AttractionsRepository attractionsRepository;
     private final CityRepository cityRepository;
     private final AttractionKindRepository kindRepository;

     //private static final String apiKey = "5ae2e3f221c38a28845f05b63a2d5c94a9a2733e6841db3e6c20d838";

     public Optional<JsonNode> getNearbyAttractions(double latitude, double longitude, int radius) {

         String apiUrl = String.format(Locale.ENGLISH,"https://api.opentripmap.com/0.1/en/places/radius?radius=%d&lon=%f&lat=%f&format=json&apikey=%s",
                 radius, longitude, latitude, apiKey);

         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);
         if (response.getStatusCode().is2xxSuccessful()) {
             JsonNode responseBody = response.getBody();
             return Optional.ofNullable(responseBody);
             // Parse the JSON response and extract the required information
             // ...
         } else {
             System.out.println("Request failed with code: " + response.getStatusCode());
             return Optional.empty();
         }
     }

     public boolean addAttractionsFromCity(City city) {

         Optional<CityEntity> cityEntityOptional = cityRepository.findByName(city.name());
         if (cityEntityOptional.isEmpty()) return false;
         CityEntity cityEntity = cityEntityOptional.get();

         Optional<JsonNode> attractionsOptional = this.getNearbyAttractions(city.getLatitude(), city.getLongitude(), 5000);
         if (attractionsOptional.isEmpty()) return false;
         JsonNode attractions = attractionsOptional.get();

         try {
             attractions.forEach(attraction -> {
                 String name = attraction.get("name").asText();
                 double lon = attraction.get("point").get("lon").asDouble();
                 double lat = attraction.get("point").get("lat").asDouble();
                 int score = attraction.get("rate").asInt();

                 String kinds = attraction.get("kinds").asText();
                 String[] kindsArray = kinds.split(",");
                 Set<AttractionKindEntity> kindEntitySet = new HashSet<>();

                 for (String kindName : kindsArray){
                     String finalKindName = kindName.trim();
                     AttractionKindEntity attractionKind = kindRepository.findByName(finalKindName)
                             .orElseGet(() -> {
                         AttractionKindEntity newAttractionKind = new AttractionKindEntity();
                         newAttractionKind.setName(finalKindName);
                         return kindRepository.save(newAttractionKind);
                     });
                     kindEntitySet.add(attractionKind);
                 }
                 if (attractionsRepository.findByName(name).isEmpty()){
                     TouristAttractionEntity touristAttraction = new TouristAttractionEntity(name,lon,lat,score,cityEntity,kindEntitySet);
                     attractionsRepository.save(touristAttraction);
                 }
             });
         }catch (Exception e){
             System.out.println(e);
             return false;
         }
         return true;
     }

 }
