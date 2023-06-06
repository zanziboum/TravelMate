package fr.isep.TravelMate.repository;

import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionsRepository extends JpaRepository<TouristAttractionEntity,Long> {
    Optional<TouristAttractionEntity> findByName(String name);
    List<TouristAttractionEntity> findByKindsName(String kindName);
    @Query("SELECT a.name FROM TouristAttractionEntity a")
    List<String> getAllAttractionNames();
    @Query("SELECT a.name FROM CityEntity a")
    List<String> getCityNames();
}
