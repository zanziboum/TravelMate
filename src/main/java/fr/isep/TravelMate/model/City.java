package fr.isep.TravelMate.model;

public enum City {
    PARIS(48.8566, 2.3522),
    BOULOGNE_BILLANCOURT(48.8397, 2.2399),
    MONTREUIL(48.8635, 2.4484),
    SAINT_DENIS(48.9362, 2.3574),
    ARGENTEUIL(48.9476, 2.2469),
    NANTERRE(48.8927, 2.2179),
    VERSAILLES(48.8014, 2.1301),
    CRETEIL(48.7804, 2.4614),
    COLOMBES(48.9226, 2.2546),
    ASNIERES_SUR_SEINE(48.9088, 2.285),
    AULNAY_SOUS_BOIS(48.9333, 2.486),
    RUEIL_MALMAISON(48.8826, 2.1769),
    CHAMPIGNY_SUR_MARNE(48.8156, 2.5107),
    SAINT_MAUR_DES_FOSSES(48.7988, 2.4919),
    COURBEVOIE(48.8984, 2.2558),
    AUBERVILLIERS(48.9161, 2.3844),
    DRANCY(48.922, 2.4505),
    FONTENAY_SOUS_BOIS(48.8494, 2.4747),
    ISSY_LES_MOLINEAUX(48.8249, 2.2732),
    LEVALLOIS_PERRET(48.8931, 2.2884);

    private final double latitude;
    private final double longitude;

    City(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}