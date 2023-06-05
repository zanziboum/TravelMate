package fr.isep.TravelMate.repository;

import fr.isep.TravelMate.Entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
   Optional<CityEntity> findByName(String zip);
}
