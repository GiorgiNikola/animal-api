package com.animalapi.service;

import com.animalapi.exception.AnimalNotFoundException;
import com.animalapi.exception.GlobalExceptionHandler;
import com.animalapi.model.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimalService {

    AnimalStorageService animalStorageService;
    GlobalExceptionHandler globalExceptionHandler;

    public AnimalService(AnimalStorageService animalStorageService, GlobalExceptionHandler exceptionHandler) {
        this.animalStorageService = animalStorageService;
        this.globalExceptionHandler = exceptionHandler;
    }

    public Animal addAnimal(Animal animal) {
        Animal checkAnimal = getAnimalByID(animal.getId());
        if (checkAnimal != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Animal with ID " + animal.getId() + " already exists."
            );
        }
        animalStorageService.addAnimal(animal);
        animalStorageService.writeAnimals();
        return animal;
    }

    public List<Animal> getAnimals() {
        return animalStorageService.getAnimals();
    }

    public Animal getAnimal(int id) {
        Animal animal = getAnimalByID(id);
        if (animal == null) {
            throw new AnimalNotFoundException(id);
        }
        return animal;
    }

    public Animal getAnimalByID(int id) {
        return animalStorageService.getAnimals()
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Animal updateAnimal(Animal animal) {
        Animal animalToUpdate = getAnimalByID(animal.getId());
        if (animalToUpdate != null) {
            animalToUpdate.setName(animal.getName());
            animalToUpdate.setAge(animal.getAge());
            animalToUpdate.setFavoriteFoods(animal.getFavoriteFoods());
        } else {
            throw new AnimalNotFoundException(animal.getId());
        }
        return animalToUpdate;
    }

    public void deleteAnimal(int id) {
        Animal animalToDelete = getAnimalByID(id);
        if (animalToDelete != null) {
            animalStorageService.removeAnimal(animalToDelete);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
}
