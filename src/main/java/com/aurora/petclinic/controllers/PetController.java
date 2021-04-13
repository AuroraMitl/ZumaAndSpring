package com.aurora.petclinic.controllers;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Pet;
import com.aurora.petclinic.services.ClientService;
import com.aurora.petclinic.services.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/pets")
public class PetController {

   PetService petService;
   ClientService clientService;

    public PetController(PetService petService, ClientService clientService) {

        this.petService = petService;
        this.clientService = clientService;
    }

//    @GetMapping("")
//    public String getAllClients(Model model){
//        List<Client> clientsList=clientService.findAll();
//        model.addAttribute("clientsList", clientsList);
//        return "clientsListView";
//    }

    @GetMapping("/addPet")
    public String showAddPetForm(@RequestParam("clientId") @Valid int clientId, Model model){
        model.addAttribute("pet",new Pet());
        model.addAttribute("client", clientService.findById(clientId));
        return "editPetView";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("pet") @Valid Pet pet, @RequestParam("clientId") @Valid int clientId, Model model){
        pet.setClient(clientService.findById(clientId));
        petService.savePet(pet);
        //Client client = clientService.findById(pet.getClient().getId());
        model.addAttribute("client", clientService.findById(clientId));
        return "editClientView";
    }

    @PostMapping("/showFormForPetUpdate")
    public String showFormForEditPet(@RequestParam("id") @Valid int id, Model model) {
        Pet pet = petService.findById(id);
        model.addAttribute("pet", pet);
        return "editPetView";
    }

    @PostMapping("/deletePet")
    public String deletePet(@RequestParam("id") @Valid int id) {
      petService.deleteById(id);
        return "redirect:/clients";
    }

}
