package com.aurora.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pets")
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    @Column(name="pet_id")
    private int id;

    @NotNull
    private String name;

    public Pet(@NotNull String name) {
        this.name = name;
    }


}
