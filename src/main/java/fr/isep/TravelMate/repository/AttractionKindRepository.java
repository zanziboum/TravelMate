package fr.isep.TravelMate.repository;

import fr.isep.TravelMate.Entity.AttractionKindEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttractionKindRepository extends JpaRepository<AttractionKindEntity,Long> {
    Optional<AttractionKindEntity> findByName (String name);
}
