package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import IO.Authentification;
import IO.MessageBox;
import IO.Tools;

public abstract class Personne {
    protected String cin;
    protected String nom;
    protected String prenom;
    protected char sexe;
    protected Date dn;
    protected String telephone;
    protected String email;
    protected String pswd;
    protected String adresse;

    // Constructor 1
    public Personne(String cin, String nom, String prenom, char sexe, Date dn, String email, String pswd,
            String telephone,
            String adresse) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dn = dn;
        this.email = email;
        this.pswd = pswd;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Personne(Personne p) {
        this.cin = p.cin;
        this.nom = p.nom;
        this.prenom = p.prenom;
        this.sexe = p.sexe;
        this.dn = p.dn;
        this.email = p.email;
        this.pswd = p.pswd;
        this.telephone = p.telephone;
        this.adresse = p.adresse;
    }

    // Getters
    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Date getDN() {
        return dn;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public char getSexe() {
        return sexe;
    }

    // Setters
    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getFullName() {
        return this.nom + " " + this.prenom;
    }

    public String toString() {
        return "\033[0;36mCIN\033[0m : " + cin
                + "\n\033[0;36mNom\033[0m : " + nom
                + "\n\033[0;36mPrenom\033[0m : " + prenom
                + "\n\033[0;36mSexe\033[0m : " + ((sexe == 'h') ? "Homme" : "Femme")
                + "\n\033[0;36mDate de naissance\033[0m : " + new SimpleDateFormat("dd/MM/yyyy").format(dn)
                + "\n\033[0;36mEmail\033[0m : " + email
                + "\n\033[0;36mTelephone\033[0m : " + telephone
                + "\n\033[0;36mAdresse\033[0m : " + adresse;
    }

    @Override
    public boolean equals(Object obj) {
        return this.cin.equals(((Personne) obj).cin);
    }

    public void verifyCurrentPassword() {
        while (true) {
            System.out.print("Mot de passe actuel: ");
            if (String.valueOf(System.console().readPassword()).equals(this.getPswd())) {
                break;
            }
            MessageBox.Alert("Ce mot de passe est incorrect !", "Mot de passe incorrect");
            Tools.deleteLine();
        }
    }

    public Personne edit() {
        Scanner sc = new Scanner(System.in);
        Personne p;
        if (this instanceof Client) {
            p = new Client((Client) this);
        } else {
            p = new Employe((Employe) this);
        }
        p.setNom(Authentification.getNom(sc));
        p.setPrenom(Authentification.getPrenom(sc));
        p.setSexe(Authentification.getSexe(sc));
        p.setDn(Authentification.getDateNaissance(sc));
        p.setTelephone(Authentification.getTelephone(sc));
        p.setEmail(Authentification.getEmail(sc));
        p.setAdresse(Authentification.getAdresse(sc));
        return p;
    }
}
