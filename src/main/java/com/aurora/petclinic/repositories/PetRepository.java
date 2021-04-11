package com.aurora.petclinic.repositories;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Integer> {
}
