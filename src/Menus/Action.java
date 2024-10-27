package Menus;

import java.util.Arrays;
import Classes.Client;
import IO.Authentification;
import IO.MessageBox;
import session.user;

public class Action {

    public static void Client() {
        Menu action = new Menu("TransPay",
                Arrays.asList("Retour", "Se connecter", "Créer un compte"));
        while (true) {
            action.showMenu(true);
            switch (action.selectOption()) {
                case 1 -> {
                    user.client = (Client) Authentification.login(true);
                    if (user.client != null) {
                        Home.Aceuil();
                        user.client=null;
                    } else {
                        MessageBox.Alert("Cin ou mot de passe erroné !", "Avertissement");
                    }
                }
                case 2 -> Authentification.signUp();
                default -> {
                    return;
                }
            }
        }
    }
}