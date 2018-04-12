/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Serivces;

import SoukLemdina.Entities.AuthUser;
import SoukLemdina.Entities.User;
import entities.event;
import entities.participer;
import SoukLemdina.util.Connexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AHMED
 */
public class MyServiceEvenement {
    
   
    private final AuthUser currentUser=AuthUser.getInstance();
    
         

                   public void participate(event e) throws SQLException{
                        //-------------------- Update ----------//

                String reqUp="update event set nbrparticipants=? where id=?";
                    PreparedStatement pss = Connexion.getInstance().getConnexion().prepareStatement(reqUp);

                    
     pss.setInt(1,e.getNbrparticipants());
     pss.setInt(2,e.getId());
    
    pss.executeUpdate();


                   }
               
     public void UpdateEv(event b) throws SQLException{
                        //-------------------- Update ----------//

                String reqUp="update event set nom=? ,lieu=? ,description=? , datedeb=?,datefin=?,lien=?,image=? where id=?";
                    PreparedStatement pss = Connexion.getInstance().getConnexion().prepareStatement(reqUp);

                    
     pss.setString(1,b.getNom());
     pss.setString(2,b.getLieu());
     pss.setString(3,b.getDescription());
     pss.setDate(4, (Date) b.getDateDeb());
     pss.setDate(5, (Date) b.getDate_fin());
     pss.setString(6, b.getLien());
     pss.setString(7, b.getImage());
     pss.setInt(8,b.getId());

    pss.executeUpdate();



}
    
    
    public void insertionEv(event ev) {
 
        try {
            String req= "insert into event (nom,lieu,description,dateDeb,date_fin,image,lien,idu,nom_organisateur) values (?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps =Connexion.getInstance().getConnexion().prepareStatement(req);
            
                    ps.setString(1,ev.getNom());
                    ps.setString(2,ev.getLieu());
                    ps.setString(3,ev.getDescription());
                    ps.setDate(4, (Date) ev.getDateDeb());
                    ps.setDate(5, (Date) ev.getDate_fin());
                    ps.setString(6,ev.getImage());
                    ps.setString(7,ev.getLien());
                    ps.setInt(8,ev.getIdu());
                    ps.setString(9,AuthUser.getInstance().getNom());
                    ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    
                             /**********************************/
    
    
     public void insertionparticipation(participer ev,int ide) {
 
        
        try {
            String req= "insert into participer  (nom,lieu,description,datedeb,datefin,image,idu,nomorganisateur,ide) values (?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(req);
            
                    ps.setString(1,ev.getNom());
                    ps.setString(2,ev.getLieu());
                    ps.setString(3,ev.getDescription());
                    ps.setDate(4, (Date) ev.getDatedeb());
                    ps.setDate(5, (Date) ev.getDate_fin());
                    ps.setString(6,ev.getImage());
                    ps.setInt(7,ev.getIdu());
                    ps.setString(8,ev.getNom_organisateur());
                    ps.setInt(9,ide);
                   
                    ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    /***********************************************************************************************/
    
    
      public List<event> afficheEv()
    {   List ALLproducts = new ArrayList();
        try {  
           String query="select * from event  ";
           Statement st= Connexion.getInstance().getConnexion().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next())
        {   
            event pr = new event();
            
            pr.setId(rest.getInt("id"));
            pr.setNom(rest.getString("nom"));
            pr.setLieu(rest.getString("lieu"));
          pr.setLien(rest.getString("lien"));
           
      
            pr.setDescription(rest.getString("description"));
     
         pr.setImage(rest.getString("image"));
       
         pr.setDateDeb(rest.getDate("datedeb"));
         pr.setNbrparticipants(rest.getInt("nbrparticipants"));
            ALLproducts.add(pr);
        }
           
        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
return ALLproducts;       
    
    }
    
  
      /***********************************************************************************************/
   
      public List<event> MesEv()
    {   List ALLproducts = new ArrayList();
        try {  
           String query="select * from event  where idu="+currentUser.getId();
           Statement st= Connexion.getInstance().getConnexion().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next())
        {   
            event pr = new event();
            
            pr.setId(rest.getInt("id"));
            pr.setNom(rest.getString("nom"));
            pr.setLieu(rest.getString("lieu"));
         
             pr.setLien(rest.getString("lien"));
      
            pr.setDescription(rest.getString("description"));
      
         pr.setImage(rest.getString("image"));
            pr.setNbrparticipants(rest.getInt("nbrparticiants"));
         pr.setDateDeb(rest.getDate("datedeb"));
         
            ALLproducts.add(pr);
        }
           
        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
return ALLproducts;       
    
    }
      
      
      
      /***********************************************************************************************/

      
      
      
              
      public List<event> MesPart()
    {   List ALLproducts = new ArrayList();
        try {  
           String query="select * from participer  where idu="+currentUser.getId();
           Statement st=Connexion.getInstance().getConnexion().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next())
        {   
            event pr = new event();
            
            pr.setId(rest.getInt("id"));
            pr.setNom(rest.getString("nom"));
            pr.setLieu(rest.getString("lieu"));
         pr.setLien(rest.getString("lien"));
            pr.setDescription(rest.getString("description"));
      
         pr.setImage(rest.getString("image"));
            pr.setNbrparticipants(rest.getInt("participate"));
         pr.setDateDeb(rest.getDate("datedeb"));
         
            ALLproducts.add(pr);
        }
           
        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
return ALLproducts;       
    
    }
      
      
      
      
       /********************************************/
    public void deleteEv(event e,int id) throws SQLException{
    
    
    
    
                          
                 String reqD="delete from event  where id=?";
    PreparedStatement pss = Connexion.getInstance().getConnexion().prepareStatement(reqD);
     pss.setInt(1,id);
     
      pss.executeUpdate();
  
    
    }
      
      
      
     public event GetEvbyid(int b) throws SQLException{
                        //-------------------- Update ----------//
 event pr = new event();
      
            String  query = "select * from event where id = ? ";
        PreparedStatement ps;
        try {
            ps = Connexion.getInstance().getConnexion().prepareCall(query);
            ps.setInt(1, b);
      ResultSet rest=  ps.executeQuery();
      
      
      while(rest.next())
        {   
           
             pr.setNom_organisateur(rest.getString("nomorganisateur"));
            pr.setNbrparticipants(rest.getInt("participate"));
          
            pr.setId(rest.getInt("id"));
            pr.setNom(rest.getString("nom"));
            pr.setLieu(rest.getString("lieu"));
                       pr.setLien(rest.getString("lien"));

            pr.setDescription(rest.getString("description"));
            pr.setImage(rest.getString("image"));
            pr.setDateDeb(rest.getDate("datedeb"));
        pr.setDate_fin(rest.getDate("datefin"));
        }
    
        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
return pr;


} 
      
      
      /***********************************************************************************************/
    
    
    




}




