package fr.isep.TravelMate.controller;

import fr.isep.TravelMate.service.OpenTripMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tourism")
public class TourismController {
    private final OpenTripMapService openTripMapService;

    @Autowired
    public TourismController(OpenTripMapService openTripMapService) {
        this.openTripMapService = openTripMapService;
    }

    @GetMapping("/attractions")
    public void getNearbyAttractions() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        int radius = 5000;

        openTripMapService.getNearbyAttractions(latitude, longitude, radius);
    }
}
