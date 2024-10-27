package Classes;

import java.util.ArrayList;

public class Trajet {
    private String num_ligne;
    private ArrayList<Arret> arrets;
    private String depart;
    private String arrive;

    // Constructeur
    public Trajet(String num_ligne, String depart, String arrive) {
        this.num_ligne = num_ligne;
        this.depart = depart;
        this.arrive = arrive;
        this.arrets = new ArrayList<Arret>();
    }

    public Trajet(Trajet t) {
        this.num_ligne = t.num_ligne;
        this.depart = t.depart;
        this.arrive = t.arrive;
        this.arrets = t.arrets;
    }

    // Getters
    public String getNum_ligne() {
        return num_ligne;
    }

    public Arret getArret(int i) {
        return arrets.get(i) != null ? arrets.get(i) : null;
    }

    public ArrayList<Arret> getArrets() {
        return arrets;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrive() {
        return arrive;
    }

    // Setters
    public void setNum_ligne(String num_ligne) {
        this.num_ligne = num_ligne;
    }

    public void setArrets(ArrayList<Arret> arrets) {
        this.arrets = arrets;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }
    public void AddArret(Arret a){
        this.arrets.add(a);
    }
    public String getStringDepartArrivee(){
        StringBuilder sb = new StringBuilder();
        return sb.append("\n").append("\033[33mDepart ---- \033[0m").append("\033[0;36m").append(depart).append("\033[0m -------> \033[0;36m").append(arrive).append("\033[0m").append("\033[33m ---- Arrivée  \033[0m").toString();
    }
    public String getStringArrets(){
        StringBuilder sb = new StringBuilder();
        if(arrets.size() == 0){
            return sb.append("\033[0;36mAucune arrêts entre \033[33m").append(depart).append("\033[0m et \033[33m").append(arrive).append("\033[0m").toString();

        }
        sb.append("\033[0;36mLes arrêts par ordre entre \033[33m").append(depart).append("\033[0m et \033[33m").append(arrive).append("\033[0m\n");
        for (int i = 1; i <= arrets.size(); i++) {
            if(i == 1)
                sb.append(i).append(" - ").append(arrets.get(0).getAdresse()).append("\033[33m | \033[0m");
            else if(i == arrets.size())
                sb.append(i).append(" - ").append(arrets.get(arrets.size() - 1).getAdresse());
            else
                sb.append(i).append(" - ").append(arrets.get(i-1).getAdresse()).append("\033[33m | \033[0m");
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStringDepartArrivee()).append("\n");
        sb.append(getStringArrets());
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.num_ligne.equals(((Trajet) obj).num_ligne);
    }


}
