/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Accueil2Controller implements Initializable {

    @FXML
    private ImageView imageprod;
    @FXML
    private JFXButton espaceproduit;
    @FXML
    private JFXButton espacecategorie;
    @FXML
    private ImageView imagecat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ProduitAction(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListProduit.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show(); 
        Stage stage = (Stage)espacecategorie.getScene().getWindow();
        
         stage.close();
    }
    

    @FXML
    private void CategorieAction(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListCategorie.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show(); 
        Stage stage = (Stage)espacecategorie.getScene().getWindow();
        
         stage.close();
    }
   }
    
