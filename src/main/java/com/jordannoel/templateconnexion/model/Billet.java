package com.jordannoel.templateconnexion.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "billet")
public class Billet {

    @Id
    @GeneratedValue
    private int id;
}
