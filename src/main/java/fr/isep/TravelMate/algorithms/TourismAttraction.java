package fr.isep.TravelMate.algorithms;

public class TourismAttraction {
    private String name;
    private double latitude;
    private double longitude;

    public TourismAttraction(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    // Calculate the distance between two attractions using the Haversine formula
    public double calculateDistance(TourismAttraction other) {
        double earthRadius = 6371; // in kilometers

        double lat1Rad = Math.toRadians(getLatitude());
        double lat2Rad = Math.toRadians(other.latitude);
        double deltaLat = Math.toRadians(other.latitude - getLatitude());
        double deltaLong = Math.toRadians(other.longitude - getLongitude());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLong / 2) * Math.sin(deltaLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
