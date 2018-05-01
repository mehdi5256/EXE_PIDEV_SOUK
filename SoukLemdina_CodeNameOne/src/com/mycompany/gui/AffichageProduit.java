/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Entity.Produit;
import com.mycompany.Service.Service_Categorie;
import com.mycompany.Service.Service_Produit;
import java.util.ArrayList;

/**
 *
 * @author Mehdi
 */
public class AffichageProduit {
     Form f;
     Form f2;
    SpanLabel lb;
  
    public AffichageProduit() {
        
         f=(Form) new Form("Affichage",new BoxLayout(BoxLayout.Y_AXIS));
//        lb = new SpanLabel("");
//        f.add(lb);
        Service_Produit serviceTask =new Service_Produit();
//        lb.setText(serviceTask.getListProduit().toString());
          ArrayList<Produit> listproduit = serviceTask.getListProduit();
          for(Produit a : listproduit)
          {
              
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 160), true);
 Image i = URLImage.createToStorage(placeholder,a.getImage() , a.getImage() , URLImage.RESIZE_SCALE);
 ImageViewer m = new ImageViewer(i);
            SpanLabel lab = new SpanLabel();
            Label lab1 = new Label();
              Button lab2 = new Button();
            
            lab.setText("Nom du produit: "+ a.getNomProduit());
            lab1.setText("prix: "+String.valueOf(a.getPrix())+ " DT");
            
            lab2.setText("Read more ...");
            c2.add(lab);
            c2.add(lab1);
            c2.add(lab2);
            c1.add(m);
            
            lab2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
             f2=(Form) new Form("Read more",new BoxLayout(BoxLayout.Y_AXIS));
                    
             EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(200, 150), true);
 Image i = URLImage.createToStorage(placeholder,a.getImage() , a.getImage() , URLImage.RESIZE_SCALE);
 ImageViewer m = new ImageViewer(i);
             SpanLabel l = new SpanLabel();
             Label l1 = new Label();
             Label description = new Label();
             Label prix = new Label();


             
             
             

             Label l2 = new Label();
             SpanLabel l3 = new SpanLabel();
              l2.setText(a.getNomProduit());
             l3.setText("Description: "+ a.getDescription());
            // int ida = a.getId();
            
             l1.setText("          "+"***** SoukLemdina *****");
             description.setText("Description: "+ a.getDescription());  
             prix.setText("Prix:" +a.getPrix() + "DT");

             
             //System.out.println(listcommentaires);
             
        
             
             
//            l.setText(a.getNomProduit());
            Container c =new Container(new FlowLayout(Component.RIGHT,Component.RIGHT));
            c.add(l2);
            f2.add(m);
            f2.add(c);
            f2.add(l1);
            f2.add(l);
            
            f2.add(l3);
            f2.add(description);
            f2.add(prix);
            f2.show();
                  f2.getToolbar().addCommandToRightBar("back", null, (ev)->{AffichageProduit h=new AffichageProduit();
          h.getF().show();
          });

                }
                

            });
             c.add(c1);
        c.add(c2);
             f.add(c);
         
             
           
             

          }
         f.getToolbar().addCommandToRightBar("back", null, (ev)->{gui_Produit h=new gui_Produit();
          h.getF().show();
          });
         
         
        
         
        
         
    }
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
     public Form getF2() {
        return f2;
    }

    public void setF2(Form f) {
        this.f2 = f;
    }
    
}
