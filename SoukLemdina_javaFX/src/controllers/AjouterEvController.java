/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
//user.getId() -> AuthUser.getInstance().getId();
import SoukLemdina.Entities.AuthUser;
import template.Template;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import entities.User;
import entities.event;
import SoukLemdina.Serivces.MyServiceEvenement;// ervice.MyServiceEvenement;
//import service.MyServiceEvenement;
//import util.Authentification;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
//import java.util.Date;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
        
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

/**
 *
 * @author AHMED
 */
public class AjouterEvController implements Initializable  {
    

  
    @FXML
    private TextField adresse_ev;
    @FXML
    private TextField nom_ev;

    private  MyServiceEvenement msE=new MyServiceEvenement();
  
    private ToggleGroup toggle;
    @FXML
    private JFXDatePicker datedeb_ev;
    @FXML
    private JFXDatePicker datefin_ev;
  
    @FXML
    private JFXTextArea description_ev;

       private final AuthUser currentUser=AuthUser.getInstance();//Authentification.user;
    
    
     final FileChooser fileChooser = new FileChooser();
     private Desktop desktop = Desktop.getDesktop();
     private String file_image ;
    

     
    private Path pathfrom;
    private Path pathto;
    private File Current_file;
    @FXML
    private ImageView image_p;
    private JFXButton fichier;
    @FXML
    private TextField lien;
   
    
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     

                          }
    
    private boolean testNumberInput(String a)
    {
        boolean b=false;
        if(a.matches("^[0-9]*"))
        {
            b=true;
        }
        return b;
    }

    
    private boolean NoDate(){
     boolean  test=false;
      System.out.println(ChronoUnit.DAYS.between(this.datedeb_ev.getValue(), this.datefin_ev.getValue()));  
      
         int a=(int) ChronoUnit.DAYS.between(this.datedeb_ev.getValue(), this.datefin_ev.getValue());
         int b=(int) ChronoUnit.DAYS.between(LocalDate.now(), this.datedeb_ev.getValue());
        System.out.println("aaaaaaaaaa"+b);
    if (a<0  || b<0)
    {
  
        test=true;
        
    }
    return test;
    }
    
    
    
   
    
    
    @FXML
    private void addButton(ActionEvent event) throws IOException {
        event ev=new event();
        ev.setNom(nom_ev.getText());
        ev.setLieu(adresse_ev.getText());
// 
      ev.setDescription(description_ev.getText());
ev.setLien(lien.getText());
   Date dateDeb = Date.valueOf(datedeb_ev.getValue());
   Date date_fin = Date.valueOf(datefin_ev.getValue());
        System.out.println(date_fin);
   ev.setDateDeb(dateDeb);
     ev.setDate_fin(date_fin);

//ev.setNom_organisateur(currentUser.getUsername());
// ev.setIdu(currentUser.getId());
   
   file_image="/images/"+file_image;
   ev.setImage(file_image);
        
        
            pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
            pathto = FileSystems.getDefault().getPath("C:\\"+Current_file.getName());
            Path targetDir = FileSystems.getDefault().getPath("C:\\wamp\\www\\externe\\web\\");
        System.out.println(targetDir);
                   // Files.copy(pathfrom, pathto,StandardCopyOption.REPLACE_EXISTING);

   /************ is empty verify***************/

   if(nom_ev.getText().length()==0){
    nom_ev.setStyle("-fx-text-inner-color: red");
            nom_ev.setStyle("-fx-prompt-text-fill: red");
            nom_ev.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            
            alert.setTitle("Attention");
            alert.setContentText("Veuillez nommer votre evénement!");
            alert.showAndWait();
            nom_ev.setCursor(Cursor.WAIT);
            nom_ev.setStyle("-fx-text-inner-color:  #663399");
            nom_ev.setStyle("-fx-prompt-text-fill:  #663399");

   
   }
   else if(adresse_ev.getText().length()==0){
   
  
    adresse_ev.setStyle("-fx-text-inner-color: red");
            adresse_ev.setStyle("-fx-prompt-text-fill: red");
            adresse_ev.setStyle("-fx-border-color: red");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir l'adresse de l'event!");
            alert.showAndWait();
            adresse_ev.setCursor(Cursor.WAIT);
            adresse_ev.setStyle("-fx-text-inner-color:  #663399");
            adresse_ev.setStyle("-fx-prompt-text-fill:  #663399");

   
   }
      else if(description_ev.getText().length()==0){
   
  
    description_ev.setStyle("-fx-text-inner-color: blue");
            description_ev.setStyle("-fx-prompt-text-fill: blue");
            description_ev.setStyle("-fx-border-color: blue");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
         
            
            alert.setTitle("Attention");
            alert.setContentText("Veuillez saisir la description de l'event!");
            alert.showAndWait();
            description_ev.setCursor(Cursor.WAIT);
            description_ev.setStyle("-fx-text-inner-color:  #663399");
            description_ev.setStyle("-fx-prompt-text-fill:  #663399");

   
    }
   
         else if (NoDate()){
     Alert alert =new Alert(Alert.AlertType.WARNING, " Date début doit étre supérieur à date fin", ButtonType.CLOSE);
       alert.showAndWait();
  
       notif(event);
    } 
      else
    { 
     
       msE.insertionEv(ev);
         Notifications n =Notifications.create().title("Notification")
             .text("Ajouter efféctué avec succés")
             .graphic(null)
             .position(Pos.BASELINE_LEFT)
             .onAction(new  EventHandler<ActionEvent>() {
                 
             public void handle (ActionEvent event){
                 System.out.println("notifocation");
             }
             });
     n.showConfirm();
     
//        template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getClassLoader().get  Resource("/GUI/MyMenuFXML.fxml"))));             

}   
    }

    /****************************/
   
    /***********************/
    
    private void notif(ActionEvent event) {
     Notifications n =Notifications.create().title("")
             .text("Date début doit étre supérieur à date fin ou date début sépérieur au date d'aujourd'hui")
             .graphic(null)
             .position(Pos.CENTER)
             .onAction(new  EventHandler<ActionEvent>() {
             
             public void handle (ActionEvent event){
                 System.out.println("clicked on notification");
             }
             });
     n.showWarning();
        
    }

    
   private void fichier_image(ActionEvent event) throws MalformedURLException {
        
Current_file = fileChooser.showOpenDialog(Template.getInstance().getStage());
                    if (Current_file != null) {
//                        openFile(file);
                        System.out.println(Current_file.toURI().toURL().toExternalForm());
                     file_image= Current_file.getName();
                    }
        
         
    Image image2 = new Image(Current_file.toURI().toString());
             
image_p.setImage(image2);
          
                    
                    
        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(fichier, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));        
    }

   
   
   
   
    @FXML
    private void imageDragOver(DragEvent event) {
        
            Dragboard board = event.getDragboard();
      if (board.hasFiles()) {
        event.acceptTransferModes(TransferMode.ANY);
      }
    }

    
    
    @FXML
    private void imageDropped(DragEvent event) throws FileNotFoundException {
        
            Dragboard board = event.getDragboard();
        List<File> phil = board.getFiles();
        FileInputStream fis;
          fis = new FileInputStream(phil.get(0));
        Image image = new Image(fis);
      File selectedFile = phil.get(0);
        if (selectedFile != null) {
           
         String test = selectedFile.getAbsolutePath();
            System.out.println(test);
            
            Current_file=selectedFile.getAbsoluteFile();
            file_image=Current_file.getName();
           event e= new event();
           e.setImage(selectedFile.getName());
          image_p.setImage(image);
          
          
          
    }

}

    @FXML
    private void checkWrittenNumber(KeyEvent event) {
    }

    @FXML
    private void change(ActionEvent event) {
    }
}

    
    
    
    
    
    
    
    
    
    
    

                                       