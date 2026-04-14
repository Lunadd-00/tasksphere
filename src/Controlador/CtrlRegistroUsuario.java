package Controlador;

import Modelo.Usuario;
import Modelo.ConsultasUsuario;
import Vista.RegistroUsuario;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlRegistroUsuario implements ActionListener {

    private ConsultasUsuario consultas;
    private RegistroUsuario vista;

    public CtrlRegistroUsuario(ConsultasUsuario consultas, RegistroUsuario vista) {
        this.consultas = consultas;
        this.vista = vista;
    }

    public void iniciar() {
        vista.setTitle("Registrar Usuario");
        vista.setLocationRelativeTo(null);
    }

    private void limpiar() {
        vista.txtNombre.setText("");
        vista.txtUsuario.setText("");
        vista.txtContrasena.setText("");
        vista.txtCContrasena.setText("");
        vista.cmbRol.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnRegistrar) {

            String nombre = vista.txtNombre.getText().trim();
            String usuario = vista.txtUsuario.getText().trim();
            String pass = new String(vista.txtContrasena.getPassword());
            String pass2 = new String(vista.txtCContrasena.getPassword());
            String rol = vista.cmbRol.getSelectedItem().toString();

            if (nombre.isEmpty() || usuario.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Completa todos los campos.");
                return;
            }

            if (!pass.equals(pass2)) {
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.");
                return;
            }

            if (vista.cmbRol.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vista, "Seleccione un rol válido.");
                return;
            }

            if (rol.equalsIgnoreCase("Administrador")) {
                if (vista.usuarioLogueado == null || 
                    !vista.usuarioLogueado.getRol().equalsIgnoreCase("Administrador")) {

                    JOptionPane.showMessageDialog(vista,
                            "Acción denegada — Solo Administradores pueden crear Administradores.",
                            "Acceso restringido",
                            JOptionPane.WARNING_MESSAGE);

                    return;
                }
            }

            // Crear el usuario
            Usuario nuevo = new Usuario();
            nuevo.setNombre(nombre);
            nuevo.setUsuario(usuario);
            nuevo.setPassword(pass);
            nuevo.setRol(rol);

            if (consultas.registrar(nuevo)) {
                JOptionPane.showMessageDialog(vista, "Usuario registrado correctamente.");
                vista.volverAOrigen();
            } else {
                JOptionPane.showMessageDialog(vista, "El usuario ya existe o hubo un error.");
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == vista.btnCancelar) {
            vista.volverAOrigen();
        }
    }
}