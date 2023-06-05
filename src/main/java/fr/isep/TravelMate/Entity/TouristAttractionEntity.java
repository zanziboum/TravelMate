package fr.isep.TravelMate.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "attractions")
public class TouristAttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String period;
    private String imgUrl;

    private double lon;
    private double lat;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    public TouristAttractionEntity(String name, double lon, double lat, CityEntity city) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.city = city;
    }

    public TouristAttractionEntity() {

    }
}
