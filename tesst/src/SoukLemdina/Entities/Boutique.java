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
public class Boutique {
    
    private int id;
    private String nomBoutique;
    private String lieu;
    private String description;
    private Date dateCreation;
    
    public Boutique()
    {
        
    }
    
    public Boutique(int id, String nomBoutique, String lieu, String description, Date dateCreation)
    {
        this.dateCreation = dateCreation;
        this.id=id;
        this.description=description;
        this.nomBoutique=nomBoutique;
        this.lieu=lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomBoutique() {
        return nomBoutique;
    }

    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
    
}
