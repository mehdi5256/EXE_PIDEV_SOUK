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
public class AuthUser {
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
   private Gerant gerant;
   private boolean isGerant=false;
   
   private AuthUser(){}
   
   private static AuthUser INSTANCE = null;
   
   public static AuthUser getInstance()
    {           
        if (INSTANCE == null)
        {
            synchronized(AuthUser.class)
            {
                if(INSTANCE==null)
                    INSTANCE = new AuthUser(); 
            }
        }
        return INSTANCE;
    }

    public boolean IsGerant() {
        return isGerant;
    }

    public void setGerant(boolean isGerant) {
        this.isGerant = isGerant;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
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
