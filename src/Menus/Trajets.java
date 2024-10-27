package Menus;

import Classes.Arret;
import Classes.Trajet;
import IO.JsonSerializer;
import IO.MessageBox;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Trajets {

    public static void Employe() {
        Menu cm1 = new Menu("TRAJETS",
                Arrays.asList("Retour", "Ajouter trajet", "Modifier trajet", "Supprimer trajet",
                        "Afficher tout les trajets"));
        while (true) {
            cm1.showMenu(false);
            switch (cm1.selectOption()) {
                case 1 -> AjouterTrajet();
                case 2 -> ModifierTrajet();
                case 3 -> SupprimerTrajet();
                case 4 -> AfficherTrajets();
                default -> {
                    return;
                }
            }
        }
    }

    public static void AjouterTrajet(){
        Scanner sc = new Scanner(System.in);

        // TODO : A supprimer
        String bold = "\033[1m";
        String yellow = "\033[33m";
        String reset = "\033[0m";

        String numLigne;
        while (true) {
            System.out.print(bold + "Numéro de la ligne: ");
            numLigne = sc.nextLine();
            if (numLigne.matches("\\d+")) {
                break;
            }
            MessageBox.Alert("Veuillez entrer un nombre valide.","Alert");
        }

        System.out.print(bold + "Départ: ");

        String depart = sc.nextLine();

        System.out.print(bold + "Arrivée: ");
        String arrivee = sc.nextLine();

        Trajet t = new Trajet(numLigne, depart, arrivee);

        int nbrArrets = 0;
        System.out.print(bold + "Combien d'arrêts se trouvent entre " + yellow + depart + reset + " et " + yellow + arrivee + reset + ": ");
        nbrArrets = sc.nextInt();
        sc.nextLine();

        if(nbrArrets != 0){
            for (int i = 1; i <= nbrArrets; i++){
                System.out.print(bold + "Arrêt " + i + ": ");
                String adresse = sc.nextLine();
                t.AddArret(new Arret(adresse));
            }
        }

        try {
            JsonSerializer.add(t, "trajets.json");
            MessageBox.Alert("Trajet ajouté avec succès !","Information");
        }
        catch (Exception e){
            MessageBox.Alert("Une erreur s'est produite lors de l'ajout du trajet.","Alert");
        }
    }

    public static void ModifierTrajet() {
        Scanner sc = new Scanner(System.in);
        String numLigne;
        while (true) {
            System.out.print("\033[0;36mNuméro de la ligne que vous souhaitez modifier son trajet: \033[0m");
            numLigne = sc.nextLine();
            if (numLigne.matches("\\d+")) {
                break;
            }
            MessageBox.Alert("Veuillez entrer un nombre valide.","Alert");
        }
        ArrayList<Trajet> trajets = JsonSerializer.deserialize("src/Data/trajets.json", Trajet.class);
        Trajet trajet = JsonSerializer.getObject(trajets, "num_ligne", numLigne);
        if (trajet == null) {
            MessageBox.Alert("Ligne n'existe pas!!", "Alert");
            ModifierTrajet();
            return;
        }
        System.out.println(trajet);
        while (true) {
            Menu menu = new Menu("Que souhaitez-vous modifier ?", Arrays.asList("Retour", "Les arrêts", "Le point de départ", "Le point d'arrivée"));
            menu.showMenuWithoutCaractère();
            int choix = menu.selectOption();
            if (choix == 0) {
                return;
            }
            Trajet newTrajet = new Trajet(trajet);
            switch (choix) {
                case 1:
                    System.out.println(trajet.getStringArrets());
                    menu = new Menu("Que ce que vous voulez faire ?", Arrays.asList("Retour", "Ajouter arrêt", "Modifier arrêt", "Supprimer arrêt"));
                    menu.showMenuWithoutCaractère();
                    choix = menu.selectOption();
                    if (choix == 0) {
                        return;
                    }
                    switch (choix) {
                        case 1:
                            System.out.println(trajet.getStringArrets());
                            System.out.print("\033[0;36ml'Adresse de la nouvelle arrêt: \033[0m");
                            String adresse = sc.nextLine();
                            System.out.print("\033[0;36mSon ordre par rapport aux autres arrêts de ce trajet: \033[0m");
                            int num_trajet = sc.nextInt();
                            try {
                                 newTrajet.getArrets().add(num_trajet - 1,new Arret(adresse));
                            }catch (Exception e) {
                                MessageBox.Alert("insertion erronée","Alert");
                            }
                            break;
                        case 2:
                            System.out.println(trajet.getStringArrets());
                            System.out.print("\033[0;36mNuméro d'arrêt que vous souhaitez modifier: \033[0m");
                            int numArret = sc.nextInt();
                            sc.nextLine();
                            if (numArret <= 0 || numArret > trajet.getArrets().size()) {
                                MessageBox.Alert("Arrêt n'existe pas!!", "Alert");
                                break;
                            }
                            System.out.println("\033[33m | \033[0m" + numArret + " - " + trajet.getArret(numArret - 1).getAdresse() + "\033[33m | \033[0m");
                            System.out.print("\033[0;36mNouvelle adresse de l'arrêt: \033[0m");
                            adresse = sc.nextLine();
                            newTrajet.getArrets().get(numArret - 1).setAdresse(adresse);
                            break;
                        case 3:
                            System.out.println(trajet.getStringArrets());
                            System.out.print("\033[0;36mNuméro d'arrêt que vous souhaitez supprimer: \033[0m");
                            numArret = sc.nextInt();
                            sc.nextLine();
                            if (numArret <= 0 || numArret > trajet.getArrets().size()) {
                                MessageBox.Alert("Arrêt n'existe pas!!", "Alert");
                                break;
                            }
                            if (MessageBox.Confirmer("Voulez-vous vraiment supprimer l'arrêt "+newTrajet.getArrets().get(numArret - 1).getAdresse()+" ?", "Confirmation")) {
                                newTrajet.getArrets().remove(numArret - 1);
                            }
                            else {
                                MessageBox.Alert("suppression erronée", "Alert");
                            }
                            break;
                    }

                    break;
                case 2:
                    System.out.println(trajet.getStringDepartArrivee());
                    System.out.print("\033[33mLe nouveau point de départ: \033[0m");
                    String depart = sc.nextLine();
                    newTrajet.setDepart(depart);
                    break;
                case 3:
                    System.out.println(trajet.getStringDepartArrivee());
                    System.out.print("\033[33mLe nouveau point d'arrivée: \033[0m");
                    String arrivee = sc.nextLine();
                    newTrajet.setArrive(arrivee);
                    break;
                default:
                    break;
            }
            try {
                JsonSerializer.modify(trajet, newTrajet, "trajets.json");
                MessageBox.Alert("Modification effectuée avec succès.", "Modification réussie");
                trajet = newTrajet;
            } catch (Exception e) {
                MessageBox.Alert("Une erreur est survenue lors de la modification.", "Modification échouée");
            }
        }
    }

    public static void SupprimerTrajet(){
        Scanner sc = new Scanner(System.in);
        String numLigne;
        while (true) {
            System.out.print("\033[0;36mNuméro de la ligne que vous souhaitez supprimer son trajet: \033[0m");
            numLigne = sc.nextLine();
            if (numLigne.matches("\\d+")) {
                break;
            }
            MessageBox.Alert("Veuillez entrer un nombre valide.","Alert");
        }
        ArrayList<Trajet> trajet = JsonSerializer.deserialize("src/Data/trajets.json", Trajet.class);
        Trajet t = JsonSerializer.getObject(trajet,"num_ligne",numLigne);
        if(t != null){
            System.out.println(t);
            System.out.print("\033[0;36mÊtes-vous sûr de vouloir supprimer ce trajet? (oui/non): \033[0m");
            String confirmation = sc.nextLine();
            if(confirmation.equalsIgnoreCase("oui")){
                try {
                    JsonSerializer.remove(t, "trajets.json");
                    MessageBox.Alert("Le trajet a été supprimé avec succès", "Suppression réussite");
                }catch (Exception e){
                    MessageBox.Alert("Une erreur est survenue lors de la suppression du trajet", "Suppression erronée");
                }
            }
        }
        else{
            MessageBox.Alert("Ligne n'existe pas!!","Alert");
            SupprimerTrajet();
        }
    }

    public static void AfficherTrajets(){
        ArrayList<Trajet> trajets = JsonSerializer.deserialize("src/Data/trajets.json", Trajet.class);
        for (Trajet t : trajets) {
            System.out.println(t);
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[0;36mSaisir n'importe quoi pour revenir au menu précédent: \033[0m");
        String input = sc.nextLine();
    }

}
