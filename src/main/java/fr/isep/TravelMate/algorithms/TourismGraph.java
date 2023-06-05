package fr.isep.TravelMate.algorithms;

import java.util.*;

public class TourismGraph {
    private Map<TourismAttraction, List<GraphEdge>> graph;

    public TourismGraph() {
        this.graph = new HashMap<>();
    }

    public void addAttraction(TourismAttraction attraction) {
        graph.put(attraction, new ArrayList<>());
    }

    public void addEdge(TourismAttraction source, TourismAttraction destination, double distance) {
        graph.get(source).add(new GraphEdge(destination, distance));
        graph.get(destination).add(new GraphEdge(source, distance));
    }

    public List<TourismAttraction> findClosestAttraction(TourismAttraction source) {
        Map<TourismAttraction, Double> distances = new HashMap<>();
        Map<TourismAttraction, TourismAttraction> previous = new HashMap<>();
        Set<TourismAttraction> visited = new HashSet<>();

        PriorityQueue<AttractionDistance> queue = new PriorityQueue<>(Comparator.comparingDouble(AttractionDistance::getDistance));
        queue.add(new AttractionDistance(source, 0.0));

        distances.put(source, 0.0);

        while (!queue.isEmpty()) {
            AttractionDistance current = queue.poll();
            TourismAttraction currentAttraction = current.getAttraction();

            if (visited.contains(currentAttraction)) {
                continue;
            }

            visited.add(currentAttraction);

            for (GraphEdge edge : graph.get(currentAttraction)) {
                TourismAttraction neighbor = edge.getDestination();
                double distance = edge.getDistance();
                double totalDistance = distances.get(currentAttraction) + distance;

                if (!distances.containsKey(neighbor) || totalDistance < distances.get(neighbor)) {
                    distances.put(neighbor, totalDistance);
                    previous.put(neighbor, currentAttraction);
                    queue.add(new AttractionDistance(neighbor, totalDistance));
                }
            }
        }

        List<TourismAttraction> closestAttractions = new ArrayList<>();

        for (TourismAttraction attraction : distances.keySet()) {
            closestAttractions.add(attraction);
        }

        return closestAttractions;
    }

    private static class GraphEdge {
        private final TourismAttraction destination;
        private final double distance;

        public GraphEdge(TourismAttraction destination, double distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public TourismAttraction getDestination() {
            return destination;
        }

        public double getDistance() {
            return distance;
        }
    }

    private static class AttractionDistance {
        private final TourismAttraction attraction;
        private final double distance;

        public AttractionDistance(TourismAttraction attraction, double distance) {
            this.attraction = attraction;
            this.distance = distance;
        }

        public TourismAttraction getAttraction() {
            return attraction;
        }

        public double getDistance() {
            return distance;
        }
    }
}
