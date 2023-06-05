package fr.isep.TravelMate.service;

import fr.isep.TravelMate.Entity.CityEntity;
import fr.isep.TravelMate.repository.CityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostConstruct
    public void init() {
        List<CityEntity> cityEntities = CityEntity.fromEnum();
        cityRepository.saveAll(cityEntities);
    }
}