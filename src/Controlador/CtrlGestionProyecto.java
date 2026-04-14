package Controlador;

import Modelo.ConsultasProyecto;
import Modelo.ConsultasTarea;
import Modelo.ConsultasReporte;
import Modelo.Usuario;
import Modelo.ConsultasRiesgo;

import Vista.Riesgos;
import Vista.GestionProyecto;
import Vista.MenuPrincipalTaskSphere;
import Vista.RegistrarProyecto;
import Vista.Tareas;
import Vista.Costos;
import Vista.Cronograma;
import Vista.GenerarReporte;
import Controlador.CtrlRiesgos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlGestionProyecto implements ActionListener {

    private ConsultasProyecto consultas;
    private GestionProyecto vista;
    private Usuario usuarioActivo;

    public CtrlGestionProyecto(ConsultasProyecto consultas, GestionProyecto vista, Usuario usuarioActivo) {
        this.consultas = consultas;
        this.vista = vista;
        this.usuarioActivo = usuarioActivo;

        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnRegistrarProyecto1.addActionListener(this);
        this.vista.btnTareas1.addActionListener(this);
        this.vista.btnCronograma1.addActionListener(this);
        this.vista.btnRiesgos1.addActionListener(this);
        this.vista.btnCostos1.addActionListener(this);
        this.vista.btnReportes.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Gestión de Proyectos");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnRegresar) {
            volverAlMenu();
        }

        if (e.getSource() == vista.btnRegistrarProyecto1) {
            abrirRegistrarProyecto();
        }

        if (e.getSource() == vista.btnTareas1) {
            abrirTareas();
        }

        if (e.getSource() == vista.btnCostos1) {
            abrirCostos();
        }

        if (e.getSource() == vista.btnCronograma1) {
            abrirCronograma();
        }

        if (e.getSource() == vista.btnRiesgos1) {
            abrirRiesgos();
        }

        if (e.getSource() == vista.btnReportes) {
            abrirReportes();
        }
    }

    private boolean validarSesion() {
        if (usuarioActivo == null) {
            JOptionPane.showMessageDialog(vista,
                    "No hay un usuario activo. Inicie sesión nuevamente.",
                    "Sesión inválida",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void volverAlMenu() {
        if (!validarSesion()) {
            return;
        }

        MenuPrincipalTaskSphere menu = new MenuPrincipalTaskSphere(usuarioActivo, false);
        CtrlMenuPrincipal ctrlMenu = new CtrlMenuPrincipal(menu, usuarioActivo);

        ctrlMenu.iniciar();
        menu.setVisible(true);
        vista.setVisible(false);

    }
    private void abrirRegistrarProyecto() {
        if (!validarSesion()) {
            return;
        }

        RegistrarProyecto reg = new RegistrarProyecto(usuarioActivo);
        ConsultasProyecto consultas = new ConsultasProyecto();
        CtrlRegistrarProyecto ctrl = new CtrlRegistrarProyecto(consultas, reg, usuarioActivo);

        ctrl.iniciar();
        reg.setVisible(true);
        vista.setVisible(false);

    }

    private void abrirTareas() {
        if (!validarSesion()) {
            return;
        }

        if (!usuarioActivo.getRol().equalsIgnoreCase("Colaborador")) {
            JOptionPane.showMessageDialog(vista,
                    "Solo los COLABORADORES pueden acceder a la gestión de tareas.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tareas vistaT = new Tareas();
        ConsultasTarea consultasT = new ConsultasTarea();
        CtrlTareas ctrlT = new CtrlTareas(vistaT, consultasT, usuarioActivo.getId(), usuarioActivo);

        ctrlT.iniciar();
        vistaT.setVisible(true);
        vista.setVisible(false);

    }

    private void abrirCostos() {
        if (!validarSesion()) {
            return;
        }

        Costos c = new Costos(vista, usuarioActivo);
        new CtrlCostos(c, vista, usuarioActivo);

        c.setVisible(true);
        vista.setVisible(false);
    }

    private void abrirCronograma() {
        if (!validarSesion()) {
            return;
        }

        Cronograma cro = new Cronograma(usuarioActivo);
        ConsultasTarea consultasT = new ConsultasTarea();

        CtrlCronograma ctrlC = new CtrlCronograma(cro, consultasT, usuarioActivo);
        ctrlC.iniciar();

        vista.dispose(); 
    }

    private void abrirRiesgos() {
        if (!validarSesion()) {
            return;
        }

        Riesgos vr = new Riesgos();
        ConsultasRiesgo consultasR = new ConsultasRiesgo();
        ConsultasProyecto consultasP = new ConsultasProyecto();

        CtrlRiesgos ctrlR = new CtrlRiesgos(vr, consultasR, consultasP, usuarioActivo);

        ctrlR.iniciar();
        vista.setVisible(false);

    }

    private void abrirReportes() {
        if (!validarSesion()) {
            return;
        }

        if (!usuarioActivo.getRol().equalsIgnoreCase("Administrador")) {
            JOptionPane.showMessageDialog(vista,
                    "Solo los ADMINISTRADORES pueden acceder a la generación de reportes.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        GenerarReporte v = new GenerarReporte();
        ConsultasReporte consultasR = new ConsultasReporte();
        CtrlReporte ctrl = new CtrlReporte(v, consultasR, usuarioActivo);

        ctrl.iniciar();
        v.setVisible(true);
        vista.setVisible(false);

    }
}
