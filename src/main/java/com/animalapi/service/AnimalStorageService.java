package com.animalapi.service;

import com.animalapi.data.Constants;
import com.animalapi.model.Animal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalStorageService {
    private final ObjectMapper mapper = new ObjectMapper();
    @Getter
    private List<Animal> animals = new ArrayList<>();

    @PostConstruct
    public void init() {
        readAnimals();
    }

    public void readAnimals() {
        try{
            animals = mapper.readValue(
                    new File(Constants.animalsJSONPath),
                    new TypeReference<>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeAnimals() {
        try{
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(
                    new File(Constants.animalsJSONPath),
                    animals
            );
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @PreDestroy
    public void destroy() {
        writeAnimals();
    }
}
