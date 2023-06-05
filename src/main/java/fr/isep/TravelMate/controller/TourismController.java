package fr.isep.TravelMate.controller;

import fr.isep.TravelMate.algorithms.TourismAttraction;
import fr.isep.TravelMate.service.TourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tourism")
public class TourismController {
    private final TourismService tourismService;

    @Autowired
    public TourismController(TourismService tourismService) {
        this.tourismService = tourismService;
    }

    @GetMapping("/attractions")
    public List<TourismAttraction> getTourismAttractions() {
        // Replace with your implementation to fetch the list of attractions
        return null;
    }

    @GetMapping("/closest-attractions")
    public List<TourismAttraction> getClosestAttractions() {
        double sourceLatitude = 40.7128;
        double sourceLongitude = -74.0060;

        TourismAttraction sourceAttraction = new TourismAttraction();
        sourceAttraction.setLatitude(sourceLatitude);
        sourceAttraction.setLongitude(sourceLongitude);

        return tourismService.findClosestAttraction(sourceAttraction);
    }
}
