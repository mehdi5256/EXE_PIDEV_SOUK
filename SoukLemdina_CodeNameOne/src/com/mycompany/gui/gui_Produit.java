/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Categorie;
import com.mycompany.Entity.Produit;
import com.mycompany.Service.Service_Categorie;
import com.mycompany.Service.Service_Produit;
import java.io.IOException;

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
    
    TextField prix; 
    
    Button btnajout,btnaff;

     public gui_Produit() {
        f = new Form("home");
        nomProduit = new TextField("", "Nom du produit", 20, 0);
        description= new TextField("", "Description", 60, 20);
        upload= new Button("Upload image");
        cbCat= new ComboBox<Categorie>("Choisir catégorie");
        prix = new TextField("", "prix du produit", 80, 60);
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage"); 
        String image;
        

        
        f.add(nomProduit);
        f.add(description); 
        f.add(upload);
       f.add(cbCat);
        f.add(prix);
        f.add(btnajout);
        f.add(btnaff);
        
//        btnajout.addActionListener((e) -> {
//            Service_Produit ser = new Service_Produit();
//            Produit t = new Produit(nomProduit.getText(),description.getText());
//            ser.ajoutProduit(t);
//            

//        });
        btnaff.addActionListener((e)->{AffichageProduit a=new AffichageProduit();
        a.getF().show();
        });
//        upload.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                Display.getInstance().openGallery(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent ev) {
//                        if (ev != null && ev.getSource() != null) {
//                            String filePath = (String) ev.getSource();
//                            int fileNameIndex = filePath.lastIndexOf("/") + 1;
//                            String fileName = filePath.substring(fileNameIndex);
//                            System.out.println("filepath: " + filePath);
//                            System.out.println("fileName: " + fileName);
//                            image = fileName;
////                            image2 = fileName;
//                            MultipartRequest req = new MultipartRequest();
//                            req.setUrl("http://localhost/mobile/filepath.php?imagename="
//                                    + image);
//                            try {
//                                req.addData("file", filePath, "image/jpeg");
//                            } catch (IOException ex) {
//                                System.out.println("Error");
//                            }
//                            req.addResponseListener((NetworkEvent ev1) -> {
//                                String res = new String(req.getResponseData());
//                                if (res.equals("ok")) {
//                                    Dialog.show("", "Sauvgarde effectue", "ok", null);
//                                } else {
//                                    Dialog.show("", "Erreur", "ok", null);
//                                }
//                            });
//                            NetworkManager.getInstance().addToQueue(req);
//                        }
//                    }
//                }, Display.GALLERY_IMAGE);
//            }
//        });
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
