/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class SignupControllertt implements Initializable {

    @FXML
    private AnchorPane pane_cnx;
    @FXML
    private AnchorPane pane_login;
    @FXML
    private JFXButton btn_inscrire;
    @FXML
    private JFXButton btn_cnx;
    @FXML
    private JFXTextField ch_prenom;
    @FXML
    private JFXTextField ch_username;
    @FXML
    private JFXTextField ch_adresse;
    @FXML
    private JFXTextField ch_nom;
    @FXML
    private JFXTextField ch_email;
    @FXML
    private JFXPasswordField ch_mdp1;
    @FXML
    private JFXPasswordField ch_mdp2;
    @FXML
    private Label label_erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleButtonAction(ActionEvent event) {
        if(event.getSource()==btn_cnx)
        {
           pane_login.toFront();
        }
        else
            if (event.getSource()==btn_inscrire)
            {
                pane_cnx.toFront();
            }
    }

    @FXML
    private void HandleButtonAction(MouseEvent event) {
    }

    @FXML
    private void InscriptionAction(ActionEvent event) {
    }
    
}
