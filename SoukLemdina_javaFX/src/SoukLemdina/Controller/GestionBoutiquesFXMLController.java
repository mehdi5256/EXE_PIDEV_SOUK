/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Controller;

import SoukLemdina.Entities.AuthUser;
import SoukLemdina.Entities.Boutique;
import SoukLemdina.Entities.Categorie;
import SoukLemdina.Entities.Commande;
import SoukLemdina.Entities.Produit;
import SoukLemdina.Entities.User;
import SoukLemdina.Serivces.OtherServices;
import com.sun.xml.internal.bind.AnyTypeAdapter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;



/**
 * FXML Controller class
 *
 * @author Amine
 */
public class GestionBoutiquesFXMLController implements Initializable {

    @FXML
    private AnchorPane pane_espace_boutique;
    @FXML
    private Button btn_gerer_ma_boutique;
    
    List<Boutique> list = OtherServices.selectAllBoutiques();
    
    private int index;
    @FXML
    private TableColumn<Boutique, Integer> col_id= new TableColumn<Boutique, Integer>("id");;
    @FXML
    private TableColumn<Boutique, String> col_nom = new TableColumn<Boutique, String>("nom");
    @FXML
    private TableColumn<Boutique, String> col_lieu= new TableColumn<Boutique, String>("lieu");;
    @FXML
    private TableColumn<Boutique, String> col_description= new TableColumn<Boutique, String>("description");;
    @FXML
    private TableView<Boutique> tableView = new TableView<Boutique>();
    @FXML
    private Button btn_visiter;
    @FXML
    private AnchorPane pane_espace_boutique_aff_prod;
    @FXML
    private TableView<Produit> tableViewProds;
    @FXML
    private TableColumn<Produit, Integer> col_2_id;
    @FXML
    private TableColumn<Produit, String> col_2_nom;
    @FXML
    private TableColumn<Produit, Float> col_2_prix;
    @FXML
    private TableColumn<Produit, String> col_2_descr;
    @FXML
    private TableColumn<?, ?> col_2_img;
    @FXML
    private TableColumn<Produit, String> col_2_categorie;
    
    Boutique boutiqueSelect = new Boutique();
    Produit produitSelect = new Produit();
    Categorie categorieSelect = new Categorie();
    Produit produitModifSelect,produitAjoutSelect = new Produit();
    
    
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_acheter;
    @FXML
    private Text lab_bout_name;
    @FXML
    private Text lab_bout_desc;
    @FXML
    private Button btn_afficher_commandes;
    @FXML
    private Button btn_supprimer_commande;
    @FXML
    private AnchorPane pane_gerer_boutique;
    @FXML
    private TextField champs_nom_prod;
    @FXML
    private TextField champs_prix_prod;
    @FXML
    private TextField champs_descr_prod;
    @FXML
    private ChoiceBox<Categorie> choice_box_prod;
    @FXML
    private Button btn_ajouter_prod;
    @FXML
    private Button btn_retour1;
    @FXML
    private TableView<Produit> tableView_modif_prod;
    @FXML
    private TableColumn<Produit, Integer> col_id_modif;
    @FXML
    private TableColumn<Produit, String> col_nom_modif;
    @FXML
    private TextField champs_modif_nom;
    @FXML
    private TextField champs_modif_descr;
    @FXML
    private TextField champs_modif_prix;
    @FXML
    private Button btn_supprimer_prod;
    @FXML
    private Button btn_valider_modif;
    @FXML
    private TextField chps_modif_nom_bout;
    @FXML
    private TextField chps_modif_lieu_bout;
    @FXML
    private TextArea chps_modif_descr_bout;
    @FXML
    private Button btn_valider_modif_bout;
    @FXML
    private Label lab_modif_boutique;
    @FXML
    private TableView<Produit> tableView_modif_prod1;
    @FXML
    private TableColumn<Produit, Integer> col_id_modif1;
    @FXML
    private TableColumn<Produit, String> col_nom_modif1;
    @FXML
    private BarChart<String, Integer> monBarChart;

    private  XYChart.Series<String, Integer> series1;
    
    @FXML
    private LineChart<String, Integer> lineChart;
    
