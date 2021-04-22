package com.aurora.petclinic.repositories;

import com.aurora.petclinic.model.Client;
import com.aurora.petclinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
