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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class ListProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> id;
    @FXML
    private TableColumn<Produit, String> nomproduit;
    @FXML
    private TableColumn<Produit, String> description;
    @FXML
    private TableColumn<Produit, String> image;
    @FXML
    private TableColumn<Produit, String> categorie;
    @FXML
    private TableColumn<Produit, String> prix;
    @FXML
    private JFXButton suppromerBT;
    private JFXButton AjouterBT1;
    @FXML
    private JFXButton AjouterBtn;
    @FXML
    private JFXButton RetourBtn;
    @FXML
    private JFXButton DisplayBtn;
    private JFXTextField nomProduit;
    private JFXTextField description1;
    @FXML
    private JFXButton uploadimage;
    String oldName ;

    private JFXTextField prix1;
    @FXML
    private ChoiceBox<String> cbCategorie;
    @FXML
    private Label photoname;
    @FXML
    private Label photopath;
    @FXML
    private ImageView imgVw;
    
    private String path="";
    @FXML
    private JFXTextField ftProduit;
    @FXML
    private JFXTextField ftDescription;
    @FXML
    private JFXTextField ftPrix;

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
    
//        RecupererDonnéeProduit();
    }    


    private void ajoutcategorie(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/AjoutProduit.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show(); 
        Stage stage = (Stage)  AjouterBT1.getScene().getWindow();
        
         stage.close();
        
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
        try {
            int id = table.getSelectionModel().getSelectedItem().getId();
            ProduitService a = new ProduitService();
            a.SupprimerProduit(id);
            Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListProduit.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.table.getScene().getWindow();
            x.setTitle("Afficher produit");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void UpdateProduit(ActionEvent event) throws IOException {
        
         Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/UpdateProduit.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show(); 
        Stage stage = (Stage)  AjouterBT1.getScene().getWindow();
        
         stage.close();
    }
    

    @FXML
    private void Display(ActionEvent event) {
            ProduitService service = new ProduitService();
        CategoryService categoryservice = new CategoryService();
        ObservableList<Produit> list = FXCollections.observableArrayList(service.AfficherProduit());
        table.setItems(list);
         id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getId());

              }
        });
         nomproduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProduit());

              }
        });
         description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());

              }
        });
         image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImage());

              }
        });
         categorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().categorie_id);

              }
        });
         
         prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().prix);

              }
        });
            
    }

    private void AjouterProduit(ActionEvent event) throws SQLException, MessagingException, javax.mail.MessagingException {
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
         imageProduit =  path;
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
              JOptionPane.showMessageDialog(null, "Categorie ajouté avec succés");
               
         }else {
             JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs");
         }
     }

    @FXML
    private void linkImage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
                     FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                     FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
                     FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                     fileChooser.getExtensionFilters().addAll(exjpg,exjpg2, expng);
                     fileChooser.setTitle("Choose an image File");

                     File file = fileChooser.showOpenDialog(null);
                     
                             if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                
                   Image img = new Image(file.toURI().toString() ); 
                                imgVw.setImage(img);
                                
                               
                                photopath.setText("C:\\wamp64\\www\\"+file.getName());
                                photoname.setText(file.getName());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choose another image");
            }


            }
    }
    
    private void buildTableviewData() throws ClassNotFoundException, SQLException {
              ProduitService service = new ProduitService();
        CategoryService categoryservice = new CategoryService();
        ObservableList<Produit> list = FXCollections.observableArrayList(service.AfficherProduit());
        table.setItems(list);
         id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getId());

              }
        });
         nomproduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProduit());

              }
        });
         description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());

              }
        });
         image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImage());

              }
        });
         categorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().categorie_id);

              }
        });
         
         prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().prix);

              }
        });
            
    }
    
    
     private void RecupererDonnéeProduit() {
        
                 table.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Produit> observable,
                Produit oldValue,
                Produit newValue) -> {
            if (newValue == null) {
              
                ftProduit.setText(null);
                ftDescription.setText(null);
                cbCategorie.setValue(null);
                
               
                ftPrix.setText(null);
               
                
                return;
            }
            ftProduit.setText(newValue.getNomProduit());
            cbCategorie.setValue(newValue.getCategory_idd().getNomCategory());
            
             
            // ref.setDisable(true);
        
          ftPrix.setText(String.valueOf(newValue.getPrix()));
            oldName= newValue.getImage();
              Image img = new Image("file:///C:/Users/Mehdi/Desktop/image/"+newValue.getImage()) ;
      System.out.println("file:///C:/Users/Mehdi/Desktop/image/"+newValue.getImage());
      imgVw.setImage(img);         
        });
    }

    @FXML
    private void AminAction(MouseEvent event) {
        
    Produit produit = table.getSelectionModel().getSelectedItem();
    ftProduit.setText(produit.getNomProduit());
    ftDescription.setText(produit.getDescription());
    ftPrix.setText(String.valueOf(produit.getPrix()));


 Image img = new Image(produit.getImage()) ;
      System.out.println("file:///C:/Users/Mehdi/Desktop/image/"+produit.getImage());
      imgVw.setImage(img);         
        

    

}

    @FXML
    private void UpdateProduitAction(ActionEvent event) {
    }

    @FXML
    private void AjouterProduitAction(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/AjoutProduit.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show(); 
        Stage stage = (Stage)  AjouterBtn.getScene().getWindow();
        
         stage.close();
    }
   
    
    
}



                 
   

    