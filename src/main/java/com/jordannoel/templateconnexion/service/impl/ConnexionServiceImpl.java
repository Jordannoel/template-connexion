package com.jordannoel.templateconnexion.service.impl;

import com.jordannoel.templateconnexion.exception.TemplateConnexionException;
import com.jordannoel.templateconnexion.model.Utilisateur;
import com.jordannoel.templateconnexion.service.ConnexionService;
import com.jordannoel.templateconnexion.service.UtilisateurService;
import com.jordannoel.templateconnexion.utils.TemplateConnexionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnexionServiceImpl implements ConnexionService {

    private UtilisateurService utilisateurService;

    @Autowired
    public ConnexionServiceImpl(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public Utilisateur connecterUtilisateur(String email, String motDePasse) throws TemplateConnexionException {

        TemplateConnexionException ex = new TemplateConnexionException();

        if (email != null && !email.equals("")) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                ex.addMessage("email", "Merci de saisir une adresse e-mail valide.");
            }
        } else {
            ex.addMessage("email", "Merci de saisir votre adresse e-mail.");
        }
        if (motDePasse != null && !motDePasse.equals("")) {
            if (!motDePasseCorrect(email, motDePasse)) {
                ex.addMessage("motDePasse", "Le mot de passe n'est pas correct.");
            }
        } else {
            ex.addMessage("motDePasse", "Merci de saisir votre mot de passe.");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }

        return utilisateurService.findOneByEmail(email);
    }

    private boolean motDePasseCorrect(String email, String motDePasse) {
        return TemplateConnexionUtils.sha256(motDePasse).equals(utilisateurService.findMotDePasseByEmail(email));
    }
}