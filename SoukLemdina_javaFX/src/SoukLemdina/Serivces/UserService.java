/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Serivces;

import SoukLemdina.Entities.AuthUser;
import SoukLemdina.Entities.Boutique;
import SoukLemdina.Entities.User;
import static SoukLemdina.Serivces.OtherServices.selectGerant;
import SoukLemdina.util.Connexion;
import SoukLemdina.util.Security;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amine
 */
public class UserService {
    Security sec = new Security();
    public void inscrirUser(User user)
    {
        
        String mdp = sec.hashageMotDePasse(user.getPassword());
        String requete = "insert into fos_user (id, username, username_canonical, email_canonical, nom, prenom, addresse, password, last_login, email, enabled, roles ) values (?,?,?,?,?,?,?,?,?,?,1,'a:0:{}')";
        try {
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1,user.getId());
            ps.setString(2,user.getUsername());
            ps.setString(3, user.getUsername().toLowerCase());
            ps.setString(4,user.getEmail().toLowerCase());
            
            ps.setString(5,user.getNom());
            ps.setString(6,user.getPrenom());
            ps.setString(7,user.getAdresse());
            ps.setString(8, mdp);
            ps.setDate(9, (Date) user.getLast_login());
            //ps.setInt(8,user.getAge());
            ps.setString(10,user.getEmail());
            //ps.setString(9,user.getRole());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void authenticateUser(String username)
    {
        try {
            String req="Select * From fos_user WHERE username = ?";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setString(1, username);
              ResultSet rs =ps.executeQuery();
              if(rs.next())
              {
                  AuthUser.getInstance().setAdresse(rs.getString("addresse"));
                  AuthUser.getInstance().setNom(rs.getString("nom"));
                  AuthUser.getInstance().setPrenom(rs.getString("prenom"));
                  AuthUser.getInstance().setUsername(rs.getString("username"));
                  AuthUser.getInstance().setEmail(rs.getString("email"));
                  AuthUser.getInstance().setId(rs.getInt("id"));
                  AuthUser.getInstance().setPassword(rs.getString("password"));
                  AuthUser.getInstance().setRole(rs.getString("roles"));
                  System.out.println("Selection de gérant pour authUser");
                  AuthUser.getInstance().setGerant(selectGerant(AuthUser.getInstance().getId()));
              }
        } catch (SQLException e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
    }
    
    public static boolean checkLoginUser(String username, String pwd)
    {
        try {
            String req="Select * From fos_user WHERE username = ?";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setString(1, username);
              ResultSet rs =ps.executeQuery();
              if(rs.next())
              {
                if(Security.verifierMotDePasse(pwd, rs.getString("password")))
                return true;
              }
              return false;
        } catch (SQLException e) {
            System.out.println("non affiché");
             e.printStackTrace();
             return false;
        }
    }
    
    public static boolean checkUsername(String username)
    {
        try {
            String req="Select username From fos_user WHERE username = ?";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setString(1, username);
              ResultSet rs =ps.executeQuery();
              if(rs.next())
                  return false;
              else return true;
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return false;
    }
    
    public static boolean checkEmail(String email)
    {
        try {
            String req="Select email From fos_user WHERE email = ?";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            ps.setString(1, email);
              ResultSet rs =ps.executeQuery();
              if(rs.next())
                  return false;
              else return true;
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return false;
    }
    
    public static ArrayList<User> selectAllUsers()
    {
        ArrayList<User> liste = new ArrayList<User>();
        try {
            String req="Select * From fos_user";
            java.sql.PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
              ResultSet rs =ps.executeQuery(); 
              System.out.println("Selected all users");
              while(rs.next())
              {
                  User user = new User();
                  user.setUsername(rs.getString("username"));
                  user.setNom(rs.getString("nom"));
                  user.setPrenom(rs.getString("prenom"));
                  user.setAdresse(rs.getString("addresse"));
                  liste.add(user);
              }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return liste;
    }   
}