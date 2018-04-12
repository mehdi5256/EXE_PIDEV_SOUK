/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SoukLemdina.Entities.AuthUser;
import entities.User;
import entities.event;
import entities.participer;
import java.io.IOException;
import static java.lang.System.in;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import static javax.management.Query.and;
import org.controlsfx.control.Notifications;
import SoukLemdina.Serivces.MyServiceEvenement;//service.MyServiceEvenement;
import SoukLemdina.util.Connexion;
//import util.Authentification;
//import util.MyConnexion;

/**
 *
 * @author AHMED
 */
public class ParticiperController {
   
    
    private final AuthUser currentUser=AuthUser.getInstance();//Authentification.user;
    
       MyServiceEvenement service = new MyServiceEvenement();
       
 
       
       
       
       public boolean test() throws SQLException{
     boolean t = false;
           int a;
           int e ;
           boolean b=false;
 int i=0;
 int k=1;
    event e1 = service.GetEvbyid(event.getId_courant());
       int[] anArray = new int[100];
           String query="select * from participer where ide="+e1.getId();
           Statement st= Connexion.getInstance().getConnexion().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next() && i<100)
        {   
         
          a= rest.getInt("ide");
          
        anArray[i]=a;
              i++;
            
            }
          
           for (int j = 0; j < anArray.length; j++) {
               
           
           if ( anArray[j]==e1.getId()   ){   
         t=true;
         b=t;
           }
       
          
          }
          
        return b;
   
       }
       
       /**********************************/
       
        public boolean testuser() throws SQLException{
     boolean t = false;
      boolean b = false;
           int a;
           int e ;
 int i=0;
 int k=1;
    event e1 = service.GetEvbyid(event.getId_courant());
       int[] anArray = new int[100];

           String query="select * from participer";
           Statement st= Connexion.getInstance().getConnexion().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next() && i<100)
        {   
          a= rest.getInt("idu");
          
        anArray[i]=a;
              i++;
            }
             
            int j=0;
      while(j<anArray.length)
               
      {System.out.println("________________________");
          System.out.println("test avant :" +t);
           if (currentUser.getId()==anArray[j])
           {       
               t=true;
               b=t;
             System.out.println("-----tableau"+anArray[j]);
          System.out.println("user id=="+currentUser.getId());
    System.out.println(" test apres =="+t);
          System.out.println("_____________________");
      }
           else if (currentUser.getId()!=anArray[j]){
               
               
               System.out.println("*************************************");
                t=false;  
                
                
              System.out.println("_____________________");
              
              System.out.println("_________"+t+"____________");
              
              System.out.println("_____________________");
         
           }
        
           j++;
       }
            System.out.println ("------------------   ==="+b);
       return b;
       }
       
       /****************************/
       
       
    public void part(event e11) throws SQLException, IOException{
          final event e =new event();
          participer p=new participer();
           event e1 = service.GetEvbyid(event.getId_courant());
           System.out.println("test id ev"+test());
   System.out.println("test id user"+testuser());
//     testuser();

            if (((test()==false && testuser()==false))|| ((test()==true && testuser()==false))) {
            
     
               e1.setNbrparticipants(e1.getNbrparticipants()+1);
   ;
        service.participate(e1);
        p.setIdu(currentUser.getId());
      
           p.setNom_organisateur(e1.getNom_organisateur());
             p.setNom(e1.getNom());
          
           p.setImage(e1.getImage());
           p.setLieu(e1.getLieu());
//           p.setDatedeb(e1.getDateDeb());
//           p.setDate_fin(e1.getDate_fin());
           p.setDescription(e1.getDescription());
        
        service.insertionparticipation(p,e1.getId());
                                 Notifications n =Notifications.create().title("Notification")
             .text("Participation effectuer avec success")
             .graphic(null)
             .position(Pos.BASELINE_LEFT)
             .onAction(new  EventHandler<ActionEvent>() {
             
             public void handle (ActionEvent event){
                 System.out.println("notifocation");
             }
             });
     n.showConfirm();  
     template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/MyMenuFXML.fxml"))));
            
        }    
            else {
            
                             Notifications n =Notifications.create().title("Notification")
             .text("Vous étes déja  participer")
             .graphic(null)
             .position(Pos.BASELINE_LEFT)
             .onAction(new  EventHandler<ActionEvent>() {
             
             public void handle (ActionEvent event){
                 System.out.println("notifocation");
             }
             });
     n.showWarning();
            /*************/
           
            }
  
    };

    
}
