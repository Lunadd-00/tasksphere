package Controlador;

import Modelo.ConsultasProyecto;
import Modelo.ConsultasTarea;
import Modelo.Usuario;
import Modelo.ConsultasUsuario;
import Vista.MenuPrincipalTaskSphere;
import Vista.RegistroUsuario;
import Vista.GestionProyecto;
import Vista.GestionTareas;
import Vista.Login;
import Vista.VistaUsuario;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMenuPrincipal implements ActionListener {

    private MenuPrincipalTaskSphere vista;
    private Usuario usuarioActivo;

    public CtrlMenuPrincipal(MenuPrincipalTaskSphere vista, Usuario usuarioActivo) {
        this.vista = vista;
        this.usuarioActivo = usuarioActivo;

        vista.btnRegistro.addActionListener(this);
        vista.btnGProyectos.addActionListener(this);
        vista.btnGTareas.addActionListener(this);
        vista.btnUsuarios.addActionListener(this);
        vista.btnSalir.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Menú Principal - TaskSphere");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == vista.btnRegistro) {
            RegistroUsuario reg = new RegistroUsuario("menu", usuarioActivo);
            ConsultasUsuario consultas = new ConsultasUsuario();
            CtrlRegistroUsuario ctrl = new CtrlRegistroUsuario(consultas, reg);
            reg.setControlador(ctrl);
            ctrl.iniciar();
            reg.setVisible(true);
            vista.setVisible(false);

        }

        if (e.getSource() == vista.btnGProyectos) {

            GestionProyecto v = new GestionProyecto(vista, usuarioActivo);

            ConsultasProyecto consultasGP = new ConsultasProyecto();
            CtrlGestionProyecto ctrl = new CtrlGestionProyecto(consultasGP, v, usuarioActivo);
            ctrl.iniciar();
            v.setVisible(true);
            vista.setVisible(false);

        }

        if (e.getSource() == vista.btnGTareas) {

            if (!usuarioActivo.getRol().equals("Administrador")) {
                JOptionPane.showMessageDialog(vista,
                        "Solo los administradores pueden acceder a la gestión de tareas.",
                        "Acceso denegado",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                GestionTareas t = new GestionTareas(vista, usuarioActivo);

                ConsultasTarea ct = new ConsultasTarea();
                ConsultasProyecto cp = new ConsultasProyecto();
                ConsultasUsuario cu = new ConsultasUsuario();

                CtrlGestionTareas ctrl = new CtrlGestionTareas(t, ct, cp, cu);
                ctrl.iniciar();
                t.setVisible(true);
                vista.setVisible(false);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,
                        "Error al abrir gestión de tareas.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnUsuarios) {
            
            VistaUsuario vu = new VistaUsuario(vista, usuarioActivo);
            ConsultasTarea consultas = new ConsultasTarea();
            CtrlVistaUsuario ctrlVU = new CtrlVistaUsuario(vu, consultas, usuarioActivo.getId());
            ctrlVU.iniciar();

            vista.setVisible(false);
            vu.setVisible(true);

        }

        if (e.getSource() == vista.btnSalir) {

            int op = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Desea cerrar sesión?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (op == JOptionPane.YES_OPTION) {
                Login login = new Login();
                Usuario modeloLogin = new Usuario();
                ConsultasUsuario consultas = new ConsultasUsuario();

                CtrlLogin ctrlLogin = new CtrlLogin(modeloLogin, consultas, login);
                login.setControlador(ctrlLogin);
                ctrlLogin.iniciar();
                login.setVisible(true);

                vista.setVisible(false);

            }
        }
    }
}
