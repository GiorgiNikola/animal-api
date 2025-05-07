package com.animalapi.exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(int id) {
        super("Animal with id " + id + " not found");
    }
}
