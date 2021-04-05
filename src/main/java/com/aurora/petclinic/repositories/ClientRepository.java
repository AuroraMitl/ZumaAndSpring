package com.aurora.petclinic.repositories;

import com.aurora.petclinic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {

}
