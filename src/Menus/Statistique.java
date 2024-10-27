package Menus;

import Classes.Client;
import IO.Tools;

public class Statistique {
    public static void show() {
        Client.stats();
        Tools.pause();
    }
}
