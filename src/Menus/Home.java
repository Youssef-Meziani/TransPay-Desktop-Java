package Menus;

import java.util.Arrays;
import IO.MessageBox;
import session.user;

public class Home {

    public static void Aceuil() {
        Menu cm1 = new Menu("Bonjour " + user.client.getFullName() + "\nACCUEIL", Arrays.asList("Se déconnecter",
                "Profil", "Forfaits", "Abonnements", "Groupe", "Notifications", "Assisstance"));

        while (true) {
            cm1.showMenu(true);
            switch (cm1.selectOption()) {
                case 1:
                    Profil.show(true);
                    break;
                case 2:
                    Forfaits.Client();
                    break;
                case 3:
                    //
                    break;
                case 4:
//                    MessageBox.Alert("Cette fonctionnalité va bientôt être disponible au futur.", "Avertissement");
                     Groupes.show();
                    break;
                case 5:
                    //
                    break;
                case 6:
                    //
                    break;
                case 7:
                    //
                    break;

                default:
                    return;
            }
        }
    }

    public static void Dashboard() {
        Menu em1 = new Menu("Bonjour " + user.employe.getFullName() + "\nTABLEAU DE BORD",
                Arrays.asList("Se déconnecter", "Profil", "Statistiques", "Offres", "Fofaits","Trajets", "Notifications"));

        if (user.employe.appartient_serv_client()) {
            em1.addItem("Assisstance");
        }

        while (true) {
            em1.showMenu(false);
            switch (em1.selectOption()) {
                case 1:
                    Profil.show(false);
                    break;
                case 2:
                    Statistique.show();
                    break;
                case 3:
                    //
                    break;
                case 4:
                    Forfaits.Employe();
                    break;
                case 5:
                    Trajets.Employe();
                    break;
                case 6:
                    //
                    break;

                default:
                    return;
            }
        }
    }
}
