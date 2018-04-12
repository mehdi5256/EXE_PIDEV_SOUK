/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Controller;

import SoukLemdina.Entities.Category;
import SoukLemdina.Entities.Produit;
import SoukLemdina.Serivces.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class UpdateProduitController implements Initializable {

    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField nomProduit;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton uploadimage;
    @FXML
    private ImageView photoproduit;
    @FXML
    private JFXTextField prix;
    @FXML
    private ChoiceBox<Category> cbCategorie;
    @FXML
    private JFXButton btnfindbyid;
    @FXML
    private JFXButton Retour;
    @FXML
    private JFXTextField idproduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void linkImage(ActionEvent event) {
    }

    @FXML
    private void FindProduitById(ActionEvent event) {
        String pid = idproduit.getText();
        int id = Integer.parseInt(pid);
        Produit produit = ProduitService.findProduitById(id);
//        ProduitService ps = new ProduitService();
//        ps.findProduitById(id);
        nomProduit.setText(produit.getNomProduit());     
        description.setText(produit.getDescription());    
//       photoproduit.setText(String.valueOf(produit.getImage())); 
//         byte[] img = produit.getBytes();
        prix.setText(String.valueOf(produit.getPrix()));
 }

    @FXML
    private void Retour(ActionEvent event) {
    }

    @FXML
    private void UpdateProduit(ActionEvent event) {
        ProduitService ps = new ProduitService();
        String sid = idproduit.getText();
        int id = Integer.parseInt(sid);
        String nomp = nomProduit.getText();
        String desc = description.getText();
        Float prixp = Float.parseFloat(prix.getText());
        Produit p = new Produit();
        p.setId(id);
        p.setNomProduit(nomp);
        p.setDescription(desc);
        p.setPrix(prixp);
        ps.ModifierProduit(p);
        
        
        
//
//        ProduitService ps = new ProduitService();
//        Produit newprdouit = new  Produit(nomProduit.getText(), description.getText(), photoproduit.getTypeSelector(), Integer.parseInt(prix.getText()), cbCategorie.getValue());
//        ps.ModifierProduit(newprdouit);
    }
    
}
