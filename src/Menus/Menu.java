package Menus;

import java.util.List;
import java.util.Scanner;

import IO.MessageBox;
import IO.Tools;

public class Menu {
    protected String title;
    protected List<String> items;

    public Menu(String title, List<String> items) {
        this.title = title;
        this.items = items;
    }

    public int selectOption() {
        int option = -1;
        Scanner sc = new Scanner(System.in);
        do {
            if (!sc.hasNextInt()) {
                System.out.print("\033[1A\033[K\u001B[31m=>\u001B[0m ");
                sc.next();
                continue;
            }
            option = sc.nextInt();
            if (option < 0 || option > items.size()) {
                MessageBox.Alert("L'option que vous avez choisi n'existe pas !", "Option invalide");
                System.out.print("\033[1A\033[K\u001B[31m=>\u001B[0m ");
            }
        } while (option < 0 || option > items.size());
        if (option == items.size()) {
            MessageBox.Quitter();
        }
        return option;
    }

    public void showMenu(Boolean isClient) {
        Tools.clearConsole();
        char sep = (isClient == null) ? '-' : ((isClient) ? '=' : '#');
        String c = "";
        short i;
        for (i = 0; i < 30; i++) {
            c += sep;
        }
        System.out.println("\033[33m"+c+"\033[0m");
        System.out.println("\033[1m" + title + "\033[0m");
        System.out.println("\033[33m"+c+"\033[0m");
        itemsMenu();
    }

    public void showMenuWithoutCaractère() {
        Tools.clearConsole();
        int i;
        System.out.println("\033[0;36m" + title + "\033[0m");
        itemsMenu();
    }

    private void itemsMenu() {
        int i;
        for (i = 0; i < items.size(); i++) {
            if(items.get(i).equals("Retour"))
                System.out.println("\033[0;36m[" + i + "]\033[0m\033[33m " + items.get(i)+"\033[0m");
            else
                System.out.println("\033[0;36m[" + i + "]\033[0m " + items.get(i));
        }
        System.out.println("\033[0;36m[" + i + "]\033[0m\033[33m Quitter\033[0m");
        System.out.print("\033[33m=> \033[0m");
    }

    public void addItem(String item) {
        items.add(item);
    }

    public int getSize() {
        return items.size();
    }
}