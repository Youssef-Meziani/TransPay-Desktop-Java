package Classes;

import java.util.ArrayList;
import java.util.Date;

public class Abonnement extends Forfait {
    private Date date_debut;
    private Date date_fin;
    private float cout; // champ calculé
    private ArrayList<Trajet> trajets;

    public Abonnement(String id, String nom, String type, float prix, Boolean isVisible, String description,
            Date date_debut, Date date_fin) {
        super(id, nom, type, prix, isVisible, description);
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }

    public Date getDateDebut() {
        return date_debut;
    }

    public void setDateDebut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDateFin() {
        return date_fin;
    }

    public void setDateFin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public ArrayList<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(ArrayList<Trajet> trajets) {
        this.trajets = trajets;
        setCout();
    }

    public void ajouterTrajet() {
        // todo
        setCout();
    }

    public void modifierTrajet() {
        setCout();
    }

    public void supprimerTrajet(int position) {
        trajets.remove(position);
        setCout();
    }

    private void setCout() {
        this.cout = trajets.size() * super.getPrix();
    }

    public String toString() {
        return "";
    }

    public void getInfo() {
        System.out.println();
    }
}
