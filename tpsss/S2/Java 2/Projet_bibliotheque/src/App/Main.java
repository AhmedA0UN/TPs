package App;

import Vues.AppTheme;
import Vues.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        AppTheme.applyGlobalLook();
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}