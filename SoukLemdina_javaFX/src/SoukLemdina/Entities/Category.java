/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import java.util.ArrayList;

/**
 *
 * @author Mehdi
 */
public class Category {
     public int id ; 
    public String nomCategory; 
    ArrayList<Produit> listproduit =  new ArrayList<>();

    public Category(int id, String nomCategory) {
        this.id = id;
        this.nomCategory = nomCategory;
    }
    
     public Category(String nomCategory) {
        this.nomCategory = nomCategory;
    }

     public Category() {
       
    }
    public int getId() {
        return id;
    }

    public String getNomCategory() {
        return nomCategory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }

    public Category(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nomCategory=" + nomCategory + '}';
    }
    
}
