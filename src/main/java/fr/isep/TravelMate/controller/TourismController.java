package fr.isep.TravelMate.controller;

import com.fasterxml.jackson.databind.JsonNode;
import fr.isep.TravelMate.Entity.TouristAttractionEntity;
import fr.isep.TravelMate.model.City;
import fr.isep.TravelMate.repository.AttractionsRepository;
import fr.isep.TravelMate.service.AttractionService;
import fr.isep.TravelMate.service.KindService;
import fr.isep.TravelMate.service.TourismService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tourism")
public class TourismController {
    private final TourismService tourismService;
    private final AttractionService attractionService;
    private final KindService kindService;
    private final AttractionsRepository attractionsRepository;

    @PostMapping("/closest-attractions")
    public JsonNode getClosestAttractions(@RequestBody String cityName) {
        City city = City.valueOf(cityName);
        //TourismAttraction sourceAttraction = new TourismAttraction(city.getLatitude(), city.getLongitude());
        //sourceAttraction.setLatitude(city.getLatitude());
        //sourceAttraction.setLongitude(city.getLongitude());
        Optional<JsonNode> dest = tourismService.findClosestAttraction(city);
        return dest.orElse(null);
    }
    @GetMapping("/NameAttractions")
    public List<String> getAttractionNames() {
        return attractionsRepository.getAllAttractionNames();
    }
    @GetMapping("/NameCity")
    public List<String> getCityNames() {
        return attractionsRepository.getCityNames();
    }

    @GetMapping("/fromCity")
    public List<String> getAttractionByCity(@RequestParam  String city){
        return attractionService.getAttractionsFromCity(city).stream()
                .map(TouristAttractionEntity::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/fromKind")
    public List<TouristAttractionEntity> getAttractionsByKind(@RequestParam List<String> kinds) {
        return attractionService.getAttractionsFromKind(kinds);
    }

    @GetMapping("/kindName")
    public List<String> getKindName(){
        return kindService.getKindEntityName();
    }

    @GetMapping("/fromCityAndKinds")
    public List<TouristAttractionEntity> getAttractionByCityAndKind(
            @RequestParam("city") String city,
            @RequestParam("kinds") List<String> kinds){
        return attractionService.getAttractionFromCityAndKinds(city,kinds);
    }

}
