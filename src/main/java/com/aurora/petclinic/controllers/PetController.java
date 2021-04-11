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

    public PetController(PetService petService) {

        this.petService = petService;
    }

//    @GetMapping("")
//    public String getAllClients(Model model){
//        List<Client> clientsList=clientService.findAll();
//        model.addAttribute("clientsList", clientsList);
//        return "clientsListView";
//    }

    @GetMapping("/addPet")
    public String showAddPetForm(Model model){
        model.addAttribute("pet",new Pet());
        return "editPetView";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("pet") @Valid Pet pet){
       petService.savePet(pet);
        return "redirect:/clients";
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
