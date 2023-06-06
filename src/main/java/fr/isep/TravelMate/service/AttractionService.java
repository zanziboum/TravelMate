package fr.isep.TravelMate.service;

import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import fr.isep.TravelMate.repository.AttractionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionsRepository attractionsRepository;

    public List<TouristAttractionEntity> getAttractionsFromCity(String cityName){
        return attractionsRepository.findByCityName(cityName);
    }
    public List<String> getAttractionsNamesFromCity(String cityName){
        List<String> nameList = new ArrayList<>();
        String name;
        for(int i = 0;i<getAttractionsFromCity(cityName).size();i++){
            name = attractionsRepository.findByCityName(cityName).stream().map(TouristAttractionEntity :: getName).toList().get(i);
            nameList.add(name);
        }
        return nameList;
    }

    public List<double[]> getAttractionsCoordinatesFromCity(String cityName){
        List<double[]> coordinatesList = new ArrayList<>();
        double[] coordinates = new double[2];
        for (int i = 0;i<getAttractionsFromCity(cityName).size();i++) {
            coordinates [0] = attractionsRepository.findByCityName(cityName).stream().map(TouristAttractionEntity :: getLon).toList().get(i);
            coordinates [1] = attractionsRepository.findByCityName(cityName).stream().map(TouristAttractionEntity :: getLat).toList().get(i);
            coordinatesList.add(coordinates);
        }
        return coordinatesList;
    }

    public List<TouristAttractionEntity> getAttractionsFromKind(List<String> kindName){
        return attractionsRepository.findByKindsNameIn(kindName);
    }

    public List<TouristAttractionEntity> getAttractionFromCityAndKinds(String cityName, List<String> kinds){
        return attractionsRepository.findByCityNameAndKindsNameIn(cityName,kinds);
    }

    public List<String> getAttractionNameFromCityAndKinds(String cityName, List<String> kinds){
        return this.getAttractionFromCityAndKinds(cityName,kinds)
                .stream()
                .map(TouristAttractionEntity::getName)
                .toList();
    }

    public List<TouristAttractionEntity> getAllAttraction(){
        return attractionsRepository.findAll();
    }



}
