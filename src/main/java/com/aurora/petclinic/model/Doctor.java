package com.aurora.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    @Column(name="doctor_id")
    private int doctorId;

    @NotNull
    @Column(name="name")
    private String doctorName;

    @ManyToMany(mappedBy = "doctorsList")
    private List<Client> clientsList = new ArrayList<>();

    public Doctor(@NotNull String doctorName) {
        this.doctorName = doctorName;
    }


}
