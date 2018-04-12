/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.util;

import SoukLemdina.Serivces.UserService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine
 */
public class Utilitaire {
    public static List<String> Erreur = new ArrayList<String>();
    public static boolean verifMdp(String mdp1, String mdp2)
    {
        boolean val = true;
        if(!mdp1.equals(mdp2))
            val = false;
        return val;
    }
    
    public static boolean verifInscription(String mdp1, String mdp2, String username, String email)
    {
        //UserService userv = new UserService();
        Erreur.clear();
        boolean val1, val2, val3;
        val1 = verifMdp(mdp1,mdp2);
        if(val1==false)
            Erreur.add("Les deux mots de passes sont différents");
        val2 = UserService.checkUsername(username);
        if(val2==false)
            Erreur.add("Ce nom d'utilisateur existe déja");
        val3 = UserService.checkEmail(email);
        if(val3==false)
            Erreur.add("Cet email est déja utilisé");
        if(val1==true && val2==true && val3==true)
            return true;
        else return false;
    }
}
