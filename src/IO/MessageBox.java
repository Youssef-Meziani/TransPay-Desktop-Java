package IO;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageBox {

    public static Boolean Confirmer(String message, String title) {
        return JOptionPane.showConfirmDialog(createParent(), message, title, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public static void Quitter() {
        if (Confirmer("Voulez-vous vraiment quitter ?", "Quitter")) {
            System.exit(0);
        }
    }

    public static void Alert(String message, String title) {
        JOptionPane.showMessageDialog(createParent(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static JFrame createParent() {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setVisible(false);
        return frame;
    }
}
