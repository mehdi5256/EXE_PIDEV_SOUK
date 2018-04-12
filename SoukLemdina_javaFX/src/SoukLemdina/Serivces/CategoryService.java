/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Serivces;

import SoukLemdina.Entities.Category;
import SoukLemdina.util.Connexion;
import SoukLemdina.util.MyConnection;
import com.mysql.jdbc.PreparedStatement;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi
 */
public class CategoryService {
    private Connection connection;
    private PreparedStatement ps;
    public CategoryService (){
                connection = MyConnection.getInstance();

  }
    
   public void ajouter(Category category){
        
        String requete = "insert into categorie (id, nomCategorie ) values (?,?)";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1,category.getId());
            ps.setString(2,category.getNomCategory());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    public static List<Category> afficher ()
    {
        List<Category> list =new ArrayList<>() ; 
        String reqSelect="Select * From Categorie ";

        try {
          java.sql.Statement ps = Connexion.getInstance().getConnexion().createStatement();
              ResultSet rs =ps.executeQuery(reqSelect);
              while (rs.next()){
                  Category p = new Category();
                  p.id= rs.getInt(1);
                  p.nomCategory = rs.getString(2);
                  list.add(p);
                  System.out.println("id:"+rs.getInt("id")+"|nomCategorie:"+rs.getString("nomCategorie"));
              }
        } catch (SQLException e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
       return list;
    }
     
     public void delete(int id) {
        String requete = "delete from categorie where id=?";
        try {
          java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Parcours supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
     
     public static void update(Category parcours) {
        System.out.println(parcours);
        String req = "update Categorie set nomCategorie=? where id=?" ;
        try {
          java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);

            ps.setString(1, parcours.getNomCategory());   
            ps.setInt(2, parcours.getId());

            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     public Category findCategoryById (int categoryId)
    {
    String req="SELECT * from categorie where  id =?" ;
    Category category = new Category();
        try { 
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1,categoryId);
            ResultSet rs =ps.executeQuery(req);
            category.id= rs.getInt(1);
            category.nomCategory = rs.getString(2);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
        }
        
        return category;
    
      }
     public static Category findCategoryByName (String categoryName)
    {
    String req="SELECT * from categorie where  nomCategorie=?" ;
    Category category = new Category();
        try { 
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setString(1,categoryName);
            ResultSet rs =ps.executeQuery(req);
            category.id= rs.getInt(1);
            category.nomCategory = rs.getString(2);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
        }
        
        return category;
    
      }
   
}
