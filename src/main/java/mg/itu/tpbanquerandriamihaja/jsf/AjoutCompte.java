/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquerandriamihaja.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import mg.itu.tpbanquerandriamihaja.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandriamihaja.entities.CompteBancaire;
import mg.itu.tpbanquerandriamihaja.jsf.util.Util;

/**
 *
 * @author maria
 */

@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    private String nom;

    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String action() {
        boolean erreur = false;
        if (solde < 0) {
            Util.messageErreur("Solde négatif!", "Solde négatif !", "formAjoutCompte:solde");
            erreur = true;
        }
        if (erreur) {
            return null;
        }
        CompteBancaire compteBancaire = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte créé");
        return "listeComptes?faces-redirect=true";
    }
}
