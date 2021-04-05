package com.aurora.petclinic.controllers;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("")
    public String getAllClients(Model model){
        List<Client> clientsList=clientService.findAll();
        model.addAttribute("clientsList", clientsList);
    return "clientsListView";
    }

    @GetMapping("/addClient")
    public String showAddClientForm(Model model){
        model.addAttribute("client",new Client());
        return "addClientView";
    }

    @PostMapping("/addClient")
    public String saveClient(@RequestParam("client") @Valid Client client){
        clientService.saveClient(client);
        return "redirect:/clients";
    }


}
