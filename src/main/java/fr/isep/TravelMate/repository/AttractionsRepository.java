package fr.isep.TravelMate.repository;

import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttractionsRepository extends JpaRepository<TouristAttractionEntity,Long> {
    Optional<TouristAttractionEntity> findByName(String name);
    //List<TouristAttractionEntity> findByKindsName(String kindName);
}
