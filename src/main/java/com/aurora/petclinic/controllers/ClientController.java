package com.aurora.petclinic.controllers;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String saveClient(@ModelAttribute("client") @Valid Client client){
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/showFormForClientUpdate")
    public String getForPostEditClient(@RequestParam("id") @Valid int id, Model model){
        model.addAttribute("client",clientService.findById(id));
        return "editClientView";
    }


    @PostMapping("/showFormForClientUpdate")//спр Димать
    public String showFormForEditClient(@RequestParam("id") @Valid int id, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        return "editClientView";
    }

    @PostMapping("/deleteClient")
    public String deleteClient(@RequestParam("id") @Valid int id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
