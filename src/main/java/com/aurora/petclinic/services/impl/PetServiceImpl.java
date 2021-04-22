package com.aurora.petclinic.services.impl;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Pet;
import com.aurora.petclinic.repositories.ClientRepository;
import com.aurora.petclinic.repositories.PetRepository;
import com.aurora.petclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    PetRepository petRepository;


    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAll() {
      return petRepository.findAll();
    }

    @Override
    public void savePet(Pet pet) {
       petRepository.save(pet);
    }

    @Override
    public Pet findById(int id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        petRepository.deleteById(id);

    }

}
