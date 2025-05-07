package com.animalapi.controller;

import com.animalapi.model.Animal;
import com.animalapi.service.AnimalService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {
    AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/api/animals")
    public List<Animal> getAllAnimals() {
        return animalService.getAnimals();
    }

    @GetMapping("/api/animals/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal found",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = com.animalapi.model.ApiResponse.class))),
    })
    public Animal getAnimal(@PathVariable("id") int id) {
        return animalService.getAnimal(id);
    }

    @PostMapping("/api/animals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal posted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "409", description = "Animal already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.animalapi.model.ApiResponse.class))),
    })
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }

    @PutMapping("/api/animals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.animalapi.model.ApiResponse.class))),
    })
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }
    
    @DeleteMapping("/api/animals/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Animal deleted",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = com.animalapi.model.ApiResponse.class))),
    })
    public ResponseEntity<Void> deleteAnimal(@PathVariable("id") int id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
