package com.aurora.petclinic.services.impl;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Doctor;
import com.aurora.petclinic.repositories.ClientRepository;
import com.aurora.petclinic.repositories.DoctorRepository;
import com.aurora.petclinic.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

   DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findById(int id){
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        doctorRepository.deleteById(id);
    }
}



