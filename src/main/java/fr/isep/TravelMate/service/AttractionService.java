package fr.isep.TravelMate.service;

import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import fr.isep.TravelMate.repository.AttractionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionsRepository attractionsRepository;

    public List<TouristAttractionEntity> getAttractionsFromCity(String cityName){
        return attractionsRepository.findByCityName(cityName);
    }

    public List<TouristAttractionEntity> getAttractionsFromKind(String kindName){
        return attractionsRepository.findByKindsName(kindName);
    }


}
