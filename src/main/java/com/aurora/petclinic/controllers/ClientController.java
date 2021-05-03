package com.aurora.petclinic.controllers;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Doctor;
import com.aurora.petclinic.model.Pet;
import com.aurora.petclinic.services.ClientService;
import com.aurora.petclinic.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    ClientService clientService;
    DoctorService doctorService;

    public ClientController(ClientService clientService, DoctorService doctorService) {
        this.clientService = clientService;
        this.doctorService = doctorService;
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

    @GetMapping("/addClientForDoctor")
    public String showAddClientForm(@RequestParam("doctorId") @Valid int doctorId, Model model){
        model.addAttribute("client",new Client());
        model.addAttribute("doctorId",doctorId);
        return "addClientView";
    }

    @GetMapping("/searchClient")
    public String searchClient(@RequestParam("name") @Valid String name, Model model){
        List<Client> clientsList=clientService.findClientsByName(name);
        model.addAttribute("clientsList", clientsList);
        return "clientsListView";
    }

    @GetMapping("/showFormForClientUpdate")
    public String getForPostEditClient(@RequestParam("id") @Valid int id, Model model){
        model.addAttribute("client",clientService.findById(id));
        return "editClientView";
    }


    @PostMapping("/showFormForClientUpdate")
    public String showFormForEditClient(@RequestParam("id") @Valid int id, @RequestParam("doctorId") @Valid int doctorId, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("doctorId", doctorId);
        return "editClientView";
    }

    @PostMapping("/deleteClient")
    public String deleteClient(@RequestParam("id") @Valid int id, @RequestParam("doctorId") @Valid int doctorId, Model model) {
        clientService.deleteById(id);
        if (doctorId == -1) {
            return "redirect:/clients/";
        } else {
            Doctor doctor = doctorService.findById(doctorId);
            model.addAttribute("doctor", doctor);
            return "editDoctorView";
        }
    }

    @PostMapping("/addClient")
    public String saveClient(@ModelAttribute("client") @Valid Client client, @RequestParam("doctorId") @Valid int doctorId, Model model){
        Doctor doctor= doctorService.findById(doctorId);
        List<Doctor> doctorsList=client.getDoctorsList();
        doctorsList.add(doctor);
        client.setDoctorsList(doctorsList);
        clientService.saveClient(client);

        model.addAttribute("client",client);

        return "redirect:/doctors/showFormForDoctorUpdate?id="+doctor.getDoctorId();
    }

}
