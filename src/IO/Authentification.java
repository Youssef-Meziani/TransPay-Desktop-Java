package IO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Classes.Client;
import Classes.Employe;

public class Authentification {
    public static Object login(Boolean isClient) {
        Scanner sc = new Scanner(System.in);
        String cin = getCIN(sc);
        System.out.print("Mot de passe: ");
        String pwd = sc.nextLine();
        if (isClient) {
            ArrayList<Client> list = JsonSerializer.deserialize("src/Data/clients.json", Client.class);
            Client c;
            if ((c = JsonSerializer.getObject(list, "cin", cin)) != null) {
                if (c.getPswd().equals(pwd)) {
                    return c;
                }
            }
        } else {
            ArrayList<Employe> list = JsonSerializer.deserialize("src/Data/employees.json",
                    Employe.class);
            Employe e;
            if ((e = JsonSerializer.getObject(list, "cin", cin)) != null) {
                if (e.getPswd().equals(pwd)) {
                    return e;
                }
            }
        }
        return null;
    }

    public static void signUp() {
        Scanner sc = new Scanner(System.in);

        String cin = getCIN(sc);
        if (CINExist(cin)) {
            MessageBox.Alert("CIN appartient déjà à un compte !", "Compte existant");
            return;
        }

        String nom = getNom(sc);
        String prenom = getPrenom(sc);
        char sexe = getSexe(sc);
        Date date = getDateNaissance(sc);
        String tel = getTelephone(sc);
        String adresse = getAdresse(sc);
        String langue_pref = getLanguePreferee(sc);
        String email = getEmail(sc);
        String pwd = getMotDePasse(sc);

        Client c = new Client(cin, nom, prenom, sexe, date, email, pwd, tel, adresse, true, langue_pref, 0);
        JsonSerializer.add(c, "clients.json");
        MessageBox.Alert("vous êtes inscrit","inscription réussite");
    }

    public static String getCIN(Scanner sc) {
        String cin;
        do {
            System.out.print("CIN: ");
            cin = sc.nextLine().toLowerCase();
            if (!cin.matches("^[a-zA-Z]{1,2}[0-9]{1,6}$")) {
                MessageBox.Alert("Veuillez saisir un identifiant valide !", "CIN invalide");
                Tools.deleteLine();
            }
        } while (!cin.matches("^[a-zA-Z]{1,2}[0-9]{1,6}$"));
        return cin;
    }

    public static boolean CINExist(String cin) {
        ArrayList<Client> list = JsonSerializer.deserialize("src/Data/clients.json", Client.class);
        return JsonSerializer.getObject(list, "cin", cin) != null;
    }

    public static String getNom(Scanner sc) {
        System.out.print("Nom: ");
        return sc.nextLine().toUpperCase();
    }

    public static String getPrenom(Scanner sc) {
        System.out.print("Prenom: ");
        String prenom = sc.nextLine();
        return prenom.toUpperCase().charAt(0) + prenom.substring(1, prenom.length()).toLowerCase();
    }

    public static char getSexe(Scanner sc) {
        char sexe;
        do {
            System.out.print("Sexe (H/F): ");
            sexe = sc.nextLine().toLowerCase().charAt(0);
            if (sexe != 'h' && sexe != 'f') {
                MessageBox.Alert("Veuillez respecter la forme demandé !", "Sexe invalide");
                Tools.deleteLine();
            }
        } while (sexe != 'h' && sexe != 'f');
        return sexe;
    }

    public static Date getDateNaissance(Scanner sc) {
        Date date = null;
        do {
            System.out.print("Date de naissance (jj/mm/aaaa): ");
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
                break;
            } catch (ParseException e) {
                MessageBox.Alert("Veuillez respecter la forme jour/mois/année !", "Date invalide");
                Tools.deleteLine();
            }
        } while (true);
        return date;
    }

    public static String getTelephone(Scanner sc) {
        String tel;
        do {
            System.out.print("Téléphone: ");
            tel = sc.nextLine();
            if (!tel.matches("^(\\+212|0(6|7|5))[0-9]{8}$")) {
                MessageBox.Alert("Veuillez saisir un numéro de téléphone valide !", "Numéro invalide");
                Tools.deleteLine();
            }
        } while (!tel.matches("^(\\+212|0(6|7|5))[0-9]{8}$"));
        return tel;
    }

    public static String getAdresse(Scanner sc) {
        System.out.print("Adresse: ");
        return sc.nextLine();
    }

    public static String getLanguePreferee(Scanner sc) {
        String langue_pref;
        do {
            System.out.print("Langue Préférer (AR/FR/EN): ");
            langue_pref = sc.nextLine().toLowerCase();
            if (!langue_pref.equals("ar") && !langue_pref.equals("fr") && !langue_pref.equals("en")) {
                MessageBox.Alert("Veuillez choisir une des trois langues !", "Langue invalide");
                Tools.deleteLine();
            }
        } while (!langue_pref.equals("ar") && !langue_pref.equals("fr") && !langue_pref.equals("en"));
        return langue_pref;
    }

    public static String getEmail(Scanner sc) {
        String email;
        do {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                MessageBox.Alert("Veuillez saisir une adresse email valide !", "Email invalide");
                Tools.deleteLine();
            }
        } while (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        return email;
    }

    public static String getMotDePasse(Scanner sc) {
        String pwd1, pwd2 = null;
        do {
            System.out.print("Mot de passe: ");
//            pwd1 = String.valueOf(System.console().readPassword());
            pwd1 = sc.nextLine();
            if (pwd1.length() < 6) {
                MessageBox.Alert("Saisir au moins 6 caractères", "Mot de passe trop court");
                Tools.deleteLine();
                continue;
            }
            System.out.print("Confirmez le mot de passe: ");
//            pwd2 = String.valueOf(System.console().readPassword());
            pwd2 = sc.nextLine();
            if (!pwd1.equals(pwd2)) {
                Tools.deleteLines(2);
                MessageBox.Alert("Les mots de passe ne correspondent pas !", "Mot de passe incorrect");
            }
        } while (!pwd1.equals(pwd2));
        return pwd1;
    }
}
