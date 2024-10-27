package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import IO.Authentification;
import IO.MessageBox;
import IO.JsonSerializer;

public class Employe extends Personne {
    private Date date_emb;
    private Float salaire;
    private Boolean administrateur;
    private int annee_experience;
    private Boolean appartient_serv_client;

    // Constructeur
    public Employe(String cin, String nom, String prenom, char sexe, Date dn, String email, String pswd,
            String telephone,
            String adresse, Date date_emb, Float salaire,
            Boolean administrateur, int annee_experience, Boolean appartient_serv_client) {
        super(cin, nom, prenom, sexe, dn, email, pswd, telephone, adresse);
        this.date_emb = date_emb;
        this.salaire = salaire;
        this.administrateur = administrateur;
        this.annee_experience = annee_experience;
        this.appartient_serv_client = appartient_serv_client;
    }

    public Employe(Employe e) {
        super(e);
        this.date_emb = e.date_emb;
        this.salaire = e.salaire;
        this.administrateur = e.administrateur;
        this.annee_experience = e.annee_experience;
        this.appartient_serv_client = e.appartient_serv_client;
    }

    // Getters
    public Date getDate_emb() {
        return date_emb;
    }

    public Float getSalaire() {
        return salaire;
    }

    public Boolean estAdministrateur() {
        return administrateur;
    }

    public int getAnnee_experience() {
        return annee_experience;
    }

    public Boolean appartient_serv_client() {
        return appartient_serv_client;
    }

    // Setters
    public void setDate_emb(Date date_emb) {
        this.date_emb = date_emb;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    public void administrateur(Boolean administrateur) {
        this.administrateur = administrateur;
    }

    public void setAnnee_experience(int annee_experience) {
        this.annee_experience = annee_experience;
    }

    public void appartient_serv_client(Boolean appartient_serv_client) {
        this.appartient_serv_client = appartient_serv_client;
    }

    public String toString() {
        return super.toString()
                + "\n\033[0;36mDate d'embauche\033[0m : " + new SimpleDateFormat("dd/MM/yyyy").format(date_emb)
                + "\n\033[0;36mAnnée d'experience\033[0m : " + annee_experience;
    }

    public void changePassword() {
        super.verifyCurrentPassword();
        Employe new_emp = new Employe(this);
        new_emp.setPswd(Authentification.getMotDePasse(new Scanner(System.in)));
        JsonSerializer.modify(this, new_emp, "employes.json");
        MessageBox.Alert("Mot de passe changé avec succès.", "Succès");
    }

    public Employe edit() {
        return (Employe) super.edit();
    }
}