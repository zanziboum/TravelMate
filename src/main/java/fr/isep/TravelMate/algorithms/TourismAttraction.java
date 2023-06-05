package fr.isep.TravelMate.algorithms;

public class TourismAttraction {
    private String name;
    private double latitude;
    private double longitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

        public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public TourismAttraction(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
