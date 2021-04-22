package com.aurora.petclinic.services;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAll();
    void savePet(Pet pet);
    Pet findById(int id);
    void deleteById(int id);
}
