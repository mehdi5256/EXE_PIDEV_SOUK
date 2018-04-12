/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesst;

import SoukLemdina.Entities.Category;
import SoukLemdina.Entities.Produit;
import SoukLemdina.Entities.User;
import SoukLemdina.Serivces.CategoryService;
import SoukLemdina.Serivces.ProduitService;
import SoukLemdina.Serivces.UserService;
import SoukLemdina.util.Connexion;
import java.sql.Connection;

/**
 *
 * @author Mehdi
 */
public class Tesst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      CategoryService sp = new CategoryService();
//        Category personne1 = new Category(); 
//        Category personne2 = new Category(); 
//        
//        personne1.setId(1002);
//        personne1.setNomCategory("update");
//        
//        personne2.setId(1003);
//        personne2.setNomCategory("bbb");
//         sp.delete(1002);               
//      sp.afficher();
//      sp.update(personne1);
        

        //User user = new User();
//          UserService userv = new UserService();
//        //userv.inscrirUser(user);
//        
//          userv.loginUser("JavaTester","test");
//                    ProduitService ps = new ProduitService();
//
//          Produit p ;
//           p = new Produit(129, "dd", "ee", "/a/a", 0, 0, 0, 0, 0);
//ps.AjouterProduit(p);
//                        CategoryService xx = new   CategoryService();
//                        Category p = new Category("ttr");
//                        xx.ajouter(p);
//    ProduitService aa = new ProduitService();
//          Produit x = new Produit("eee", "scription", "image", 8, 8);
//         aa.AjouterProduit(x);
//         aa.AfficherProduit();
//ProduitService aa = new ProduitService();
//aa.findProduitById(143);
//      Produit p = new Produit(129, "dd", "ee", "/a/a", 8, 0, 0, 0, 0);
//
//aa.ModifierProduit(p);
CategoryService ps = new CategoryService();
        Category c = new Category(8,"moez");
ps.update(c);
        Category z = new Category(8, "zzzz");
        ps.update(c);
        


                        
                  
    }
    
}
