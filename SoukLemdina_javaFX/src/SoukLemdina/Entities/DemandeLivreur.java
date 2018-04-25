/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import com.jfoenix.controls.JFXCheckBox;
import java.sql.Array;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableSet;

/**
 *
 * @author Lobna
 */
public class DemandeLivreur {
    private IntegerProperty id; 
    private ObjectProperty<LocalDate> dateInscription; 
    private StringProperty vehicule; 
    private ObjectProperty<String[]> disponibilite;
    private IntegerProperty clientRef;
    private IntegerProperty isactive; 

    public DemandeLivreur() {
    }

    public DemandeLivreur(LocalDate dateInscription, String vehicule, String[] disponibilite) {
        this.dateInscription = new SimpleObjectProperty <LocalDate>(dateInscription);
        this.vehicule = new SimpleStringProperty(vehicule);
        this.disponibilite = new SimpleObjectProperty<String[]>(disponibilite);
    }

    public DemandeLivreur(int id, LocalDate dateInscription, String vehicule, String[] disponibilite, int clientRef, int isactive) {
        this.id = new SimpleIntegerProperty(id);
        this.dateInscription = new SimpleObjectProperty <LocalDate>(dateInscription);
        this.vehicule = new SimpleStringProperty(vehicule);
        this.disponibilite = new SimpleObjectProperty<String[]>(disponibilite);
        this.clientRef = new SimpleIntegerProperty(clientRef);
        this.isactive = new SimpleIntegerProperty(isactive);
    }

        public DemandeLivreur(int id, LocalDate dateInscription, String vehicule, int clientRef, int isactive) {
        this.id = new SimpleIntegerProperty(id);
        this.dateInscription = new SimpleObjectProperty <LocalDate>(dateInscription);
        this.vehicule = new SimpleStringProperty(vehicule);
        this.clientRef = new SimpleIntegerProperty(clientRef);
        this.isactive = new SimpleIntegerProperty(isactive);
    }
        
    public DemandeLivreur(LocalDate dateInscription, String vehicule, String[] disponibilite, int clientRef, int isactive) {
       this.dateInscription = new SimpleObjectProperty <LocalDate>(dateInscription);
        this.vehicule = new SimpleStringProperty(vehicule);
        this.disponibilite = new SimpleObjectProperty<String[]>(disponibilite);
        this.clientRef = new SimpleIntegerProperty(clientRef);
        this.isactive = new SimpleIntegerProperty(isactive);
    }

    public DemandeLivreur(int i, LocalDate str0, String str1, String str2, int str3, int str4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id.get();
    }
    public IntegerProperty IdProperty()
    {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getDateInscription() {
        return dateInscription.get();
    }
    
    public ObjectProperty<LocalDate> LocalDatePorperty()
    {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription.set(dateInscription);
    }

    public String getVehicule() {
        return vehicule.get();
    }
    
    public StringProperty VehiculeProperty()
    {
        return vehicule;
    }
    
    public void setVehicule(String vehicule) {
        this.vehicule.set(vehicule);
    }

    public String[] getDisponibilite() {
        return disponibilite.get();
    }
    
    public ObjectProperty<String[]> DisponibiliteProperty()
    {
        return disponibilite;
    }

    public void setDisponibilite(String[] disponibilite) {
        this.disponibilite.set(disponibilite);
    }

    public int getClientRef() {
        return clientRef.get();
    }
    
    public IntegerProperty ClientRefProperty()
    {
        return clientRef;
    }
    
    public void setClientRef(int clientRef) {
        this.clientRef.set(clientRef);
    }

    public int getIsactive() {
        return isactive.get();
    }

    public IntegerProperty IsactiveProperty()
    {
        return IsactiveProperty();
    }
    
    public void setIsactive(int isactive) {
        this.isactive.set(isactive);
    }

    @Override
    public String toString() {
        return "DemandeLivreur{" + "id=" + id + ", dateInscription=" + dateInscription + ", vehicule=" + vehicule + ", disponibilite=" + disponibilite + ", clientRef=" + clientRef + ", isactive=" + isactive + '}';
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
        final DemandeLivreur other = (DemandeLivreur) obj;
        if (!Objects.equals(this.clientRef, other.clientRef)) {
            return false;
        }
        return true;
    }

    public void setDisponibilite(ObservableSet<JFXCheckBox> selectedCheckBoxes) {
        
    }
    
    
    
    
    
}
