package com.epsi.blockchainsmokers.workshopi4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

}

