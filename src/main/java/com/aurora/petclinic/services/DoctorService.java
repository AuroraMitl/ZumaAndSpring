package com.aurora.petclinic.services;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();
    void saveDoctor(Doctor doctor);
    Doctor findById(int id);
    void deleteById(int id);
}
