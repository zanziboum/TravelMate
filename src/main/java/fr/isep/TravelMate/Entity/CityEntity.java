package fr.isep.TravelMate.Entity;

import jakarta.persistence.*;
import lombok.Data;

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

}
