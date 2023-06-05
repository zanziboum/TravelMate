package fr.isep.TravelMate.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "kind")
public class AttractionKindEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
