package Classes;

import java.util.Date;

public class Paiement {
    private int id;
    private float montant;
    private Date date;
    private String methode_p;

    public Paiement(int id, float montant, Date date, String methode_p) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.methode_p = methode_p;
    }

    // Getters
    public int getid() {
        return id;
    }

    public float getmontant() {
        return montant;
    }

    public Date getdate() {
        return date;
    }

    public String getmethodep() {
        return methode_p;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMethodeP(String methode_p) {
        this.methode_p = methode_p;
    }
}