package com.aurora.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    @Column(name="pet_id")
    private int id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    public Pet(@NotNull String name) {
        this.name = name;
    }


}
