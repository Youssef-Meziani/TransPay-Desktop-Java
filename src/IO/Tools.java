package IO;

import java.util.Scanner;

public class Tools {
    public static void deleteLine() {
        // move the cursor up one line
        System.out.print("\033[1A");
        // clear the entire line
        System.out.print("\033[K");
    }

    public static void clearConsole(){
        System.out.println("\033[H\033[1J");
        System.out.flush();
    }

    public static void deleteLines(int numLines) {
        for (int i = 0; i < numLines; i++) {
            deleteLine();
        }
    }

//    public static void pause() {
//        System.out.print("\nAppuyez sur ENTRER pour retourner...");
//        System.console().readPassword();
//    }

    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nAppuyez sur ENTRER pour retourner...");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        scanner.close();
    }

    public static Boolean confirmEdit() {
        return MessageBox.Confirmer("Vous voullez enregistrer les modifications ?", "Valider modification");
    }
}
