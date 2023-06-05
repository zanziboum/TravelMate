package fr.isep.TravelMate.service;

import fr.isep.TravelMate.Entity.CityEntity;
import fr.isep.TravelMate.model.City;
import fr.isep.TravelMate.repository.CityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostConstruct
    public void init() {
        if (cityRepository.count() == 0){
            List<CityEntity> cityEntities = fromEnum();
            cityRepository.saveAll(cityEntities);
        }
    }
    public static List<CityEntity> fromEnum() {
        List<CityEntity> cityEntities = new ArrayList<>();
        for (City city : City.values()) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(city.name());
            cityEntity.setLon(city.getLongitude());
            cityEntity.setLat(city.getLatitude());
            cityEntity.setDepartment(city.getZipCode());
            cityEntities.add(cityEntity);
        }
        return cityEntities;
    }
}