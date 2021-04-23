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
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    @Column(name="client_id")
    private int id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Pet> petsList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "doctors_clients",
                joinColumns = @JoinColumn(name = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctorsList = new ArrayList<>();

    public Client(@NotNull String name) {
        this.name = name;
    }

}
