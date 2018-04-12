/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;





import entities.event;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author said
 */



class ButtonCell2 extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Update");

        ButtonCell2(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    try {
                        // get Selected Item
                        event Produitcourant = (event) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                        //remove selected item from the table list
                        
                        System.out.println(Produitcourant);
                        event.setId_courant(Produitcourant.getId());
                        
                        
        template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/UpdateEvFXML.fxml"))));             
                    } catch (IOException ex) {
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