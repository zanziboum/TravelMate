package fr.isep.TravelMate.algorithms;

import java.util.*;

public class TourismGraph {

    private Map<String, List<Edge>> graph = new HashMap<>();

    public List<String> findShortestPath(Map<String, List<Edge>> graph, String sourceAttraction, String destinationAttraction) {
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

        // Haversine formula
        double dlon = lon2Rad - lon1Rad;
        double dlat = lat2Rad - lat1Rad;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }

    public void generateAllEdges(Map<String, double[]> attractions, int edgeNumberPerNode) {
        for (String attraction1 : attractions.keySet()) {
            double[] coordinates1 = attractions.get(attraction1);
            List<String> nearbyAttractions = new ArrayList<>(attractions.keySet());
            nearbyAttractions.remove(attraction1);

            int edgesGenerated = 0;
            while (edgesGenerated < edgeNumberPerNode && !nearbyAttractions.isEmpty()) {
                int randomIndex = (int) (Math.random() * nearbyAttractions.size());
                String attraction2 = nearbyAttractions.get(randomIndex);
                double[] coordinates2 = attractions.get(attraction2);
                double distance = calculateDistance(coordinates1[0], coordinates1[1], coordinates2[0], coordinates2[1]);
                Edge edge = new Edge(attraction2, distance);

                if (!graph.containsKey(attraction1)) {
                    graph.put(attraction1, new ArrayList<>());
                }
                graph.get(attraction1).add(edge);

                nearbyAttractions.remove(randomIndex);
                edgesGenerated++;
            }
        }
    }

    public static void main(String[] args) {
        // Create a new instance of TourismGraph
        TourismGraph graph = new TourismGraph();

        // Create a HashMap to store attractions and their coordinates
        Map<String, double[]> attractions = new HashMap<>();
        attractions.put("Eiffel Tower", new double[]{48.8566, 2.3522});
        attractions.put("Louvre Museum", new double[]{48.8606, 2.3376});
        attractions.put("Notre-Dame Cathedral", new double[]{48.8529, 2.3499});
        attractions.put("Arc de Triomphe", new double[]{48.8738, 2.295});
        attractions.put("Montmartre", new double[]{48.8867, 2.3431});
        attractions.put("Palace of Versailles", new double[]{48.8048, 2.1204});

        // Generate edges between attractions
        graph.generateAllEdges(attractions,4);

        // Find the shortest path between two attractions
        String sourceAttraction = "Eiffel Tower";
        String destinationAttraction = "Notre-Dame Cathedral";
        List<String> shortestPath = graph.findShortestPath(graph.graph, sourceAttraction, destinationAttraction);

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
