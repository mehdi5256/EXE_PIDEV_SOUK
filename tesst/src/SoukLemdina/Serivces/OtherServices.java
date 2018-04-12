/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Serivces;

import SoukLemdina.Entities.AuthUser;
import SoukLemdina.Entities.Boutique;
import SoukLemdina.Entities.Categorie;
import SoukLemdina.Entities.Commande;
import SoukLemdina.Entities.Gerant;
import SoukLemdina.Entities.Produit;
import SoukLemdina.Entities.User;
import SoukLemdina.util.Connexion;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Amine
 */
public  class OtherServices {
    /*
   SELECT COUNT(*), dateCreation
FROM commande c, produit_boutique p, boutique b
WHERE c.produit_id = p.id
AND p.boutique_id = b.id
AND b.id = 5
GROUP BY dateCreation ASC
    **/
    public static XYChart.Series selectStats2(int boutique_id)
    {
        //List<Categorie> liste = new ArrayList<Categorie>();
       XYChart.Series series = new XYChart.Series();
        try {
            String req="SELECT COUNT(*) as nombre, dateCreation " +
                        "FROM commande c, produit_boutique p, boutique b " +
                        "WHERE c.produit_id = p.id " +
                        "AND p.boutique_id = b.id " +
                        "AND b.id = ? " +
                        "GROUP BY dateCreation ASC";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1, boutique_id);
            ResultSet rs =ps.executeQuery();
              while(rs.next())
              {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    // Get the date today using Calendar object.
                    java.util.Date sdate = Calendar.getInstance().getTime();        
                    sdate=rs.getDate("dateCreation");
                    String strDate = df.format(sdate);
                //XYChart.Series<String, Integer> series = new XYChart.Series();
                series.getData().add(new XYChart.Data(strDate,rs.getInt("nombre")));
                //series.getData().add(new XYChart.Data<String, Integer>(rs.getString("nom"), rs.getInt("nombre")));
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return series;
    }
    
    //Stats1
    public static List<XYChart.Series<String, Integer>> selectCategoriesCountDuneBoutique(int boutique_id)
    {
        //List<Categorie> liste = new ArrayList<Categorie>();
        List<XYChart.Series<String, Integer>> liste = new ArrayList<XYChart.Series<String, Integer>>();
        try {
            String req="SELECT COUNT(*) as nombre, c.nom " +
                        "FROM produit_boutique p, boutique b, categorie c " +
                        "WHERE p.boutique_id=b.id " +
                        "AND p.categorie_id = c.id " +
                        "AND b.id=? " +
                        "GROUP BY p.categorie_id";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1, boutique_id);
            ResultSet rs =ps.executeQuery();
              while(rs.next())
              {
                XYChart.Series<String, Integer> series = new XYChart.Series();
                series.getData().add(new XYChart.Data<String, Integer>(rs.getString("nom"), rs.getInt("nombre")));
                liste.add(series);
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return liste;
    }
    
    public static void modifierBouique(Boutique boutique)
    {
        String requete = "UPDATE boutique SET nomBoutique = ?, lieu = ?, description= ? WHERE id = ?";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setString(1,boutique.getNomBoutique());
            ps.setString(2,boutique.getLieu());
            ps.setString(3,boutique.getDescription());
            ps.setInt(4, boutique.getId());
            System.out.println("modification de boutique id: "+boutique.getId());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void supprimerProduitDuneBoutique(Produit produit)
    {
        String requete = "DELETE FROM produit_boutique WHERE id = ?";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1,produit.getId());
            System.out.println("suppression du produit id: "+produit.getId());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modifierProduit(Produit produit)
    {
        String requete = "UPDATE produit_boutique SET nomProduit = ?, prix = ?, description= ? WHERE id = ?";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setString(1,produit.getNomProduit());
            System.out.println("nom produit a modifier: "+produit.getNomProduit());
            ps.setFloat(2,produit.getPrix());
            ps.setString(3,produit.getDescription());
            ps.setInt(4,produit.getId());
            System.out.println("modification du produit id: "+produit.getId());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ajouterProduitBoutique(Produit produit, int boutique_id)
    {
        String requete = "insert into produit_boutique (nomProduit, dateDepot, prix, boutique_id, categorie_id, description ) values (?,?,?,?,?,?)";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            
            ps.setString(1,produit.getNomProduit());
            System.out.println("add prod id done");
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            ps.setTimestamp(2,date);
            ps.setFloat(3,produit.getPrix());
            ps.setInt(4,boutique_id);
            ps.setInt(5,produit.getCategorie().getId());
            ps.setString(6, produit.getDescription());
            System.out.println("Execution ajout produit boutique: "+boutique_id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Categorie> selectAllCategories()
    {
        List<Categorie> liste = new ArrayList<Categorie>();
        try {
            String req="Select * from categorie";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            //ps.setInt(1, user_id);
            ResultSet rs =ps.executeQuery();
              while(rs.next())
              {
                Categorie categorie = new Categorie();
                categorie.setNom(rs.getString("nom"));
                categorie.setId(rs.getInt("id"));
                liste.add(categorie);
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return liste;
    }
    
    public static void supprimerCommande(int user_id, int produit_id)
    {
        String requete = "DELETE FROM commande where user_id=? AND produit_id=?";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            
            ps.setInt(1,user_id);
            System.out.println("Suppression de commande");
            ps.setInt(2,produit_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static List<Produit> selectProduitsCommandes(int user_id)
    {
        List<Produit> liste = new ArrayList<Produit>();
        //List<Boutique> liste = new ArrayList<Boutique>();
        try {
            String req="Select p.id, p.nomProduit, p.prix, p.description, c.nom "
                        + "From produit_boutique p, categorie c, commande com "
                        + "where com.user_id=? " 
                        + "and c.id = p.categorie_id "
                        + "and com.produit_id=p.id";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1, user_id);
            ResultSet rs =ps.executeQuery();
              while(rs.next())
              {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setNomProduit(rs.getString("nomProduit"));
                produit.setPrix(rs.getFloat("prix"));
                //boutique.setDateCreation(rs.getDate("date_creation"));
                produit.setDescription(rs.getString("Description"));
                Categorie categorie = new Categorie();
                categorie.setNom(rs.getString("nom"));
                produit.setCategorie(categorie);
                liste.add(produit);
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return liste;
    }
    
    public static void ajouterCommande(Commande commande)
    {
        String requete = "insert into commande (produit_id, user_id, quantite, cloturee, dateCreation ) values (?,?,?,?,?)";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            
            ps.setInt(1,commande.getProduit_id());
            System.out.println("add prod id done");
            ps.setInt(2,commande.getUser_id());
            ps.setInt(3,commande.getQuantite());
            ps.setInt(4,commande.getCloturee());
            ps.setTimestamp(5,commande.getDateCreation());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Produit> selectAllProdsDuneBoutique(int id_boutique)
    {
        List<Produit> liste = new ArrayList<Produit>();
        //List<Boutique> liste = new ArrayList<Boutique>();
        try {
            String req="Select p.id, p.nomProduit, p.prix, p.description, c.nom "
                        + "From produit_boutique p, categorie c " 
                        + "where p.boutique_id = ? and c.id = p.categorie_id";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1, id_boutique);
            ResultSet rs =ps.executeQuery();
              while(rs.next())
              {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setNomProduit(rs.getString("nomProduit"));
                produit.setPrix(rs.getFloat("prix"));
                //boutique.setDateCreation(rs.getDate("date_creation"));
                produit.setDescription(rs.getString("Description"));
                Categorie categorie = new Categorie();
                categorie.setNom(rs.getString("nom"));
                produit.setCategorie(categorie);
                liste.add(produit);
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return liste;
    }
    
    public static int countAllBoutiques()
    {
        int count=0;
        ArrayList<Boutique> liste = new ArrayList<Boutique>();
        try {
            String req="Select count(id) From boutique";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            //ps.setInt(1, id_boutique);
            ResultSet rs =ps.executeQuery();
            rs.next();
            count=rs.getInt(1);
            System.out.println(count);
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return count;
    }
    
    public static List<Boutique> selectAllBoutiques()
    {
        
        List<Boutique> liste = new ArrayList<Boutique>();
        try {
            String req="Select * From boutique";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            //ps.setInt(1, id_boutique);
            ResultSet rs =ps.executeQuery();
              while(rs.next())
              {
                Boutique boutique = new Boutique();
                boutique.setId(rs.getInt("id"));
                boutique.setNomBoutique(rs.getString("nomBoutique"));
                boutique.setLieu(rs.getString("Lieu"));
                //boutique.setDateCreation(rs.getDate("date_creation"));
                boutique.setDescription(rs.getString("Description"));
                liste.add(boutique);
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return liste;
    }
    
    
    public static Boutique selectBoutique(int id_boutique)
    {
        Boutique boutique = new Boutique();
        try {
            String req="Select * From boutique WHERE id = ?";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1, id_boutique);
            ResultSet rs =ps.executeQuery();
              if(rs.next())
              {
                //rs.next();
                boutique.setId(id_boutique);
                boutique.setNomBoutique(rs.getString("nomBoutique"));
                boutique.setLieu(rs.getString("Lieu"));
                //boutique.setDateCreation(rs.getDate("date_creation"));
                boutique.setDescription(rs.getString("Description"));
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return boutique;
    }
    
    public static Gerant selectGerant(int id_user)
    {
        Gerant gerant = new Gerant();
        try {
            System.out.println("Try select gerant");
            String req="Select * From gerant WHERE user_id = ?";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setInt(1, id_user);
            ResultSet rs =ps.executeQuery();
              if(rs.next())
              {
                Boutique boutique = new Boutique();
                //rs.next();
                gerant.setUser(AuthUser.getInstance());
                boutique = selectBoutique(rs.getInt("boutique_id"));
                gerant.setBoutique(boutique);
                gerant.setId(rs.getInt("id"));
                gerant.setEtat(rs.getInt("etat"));
                gerant.setDate_debut(rs.getDate("dateDebut"));
                AuthUser.getInstance().setGerant(true);
                System.out.println("AuthUser.isGerant reçoit: "+AuthUser.getInstance().IsGerant());
                //String req2="Select * From boutique WHERE id = ?";
                //ps = Connexion.getInstance().getConnexion().prepareStatement(req2);
                //ps.setInt(1, );
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    return gerant;
    }
    
}
