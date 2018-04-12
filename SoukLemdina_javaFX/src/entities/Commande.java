/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lobna
 */
public class Commande {

    private IntegerProperty id;
    private ObjectProperty<LocalDate> date_commande;
    private IntegerProperty plusLivraison;
    private IntegerProperty produit_id;
    private IntegerProperty clientRef;
    private IntegerProperty vendeurRef;

    public Commande() {
    }

    public Commande(int id, LocalDate date_commande, int plusLivraison, int produit_id, int clientRef, int vendeurRef) {
        this.id = new SimpleIntegerProperty(id);
        this.date_commande = new SimpleObjectProperty<LocalDate>(date_commande);
        this.plusLivraison = new SimpleIntegerProperty(plusLivraison);
        this.produit_id = new SimpleIntegerProperty(produit_id);
        this.clientRef = new SimpleIntegerProperty(clientRef);
        this.vendeurRef = new SimpleIntegerProperty(vendeurRef);
    }

    public Commande(LocalDate date_commande, int plusLivraison, int produit_id, int clientRef, int vendeurRef) {
        this.date_commande = new SimpleObjectProperty<LocalDate>(date_commande);
        this.plusLivraison = new SimpleIntegerProperty(plusLivraison);
        this.produit_id = new SimpleIntegerProperty(produit_id);
        this.clientRef = new SimpleIntegerProperty(clientRef);
        this.vendeurRef = new SimpleIntegerProperty(vendeurRef);
    }

    public Commande(int i, LocalDate str0, int str2, int str3, int str4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty IdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getDate_commande() {
        return date_commande.get();
    }

    public ObjectProperty<LocalDate> Date_commandePorperty() {
        return date_commande;
    }

    public void setDate_commande(LocalDate date_commande) {
        this.date_commande.set(date_commande);
    }

    public int getPlusLivraison() {
        return plusLivraison.get();
    }

    public IntegerProperty PlusLivraisonProperty() {
        return plusLivraison;
    }

    public void setPlusLivraison(int plusLivraison) {
        this.plusLivraison.set(plusLivraison);
    }

    public int getProduit_id() {
        return produit_id.get();
    }

    public IntegerProperty Produit_idProperty() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id.set(produit_id);
    }

    public int getClientRef() {
        return clientRef.get();
    }

    public IntegerProperty ClientRefProperty() {
        return clientRef;
    }

    public void setClientRef(int clientRef) {
        this.clientRef.set(clientRef);
    }

    public int getVendeurRef() {
        return vendeurRef.get();
    }
    
    public IntegerProperty VendeurRefProperty()
    {
        return vendeurRef;
    }

    public void setVendeurRef(int vendeurRef) {
        this.vendeurRef.set(vendeurRef);
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date_commande=" + date_commande + ", plusLivraison=" + plusLivraison + ", produit_id=" + produit_id + ", clientRef=" + clientRef + ", vendeurRef=" + vendeurRef + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
