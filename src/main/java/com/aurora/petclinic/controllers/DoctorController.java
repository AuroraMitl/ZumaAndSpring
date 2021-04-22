package com.aurora.petclinic.controllers;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Doctor;
import com.aurora.petclinic.services.ClientService;
import com.aurora.petclinic.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {

        this.doctorService = doctorService;
    }


    @GetMapping("")
    public String getAllClients(Model model){
        List<Doctor> doctorsList=doctorService.findAll();
        model.addAttribute("doctorsList", doctorsList);
        return "doctorsListView";
    }



}
