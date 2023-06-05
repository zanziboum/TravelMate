package fr.isep.TravelMate.algorithms;

import java.util.*;

public class TourismGraph {
    private List<TourismAttraction> attractions;
    private Map<TourismAttraction, Map<TourismAttraction, Double>> adjacencyMap;

    public TourismGraph() {
        attractions = new ArrayList<>();
        adjacencyMap = new HashMap<>();
    }

    public void addAttraction(TourismAttraction attraction) {
        attractions.add(attraction);
        adjacencyMap.put(attraction, new HashMap<>());
    }

    public void addEdge(TourismAttraction attraction1, TourismAttraction attraction2) {
        double distance = attraction1.calculateDistance(attraction2);
        adjacencyMap.get(attraction1).put(attraction2, distance);
        adjacencyMap.get(attraction2).put(attraction1, distance);
    }

    public List<TourismAttraction> findClosestAttraction(TourismAttraction source) {
        Map<TourismAttraction, Double> distanceMap = new HashMap<>();
        Map<TourismAttraction, TourismAttraction> previousMap = new HashMap<>();

        Set<TourismAttraction> unvisited = new HashSet<>(attractions);

        // Set initial distances to infinity except for the source attraction
        for (TourismAttraction attraction : attractions) {
            distanceMap.put(attraction, Double.POSITIVE_INFINITY);
        }
        distanceMap.put(source, 0.0);

        while (!unvisited.isEmpty()) {
            TourismAttraction current = getClosestAttraction(distanceMap, unvisited);
            unvisited.remove(current);

            for (TourismAttraction neighbor : adjacencyMap.get(current).keySet()) {
                double distance = distanceMap.get(current) + adjacencyMap.get(current).get(neighbor);
                if (distance < distanceMap.get(neighbor)) {
                    distanceMap.put(neighbor, distance);
                    previousMap.put(neighbor, current);
                }
            }
        }

        List<TourismAttraction> path = new ArrayList<>();
        TourismAttraction attraction = source;

        while (previousMap.containsKey(attraction)) {
            path.add(attraction);
            attraction = previousMap.get(attraction);
        }

        Collections.reverse(path);
        return path;
    }

    private TourismAttraction getClosestAttraction(Map<TourismAttraction, Double> distanceMap, Set<TourismAttraction> unvisited) {
        TourismAttraction closestAttraction = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (TourismAttraction attraction : unvisited) {
            double distance = distanceMap.get(attraction);
            if (distance < minDistance) {
                closestAttraction = attraction;
                minDistance = distance;
            }
        }

        return closestAttraction;
    }
}
