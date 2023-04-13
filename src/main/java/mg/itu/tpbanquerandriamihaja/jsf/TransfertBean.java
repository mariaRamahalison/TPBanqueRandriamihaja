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
@Named(value = "transfert")
@RequestScoped
public class TransfertBean {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    private Long idSource;

    private Long idDestination;

    private int montant;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transferer() {
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Solde insuffisant ! ", "Solde insuffisant !", "form:montant");
                erreur = true;
            }
        }
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        if (destination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        gestionnaireCompte.transferer(source, destination, montant);
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes?faces-redirect=true";
    }

}
