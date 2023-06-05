package fr.isep.TravelMate.Entity;

import jakarta.persistence.*;
import lombok.Data;

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

}
