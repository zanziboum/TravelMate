package fr.isep.TravelMate.model;

public enum City {
    PARIS(48.8566, 2.3522, "75000"),
    BOULOGNE_BILLANCOURT(48.8397, 2.2399, "92100"),
    MONTREUIL(48.8635, 2.4484, "93100"),
    SAINT_DENIS(48.9362, 2.3574, "93200"),
    ARGENTEUIL(48.9476, 2.2469, "95100"),
    NANTERRE(48.8927, 2.2179, "92000"),
    VERSAILLES(48.8014, 2.1301, "78000"),
    CRETEIL(48.7804, 2.4614, "94000"),
    COLOMBES(48.9226, 2.2546, "92700"),
    ASNIERES_SUR_SEINE(48.9088, 2.285, "92600"),
    AULNAY_SOUS_BOIS(48.9333, 2.486, "93600"),
    RUEIL_MALMAISON(48.8826, 2.1769, "92500"),
    CHAMPIGNY_SUR_MARNE(48.8156, 2.5107, "94500"),
    SAINT_MAUR_DES_FOSSES(48.7988, 2.4919, "94100"),
    COURBEVOIE(48.8984, 2.2558, "92400"),
    AUBERVILLIERS(48.9161, 2.3844, "93300"),
    DRANCY(48.922, 2.4505, "93700"),
    FONTENAY_SOUS_BOIS(48.8494, 2.4747, "94120"),
    ISSY_LES_MOLINEAUX(48.8249, 2.2732, "92130"),
    LEVALLOIS_PERRET(48.8931, 2.2884, "92300");

    private final double latitude;
    private final double longitude;
    private final String zipCode;

    City(double latitude, double longitude, String zipCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getZipCode() {
        return zipCode;
    }
}
