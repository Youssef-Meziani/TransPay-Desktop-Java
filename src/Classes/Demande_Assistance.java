package Classes;

import java.util.Date;

public class Demande_Assistance {
    private int id;
    private Date date_creation;
    private Date date_cloture;
    private String description;
    private String statut;
    private String priorite;

    public Demande_Assistance(int id, Date date_creation, Date date_cloture, String description, String statut,
            String priorite) {
        this.id = id;
        this.date_creation = date_creation;
        this.date_cloture = date_cloture;
        this.description = description;
        this.statut = statut;
        this.priorite = priorite;
    }

    // Getters
    public int getid() {
        return id;
    }

    public Date getDatCreation() {
        return date_creation;
    }

    public Date getDatCloture() {
        return date_cloture;
    }

    public String getdescription() {
        return description;
    }

    public String getstatut() {
        return statut;
    }

    public String getpriorite() {
        return priorite;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setDateCreation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public void setDatCloture(Date date_cloture) {
        this.date_cloture = date_cloture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }
}
