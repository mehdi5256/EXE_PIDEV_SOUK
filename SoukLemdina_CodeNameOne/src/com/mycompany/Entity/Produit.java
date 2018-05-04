/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

/**
 *
 * @author Mehdi
 */
public class Produit {
    
    public int id ; 
    public String nomProduit; 
    public String description; 
    public String image;   
    public Categorie categorie;
    public float prix;    
    public float prix_sans_solde;   
    public int rating;  
    public int solde; 

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix_sans_solde() {
        return prix_sans_solde;
    }

    public void setPrix_sans_solde(float prix_sans_solde) {
        this.prix_sans_solde = prix_sans_solde;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Produit(int id, String nomProduit, String description, String image, float prix, Categorie categorie) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit() {
    }

    public Produit(String nomProduit, String description, String image, Categorie categorie, float prix) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "nomProduit=" + nomProduit + ", description=" + description + ", image=" + image + ", categorie=" + categorie.getNomCategorie() + ", prix=" + prix + '}';
    }

    public Produit(String nomProduit, String description) {
        this.nomProduit = nomProduit;
        this.description = description;
    }
    
    
}
