package com.aurora.petclinic.services.impl;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.repositories.ClientRepository;
import com.aurora.petclinic.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public Client findById(int id){
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}
