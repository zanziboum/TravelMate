package fr.isep.TravelMate.service;

import fr.isep.TravelMate.Entity.AttractionKindEntity;
import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import fr.isep.TravelMate.repository.AttractionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TourismGraphService {
    private final AttractionService attractionService;
    private final AttractionsRepository attractionsRepository;
    public static TourismGraphService tourismGraphService;
    private Map<String, List<Edge>> graph = new HashMap<>();



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


    public List<Edge> generateAllEdges(String sourceAttraction, Map<String, double[]> attractions, int edgeNumberPerNode) {
        List<Edge> edgeList = new ArrayList<>();

        double[] sourceCoordinates = attractions.get(sourceAttraction);
        List<String> nearbyAttractions = new ArrayList<>(attractions.keySet());
        nearbyAttractions.remove(sourceAttraction);

        int edgesGenerated = 0;
        while (edgesGenerated < edgeNumberPerNode && !nearbyAttractions.isEmpty()) {
            int randomIndex = (int) (Math.random() * nearbyAttractions.size());
            String destinationAttraction = nearbyAttractions.get(randomIndex);
            double[] destinationCoordinates = attractions.get(destinationAttraction);
            double distance = calculateDistance(sourceCoordinates[0], sourceCoordinates[1], destinationCoordinates[0], destinationCoordinates[1]);
            Edge edge = new Edge(destinationAttraction, distance);
            edgeList.add(edge);

            nearbyAttractions.remove(randomIndex);
            edgesGenerated++;
        }

        return edgeList;
    }


    public Map<String, List<Edge>> generateEdgesForAllAttractions(int numberOfEdge) {
        Map<String, List<Edge>> result = new HashMap<>();
        Map<String, double[]> touristAttractions = new HashMap<>();
        List<TouristAttractionEntity> attractions = attractionService.getAllAttraction();

        return getStringListMap(numberOfEdge, result, touristAttractions, attractions);
    }

    public Map<String, List<Edge>> generateEdgesForAllAttractions(int numberOfEdge, List<String> kindsName) {
        Map<String, List<Edge>> result = new HashMap<>();
        Map<String, double[]> touristAttractions = new HashMap<>();
        List<TouristAttractionEntity> attractions = attractionService.getAttractionsFromKind(kindsName);
        return getStringListMap(numberOfEdge, result, touristAttractions, attractions);
    }

    private Map<String, List<Edge>> getStringListMap(int numberOfEdge, Map<String, List<Edge>> result, Map<String, double[]> touristAttractions, List<TouristAttractionEntity> attractions) {
        attractions.forEach(attraction ->{
            String name = attraction.getName();
            double[] coordinates= {
                    attraction.getLon(),
                    attraction.getLat()};
            touristAttractions.put(name,coordinates);
        });

        for (String attraction : touristAttractions.keySet()){
            result.put(attraction, generateAllEdges(attraction, touristAttractions, numberOfEdge));
        }
        return result;
    }

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
        System.out.println(shortestPath);
        return shortestPath;
    }

    public List<TouristAttractionEntity> pathWithNoFilter(String start, String end, int nb){
        return this.findShortestPath(
                        this.generateEdgesForAllAttractions(nb),
                        start,
                        end)
                .stream().map(name->{
                    return attractionsRepository.findByName(name).get();
                }).toList();
    }

    public List<TouristAttractionEntity> pathWithKindFilter(String start, String end, int nb, List<String> kinds){
        return this.findShortestPath(
                        this.generateEdgesForAllAttractions(nb,kinds),
                        start,
                        end)
                .stream().map(name->{
                    return attractionsRepository.findByName(name).get();
                }).toList();
    }

}

