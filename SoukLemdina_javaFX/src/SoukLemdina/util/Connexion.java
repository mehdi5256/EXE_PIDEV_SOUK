/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.util;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mehdi
 */
public class Connexion {
    public static Connexion conn;
        String url = "jdbc:mysql://localhost:3306/symfonysouk" ;
        String login ="root";
        String pw1="";
        public Connection  cnx;
        
       
        
    public Connexion(){
            try {
                this.cnx=DriverManager.getConnection(url,login,pw1);
                System.out.println("connexion etablit");

        } catch (Exception e) {
        }
    }
    
    public static Connexion getInstance() 
    {
        if (conn==null)
                   conn = new Connexion();
                return conn;
    }
    
    public Connection getConnexion()
    {
       
        return this.cnx;
    }

    
    
}