    private CategoryAxis x;
    private NumberAxis y;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        XYChart.Series series = new XYChart.Series();
//        series.getData().add(new XYChart.Data("1",23));
//        series.getData().add(new XYChart.Data("2",25));
//        series.getData().add(new XYChart.Data("3",25));
        series = OtherServices.selectStats2(AuthUser.getInstance().getGerant().getBoutique().getId());
        lineChart.getData().addAll(series);

    ///////////////////////////////////////////
        
        for(XYChart.Series<String, Integer> e : OtherServices.selectCategoriesCountDuneBoutique(AuthUser.getInstance().getGerant().getBoutique().getId()))
        {
            monBarChart.getData().addAll(e);
        }
        
        
            ObservableList<Boutique> list = FXCollections.observableArrayList(OtherServices.selectAllBoutiques());  
            tableView.setItems(list);
            
            col_nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Boutique, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Boutique, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomBoutique());
            }});
            
            col_description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Boutique, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Boutique, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
            });
             col_lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Boutique, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Boutique, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getLieu());
            }
            });
        
        
        btn_gerer_ma_boutique.setVisible(false);
        if(AuthUser.getInstance().IsGerant())
        {
            System.out.println("btn gerer boutique, true");
            btn_gerer_ma_boutique.setVisible(true);
        }
    }

    @FXML
    private void click(MouseEvent event) {
        Boutique boutique = tableView.getSelectionModel().getSelectedItem();
        System.out.println(boutique.getId()+" "+ boutique.getNomBoutique());
        boutiqueSelect=boutique;
    }

    @FXML
    private void visiterBoutiqueAction(ActionEvent event) {
        pane_espace_boutique_aff_prod.toFront();
        lab_bout_name.setText(boutiqueSelect.getNomBoutique());
        lab_bout_desc.setText(boutiqueSelect.getDescription());
        ObservableList<Produit> liste = FXCollections.observableArrayList(OtherServices.selectAllProdsDuneBoutique(boutiqueSelect.getId()));
        tableViewProds.setItems(liste);
        
        col_2_nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProduit());
            }});
        
        col_2_prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Produit, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix());
            }});
        
        col_2_descr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }});
        
        col_2_categorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCategorie().getNom());
            }});
    }

    @FXML
    private void retourAction(ActionEvent event) {
        pane_espace_boutique.toFront();   
        btn_supprimer_commande.setVisible(false);
    }

    @FXML
    private void tableProdClick(MouseEvent event) {
        Produit produit = tableViewProds.getSelectionModel().getSelectedItem();
        System.out.println(produit.getId()+" "+ produit.getNomProduit());
        produitSelect=produit;
    }

    @FXML
    private void AcheterAction(ActionEvent event) {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        Commande commande = new Commande();
        commande.setCloturee(0);
        commande.setDateCreation(date);
        commande.setQuantite(1);
        commande.setUser_id(AuthUser.getInstance().getId());
        System.out.println(AuthUser.getInstance().getId());
        commande.setProduit_id(produitSelect.getId());
        OtherServices.ajouterCommande(commande);
    }

    @FXML
    private void AfficherCommandesAction(ActionEvent event) {
        btn_acheter.setDisable(true);
        ObservableList<Produit> liste = FXCollections.observableArrayList(OtherServices.selectProduitsCommandes(AuthUser.getInstance().getId()));
        tableViewProds.setItems(liste);
        btn_supprimer_commande.setVisible(true);
    }

    @FXML
    private void supprimerCommandeAction(ActionEvent event)
    {
        OtherServices.supprimerCommande(AuthUser.getInstance().getId(), produitSelect.getId());
        visiterBoutiqueAction(event);
        btn_supprimer_commande.setVisible(false);
        btn_acheter.setDisable(false);
    }

    @FXML
    private void GererBoutiqueAction(ActionEvent event) {
        pane_gerer_boutique.toFront();
        ObservableList<Categorie> categories = FXCollections.observableArrayList(OtherServices.selectAllCategories());
        choice_box_prod.setItems(categories);
        choice_box_prod.getSelectionModel().selectFirst();
        remplissageTableModif();
        remplissageTableModifAjoutProd();
         majChampsModifBoutique();
        
        
    }
    
    @FXML
    private void ajouterProdAction(ActionEvent event) {
        Produit produit = new Produit();
        produit.nomProduit=champs_nom_prod.getText();
        produit.setPrix(Float.parseFloat(champs_prix_prod.getText()));
        produit.setDescription(champs_descr_prod.getText());
        produit.setCategorie(choice_box_prod.getSelectionModel().getSelectedItem());
        OtherServices.ajouterProduitBoutique(produit, AuthUser.getInstance().getGerant().getBoutique().getId());
        remplissageTableModif();
        remplissageTableModifAjoutProd();
    }

    @FXML
    private void clickCategorie(MouseEvent event) {
//        Categorie categorie = choice_box_prod.getSelectionModel().getSelectedItem();
//        System.out.println(categorie.getId()+" "+categorie.getNom());
//        categorieSelect=categorie;
    }
    
    //////////////////
    public void remplissageTableModif()
    {
        System.out.println("appel remplissage table modif");
        ObservableList<Produit> produits_modif = FXCollections.observableArrayList(OtherServices.selectAllProdsDuneBoutique(AuthUser.getInstance().getGerant().getBoutique().getId()));
        tableView_modif_prod.setItems(produits_modif);
        
        col_id_modif.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Produit, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getId());
            }});
        
        col_nom_modif.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProduit());
            }});
    }
    
    public void remplissageTableModifAjoutProd()
    {
        System.out.println("appel remplissage table modif");
        ObservableList<Produit> produits = FXCollections.observableArrayList(OtherServices.selectAllProdsDuneBoutique(AuthUser.getInstance().getGerant().getBoutique().getId()));
        tableView_modif_prod1.setItems(produits);
        
        col_id_modif1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Produit, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getId());
            }});
        
        col_nom_modif1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProduit());
            }});
    }
    
    ////////////////////

    @FXML
    private void clickTableModifProd(MouseEvent event) {
        Produit produitmodif = tableView_modif_prod.getSelectionModel().getSelectedItem();
        produitModifSelect = produitmodif;
        champs_modif_nom.setText(produitModifSelect.getNomProduit());
        champs_modif_descr.setText(produitModifSelect.getDescription());
        champs_modif_prix.setText(String.valueOf(produitModifSelect.getPrix()));
    }

    @FXML
    private void validerModifAction(ActionEvent event) {
        Produit produit = new Produit();
        produit.setId(produitModifSelect.getId());
        produit.setNomProduit(champs_modif_nom.getText());
        produit.setPrix(Float.parseFloat(champs_modif_prix.getText()));
        produit.setDescription(champs_modif_descr.getText());
        OtherServices.modifierProduit(produit);
        remplissageTableModif();
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
        OtherServices.supprimerProduitDuneBoutique(produitModifSelect);
        remplissageTableModif();
    }

    @FXML
    private void modifBoutiqueAction(ActionEvent event) {
        Boutique boutique = new Boutique();
        boutique.setNomBoutique(chps_modif_nom_bout.getText());
        boutique.setDescription(chps_modif_descr_bout.getText());
        boutique.setLieu(chps_modif_lieu_bout.getText());
        boutique.setId(AuthUser.getInstance().getGerant().getBoutique().getId());
        OtherServices.modifierBouique(boutique);
    }
    
    public void majChampsModifBoutique()
    {
        Boutique boutique = new Boutique(); 
        boutique = OtherServices.selectBoutique(AuthUser.getInstance().getGerant().getBoutique().getId());
        AuthUser.getInstance().getGerant().setBoutique(boutique);
        chps_modif_descr_bout.setText(AuthUser.getInstance().getGerant().getBoutique().getDescription());
        chps_modif_nom_bout.setText(AuthUser.getInstance().getGerant().getBoutique().getNomBoutique());
        chps_modif_lieu_bout.setText(AuthUser.getInstance().getGerant().getBoutique().getLieu());
    }

    @FXML
    private void clickTableAjoutProd(MouseEvent event) {
//        Produit produitajout = tableView_modif_prod1.getSelectionModel().getSelectedItem();
//        produitAjoutSelect = produitajout;
//        champs_nom_prod.setText(produitAjoutSelect.getNomProduit());
//        champs_descr_prod.setText(produitAjoutSelect.getDescription());
//        champs_prix_prod.setText(String.valueOf(produitAjoutSelect.getPrix()));
    }
}