package com.ipc1_proyecto1.main;
import com.ipc1_proyecto1.controllers.UserController;
import com.ipc1_proyecto1.controllers.StartController;
import com.ipc1_proyecto1.views.Login;
import javax.swing.SwingUtilities;


/**
 * @author Luis Monterroso
 */
public class BibliotecaUniversitaria {

    public static void main(String[] args) {
        StartController start = new StartController();
        start.startSystem();
            SwingUtilities.invokeLater(() -> {
                Login login = new Login();
                login.setVisible(true);
            });
    }
}
