/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



/**
 *
 * @author Lobna
 */
public class Reclamation {
    private IntegerProperty id; 
    private StringProperty sujet;
    private StringProperty contenu;
    private StringProperty etat;
    private ObjectProperty<LocalDate> date;
    private IntegerProperty clientRef;
    private StringProperty answer;

 
     public Reclamation(int id, String sujet, String contenu, String etat, LocalDate date, Integer clientRef) {
        this.id= new SimpleIntegerProperty(id);
        this.sujet = new SimpleStringProperty(sujet);
        this.contenu = new SimpleStringProperty(contenu);
        this.etat = new SimpleStringProperty(etat);
        this.date = new SimpleObjectProperty <LocalDate>(date);
        this.clientRef = new SimpleIntegerProperty (clientRef);
        }
    
    public Reclamation(String sujet, String contenu, String etat, LocalDate date, Integer clientRef, String answer) {
        this.sujet = new SimpleStringProperty(sujet);
        this.contenu = new SimpleStringProperty(contenu);
        this.etat = new SimpleStringProperty(etat);
        this.date = new SimpleObjectProperty <LocalDate>(date);
        this.clientRef = new SimpleIntegerProperty (clientRef);
        this.answer = new SimpleStringProperty (answer);
    }

    
    
    public Reclamation(String sujet, String contenu, String etat, LocalDate date) {
        this.sujet = new SimpleStringProperty(sujet);
        this.contenu = new SimpleStringProperty(contenu);
        this.etat = new SimpleStringProperty(etat);
        this.date = new SimpleObjectProperty <LocalDate>(date);
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


    public String getSujet() {
        return sujet.get();
    }
    
        public StringProperty SujetProperty()
    {
        return sujet;
    }
        
    public void setSujet(String sujet) {
        this.sujet.set(sujet);
    }

    public String getContenu() {
        return contenu.get();
    }
    public StringProperty ContenuProperty(){
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu.set(contenu);
    }
    
    public String getEtat() {
        return etat.get();
    }
    
    public StringProperty EtatProperty()
    {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat.set(etat);
    }
    
    
    public LocalDate getDate() {
        return date.get();
    }
    public ObjectProperty<LocalDate> DateProperty()
    {
        return date;
    }
            
    public void setDate(LocalDate date) {
        this.date.set(date);
    }
    
    public int getClientRef() {
        return clientRef.get();
    }
    public IntegerProperty ClientRefProperty()
    {
        return clientRef;
    }
    public void setClientRef(int ClientRef) {
        this.clientRef.set(ClientRef);
    }

    public String getAnswer() {
        return answer.get();
    }
    
    public StringProperty AnswerProperty()
    {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    @Override
    public String toString() {
        return "Reclamation{" + "sujet=" + sujet + ", contenu=" + contenu + ", etat=" + etat + ", date=" + date + ", clientRef=" + clientRef + ", answer=" + answer + '}';
    }
    
    
    
}
  
