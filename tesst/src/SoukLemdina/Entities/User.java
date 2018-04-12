/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import java.util.Date;

/**
 *
 * @author Amine
 */
public class User {
   private int id;
   private String username;
   private String email;
   private String password;
   private Date last_login; 
   private String role;
   private String nom;
   private String prenom;
   private int age;
   private String adresse;

   public User(int id, String username, String email, String password, Date last_login, String role, String nom, String prenom, int age, String adresse)
   {
       this.id=id;
       this.username=username;
       this.email=email;
       this.password=password;
       this.last_login=last_login;
       this.role=role;
       this.nom=nom;
       this.prenom=prenom;
       this.age=age;
       this.adresse =adresse;
   }
   
   public User()
   {
//       this.id=0;
//       this.adresse="Cedria";
//       this.age=22;
//       this.email="tester@yahoo.com";
//       this.nom="Saidi";
//       this.prenom="Amine";
//       this.role="ADMIN";
//       this.username="JavaTester";
//       this.password="test";
       //this.last_login=System.
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
   
}
