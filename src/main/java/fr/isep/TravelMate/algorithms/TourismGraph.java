package fr.isep.TravelMate.algorithms;

import java.util.*;

public class TourismGraph {

    private Map<String, List<Edge>> graph;

    public TourismGraph() {
        this.graph = new HashMap<>();
    }

    public void addAttraction(String attractionName) {
        graph.put(attractionName, new ArrayList<>());
    }

    public void addEdge(String sourceAttraction, String destinationAttraction, double distance) {
        List<Edge> edges = graph.getOrDefault(sourceAttraction, new ArrayList<>());
        edges.add(new Edge(destinationAttraction, distance));
        graph.put(sourceAttraction, edges);
    }

    public List<String> findShortestPath(String sourceAttraction, String destinationAttraction) {
        // Create a priority queue to store the vertices and their distances
        PriorityQueue<Vertex> queue = new PriorityQueue<>();

        // Create a map to store the distances of vertices from the source
        Map<String, Double> distances = new HashMap<>();

        // Create a map to store the previous vertex in the shortest path
        Map<String, String> previous = new HashMap<>();

        // Initialize all distances to infinity except the source vertex
        for (String attraction : graph.keySet()) {
            if (attraction.equals(sourceAttraction)) {
                distances.put(attraction, 0.0);
            } else {
                distances.put(attraction, Double.POSITIVE_INFINITY);
            }
        }

        // Add the source vertex to the priority queue
        queue.offer(new Vertex(sourceAttraction, 0.0));

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            String currentAttraction = current.getAttraction();
            double currentDistance = current.getDistance();

            // If the current distance is greater than the stored distance, skip it
            if (currentDistance > distances.get(currentAttraction)) {
                continue;
            }

            // Explore the neighboring attractions
            for (Edge edge : graph.get(currentAttraction)) {
                String neighborAttraction = edge.getDestination();
                double distanceToNeighbor = edge.getDistance();
                double totalDistance = currentDistance + distanceToNeighbor;

                // If a shorter path is found, update the distance and previous vertex
                if (totalDistance < distances.get(neighborAttraction)) {
                    distances.put(neighborAttraction, totalDistance);
                    previous.put(neighborAttraction, currentAttraction);
                    queue.offer(new Vertex(neighborAttraction, totalDistance));
                }
            }
        }

        // Reconstruct the shortest path from source to destination
        List<String> shortestPath = new ArrayList<>();
        String currentAttraction = destinationAttraction;
        while (currentAttraction != null) {
            shortestPath.add(0, currentAttraction);
            currentAttraction = previous.get(currentAttraction);
        }

        return shortestPath;
    }

    private static class Edge {
        private String destination;
        private double distance;

        public Edge(String destination, double distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public String getDestination() {
            return destination;
        }

        public double getDistance() {
            return distance;
        }
    }

    private static class Vertex implements Comparable<Vertex> {
        private String attraction;
        private double distance;

        public Vertex(String attraction, double distance) {
            this.attraction = attraction;
            this.distance = distance;
        }

        public String getAttraction() {
            return attraction;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Vertex other) {
            return Double.compare(distance, other.distance);
        }
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // The radius of the Earth in kilometers
        final double R = 6371;

        // Convert latitude and longitude to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences in latitude and longitude
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Calculate the Haversine distance
        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }

    public static void main(String[] args) {
        // Create a new instance of TourismGraph
        TourismGraph graph = new TourismGraph();

        // Add attractions to the graph
        graph.addAttraction("Eiffel Tower");
        graph.addAttraction("Louvre Museum");
        graph.addAttraction("Notre-Dame Cathedral");
        graph.addAttraction("Arc de Triomphe");
        graph.addAttraction("Montmartre");
        graph.addAttraction("Palace of Versailles");

        // Add edges between attractions
        graph.addEdge("Eiffel Tower", "Louvre Museum", calculateDistance(48.8566, 2.3522, 48.8606, 2.3376));
        graph.addEdge("Eiffel Tower", "Arc de Triomphe", calculateDistance(48.8566, 2.3522, 48.8738, 2.295));
        graph.addEdge("Eiffel Tower", "Montmartre", calculateDistance(48.8566, 2.3522, 48.8867, 2.3431));
        graph.addEdge("Louvre Museum", "Notre-Dame Cathedral", calculateDistance(48.8606, 2.3376, 48.8529, 2.3499));
        graph.addEdge("Arc de Triomphe", "Louvre Museum", calculateDistance(48.8738, 2.295, 48.8606, 2.3376));
        graph.addEdge("Montmartre", "Palace of Versailles", calculateDistance(48.8867, 2.3431, 48.8048, 2.1204));
        graph.addEdge("Palace of Versailles", "Eiffel Tower", calculateDistance(48.8048, 2.1204, 48.8566, 2.3522));

        // Find the shortest path between two attractions
        List<String> shortestPath = graph.findShortestPath("Palace of Versailles", "Arc de Triomphe");

        // Print the shortest path
        if (shortestPath.isEmpty()) {
            System.out.println("No path found between the attractions.");
        } else {
            System.out.println("Shortest path:");
            for (String attraction : shortestPath) {
                System.out.println(attraction);
            }
        }
    }
}
