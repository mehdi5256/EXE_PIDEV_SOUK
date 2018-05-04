/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.Entity.Categorie;
import com.mycompany.Entity.Produit;
import com.mycompany.Service.Service_Categorie;
import com.mycompany.Service.Service_Produit;
import com.mycompany.myapp.MyApplication;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mehdi
 */
public class gui_Produit {
     Form f;
    TextField nomProduit;
    TextArea description;
    Button upload; 
    ComboBox<Categorie> cbCat ; 
    String image;
    Produit mainProd;
    Categorie cbCategory;
    String Filenom;
    Image img = null;
    Image toupload = null;
    Image profilePic = null;
    Label profile = null;

    ImageViewer imageContainer;
    
    TextField prix; 
    
    Button btnajout,btnaff;

     public gui_Produit() {
//         
//          ArrayList<Categorie> categories = new ArrayList<>();
//       
//        categories = (ArrayList<Categorie>) cs.getList2();
//        ArrayList<String> choices = new ArrayList<>();
//        
//        for(Categorie c : categories){
//            choices.add(c.getNomCategorie());
//        }
//        ObservableList<String> c = FXCollections.observableArrayList(choices);
//                
         


        Filenom = null;
        
      
        f = new Form("Annonce d'un produit", BoxLayout.y());
        nomProduit = new TextField("", "Nom du produit", 20, 0);
        description= new TextField("", "Description", 60, 20);
        upload= new Button("Upload image");
        imageContainer = new ImageViewer();
        imageContainer.setWidth(f.getWidth());
        imageContainer.setHeight(320);
        prix = new TextField("", "prix du produit", 80, 60);
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage"); 
        
        ComboBox<String> cb = new ComboBox<>("Categorie", "Soin", "Nouriture", "Vétement");
        
        cbCategory = new Categorie();
        
        
       
        
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(cb.getSelectedItem() == "Soin"){
                    cbCategory.setId(8);
                    cbCategory.setNomCategorie("Soin");
                }
                if(cb.getSelectedItem() == "Nouriture"){
                    cbCategory.setId(1077);
                    cbCategory.setNomCategorie("Nourriture");
                }
                if(cb.getSelectedItem() == "Vétement"){
                    cbCategory.setId(1057);
                    cbCategory.setNomCategorie("Vetements");
                }
                if(cb.getSelectedItem() == "Categorie"){
                   cbCategory = new Categorie();
                }
            }
        });
          
          


        
        f.add(nomProduit);
        f.add(description); 
        f.add(upload);
        f.add(imageContainer);
        f.add(prix);
        f.add(cb);
        f.add(btnajout);
//        f.add(btnaff);
       
        
        
        
        
        
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if (evt != null && evt.getSource() != null) {
                   
                       
                        
                    try {
                        String fileNameInServer = "";
                        MultipartRequest cr = new MultipartRequest();
                        String filePath = Capture.capturePhoto(-1, -1);
                        System.out.println(filePath);
                        String filenom="/images/file.jpg";
                        System.out.println(filenom);
                        cr.setUrl("http://localhost/web/uploadImage.php");
                        cr.setPost(true);
                        String mime = "image/jpeg";
                        cr.addData("file", filePath, mime);
                         String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                         cr.setFilename("file", out + ".jpg");
                        
                        fileNameInServer += out + ".jpg";
                        Filenom=fileNameInServer;
                        System.err.println("path2 =" + fileNameInServer.toString());
                        InfiniteProgress prog = new InfiniteProgress();
                        Dialog dlg = prog.showInifiniteBlocking();
                        cr.setDisposeOnCompletion(dlg);
                        NetworkManager.getInstance().addToQueueAndWait(cr);
                        //  try {
                        
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        toupload = img;
                        imageContainer.setImage(img);
                        
                        //} catch (IOException e) {
                        //  }
                    } catch (IOException ex) {
                    }
                        
                    
                    
                }
            }
            });


          
        
        btnajout.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             if(nomProduit.getText().toString().equals("") || nomProduit.getText().toString() == null){
             Dialog.show("Cher client","Veuillez ajouter un nom du produit", "ok", null);
             }else if(description.getText().toString().equals("") || description.getText().toString() == null){
             Dialog.show("Cher client","Veuillez ajouter une description du produit", "ok", null);
             }else if(Filenom == null){
             Dialog.show("Cher client","Veuillez ajouter une image du produit", "ok", null);
             }else if(prix.getText().toString() == null || prix.getText().toString() == ""){
                
             Dialog.show("Cher client","Veuillez ajouter un prix du produit", "ok", null);
             }else if(prix.getText().toString() != null && prix.getText().toString() != "" && (Float.parseFloat(prix.getText().toString())) <= 0){
             Dialog.show("Cher client","Veuillez ajouter un prix correct", "ok", null);
             }else if(cb.getSelectedItem()=="Categorie"){
             Dialog.show("Cher client","Veuillez ajouter une catégorie", "ok", null);
             }else{
                 mainProd = new Produit(nomProduit.getText().toString(), description.getText(), "http://localhost/externe/web/uploads/images/" + Filenom, cbCategory, Float.parseFloat(prix.getText().toString()));
             Service_Produit serviceProduit = new Service_Produit();
             serviceProduit.ajoutProduit(mainProd);
            Dialog.show("Cher client","Produit ajouté avec succés", "ok", null);
            AffichageProduit aff = new AffichageProduit();
            aff.getF().show();

             }
             
         }
     });
//            

        
        btnaff.addActionListener((e)->{AffichageProduit a=new AffichageProduit();
        a.getF().show();
        });
        
        f.show();
        
        
         f.getToolbar().addCommandToLeftBar("Retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {AffichageProduit h=new AffichageProduit();
          h.getF().show();
                   
                
            }
        });  
        
     }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
public TextField getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(TextField tnom) {
        this.nomProduit = tnom;
    }
   
    
    
}
