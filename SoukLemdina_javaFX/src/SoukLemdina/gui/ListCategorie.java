/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.gui;

import SoukLemdina.Entities.Category;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mehdi
 */
public class ListCategorie extends Application {

    
    @Override
    public void start(Stage primaryStage) throws IOException {
               FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Category.class.getResource("ListCategorie.fxml"));

        StackPane root = new StackPane();
        
//        Scene scene = new Scene(root, 300, 250);
        Scene scene= new Scene(FXMLLoader.load(getClass().getResource("/SoukLemdina/gui/ListProduit.fxml")));


        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
