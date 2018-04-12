/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.prism.impl.Disposer.Record;
import entities.event;
import SoukLemdina.Serivces.MyServiceEvenement;//service.MyServiceEvenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;

/**
 *
 * @author AHMED
 */
public class afficheController implements Initializable{
    
    MyServiceEvenement service_pr=new MyServiceEvenement();
    
    
    private TableView table = new TableView();
    
    private ListView<event> allproducts_LV;
    
    @FXML
    private TableView<event> table_view;
    
    private TableColumn<event, String> nom_col;
    
    private TableColumn<event, String> stock_col;
    
    
    @FXML
    private Button supp;
    @FXML
    private TableColumn<event, String> nom_ev;
    @FXML
    private TableColumn<event, String> adresse_ev;
    @FXML
    private TableColumn<event, String> genre_ev;
    @FXML
    private TableColumn<event, String> description;
    @FXML
    private TableColumn<event, Date> datedeb;
    @FXML
    private TableColumn<event   ,Image> image;
    

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         ObservableList<event> list= FXCollections.observableArrayList();
        for (event p:service_pr.afficheEv())
        {
            list.add(p);
            
        }
      
   nom_ev.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
   adresse_ev.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       

   description.setCellValueFactory(new PropertyValueFactory<>("description"));



   datedeb.setCellValueFactory(new PropertyValueFactory<>("datedeb"));
      
  


    
  TableColumn col_action = new TableColumn<>("supprimer");
        table_view.getColumns().add(col_action);
        
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
    
                
  TableColumn col_modifier = new TableColumn<>("modifier");
        table_view.getColumns().add(col_modifier);
        
        col_modifier.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_modifier.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell2();
            }  
        });
        
        table_view.setItems(list);  
    }

    
    private void retourner(ActionEvent event) throws IOException {
      
            template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/AjouterEvFXML.fxml"))));             
    }
}
