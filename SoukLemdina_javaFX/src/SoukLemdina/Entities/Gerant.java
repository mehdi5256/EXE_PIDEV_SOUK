/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import java.util.Date;

/**
 *
 * @author Amine
 */
public class Gerant {
    private int id;
    private int etat;
    private Date date_debut;
    private Boutique boutique;
    private AuthUser user;
    
    public Gerant(int id, int etat, Date date_debut, Boutique boutique, AuthUser user)
    {
        this.id=id;
        this.etat=etat;
        this.date_debut=date_debut;
        this.boutique=boutique;
        this.user=user;
    }

    public Gerant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }
    
    
}
