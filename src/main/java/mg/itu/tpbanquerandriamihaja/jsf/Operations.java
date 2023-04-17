/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquerandriamihaja.jsf;

import java.io.Serializable;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import mg.itu.tpbanquerandriamihaja.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandriamihaja.entities.CompteBancaire;
import mg.itu.tpbanquerandriamihaja.entities.OperationBancaire;

/**
 *
 * @author maria
 */
@Named(value = "operations")
@RequestScoped
public class Operations implements Serializable {

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

    public void loadOperations() {
        compteBancaire = gestionnaireCompte.findById(id);
    }

    
}