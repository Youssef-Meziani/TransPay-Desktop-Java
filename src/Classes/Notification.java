package Classes;

import java.util.Date;

public class Notification {
    private int id;
    private String type;
    private String contenu;
    private Date date_creation;

    public Notification(int id, String type, String contenu, Date date_creation) {
        this.id = id;
        this.type = type;
        this.contenu = contenu;
        this.date_creation = date_creation;
    }

    // Getters
    public int getid() {
        return id;
    }

    public String gettype() {
        return type;
    }

    public String getcontenu() {
        return contenu;
    }

    public Date getdatecreation() {
        return date_creation;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDateCreation(Date date_creation) {
        this.date_creation = date_creation;
    }

}