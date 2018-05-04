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
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.MyApplication;
import com.sun.mail.smtp.SMTPTransport;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mehdi
 */
public class AffichageProduit extends MyApplication{
     Form f;
     Form f2;
    SpanLabel lb;
    MyApplication mp  = new MyApplication();
  
    public AffichageProduit() {
        
         f=(Form) new Form("Produit Souk Lemdina",new BoxLayout(BoxLayout.Y_AXIS));
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
            
            lab2.setText("Afficher la suite");
            c2.add(lab);
            c2.add(lab1);
            c2.add(lab2);
            c1.add(m);
            
            lab2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
             f2=(Form) new Form("Produit Souk Lemdina" ,new BoxLayout(BoxLayout.Y_AXIS));
                   
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
              Button mail = new Button();

             l3.setText("Description: "+ a.getDescription());
            // int ida = a.getId();
            
             l1.setText("          "+"***** SoukLemdina *****");
//             description.setText("Description: "+ a.getDescription());  
             prix.setText("Prix:" +a.getPrix() + "DT");
             mail.setText("Contacter le vendeur");


             
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
            f2.add(mail);
           
            f2.show();
            
            mail.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {
                         try {
                 
                Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");
                Session session = Session.getInstance(props, null);
                
                MimeMessage msg = new MimeMessage(session);
                
                msg.setFrom(new InternetAddress("Souk Lemdina <my_email@myDomain.com>"));
                msg.setRecipients(Message.RecipientType.TO, "mehdidrira2@gmail.com");
                msg.setSubject( l2.getText() );
                msg.setSentDate(new Date(System.currentTimeMillis()));
                
           
                
                msg.setText("Une demande d'acheter le produit " + l2.getText());
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com","mehdi.drira@esprit.tn","aA09625531");
                st.sendMessage(msg, msg.getAllRecipients());
                System.out.println("ServerResponse : " + st.getLastServerResponse());
                     Dialog.show("Mail envoyé avec succés", "Le propriaitaire du produit va reçevoir un mail","OK",null);
         
          
          
            } catch (MessagingException ex) {
            
            }
                 }
             }              
);
            
            
          
          f2.getToolbar().addCommandToOverflowMenu("Ajout produit", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {gui_Produit h=new gui_Produit();
          h.getF().show();
                   
                
            }
        }); 
           f2.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           f.showBack();
                   
                
            }
        }); 

                }
                
                

            });
             c.add(c1);
        c.add(c2);
             f.add(c);
         
             
           
             

          }
         f.getToolbar().addCommandToOverflowMenu("Ajouter un produit", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {gui_Produit h=new gui_Produit();
          h.getF().show();
                   
                
            }
        });  
          f.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) { 
                mp.getF().show();
          
                   
                
            }
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
