/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Mehdi
 */
public class Accueil extends Form{
    CheckBox admin = new CheckBox("Compte administrateur");
    CheckBox client = new CheckBox("Compte client");
     boolean created = false;
    static int x;
    
     public Accueil(Resources theme) {
        setTitle("Welcome to Rs-Randonnee");
        ImageViewer img = new ImageViewer(theme.getImage("imagesCAAELRTE.jpg"));

       add(admin);
        add(client);
         add(img);
    
    
    
    }
     
}
