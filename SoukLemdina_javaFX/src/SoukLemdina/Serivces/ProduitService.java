/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Serivces;
import SoukLemdina.Entities.Category;
import SoukLemdina.Entities.Produit;
import SoukLemdina.util.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Mehdi
 */
public class ProduitService {
 
    
    public void AjouterProduit (Produit p) {
        String requete = "insert into produit (id, nomProduit,description,image,categorie_id,prix,prix_sans_solde,rating,solde) values (?,?,?,?,?,?,?,?,?)";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1,p.getId());
            ps.setString(2,p.getNomProduit()); 
            ps.setString(3,p.getDescription());    
            ps.setString(4,p.getImage());   
            ps.setInt(5,p.getCategory_idd().getId());     
            ps.setFloat(6,p.getPrix()); 
            ps.setFloat(7,p.getPrix_sans_solde());     
            ps.setInt(8,p.getRating());       
            ps.setInt(9,p.getSolde()); 
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static List <Produit> AfficherProduit() {
        List<Produit> list =new ArrayList<>() ; 
   
    {
        try {
            String reqSelect="Select * From produit ";
          java.sql.Statement ps = Connexion.getInstance().getConnexion().createStatement();


              ResultSet rs =ps.executeQuery(reqSelect);
                    

              while (rs.next()){
                  
                  Produit p = new Produit();
                  p.id= rs.getInt(1);
                  p.nomProduit = rs.getString(2);   
                  p.description = rs.getString(3);     
                  p.image = rs.getString(4);     
                  p.categorie_id = rs.getInt(5);    
                  p.prix = rs.getFloat(6);   
                  p.prix_sans_solde = rs.getInt(7);  
                  p.rating = rs.getInt(8);     
                  p.solde = rs.getInt(9);

                 list.add(p);                 
                 System.out.println("id:"+rs.getInt("id")+"|nomProduit:"+rs.getString("nomProduit")
                         +"|description:"+rs.getString("description")
                         +"|nomProduit:"+rs.getString("nomProduit")
                         +"|image:"+rs.getString("image")
                         +"|categorie_id:"+rs.getString("categorie_id")
                         +"|prix:"+rs.getString("prix")
                         +"|prix_sans_solde:"+rs.getString("prix_sans_solde")    
                         +"|rating:"+rs.getString("rating")                  
                        +"|solde:"+rs.getString("solde")  );
            
              }
        } catch (SQLException e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
    }   return list;
}
     
     public void SupprimerProduit(int id) {
        String requete = "delete from produit where id=?";
        try {
          java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
 public static void ModifierProduit(Produit p) {
        System.out.println(p);
        String req = "update produit set nomProduit=?,description=?,image=?,categorie_id=?,prix=?,prix_sans_solde=?,rating=?,solde=? where id=?" ;
        try {
          java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
          
           ps.setString(1, p.getNomProduit());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getImage());
            ps.setInt(4, p.getCategorie_id());
            ps.setFloat(5, p.getPrix());
            ps.setFloat(6, p.getPrix_sans_solde());
            ps.setInt(7, p.getRating());
            ps.setInt(8, p.getSolde());
            ps.setInt(9, p.getId());
            ps.executeUpdate();    


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
 

    public static Produit findProduitById(int id) {
        Produit parcours = new Produit();
        String requete = "select * from Produit WHERE id=?";
        try {
          java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                parcours.setId(resultat.getInt(1));
                parcours.setNomProduit(resultat.getString(2));
                parcours.setDescription(resultat.getString(3));
                parcours.setImage(resultat.getString(4));
                parcours.setCategorie_id(resultat.getInt(5));
                parcours.setPrix(resultat.getFloat(6));
                parcours.setPrix_sans_solde(resultat.getFloat(7));
                parcours.setRating(resultat.getInt(8));
                parcours.setSolde(resultat.getInt(9));
                
                System.out.println("id:"+resultat.getInt("id")+"|nomProduit:"+resultat.getString("nomProduit")
                         +"|description:"+resultat.getString("description")
                         +"|nomProduit:"+resultat.getString("nomProduit")
                         +"|image:"+resultat.getString("image")
                         +"|categorie_id:"+resultat.getString("categorie_id")
                         +"|prix:"+resultat.getString("prix")
                         +"|prix_sans_solde:"+resultat.getString("prix_sans_solde")    
                         +"|rating:"+resultat.getString("rating")                  
                        +"|solde:"+resultat.getString("solde")  );
            }
            
        } catch (SQLException ex) {

//            System.out.println("erreur lors de la recherche du produit " + ex.getMessage());
        }
return parcours;

    }
}
    

