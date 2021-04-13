package com.aurora.petclinic.controllers;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Pet;
import com.aurora.petclinic.services.ClientService;
import com.aurora.petclinic.services.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        model.addAttribute("pet", new Pet());
        model.addAttribute("clientId", clientId);
        return "editPetView";
    }

/*    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("pet") @Valid Pet pet, @RequestParam("clientId") @Valid int clientId, Model model){
        Client client = clientService.findById(clientId);
        pet.setClient(client);
        petService.savePet(pet);
        pet = null;
        model.addAttribute("client",client);
        return "editClientView";
    }*/

    @PostMapping("/addPet")
    public ModelAndView savePet(@ModelAttribute("pet") @Valid Pet pet, @RequestParam("clientId") @Valid int clientId, ModelMap model){
        Client client = clientService.findById(clientId);
        pet.setClient(client);
        petService.savePet(pet);
        model.addAttribute("id", client.getId());
        return new ModelAndView("redirect:/clients/showFormForClientUpdate", model);
    }

    @PostMapping("/showFormForPetUpdate")
    public String showFormForEditPet(@RequestParam("id") @Valid int id, Model model) {
        Pet pet = petService.findById(id);
        model.addAttribute("pet", pet);
        model.addAttribute("clientId", pet.getClient().getId());
        return "editPetView";
    }

    @PostMapping("/deletePet")
    public String deletePet(@RequestParam("id") @Valid int id) {
      petService.deleteById(id);
        return "redirect:/clients";
    }

}
