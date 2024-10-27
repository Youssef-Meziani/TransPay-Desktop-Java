package Classes;

import java.util.ArrayList;

import IO.JsonSerializer;

public class Groupe {
    private String nom;
    private ArrayList<String> membre;
    private float cout_total;
    private String admin;

    // Constructor
    public Groupe(String nom, float cout_total, String admin) {
        this.nom = nom;
        this.membre=new ArrayList<String>();
        this.cout_total = cout_total;
        this.admin=admin;
    }

    public Groupe(Groupe p) {
        this.nom = p.nom;
        this.membre = p.getMembers();
        this.cout_total = p.cout_total;
        this.admin=p.admin;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public ArrayList<String> getMembers() {
        return membre;
    }

    public String getMember(int index) {
        return membre.get(index);
    }

    public int getNbrMembers() {
        return membre.size();
    }

    public float getCout_total() {
        return cout_total;
    }

    public String getAdmin() {
        return admin;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addMember(String membre) {
        this.membre.add(membre);
    }

    public void removeMember(String membre) {
        this.membre.remove(membre);
    }

    public void setAdmin(String admin) {
        this.admin=admin;
    }

    public void setCout_total(float cout_total) {
        this.cout_total = cout_total;
    }

    @Override
    public boolean equals(Object obj) {
        return this.admin.equals(((Groupe) obj).admin);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        String couleurBleuCiel = "\u001B[96m";
        String couleurJaune = "\u001B[33m";
        String couleurDefaut = "\u001B[0m";
        str.append(couleurBleuCiel).append("Type d'abonnement: ").append(couleurDefaut).append(nom).append("\n");
        ArrayList<Client> clients = JsonSerializer.deserialize("src/Data/clients.json", Client.class);
        Client c = JsonSerializer.getObject(clients, "cin", admin);
        str.append(couleurBleuCiel).append("Administrateur: ").append(couleurDefaut).append(c.getFullName()).append(couleurBleuCiel).append("\nMembres:\n").append(couleurDefaut);
        if (membre.size() == 0) {
            str.append(couleurJaune).append("Aucun membre pour l'instant dans ce groupe").append(couleurDefaut);
        } else {
            short count = 1;
            for (String string : membre) {
                c = JsonSerializer.getObject(clients, "cin", string);
                str.append("\t").append(couleurJaune).append(count).append(couleurDefaut).append(". ").append(c.getFullName()).append("\n");
                count++;
            }
        }
        return str.toString();
    }


}
