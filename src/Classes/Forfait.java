package Classes;

public class Forfait {
    protected String id;
    protected String nom;
    protected String type;
    protected float prix;
    protected Boolean isVisible;
    protected String description;

    public Forfait(String id, String nom, String type, float prix, Boolean isVisible, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.isVisible = isVisible;
        this.prix = prix;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public Boolean getVisibilite() {
        return isVisible;
    }

    public void setVisibilite(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "\033[0;36mNom\033[0m : " + nom
                + "\t|\t\033[0;36mType\033[0m : " + type
                + "\t|\t\033[0;36mPrix\033[0m : " + prix
                + "\t|\t\033[0;36mDéscription\033[0m : " + description;
    }
}
