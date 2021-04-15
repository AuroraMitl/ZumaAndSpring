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

    ClientService clientService;
    PetService petService;

    public PetController(PetService petService, ClientService clientService) {
        this.clientService=clientService;
        this.petService = petService;
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
        model.addAttribute("clientId",clientId);
        return "editPetView";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("pet") @Valid Pet pet, @RequestParam("clientId") @Valid int clientId, Model model){
       Client client= clientService.findById(clientId);
       pet.setClient(client);
       petService.savePet(pet);

       model.addAttribute("client",client);

        return "redirect:/clients/showFormForClientUpdate?id="+client.getId();
    }

    @PostMapping("/showFormForPetUpdate")
    public String showFormForEditPet(@RequestParam("id") @Valid int id, Model model) {
        Pet pet = petService.findById(id);
        model.addAttribute("pet", pet);
        model.addAttribute("clientId",pet.getClient().getId());
        return "editPetView";
    }

    @PostMapping("/deletePet")
    public String deletePet(@RequestParam("id") @Valid int id, Model model) {
       Pet pet= petService.findById(id);
      Client client=clientService.findById(pet.getClient().getId()); //  получение клиента с новыми данными
      model.addAttribute("client",client);
        petService.deleteById(id);//удаляется петс
        return "editClientView";
    }

}
