package Main;

import Controlador.CtrlLogin;
import Modelo.ConsultasUsuario;
import Modelo.Usuario;
import Vista.Login;

public class Main {

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("No fue posible aplicar el tema visual.");
        }

        javax.swing.SwingUtilities.invokeLater(() -> {

            Usuario modelo = new Usuario();
            ConsultasUsuario consultas = new ConsultasUsuario();

            Login vista = new Login();
            CtrlLogin ctrl = new CtrlLogin(modelo, consultas, vista);
            
            vista.setControlador(ctrl);

            ctrl.iniciar();

            vista.setVisible(true);
        });
    }
}