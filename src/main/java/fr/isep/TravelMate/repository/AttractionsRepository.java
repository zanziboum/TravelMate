package fr.isep.TravelMate.repository;

import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionsRepository extends JpaRepository<TouristAttractionEntity,Long> {
}
