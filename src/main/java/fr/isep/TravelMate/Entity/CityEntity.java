package fr.isep.TravelMate.Entity;

import fr.isep.TravelMate.model.City;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;
    private String department;

    private double lon;
    private double lat;

    public static List<CityEntity> fromEnum() {
        List<CityEntity> cityEntities = new ArrayList<>();
        for (City city : City.values()) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(city.name());
            cityEntity.setLon(city.getLongitude());
            cityEntity.setLat(city.getLatitude());
            cityEntities.add(cityEntity);
        }
        return cityEntities;
    }
    @OneToMany(mappedBy = "city")
    private List<TouristAttractionEntity> attractions;
}
