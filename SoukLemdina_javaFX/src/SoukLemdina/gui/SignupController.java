/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.gui;

import SoukLemdina.Entities.AuthUser;
import SoukLemdina.Entities.User;
import SoukLemdina.Serivces.UserService;
import SoukLemdina.util.Utilitaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class SignupController implements Initializable {

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
    @FXML
    private JFXButton btn_se_connecter;
    @FXML
    private JFXTextField ch_login_password;
    @FXML
    private JFXTextField ch_login_username;
    @FXML
    private Label label_login_erreur;

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
    private void InscriptionAction(ActionEvent event) throws IOException {
        String messageErreur = "";
        if(!Utilitaire.verifInscription(ch_mdp1.getText(), ch_mdp2.getText(), ch_username.getText(), ch_email.getText()))
        {
            for( String i : Utilitaire.Erreur)
            {
                messageErreur =messageErreur+"\n"+ i;
            }
            //JOptionPane.showMessageDialog(null, messageErreur);
            label_erreur.setText(messageErreur);
        }
        else{
        AuthUser.getInstance();
        User user = new User();
        
        user.setAdresse(ch_adresse.getText());
        AuthUser.getInstance().setAdresse(user.getAdresse());
        
        user.setEmail(ch_email.getText());
        AuthUser.getInstance().setEmail(user.getEmail());
        
        user.setNom(ch_nom.getText());
        AuthUser.getInstance().setNom(user.getNom());
        
        user.setPrenom(ch_prenom.getText());
        AuthUser.getInstance().setPrenom(user.getPrenom());
        
        user.setUsername(ch_username.getText());
        AuthUser.getInstance().setUsername(user.getUsername());
        
        user.setPassword(ch_mdp1.getText());
        AuthUser.getInstance().setPassword(user.getPassword());
        
        UserService userv = new UserService();
        userv.inscrirUser(user);
        userv.authenticateUser(ch_username.getText());
        Parent root = FXMLLoader.load(getClass().getResource("AjoutFXML.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Login");
            s.setScene(scene);
            s.show();
            Stage stage = (Stage) label_login_erreur.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void seConnecterAction(ActionEvent event) throws IOException{
        String login = ch_login_username.getText();
        String pwd =  ch_login_password.getText();
        if(UserService.checkLoginUser(login, pwd))
        {
            //AuthUser.getInstance();
            UserService.authenticateUser(login);
            System.out.println("Authentification du user");
            Parent root = FXMLLoader.load(getClass().getResource("AjoutFXML.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Login");
            s.setScene(scene);
            s.show();
            Stage stage = (Stage) label_login_erreur.getScene().getWindow();
            stage.close();
        }
        else
            label_login_erreur.setText("Données invalides");
    }
    
}
