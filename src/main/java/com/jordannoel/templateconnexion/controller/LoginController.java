package com.jordannoel.templateconnexion.controller;

import com.jordannoel.templateconnexion.config.ApplicationUrl;
import com.jordannoel.templateconnexion.config.PageMapping;
import com.jordannoel.templateconnexion.exception.TemplateConnexionException;
import com.jordannoel.templateconnexion.model.Utilisateur;
import com.jordannoel.templateconnexion.service.ConnexionService;
import com.jordannoel.templateconnexion.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private ConnexionService connexionService;
    private InscriptionService inscriptionService;

    private static final String REDIRECT = "redirect:";

    @Autowired
    public LoginController(ConnexionService connexionService, InscriptionService inscriptionService) {
        this.connexionService = connexionService;
        this.inscriptionService = inscriptionService;
    }

    /***********************************************INDEX***************************************************/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return PageMapping.INDEX;
    }

    @RequestMapping(value = ApplicationUrl.INDEX, method = RequestMethod.GET)
    public String index() {
        return PageMapping.INDEX;
    }

    /***********************************************INSCRIPTION***************************************************/

    @RequestMapping(value = ApplicationUrl.SIGNIN, method = RequestMethod.GET)
    public String inscription() {
        return PageMapping.SIGNIN;
    }

    @RequestMapping(value = ApplicationUrl.SIGNIN, method = RequestMethod.POST)
    public String inscriptionPost(HttpServletRequest req, RedirectAttributes red) {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        String confirmationMotDePasse = req.getParameter("confirmationMotDePasse");
        boolean approbation = Boolean.valueOf(req.getParameter("approbation"));
        try {
            inscriptionService.inscrireUtilisateur(email, motDePasse, confirmationMotDePasse, approbation);
            Utilisateur utilisateur = connexionService.connecterUtilisateur(email, motDePasse);
            HttpSession session = req.getSession();
            session.setAttribute("email", utilisateur.getEmail());
            session.setAttribute("id", utilisateur.getId());
            red.addFlashAttribute("email", utilisateur.getEmail());
            return REDIRECT + ApplicationUrl.SIGNIN_OK;
        } catch (TemplateConnexionException e) {
            red.addFlashAttribute("errors", e.getMessages());
            return REDIRECT + ApplicationUrl.SIGNIN;
        }
    }

    @RequestMapping(value = ApplicationUrl.SIGNIN_OK, method = RequestMethod.GET)
    public String inscriptionValidee(HttpServletRequest req, ModelMap modelMap) {
        HttpSession session = req.getSession();
        modelMap.put("email", session.getAttribute("email"));
        return PageMapping.SIGNIN_OK;
    }

    /***********************************************CONNEXION***************************************************/

    @RequestMapping(value = ApplicationUrl.LOGIN, method = RequestMethod.GET)
    public String connexion() {
        return PageMapping.LOGIN;
    }

    @RequestMapping(value = ApplicationUrl.LOGIN, method = RequestMethod.POST)
    public String connexionPost(HttpServletRequest req, ModelMap modelMap) {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        try {
            Utilisateur utilisateur = connexionService.connecterUtilisateur(email, motDePasse);
            HttpSession session = req.getSession();
            session.setAttribute("email", utilisateur.getEmail());
            session.setAttribute("id", utilisateur.getId());
            return REDIRECT + ApplicationUrl.INDEX;
        } catch (TemplateConnexionException e) {
            modelMap.put("errors", e.getMessages());
            return PageMapping.LOGIN;
        }
    }

    /***********************************************DECONNEXION***************************************************/

    @RequestMapping(value = ApplicationUrl.LOGOUT, method = RequestMethod.GET)
    public String deconnexionPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return REDIRECT + ApplicationUrl.INDEX;
    }
}
