package com.jordannoel.templateconnexion.service;

import com.jordannoel.templateconnexion.exception.TemplateConnexionException;

public interface InscriptionService {
    void inscrireUtilisateur(String email, String motDePasse, String confirmationMotDePasse, boolean approbation) throws TemplateConnexionException;
}