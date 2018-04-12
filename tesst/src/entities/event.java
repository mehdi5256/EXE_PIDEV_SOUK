/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Hp
 */
public class event {
    
     private static int id_courant ;

    public static int getId_courant() {
        return id_courant;
    }

    public static void setId_courant(int id_courant) {
        event.id_courant = id_courant;
    }
    
    private int id ; 
    private String nom;
    private String description;
   
//    private java.util.Date datedeb = new java.util.Date();
//    private java.util.Date datefin = new java.util.Date();
//    
    
    public java.util.Date dateDeb;
    private java.util.Date date_fin;
    private String lieu ;
    private String image ;
    private String nom_organisateur;
    private String lien ;
   


    private int idu; 
    private int nbrparticipants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom_organisateur() {
        return nom_organisateur;
    }

    public void setNom_organisateur(String nom_organisateur) {
        this.nom_organisateur = nom_organisateur;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }

    public event(int id, String nom, String description, Date dateDeb, Date date_fin, String lieu, String image, String nom_organisateur, String lien, int idu, int nbrparticipants) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDeb = dateDeb;
        this.date_fin = date_fin;
        this.lieu = lieu;
        this.image = image;
        this.nom_organisateur = nom_organisateur;
        this.lien = lien;
        this.idu = idu;
        this.nbrparticipants = nbrparticipants;
    }

    public event() {
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", dateDeb=" + dateDeb + ", date_fin=" + date_fin + ", lieu=" + lieu + ", image=" + image + ", nom_organisateur=" + nom_organisateur + ", lien=" + lien + ", idu=" + idu + ", nbrparticipants=" + nbrparticipants + '}';
    }

   
   



  
}
