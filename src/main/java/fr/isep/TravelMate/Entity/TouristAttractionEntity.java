package fr.isep.TravelMate.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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
    private int score;

    private double lon;
    private double lat;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @ManyToMany
    private Set<AttractionKindEntity> kinds;

    public TouristAttractionEntity(String name, double lon, double lat, int score, CityEntity city, Set<AttractionKindEntity> kinds) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.score = score;
        this.city = city;
        this.kinds = kinds;
    }

    public TouristAttractionEntity() {

    }
}
