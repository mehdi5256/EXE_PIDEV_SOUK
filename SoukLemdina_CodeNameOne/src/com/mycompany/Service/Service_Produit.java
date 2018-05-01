/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Categorie;
import com.mycompany.Entity.Produit;
import com.sun.javafx.image.impl.IntArgb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mehdi
 */
public class Service_Produit {
    
    public void ajoutProduit(Produit pr) {
         ConnectionRequest con = new ConnectionRequest();
        String Url = " http://localhost/externe/web/app_dev.php/mehdi/newp?name=" + pr.getNomProduit() + "&"+pr.getDescription()+"&"+pr.getImage()+"&"+pr.getCategorie().getId()+"&"+pr.getPrix();
        con.setUrl(Url);

       con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
         NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Produit> getListTask(String json) {

        ArrayList<Produit> listProduit = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Produit e = new Produit();
              Categorie c = new Categorie();
              c.setNomCategorie(obj.get("nomCategorie").toString());

                // System.out.println(obj.get("id"));
//                float id = Float.parseFloat(obj.get("id").toString());
//                System.out.println(id);
//                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNomProduit(obj.get("nomProduit").toString());
                e.setDescription(obj.get("description").toString());
                e.setImage(obj.get("image").toString()); 
//             e.categorie.setNomCategorie(obj.get("nomCategorie").toString());
//           e.setCategorie(c.getNomCategorie(obj.get("nomCategorie").toString()));
//                e.setCategorie(c.getNomCategorie(obj.get("nomCategoire").toString()));
                e.setCategorie(c);
                
               e.setPrix(Float.parseFloat(obj.get("prix").toString()));
                
//                e.setNom(obj.get("name").toString());

               System.out.println(e);
         
   

                listProduit.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduit);
        return listProduit;

    }
    
    
    ArrayList<Produit> listProduit = new ArrayList<>();
    
    public ArrayList<Produit> getListProduit(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/externe/web/app_dev.php/mehdi/allp");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Service_Produit ser = new Service_Produit();
                listProduit = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }
}
