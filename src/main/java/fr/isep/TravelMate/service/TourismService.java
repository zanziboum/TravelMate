package fr.isep.TravelMate.service;

import fr.isep.TravelMate.algorithms.TourismAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourismService {
    private final OpenTripMapService openTripMapService;

    @Autowired
    public TourismService(OpenTripMapService openTripMapService) {
        this.openTripMapService = openTripMapService;
    }

    public List<TourismAttraction> findClosestAttraction(TourismAttraction source) {
        // Call the OpenTripMapService to fetch nearby attractions
        double latitude = source.getLatitude();
        double longitude = source.getLongitude();
        int radius = 5000; // Set the desired radius for nearby attractions

        openTripMapService.getNearbyAttractions(latitude, longitude, radius);

        // Rest of the code to find the closest attractions
        // You can use the fetched nearby attractions to create the graph and perform Dijkstra's algorithm
        // as shown in the previous code example

        return null; // Placeholder, replace with actual logic
    }
}
