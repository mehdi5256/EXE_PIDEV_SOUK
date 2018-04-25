/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.Entity.Categorie;
import com.mycompany.Service.Service_Categorie;

/**
 *
 * @author Mehdi
 */
public class gui_Categorie {
    Form f;
    TextField nomCategorie;
    Button btnajout,btnaff;

     public gui_Categorie() {
        f = new Form("home");
        nomCategorie = new TextField();
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        f.add(nomCategorie);
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            Service_Categorie ser = new Service_Categorie();
            Categorie t = new Categorie(0, nomCategorie.getText());
            ser.ajoutTask(t);
            

        });
        btnaff.addActionListener((e)->{Affichage a=new Affichage();
        a.getF().show();
        });
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
public TextField getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(TextField tnom) {
        this.nomCategorie = tnom;
    }
   
    
    
}
