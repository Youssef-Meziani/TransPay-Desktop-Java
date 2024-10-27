package Classes;

public class Fournisseur_de_Transport {
    private int id;
    private String nom;
    private String adresse;
    private String adresse_mail;
    private int tele;
    private String ville;

    public Fournisseur_de_Transport(int id, String nom, String adresse, String adresse_mail, int tele, String ville) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.adresse_mail = adresse_mail;
        this.tele = tele;
        this.ville = ville;
    }

    // Getters
    public int getid() {
        return id;
    }

    public String getnom() {
        return nom;
    }

    public String getadresse() {
        return adresse;
    }

    public String getadressemail() {
        return adresse_mail;
    }

    public int gettele() {
        return tele;
    }

    public String getville() {
        return ville;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setAdresseMail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public void setTele(int tele) {
        this.tele = tele;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}