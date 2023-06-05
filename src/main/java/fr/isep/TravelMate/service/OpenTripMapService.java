package fr.isep.TravelMate.service;

import com.fasterxml.jackson.databind.JsonNode;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Optional;

 @Service
 public class OpenTripMapService {
     //@Value(value = "${api-key}")
     private static final String apiKey = "5ae2e3f221c38a28845f05b63a2d5c94a9a2733e6841db3e6c20d838";

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
 }
