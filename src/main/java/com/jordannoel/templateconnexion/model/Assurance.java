package com.jordannoel.templateconnexion.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "assurance")
public class Assurance {

    @Id
    @GeneratedValue
    private int id;

    private String dateDebut;

    private String dateFin;
}
