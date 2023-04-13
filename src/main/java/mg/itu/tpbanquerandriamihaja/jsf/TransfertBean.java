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
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        gestionnaireCompte.transferer(source, destination, montant);
        return "listeComptes?faces-redirect=true";
    }

}
