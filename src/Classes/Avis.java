package Classes;

import java.time.LocalDateTime;

public class Avis {
    private int id;
    private String commentaire;
    private int note; // de 1 à 5 étoile
    private Client client;
    private LocalDateTime date;

    // Constructor
    public Avis(int id, String commentaire, int note, Client client, LocalDateTime date) {
        this.id = id;
        this.commentaire = commentaire;
        this.note = note;
        this.client = client;
        this.date = date;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getNote() {
        return note;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
