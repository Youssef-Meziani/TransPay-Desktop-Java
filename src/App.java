import java.util.Arrays;
import Classes.Employe;
import IO.Authentification;
import IO.MessageBox;
import Menus.Action;
import Menus.Home;
import Menus.Menu;
import session.user;

public class App {
    public static void main(String[] args) {

        Menu m1 = new Menu("Vous êtes ?", Arrays.asList("Un Client",
                "Un Employé"));

        while (true) {
            m1.showMenu(null);

            switch (m1.selectOption()) {
                case 0 -> Action.Client();
                case 1 -> {
                    user.employe = (Employe) Authentification.login(false);
                    if (user.employe != null) {
                        Home.Dashboard();
                        user.employe=null;
                    } else {
                        MessageBox.Alert("Cin ou mot de passe erroné !", "Avertissement");
                    }
                }
                default -> {
                }
            }
        }
    }
}