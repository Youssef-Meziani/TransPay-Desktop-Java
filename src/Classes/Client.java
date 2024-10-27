package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import IO.Authentification;
import IO.MessageBox;
import IO.JsonSerializer;

public class Client extends Personne {
    private Boolean active;
    private String langue_pref;
    private int nbr_pt_fidelite;
    private char role; // 'm' pour membre d'un groupe, 'a' pour admin, '' pour aucun
    private Abonnement abonnements;

    // Constructeur
    public Client(String cin, String nom, String prenom, char sexe, Date dn, String email, String pswd,
            String telephone,
            String adresse, Boolean active, String langue_pref, int nbr_pt_fidelite) {
        super(cin, nom, prenom, sexe, dn, email, pswd, telephone, adresse);
        this.active = active;
        this.langue_pref = langue_pref;
        this.nbr_pt_fidelite = nbr_pt_fidelite;
        this.role = ' ';
    }

    public Client(Client c) {
        super(c);
        this.active = c.active;
        this.langue_pref = c.langue_pref;
        this.nbr_pt_fidelite = c.nbr_pt_fidelite;
        this.role = c.role;
        this.abonnements = c.abonnements;
    }

    // Getters
    public Boolean isActive() {
        return active;
    }

    public String getLangue_pref() {
        return langue_pref;
    }

    public int getNbr_pt_fidelite() {
        return nbr_pt_fidelite;
    }

    public char getRole() {
        return role;
    }

    public Abonnement getaAbonnement() {
        return this.abonnements;
    }

    // Setters
    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setLangue_pref(String langue_pref) {
        this.langue_pref = langue_pref;
    }

    public void setNbr_pt_fidelite(int nbr_pt_fidelite) {
        this.nbr_pt_fidelite = nbr_pt_fidelite;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public void setAbonement(Abonnement a) {
        this.abonnements = a;
    }

    public String toString() {
        return super.toString()
                + "\n\033[0;36mLangue préféré\033[0m : " + langue_pref
                + "\n\033[0;36mPoint\033[0m : " + nbr_pt_fidelite;
    }

    public void changePassword() {
        super.verifyCurrentPassword();
        Client new_cli = new Client(this);
        new_cli.setPswd(Authentification.getMotDePasse(new Scanner(System.in)));
        JsonSerializer.modify(this, new_cli, "clients.json");
        MessageBox.Alert("Mot de passe changé avec succès.", "Succès");
    }

    public Client edit() {
        Client c = (Client) super.edit();
        c.setLangue_pref(Authentification.getLanguePreferee(new Scanner(System.in)));
        return c;
    }

    public static void stats() {
        ArrayList<Client> clients = JsonSerializer.deserialize("src/Data/clients.json", Client.class);

        // Calculate the total number of clients
        int totalClients = clients.size();

        // Calculate the number of active clients
        int activeClients = 0;
        for (Client client : clients) {
            if (client.active) {
                activeClients++;
            }
        }

        // Calculate the number of male and female clients
        int maleClients = 0;
        int femaleClients = 0;
        for (Client client : clients) {
            if (client.sexe == 'm') {
                maleClients++;
            } else if (client.sexe == 'f') {
                femaleClients++;
            }
        }

        // Calculate the average age of clients
        long totalAgeMillis = 0;
        for (Client client : clients) {
            Date dob = client.dn;
            Date today = new Date();
            long ageMillis = today.getTime() - dob.getTime();
            totalAgeMillis += ageMillis;
        }
        double avgAgeMillis = totalAgeMillis / (double) totalClients;
        double avgAgeYears = avgAgeMillis / (365.25 * 24 * 60 * 60 * 1000);

        // Calculate the number of clients who prefer each language
        HashMap<String, Integer> languageCounts = new HashMap<>();
        for (Client client : clients) {
            String language = client.langue_pref;
            if (languageCounts.containsKey(language)) {
                int count = languageCounts.get(language);
                languageCounts.put(language, count + 1);
            } else {
                languageCounts.put(language, 1);
            }
        }

        // Print out the statistics
        System.out.println("Clients: " + totalClients);
        System.out.println("Comptes bannis: " + (totalClients - activeClients));
        System.out.println("Homme: " + maleClients);
        System.out.println("Femme: " + femaleClients);
        System.out.printf("Age moyen: %.2f%n", avgAgeYears);
        System.out.println("Préférence de langue:");
        for (String language : languageCounts.keySet()) {
            int count = languageCounts.get(language);
            System.out.println("- " + language + ": " + count);
        }
    }

}
