package Menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Classes.Client;
import Classes.Groupe;
import IO.Authentification;
import IO.MessageBox;
import IO.Tools;
import session.user;
import IO.JsonSerializer;

public class Groupes {
    public static void show() {
        switch (user.client.getRole()) {
            case 'a':
                admin();
                break;
            case 'm':
                member();
                break;

            default:
                normale();
                break;
        }
    }

    public static void admin() {

        Menu g_menu = new Menu("GROUPE", Arrays.asList("Retour", "Consulter mon groupe", "Paramètre de paiement",
                "Retirer un membre", "Supprimer mon groupe"));
        Groupe groupe=getAdminGroupe(user.client.getCin());

        while (true) {
            g_menu.showMenu(true);
            switch (g_menu.selectOption()) {
                case 1:
                    printGroupeInfo(groupe);
                    Tools.pause();
                    // TODO : A eleminer se code
                    // Scanner sc = new Scanner(System.in);
                    // System.out.print("\033[0;36mSaisir n'importe quoi pour revenir au menu précédent: \033[0m");
                    // String input = sc.nextLine();
                    break;
                case 2:
                    //
                    break;
                case 3:
                    if(groupe.getNbrMembers() == 0){
                        MessageBox.Alert("Ton groupe est vide", "Alert");
                        break;
                    }
                    printGroupeInfo(groupe);
                    // TODO : Ajouter Scanner !!
                    Scanner sc = new Scanner(System.in);
                    int option=-1;
                    do {
                        System.out.print("\033[0;36mNuméro du membre a supprimer => \033[0m");
                        if (!sc.hasNextInt()) {
                            System.out.print("\033[1A\033[K\u001B[31m=>\u001B[0m");
                            sc.next();
                            continue;
                        }
                        option = sc.nextInt();
                        if (option < 1 || option > groupe.getNbrMembers()) {
                            MessageBox.Alert("Membre introuvable !", "Option invalide");
                            System.out.print("\033[1A\033[K\u001B[31m=>\u001B[0m");
                        }
                    } while (option < 1 || option > groupe.getNbrMembers());

                    if (MessageBox.Confirmer("Voulez-vous retirer ce membre de votre groupe ?", "Retirer membre")) {

                        ArrayList<Client> clients=JsonSerializer.deserialize("src/Data/clients.json", Client.class);

                        Client client= JsonSerializer.getObject(clients, "cin", groupe.getMember(option-1));
//                        assert client != null;   // TODO : effectuer des vérifications conditionnelles pendant le développement et les tests.
                        client.setRole(' ');

                        JsonSerializer.serialize(clients, "src/Data/clients.json");

                        Groupe old_g=new Groupe(groupe);
                        groupe.removeMember(client.getCin());
                        JsonSerializer.modify(old_g, groupe, "groupes.json");
                        return;
                    }
                    break;

                case 4:
                    if (MessageBox.Confirmer("Voulez-vous supprimer ce groupe ?\nTous les membres vont être retirés.", "Suppression")) {
                        ArrayList<Client> clients=JsonSerializer.deserialize("src/Data/clients.json", Client.class);
                        for (String cin : groupe.getMembers()) {
                            JsonSerializer.getObject(clients, "cin", cin).setRole(' ');
                        }

                        JsonSerializer.getObject(clients, "cin", user.client.getCin()).setRole(' ');
                        user.client.setRole(' ');

                        JsonSerializer.serialize(clients, "src/Data/clients.json");

                        JsonSerializer.remove(groupe, "groupes.json");
                    }
                    return;

                default:
                    return;
            }
        }
    }

    public static void member() {

        Menu g_menu = new Menu("GROUPE", Arrays.asList("Retour", "Consulter mon groupe", "Quitter le groupe"));

        Groupe groupe=getMemberGroupe(user.client.getCin());

        while (true) {
            g_menu.showMenu(true);
            switch (g_menu.selectOption()) {
                case 1:
                    printGroupeInfo(groupe);
                    Tools.pause();
                    break;
                case 2:
                    if (MessageBox.Confirmer("Voulez-vous quitter ce groupe ?", "Quitter groupe")) {
                        Client old_client= new Client(user.client);
                        user.client.setRole(' ');
                        JsonSerializer.modify(old_client, user.client, "clients.json");
                    }
                    break;

                default:
                    return;
            }
        }
    }

    public static void normale() {

        Menu g_menu = new Menu("GROUPE", Arrays.asList("Retour", "Créer un groupe", "Rejoindre un groupe"));

        while (true) {
            g_menu.showMenu(true);
            switch (g_menu.selectOption()) {
                case 1:
                    createGroupe();
                    return;
                case 2:
                    joinGroupe();
                    return;

                default:
                    return;
            }
        }
    }

    private static void joinGroupe() {
        System.out.println("\033[0;36mSaisir le CIN de l'admin du groupe à rejoindre\033[0m");
        String cin = Authentification.getCIN(new Scanner(System.in));

        ArrayList<Groupe> groupes=JsonSerializer.deserialize("src/Data/groupes.json", Groupe.class);
        Groupe groupe=JsonSerializer.getObject(groupes, "admin", cin);
        if (groupe==null) {
            MessageBox.Alert("Ce groupe n'existe pas !", "Introuvable");
        }else if (groupe.getNbrMembers()>=4) {
            MessageBox.Alert("Ce groupe a atteint le nombre maximum de membres.", "Groupe saturé");
        }else{
            Client old_client = new Client(user.client);
            user.client.setRole('m');
            JsonSerializer.modify(old_client, user.client, "clients.json");
            groupe.addMember(user.client.getCin());
            JsonSerializer.serialize(groupes, "src/Data/groupes.json");
            MessageBox.Alert("Vous êtes ajouter a ce groupe","ajout réussi");
        }
    }

    private static void createGroupe() {
        //todo: nzido le nom o le cout:
        JsonSerializer.add(new Groupe("", 0, user.client.getCin()), "groupes.json");
        Client old_client= new Client(user.client);
        user.client.setRole('a');
        JsonSerializer.modify(old_client, user.client, "clients.json");
        MessageBox.Alert("Groupe créer avec succès.", "Succès");
    }

    public static Groupe getAdminGroupe(String cin){
        return JsonSerializer.getObject(JsonSerializer.deserialize("src/Data/groupes.json", Groupe.class), "admin", cin);
    }

    public static Groupe getMemberGroupe(String cin){
        ArrayList<Groupe> groupes=JsonSerializer.deserialize("src/Data/groupes.json", Groupe.class);
        for (Groupe groupe : groupes) {
            for (String member : groupe.getMembers()) {
                if (member.equals(cin)) {
                    return groupe;
                }
            }
        }
        return null;
    }

    public static void printGroupeInfo(Groupe g){
        Tools.clearConsole();
        System.out.print(g);
    }
}
