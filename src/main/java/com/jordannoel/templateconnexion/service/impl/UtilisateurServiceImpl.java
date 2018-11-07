package com.jordannoel.templateconnexion.service.impl;

import com.jordannoel.templateconnexion.dao.UtilisateurDao;
import com.jordannoel.templateconnexion.exception.TemplateConnexionException;
import com.jordannoel.templateconnexion.model.Utilisateur;
import com.jordannoel.templateconnexion.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDao utilisateurDao;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public String findMotDePasseByEmail(String email) {
        List<Utilisateur> utilisateurs = utilisateurDao.findByEmail(email);
        if (utilisateurs.size() == 0) {
            return null;
        } else {
            return utilisateurs.get(0).getPassword();
        }
    }

    @Override
    public Utilisateur findOneById(Long id) {
        return utilisateurDao.findOneById(id);
    }

    @Override
    public Utilisateur findOneByEmail(String email) {
        List<Utilisateur> utilisateurs = utilisateurDao.findByEmail(email);
        if (utilisateurs.size() == 0) {
            return null;
        } else {
            return utilisateurs.get(0);
        }
    }

    @Override
    public List<Utilisateur> findAllByIdIn(List<Long> ids) {
        return utilisateurDao.findAllByIdIn(ids);
    }

    @Override
    public void utilisateurConnecte(Long id) throws TemplateConnexionException {
        TemplateConnexionException ex = new TemplateConnexionException();
        if (id == null) {
            ex.addMessage("connexion", "Vous n'êtes pas connecté");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }
    }
}
