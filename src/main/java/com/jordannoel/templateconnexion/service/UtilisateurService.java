package com.jordannoel.templateconnexion.service;

import com.jordannoel.templateconnexion.exception.TemplateConnexionException;
import com.jordannoel.templateconnexion.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    String findMotDePasseByEmail(String email);

    Utilisateur findOneById(Long id);

    Utilisateur findOneByEmail(String email);

    List<Utilisateur> findAllByIdIn(List<Long> ids);

    void utilisateurConnecte(Long id) throws TemplateConnexionException;

}