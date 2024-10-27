package Classes;

public class Arret {
    private static int cpt = 0;
    private int id;
    private String adresse;

    // Constructeur
    public Arret(String adresse) {
        this.id = ++cpt;
        this.adresse = adresse;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
