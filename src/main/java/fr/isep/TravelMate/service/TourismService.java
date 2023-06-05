package fr.isep.TravelMate.service;

import fr.isep.TravelMate.algorithms.TourismAttraction;
import fr.isep.TravelMate.algorithms.TourismGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourismService {
    private TourismGraph tourismGraph;

    public TourismService() {
        tourismGraph = new TourismGraph();
        initializeGraph();
    }

    private void initializeGraph() {
        // Add attractions to the graph
        TourismAttraction attraction1 = new TourismAttraction("Attraction 1", 40.7128, -74.0060);
        TourismAttraction attraction2 = new TourismAttraction("Attraction 2", 40.7129, -74.0059);
        TourismAttraction attraction3 = new TourismAttraction("Attraction 3", 40.7127, -74.0061);
        tourismGraph.addAttraction(attraction1);
        tourismGraph.addAttraction(attraction2);
        tourismGraph.addAttraction(attraction3);

        // Add edges between attractions
        tourismGraph.addEdge(attraction1, attraction2);
        tourismGraph.addEdge(attraction2, attraction3);
    }

    public List<TourismAttraction> findClosestAttraction(TourismAttraction source) {
        return tourismGraph.findClosestAttraction(source);
    }
}
