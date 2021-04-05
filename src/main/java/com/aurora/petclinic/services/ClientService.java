package com.aurora.petclinic.services;

import com.aurora.petclinic.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    void saveClient(Client client);
}
