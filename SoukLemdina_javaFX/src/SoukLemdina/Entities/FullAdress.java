/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoukLemdina.Entities;

/**
 *
 * @author Lobna
 */
public class FullAdress {
    public int id ;
    public String rue;
    public int num_maison;
    public String ville;
    public int code_postal;
    public String pays;
    public int idUser;

    public FullAdress(String rue, int num_maison, String ville, int code_postal, String pays) {
        this.rue = rue;
        this.num_maison = num_maison;
        this.ville = ville;
        this.code_postal = code_postal;
        this.pays = pays;
    }

    public FullAdress(int i, String str0, int str1, String str2, int str3, String str4, int str5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNum_maison() {
        return num_maison;
    }

    public void setNum_maison(int num_maison) {
        this.num_maison = num_maison;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "FullAdress{" + "rue=" + rue + ", num_maison=" + num_maison + ", ville=" + ville + ", code_postal=" + code_postal + ", pays=" + pays + '}';
    }
    
    
    
    
    
}
