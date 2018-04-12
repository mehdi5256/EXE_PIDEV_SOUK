/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author Mehdi
 */
public class Produit {
    public int id ; 
    public String nomProduit; 
    public String description; 
    public String image;   
    public int categorie_id;    
    public float prix;    
    public float prix_sans_solde;   
    public int rating;  
    public int solde; 
    public Category category_idd ;
    public Categorie categorie;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  Category getCategory_idd() {
        return category_idd;
    }

    public void setCategory_idd(Category category_idd) {
        this.category_idd = category_idd;
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

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
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

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nomProduit=" + nomProduit + ", description=" + description + ", image=" + image + ", categorie_id=" + categorie_id + ", prix=" + prix + ", prix_sans_solde=" + prix_sans_solde + ", rating=" + rating + ", solde=" + solde + '}';
    }

    public Produit() {
    }

    public Produit(int id, String nomProduit, String description, String image, int categorie_id, float prix, float prix_sans_solde, int rating, int solde) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.description = description;
        this.image = image;
        this.categorie_id = categorie_id;
        this.prix = prix;
        this.prix_sans_solde = prix_sans_solde;
        this.rating = rating;
        this.solde = solde;
    }

    public void setImage(JFXButton uploadimage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Produit(String nomProduit, String description, String image, int categorie_id, float prix) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.image = image;
        this.categorie_id = categorie_id;
        this.prix = prix;
    }

    public Produit(String nomProduit, String description, String image, float prix, Category category_idd) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.category_idd = category_idd;
    }

   
    
}
