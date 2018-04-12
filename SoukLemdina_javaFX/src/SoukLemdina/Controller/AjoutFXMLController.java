/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Controller;

import SoukLemdina.Entities.Category;
import SoukLemdina.Serivces.CategoryService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class AjoutFXMLController implements Initializable {

    @FXML
    private TextField nomCategorie;
    @FXML
    private Button ajouter;
    CategoryService e= new CategoryService();
    private TableColumn<Category, String> id;
    private TableColumn<Category, String> nomcategorie;
    private TableView<Category> table;
    @FXML
    private JFXButton retour;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        CategoryService service = new CategoryService();
//        ObservableList<Category> list = FXCollections.observableArrayList(service.afficher());
//        table.setItems(list);       
//       id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() 
//       {
//           
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
//                return new ReadOnlyObjectWrapper(param.getValue().getId());
//            }
//        });
//       
//       nomcategorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
//                return new ReadOnlyObjectWrapper(param.getValue().getNomCategory());
//            }
//        });

        

        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        CategoryService e= new CategoryService();
        Category c = new Category();
        c.nomCategory=nomCategorie.getText();
        e.ajouter(c);
         JOptionPane.showMessageDialog(null, "Categorie ajouté avec succés");

        Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListCategorie.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
//            s.initStyle(StageStyle.TRANSPARENT);
            
            s.setScene(scene);
            s.show();
        
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListCategorie.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.show();
        Stage stage = (Stage) ajouter.getScene().getWindow();
         stage.close();
    }

    @FXML
    private void espaceProduitAction(MouseEvent event) {
    }

    @FXML
    private void espaceBoutiqueAction(MouseEvent event) {
         System.out.println("Essai de redirection");
            try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SoukLemdina/gui/GestionBoutiquesFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
            Stage stage = (Stage) ajouter.getScene().getWindow();
            stage.close();
    }

    @FXML
    private void espaceEvntClick(MouseEvent event) {
        System.out.println("Essai de redirection");
            try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/MyMenuFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
            Stage stage = (Stage) ajouter.getScene().getWindow();
            stage.close();
    }
}

    

