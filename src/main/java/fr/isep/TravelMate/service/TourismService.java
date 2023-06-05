package fr.isep.TravelMate.service;

import com.fasterxml.jackson.databind.JsonNode;
import fr.isep.TravelMate.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourismService {
    private final OpenTripMapService openTripMapService;

    public Optional<JsonNode> findClosestAttraction(City city) {
        // Call the OpenTripMapService to fetch nearby attractions
        double latitude = city.getLatitude();
        double longitude = city.getLongitude();
        int radius = 5000; // Set the desired radius for nearby attractions



        // Rest of the code to find the closest attractions
        // You can use the fetched nearby attractions to create the graph and perform Dijkstra's algorithm
        // as shown in the previous code example

        return openTripMapService.getNearbyAttractions(latitude, longitude, radius); // Placeholder, replace with actual logic
    }
}