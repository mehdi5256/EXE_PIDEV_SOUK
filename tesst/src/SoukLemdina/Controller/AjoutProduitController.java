/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Controller;

import SoukLemdina.Entities.Category;
import SoukLemdina.Entities.Produit;
import SoukLemdina.Serivces.CategoryService;
import SoukLemdina.Serivces.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.awt.Choice;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class AjoutProduitController implements Initializable {
 private String path="";
        Stage mainStage;   
       

    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField nomProduit;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton uploadimage;
    @FXML
    private JFXTextField prix;
    @FXML
    private ChoiceBox<String> cbCategorie;
    @FXML
    private ImageView photoproduit;
    @FXML
    private Label photoname;
    @FXML
    private Label photopath;
    @FXML
    private JFXButton retour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Category> categories = new ArrayList<>();
       
        categories = (ArrayList<Category>) CategoryService.afficher();
        ArrayList<String> choices = new ArrayList<>();
        
        for(Category c : categories){
            choices.add(c.getNomCategory());
        }
        ObservableList<String> c = FXCollections.observableArrayList(choices);
       
        cbCategorie.setItems(c);
        cbCategorie.getSelectionModel().select(0);
        
        
      };
          

    @FXML
    private void AjouterProduit(ActionEvent event) throws IOException,MessagingException, javax.mail.MessagingException{
        Produit a ; 
        String categoryName = cbCategorie.getValue();
         ArrayList<Category> categories = new ArrayList<>();
         Category category = new Category();
         categories = (ArrayList<Category>) CategoryService.afficher();
         
         
         if(categoryName != null){
            for(Category c : categories){
             if(c.getNomCategory().equals(categoryName)){
                 category = c;
             }
            }
            
         }else{
             JOptionPane.showMessageDialog(null, "Veuillez seléctionner une catégorie!");
         }
         
         String nom, descriptionProduit, imageProduit, cat= new String();
         int prixProduit;
         nom =  nomProduit.getText();
         descriptionProduit = description.getText();
         imageProduit = path;
         prixProduit = Integer.parseInt(prix.getText());
         
         if(nom != "" && descriptionProduit != "" && imageProduit != ""){
              a= new Produit(nom, descriptionProduit, imageProduit, prixProduit, category);
              System.out.println(nom);
              System.out.println(descriptionProduit);
              System.out.println(imageProduit);
              System.out.println(prixProduit);
              System.out.println(category.toString());
              ProduitService e= new ProduitService();
              e.AjouterProduit(a);
              String mail="mehdidrira2@gmail.com";
            sendMail("mehdi.drira@esprit.tn","","Ajout De produit","votre produit a ete ajouté avec succès",mail);
              JOptionPane.showMessageDialog(null, "Produit ajouté avec succés !!! ");
               Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListProduit.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
//            s.initStyle(StageStyle.TRANSPARENT);
            
            s.setScene(scene);
            s.show();
         }else {
             JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs");
         }
        
                         
        
         
        
         
        
         
       
//        Produit c = new Produit();
//        c.nomProduit=nomProduit.getText();
//        c.description=description.getText();
//        c.image= photoproduit.getImage().toString();
//        c.prix=Float.valueOf(prix.getText()); 
//        System.out.println(c.getImage());
//        c.categorie_id=Integer.parseInt(categorie.getText());
//        
        
        
    }

    @FXML
    private void linkImage(ActionEvent event) throws MalformedURLException {
         FileChooser fileChooser = new FileChooser();
            
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
             
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
                      
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photoproduit.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            path=file.toURI().toString();
    }

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListProduit.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
//            s.initStyle(StageStyle.TRANSPARENT);
            s.setTitle("Afficher Produit");
            s.setScene(scene);
            s.show();
    }
    
     public void sendMail(String userMail,String pass,String sujet,String contenu, String send) throws MessagingException, AddressException, javax.mail.MessagingException{
    
        
        String to = send;
        String host = "smtp.gmail.com";
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put( "mail.smtp.host", host );
        prop.put("mail.smtp.user", userMail);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userMail));
         
        InternetAddress toAdresse = new InternetAddress(to);
                
        msg.setRecipient(Message.RecipientType.TO, toAdresse);
        msg.setSubject(sujet);
        msg.setContent(contenu,"text/html; charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, userMail, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    
          
        
        
    }
    
    }

          
    
    
    


