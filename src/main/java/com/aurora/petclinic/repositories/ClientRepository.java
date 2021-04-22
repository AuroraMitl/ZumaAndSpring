package com.aurora.petclinic.repositories;

import com.aurora.petclinic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    List<Client> findAllByName(String name);
}
