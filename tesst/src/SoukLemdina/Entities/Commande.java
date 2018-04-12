/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Amine
 */
public class Commande {
    //produit_id, user_id, quantite, cloturee, dateCreation
    private int id;
    private int user_id;
    private int produit_id;
    private int quantite;
    private int cloturee;
    private Timestamp dateCreation;

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getCloturee() {
        return cloturee;
    }

    public void setCloturee(int cloturee) {
        this.cloturee = cloturee;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
}
