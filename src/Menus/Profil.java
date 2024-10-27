package Menus;

import java.util.Arrays;

import Classes.Client;
import Classes.Employe;
import IO.MessageBox;
import IO.JsonSerializer;
import IO.Tools;
import session.user;

public class Profil {

    public static void show(boolean isClient) {
        Menu profil = new Menu("PROFIL", Arrays.asList("Retour",
                "Afficher mes informations",
                "Modifier mes informations", "Changer mon mot de passe"));
        
        while (true) {
            profil.showMenu(isClient);
            switch (profil.selectOption()) {
                case 1:
                    if (isClient) {
                        System.out.println(user.client.toString());
                    } else {
                        System.out.println(user.employe.toString());
                    }
                    Tools.pause();
                    break;
                case 2:
                    if (isClient) {
                        Client n_client = user.client.edit();
                        if (!Tools.confirmEdit()) {
                            break;
                        }
                        JsonSerializer.modify(user.client, n_client, "clients.json");
                        user.client = n_client;
                    } else {
                        Employe n_emp = user.employe.edit();
                        if (!Tools.confirmEdit()) {
                            break;
                        }
                        JsonSerializer.modify(user.employe, n_emp, "employes.json");
                        user.employe = n_emp;
                    }
                    MessageBox.Alert("Informations changé avec succès.", "Succès");
                    break;
                case 3:
                    if (isClient) {
                        user.client.changePassword();
                    } else {
                        user.employe.changePassword();
                    }
                    break;
                default:
                    return;
            }
        }
    }
    
}