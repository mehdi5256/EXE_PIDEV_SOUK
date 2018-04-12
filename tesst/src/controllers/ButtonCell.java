/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import template.Template;
import com.sun.prism.impl.Disposer;
import entities.event;
import SoukLemdina.Serivces.MyServiceEvenement;//service.MyServiceEvenement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author AHMED
 */
class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Delete");
         MyServiceEvenement service_pr=new MyServiceEvenement();
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    try {
                        // get Selected Item
                        event Produitcourant = (event) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        //remove selected item from the table list
                        ObservableList<event> list= FXCollections.observableArrayList();
                        for (event p:service_pr.afficheEv())
                        {
                            list.add(p);
                            
                        }
                        System.out.println(Produitcourant);
                        list.remove(Produitcourant);
                        
                        service_pr.deleteEv(Produitcourant,1);
                        System.out.println(list);
        template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/afficheFXML.fxml"))));             
                    } catch (IOException ex) {
                        Logger.getLogger(ButtonCell.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonCell.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
