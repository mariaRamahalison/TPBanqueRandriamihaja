/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquerandriamihaja.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tpbanquerandriamihaja.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandriamihaja.entities.CompteBancaire;
import mg.itu.tpbanquerandriamihaja.jsf.util.Util;

/**
 *
 * @author maria
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    private CompteBancaire compteBancaire;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public void findCompte() {
        compteBancaire = gestionnaireCompte.findById(id);
    }

    public String modifier() {
        boolean erreur = false;
        if (compteBancaire.getSolde() < 0) {
            Util.messageErreur("Solde négatif!", "Solde négatif !", "formModification:solde");
            erreur = true;
        }
        if (erreur) {
            return null;
        }
        gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Modification effectué sur le compte de " + compteBancaire.getNom());
        return "listeComptes?faces-redirect=true";
    }
}
