package fr.isep.TravelMate.service;

import fr.isep.TravelMate.Entity.AttractionKindEntity;
import fr.isep.TravelMate.repository.AttractionKindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KindService {

    private final AttractionKindRepository attractionKindRepository;

    public List<AttractionKindEntity> getKindEntity(){
        return attractionKindRepository.findAll();
    }

    public List<String> getKindEntityName(){
        return this.getKindEntity().stream().map(AttractionKindEntity::getName).collect(Collectors.toList());
    }
}
