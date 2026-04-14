package Controlador;

import Modelo.ConsultasUsuario;
import Modelo.Usuario;
import Vista.Login;
import Vista.MenuPrincipalTaskSphere;
import Vista.RegistroUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlLogin implements ActionListener {

    private Usuario modelo;
    private ConsultasUsuario consultas;
    private Login vista;

    public CtrlLogin(Usuario modelo, ConsultasUsuario consultas, Login vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;

        vista.btnLogin.addActionListener(this);
        vista.btnSalir.addActionListener(this);
        vista.btnRegistrar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Inicio de sesión - TaskSphere");
        vista.setLocationRelativeTo(null);
    }

    private void limpiar() {
        vista.txtUsuario.setText("");
        vista.txtContrasena.setText("");
        vista.cmbRol.setSelectedIndex(0);
    }

    public void iniciarSesion(String user, String pass, String rol) {

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Complete todos los campos.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (consultas.validar(user, pass, rol, modelo)) {

            JOptionPane.showMessageDialog(vista,
                "Bienvenido " + modelo.getNombre() + " – Rol: " + modelo.getRol(),
                "Acceso concedido",
                JOptionPane.INFORMATION_MESSAGE);

            MenuPrincipalTaskSphere menu = new MenuPrincipalTaskSphere(modelo);
            menu.setName("MenuPrincipalTaskSphere");
            CtrlMenuPrincipal ctrlMenu = new CtrlMenuPrincipal(menu, modelo);
            ctrlMenu.iniciar();
            menu.setVisible(true);
            vista.dispose();

        } else {
            JOptionPane.showMessageDialog(vista,
                    "Usuario, contraseña o rol incorrectos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            limpiar();
        }
    }

    public void abrirRegistro() {
        RegistroUsuario registro = new RegistroUsuario("login", null);

        ConsultasUsuario con = new ConsultasUsuario();
        CtrlRegistroUsuario ctrl = new CtrlRegistroUsuario(con, registro);

        registro.setControlador(ctrl);
        ctrl.iniciar();
        registro.setVisible(true);
        vista.dispose();
    }

    public void salir() {
        int opcion = JOptionPane.showConfirmDialog(vista,
                "¿Desea salir del sistema?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnLogin) {
            String user = vista.txtUsuario.getText().trim();
            String pass = new String(vista.txtContrasena.getPassword());
            String rol = vista.cmbRol.getSelectedItem().toString();
            iniciarSesion(user, pass, rol);
        }

        if (e.getSource() == vista.btnRegistrar) {
            abrirRegistro();
        }

        if (e.getSource() == vista.btnSalir) {
            salir();
        }
    }
}