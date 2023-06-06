package fr.isep.TravelMate.controller;

import com.fasterxml.jackson.databind.JsonNode;
import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import fr.isep.TravelMate.algorithms.TourismAttraction;
import fr.isep.TravelMate.model.City;
import fr.isep.TravelMate.repository.AttractionsRepository;
import fr.isep.TravelMate.service.TourismService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tourism")
public class TourismController {
    private final TourismService tourismService;
    private final AttractionsRepository attractionsRepository;

    @GetMapping("/attractions")
    public List<TourismAttraction> getTourismAttractions() {
        // Replace with your implementation to fetch the list of attractions
        return null;
    }

    @PostMapping("/closest-attractions")
    public JsonNode getClosestAttractions(@RequestBody String cityName) {
        City city = City.valueOf(cityName);
        //TourismAttraction sourceAttraction = new TourismAttraction(city.getLatitude(), city.getLongitude());
        //sourceAttraction.setLatitude(city.getLatitude());
        //sourceAttraction.setLongitude(city.getLongitude());

        Optional<JsonNode> dest = tourismService.findClosestAttraction(city);
        return dest.orElse(null);
    }
    @GetMapping("/NameAttractions")
    public List<String> getAttractionNames() {
        List<String> attractionNames = attractionsRepository.getAllAttractionNames();
        return attractionNames;
    }
    @GetMapping("/NameCity")
    public List<String> getCityNames() {
        List<String> cityNames = attractionsRepository.getCityNames();
        return cityNames;
    }



}
