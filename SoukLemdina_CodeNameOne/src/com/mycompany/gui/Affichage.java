/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.mycompany.Service.Service_Categorie;

/**
 *
 * @author Mehdi
 */
public class Affichage {
    
     Form f;
    SpanLabel lb;
  
    public Affichage() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        Service_Categorie serviceTask=new Service_Categorie();
        lb.setText(serviceTask.getList2().toString());
         f.getToolbar().addCommandToRightBar("back", null, (ev)->{gui_Categorie h=new gui_Categorie();
          h.getF().show();
          });
         
    }
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
