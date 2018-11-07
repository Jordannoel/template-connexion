package com.jordannoel.templateconnexion.service.impl;

import com.jordannoel.templateconnexion.dao.UtilisateurDao;
import com.jordannoel.templateconnexion.exception.TemplateConnexionException;
import com.jordannoel.templateconnexion.model.Utilisateur;
import com.jordannoel.templateconnexion.service.InscriptionService;
import com.jordannoel.templateconnexion.utils.TemplateConnexionUtils;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    private UtilisateurDao utilisateurDao;

    public InscriptionServiceImpl(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public void inscrireUtilisateur(String email, String motDePasse, String confirmationMotDePasse, boolean approbation) throws TemplateConnexionException {

        TemplateConnexionException ex = new TemplateConnexionException();
        if (email == null || email.equals("")) {
            ex.addMessage("email", "Merci de saisir votre adresse e-mail.");
        } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            ex.addMessage("email", "Merci de saisir une adresse mail valide.");
        }
        if (emailDejaExistant(email)) {
            ex.addMessage("email", "Un compte est déjà associé à cette adresse e-mail");
        }

        if (motDePasse == null || motDePasse.length() < 8) {
            ex.addMessage("motDePasse", "Le mot de passe doit contenir au moins 8 caractères.");
        }
        if (motDePasse != null && !motDePasse.equals(confirmationMotDePasse)) {
            ex.addMessage("confirmationMotDePasse", "Les deux mots de passe ne sont pas identiques.");
        }
        if (!approbation) {
            ex.addMessage("approbation", "Vous devez accepter les conditions.");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }

        utilisateurDao.save(new Utilisateur(email, TemplateConnexionUtils.sha256(motDePasse)));
    }

    private boolean emailDejaExistant(String email) {
        return utilisateurDao.countByEmail(email) > 0;
    }
}