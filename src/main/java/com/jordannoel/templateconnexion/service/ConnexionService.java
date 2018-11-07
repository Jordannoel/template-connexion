package com.jordannoel.templateconnexion.service;

import com.jordannoel.templateconnexion.exception.TemplateConnexionException;
import com.jordannoel.templateconnexion.model.Utilisateur;

public interface ConnexionService {
    Utilisateur connecterUtilisateur(String email, String motDePasse) throws TemplateConnexionException;
}