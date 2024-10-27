package Classes;

import java.util.Date;

public class Offre {
    private int id;
    private String nom;
    private Date date_debut;
    private Date date_fin;
    private float taux_de_reduction;

    public Offre(int id, String nom, Date date_debut, Date date_fin, float taux_de_reduction) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.taux_de_reduction = taux_de_reduction;
    }

    // Getters
    public int getid() {
        return id;
    }

    public String getnom() {
        return nom;
    }

    public Date getdatedebut() {
        return date_debut;
    }

    public Date getdatefin() {
        return date_fin;
    }

    public float gettauxreduction() {
        return taux_de_reduction;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDateFin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setTauxReduction(float taux_de_reduction) {
        this.taux_de_reduction = taux_de_reduction;
    }
}