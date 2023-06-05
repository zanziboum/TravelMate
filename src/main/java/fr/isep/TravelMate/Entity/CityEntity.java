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


    @OneToMany(mappedBy = "city")
    private List<TouristAttractionEntity> attractions;
}
