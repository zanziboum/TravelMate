package fr.isep.TravelMate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.isep.TravelMate.model.City;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "attractions")
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
    @JsonIgnore
    private List<TouristAttractionEntity> attractions;
}
