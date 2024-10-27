package Menus;

import Classes.Forfait;
import IO.MessageBox;
import IO.JsonSerializer;
import IO.Tools;
import session.user;

import java.util.*;

public class Forfaits {

    public static void Client() {
        List<String> liste = new ArrayList<>();
        liste.add("Retour");
        liste.addAll(AffichersForfait(true));
        Menu cm1 = new Menu("FORFAITS \033[3m\033[33m(choisir le forfait que vous voulez achetez)\033[0m", liste);
        int selectedOption = -1;
        while (true) {
            cm1.showMenu(true);
            selectedOption = cm1.selectOption();
            if (selectedOption == 0) {
                break;
            }
            if (selectedOption >= 1 && selectedOption <= liste.size()) {
                //deserializer le fichier json forfait.json pour recuperer le forfait choisie par le client
                ArrayList<Forfait> forfaits = JsonSerializer.deserialize("src/Data/forfaits.json", Forfait.class);
                // recuperer le forfait pour verifier si il a deja se forfait ou non dans cette methode using "selectedOption"
                AddAbonnementToClient(forfaits.get(selectedOption - 1));
                break;
            }
            if (selectedOption == liste.size() + 1) {
                MessageBox.Quitter();
            }
            else {
                MessageBox.Alert("Choix invalid","Alert");
            }
        }
    }

    public static void Employe() {
        Menu cm1 = new Menu("FORFAITS",
                Arrays.asList("Retour", "Ajouter forfait", "Modifier forfait", "Masquer forfait",
                        "Afficher les forfaits"));
        while (true) {
            cm1.showMenu(false);
            switch (cm1.selectOption()) {
                case 1:
                    AddForfait();
                    break;
                case 2:

                    break;
                case 3:

                    break;

                default:
                    return;
            }
        }
    }

    public static ArrayList<String> AffichersForfait(Boolean showHiden) {
        ArrayList<Forfait> list = JsonSerializer.deserialize("src/Data/forfaits.json", Forfait.class);
        ArrayList<String> listString = new ArrayList<String>();
        for (Forfait f : list) {
            if (showHiden) {
                listString.add(f.toString());
            } else {
                if (f.getVisibilite()) {
                    listString.add(f.toString());
                }
            }
        }
        return listString;
    }

    public static void AddAbonnementToClient(Forfait forfait)
    {
        // verifier si l'abonnement choisie est déja fait pour se client
        if(user.client.getaAbonnement().getId().equals(forfait.getId())){
            MessageBox.Alert("Abonnement déja faite pour vous", "Abonnement existante");
            return;
        }
        // commencer a remplir les donnees nessecaire pour l'abonnement choisie
        Date date_debut,date_fin;
        float cout;
        // saisir la date de debut automatiquement pour cette abonnement
        date_debut = new Date();
        // deduire la date de fin de cette abonnement selon le type de forfait selectionner
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_debut);
        switch (forfait.getType()) {
            case "mensuel" -> {
                calendar.add(Calendar.MONTH, 1);
            }
            case "hebdomadaire" -> {
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
            }
            case "journalier" -> {
                calendar.add(Calendar.DATE, 1);
            }
            case "annuel" -> {
                calendar.add(Calendar.YEAR, 1);
            }
        }
        date_fin = calendar.getTime();
        // TODO : il faut ajouter une methode qui calcule et retourne le cout de l'abonnement d'un maniere auto
        cout = 0;
        // donner le droit a le client de selectionner les trajet inclue dans cette abonnement
    }

    public static void AddForfait() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID: ");
        String id = sc.nextLine();
        if (ForfaitExist(id)) {
            MessageBox.Alert("Forfait déja porte ce ID !", "Forfait existant");
            return;
        }
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        String type = getType(sc);
        float prix = getPrix(sc);
        Boolean isVisible = getVisibilité(sc);
        System.out.print("Déscription: ");
        String description = sc.nextLine();

        Forfait f = new Forfait(id, nom, type, prix, isVisible, description);
        JsonSerializer.add(f, "forfaits.json");
        MessageBox.Alert("Forfait ajoutez avec succès","Information");
    }

    public static boolean ForfaitExist(String id) {
        ArrayList<Forfait> list = JsonSerializer.deserialize("src/Data/forfaits.json", Forfait.class);
        return JsonSerializer.getObject(list, "id", id) != null;
    }

    public static String getType(Scanner sc) {
        String type;
        do {
            System.out.print("Type (mensuel / hebdomadaire / journalier / annuel): ");
            type = sc.nextLine();
            if (!type.equals("mensuel") && !type.equals("hebdomadaire") && !type.equals("journalier")
                    && !type.equals("annuel")) {
                MessageBox.Alert("Veuillez respecter les types demandé !", "Type invalide");
                Tools.deleteLine();
            }
        } while (!type.equals("mensuel") && !type.equals("hebdomadaire") && !type.equals("journalier")
                && !type.equals("annuel"));
        return type;
    }

    public static boolean getVisibilité(Scanner sc) {
        Boolean isVisible = null;
        do {
            System.out.print("Visible (O / N): ");
            if (sc.nextLine().toUpperCase().charAt(0) == 'O') {
                isVisible = true;
            } else if (sc.nextLine().toUpperCase().charAt(0) == 'N') {
                isVisible = false;
            } else {
                MessageBox.Alert("Veuillez respecter le format de la réponse demandé !", "Réponse invalide");
                Tools.deleteLine();
            }
        } while (isVisible == null);
        return isVisible;
    }

    public static float getPrix(Scanner sc) {
        float prix = -1;
        do {
            System.out.print("Prix: ");
            String input = sc.nextLine();
            if (!isNumeric(input)) {
                MessageBox.Alert("Veuillez entrer un nombre valide.", "Réponse invalide");
                continue;
            }
            prix = Float.parseFloat(input);
        } while (prix <= 0);
        return prix;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}