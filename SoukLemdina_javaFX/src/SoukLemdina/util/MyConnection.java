/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author madyb
 */
public class MyConnection {
    
    
    private static Connection conn;
    private String url="jdbc:mysql://localhost:3306/symfonysouk";
    private String login="root";
    private String password="";
    private MyConnection()
    {
        
    }
    public Connection etablirConnection()
    {
        try {
            conn=DriverManager.getConnection(url,login,password);
            System.out.println("Connection etablie");
            
        }
        catch (SQLException ex){
            System.out.println("probleme d'etablissement de connection");
            
        }
        return conn;
    }
    public static Connection getInstance()
    {
        if (conn==null)
        {
            new MyConnection().etablirConnection();
            
            
        }
        return conn;
    }
    
}

