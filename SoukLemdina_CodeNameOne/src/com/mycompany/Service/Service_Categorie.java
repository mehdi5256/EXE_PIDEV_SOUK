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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mehdi
 */
public class Service_Categorie {
    
     public void ajoutTask(Categorie ca) {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/externe/web/app_dev.php/mehdi/new?name=" + ca.getNomCategorie() ;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
         NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     public ArrayList<Categorie> getListTask(String json) {

        ArrayList<Categorie> listEtudiants = new ArrayList<>();
        

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Categorie e = new Categorie();

                // System.out.println(obj.get("id"));
//                float id = Float.parseFloat(obj.get("id").toString());
//                System.out.println(id);
//                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNomCategorie(obj.get("nomCategorie").toString());
//                e.setNom(obj.get("name").toString());
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
         System.out.println(listEtudiants);
        return listEtudiants;
        

    }
     
     ArrayList<Categorie> listCategorie = new ArrayList<>();
    
    public ArrayList<Categorie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/externe/web/app_dev.php/mehdi/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Service_Categorie ser = new Service_Categorie();
                listCategorie = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;
    }

    
    
}
