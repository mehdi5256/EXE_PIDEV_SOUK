/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author AHMED
 */
public class MyMenuFXMLController implements Initializable {
    
    
      @FXML
    private BorderPane BorderPane;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LoadUI("AfficheEvFXML");
    }    

    @FXML
    private void Profile(MouseEvent event) {
    }

    @FXML
    private void AjoutP(MouseEvent event) throws IOException {
        LoadUI("AjouterEvFXML");
    }

    @FXML
    private void AfficheP(MouseEvent event) {
       LoadUI("AfficheEvFXML");
    
    }

    @FXML
    private void CommandeP(MouseEvent event) {
           LoadUI("MesEvFXML");
    } 

    @FXML
    private void Statistique(MouseEvent event) {
           LoadUI("MesPartFXML");
        
    }
      @FXML
    private void ToHome(MouseEvent event) throws IOException {
          template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/menuAdmin.fxml"))));   
  
          
        
    }
    
    private void LoadUI(String ui)
    {
        Parent root= null;
        try {
                  // Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/views/Affiche.fxml"))));             

            root= FXMLLoader.load(getClass().getResource("/GUI/"+ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MyMenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BorderPane.setCenter(root);
        
    }

  

    
}
