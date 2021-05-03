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

    @GetMapping("/addDoctor")
    public String showAddDoctorForm(Model model){
        model.addAttribute("doctor",new Doctor());
        return "addDoctorView";
    }

    @PostMapping("/addDoctor")
    public String saveDoctor(@ModelAttribute("doctor") @Valid Doctor doctor){
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/showFormForDoctorUpdate")
    public String getForPostEditClient(@RequestParam("id") @Valid int id, Model model){
        model.addAttribute("doctor",doctorService.findById(id));
        return "editDoctorView";
    }


    @PostMapping("/showFormForDoctorUpdate")
    public String showFormForEditClient(@RequestParam("id") @Valid int id, Model model) {
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor", doctor);
        return "editDoctorView";
    }



    @PostMapping("/deleteDoctor")
    public String deleteDoctor(@RequestParam("id") @Valid int id) {
       doctorService.deleteById(id);
        return "redirect:/doctors";
    }

}
