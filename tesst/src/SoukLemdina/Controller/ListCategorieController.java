/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Controller;

import SoukLemdina.Entities.Category;
import SoukLemdina.Serivces.CategoryService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ListCategorieController implements Initializable {

    @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, String> id;
    @FXML
    private TableColumn<Category, String> nomcategorie;
    @FXML
    private JFXButton suppromerBT;
    @FXML
    private JFXButton AjouterBT1;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXTextField textfieldupdate;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       setCategoryTable();
    }
        // TODO

    @FXML
    private void supprimerAnimal(ActionEvent event) {
        try {
            int id = table.getSelectionModel().getSelectedItem().getId();
            CategoryService a = new CategoryService();
            a.delete(id);
           JOptionPane.showMessageDialog(null, "Categorie Supprimée avec succés");
           Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListCategorie.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.table.getScene().getWindow();
            x.setTitle("Afficher Animal");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            Logger.getLogger(ListCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/Accueil2.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
//            s.initStyle(StageStyle.TRANSPARENT);
            s.setTitle("Afficher Produit");
            s.setScene(scene);
            s.show();
    }

    @FXML
    private void ajoutcategorie(ActionEvent event) throws IOException {
        Stage primaryStage= new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/AjoutFXML.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.show();
//        Stage stage = (Stage) AjouterBT1.getScene().getWindow();
//        
//         stage.close();
    }
   
    private void setCategoryTable(){
CategoryService service = new CategoryService();
        ObservableList<Category> list = FXCollections.observableArrayList(service.afficher());
        table.setItems(list);       
       id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() 
       {
           
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getId());
            }
        });
       
       nomcategorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomCategory());
            }
        });
}


    @FXML
    private void UpdateAction(ActionEvent event) throws IOException {
                Category produit = table.getSelectionModel().getSelectedItem();

        CategoryService ps = new CategoryService() ;
       produit.setNomCategory(textfieldupdate.getText());
        ps.update(produit);
        setCategoryTable();
    }

    @FXML
    private void UpdateTable(MouseEvent event) {
        Category produit = table.getSelectionModel().getSelectedItem();
        textfieldupdate.setText(produit.getNomCategory());
    }
    

    }



   

    
    
