package Controlador;

import Modelo.Proyecto;
import Modelo.ConsultasProyecto;
import Modelo.Usuario;
import Vista.GestionProyecto;
import Vista.RegistrarProyecto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CtrlRegistrarProyecto implements ActionListener {

    private Proyecto modelo;
    private ConsultasProyecto consultas;
    private RegistrarProyecto vista;
    private Usuario usuarioActivo;

    public CtrlRegistrarProyecto(ConsultasProyecto consultas, RegistrarProyecto vista, Usuario usuarioActivo) {
        this.modelo = new Proyecto();
        this.consultas = consultas;
        this.vista = vista;
        this.usuarioActivo = usuarioActivo;

        agregarEventos();
        cargarTabla();
        vista.cargarResponsables(consultas.obtenerIdsUsuarios());
    }

    public void iniciar() {
        vista.setTitle("Registrar Proyecto");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    private void agregarEventos() {
        vista.btnGuardarProyecto.addActionListener(this);
        vista.btnEditarProyecto.addActionListener(this);
        vista.btnEliminarProyecto.addActionListener(this);
        vista.btnRegresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardarProyecto) {
            guardar();
        }
        if (e.getSource() == vista.btnEditarProyecto) {
            actualizar();
        }
        if (e.getSource() == vista.btnEliminarProyecto) {
            eliminar();
        }
        if (e.getSource() == vista.btnRegresar) {
            regresarAlMenu();
        }
    }

    private void guardar() {

        String responsable = vista.getResponsable();
        int idResponsable;

        try {
            idResponsable = Integer.parseInt(responsable);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                    "Debe seleccionar un responsable válido (ID numérico).");
            return;
        }

        modelo.setNombre(vista.getNombre());
        modelo.setDescripcion(vista.getDescripcion());
        modelo.setIdResponsable(idResponsable);
        modelo.setPresupuestoInicial(vista.getPresupuesto());

        try {
            modelo.setFechaInicio(Date.valueOf(vista.getFechaInicio()));
            modelo.setFechaFin(Date.valueOf(vista.getFechaFin()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Las fechas deben tener formato yyyy-MM-dd");
            return;
        }

        if (consultas.registrar(modelo)) {
            JOptionPane.showMessageDialog(vista, "Proyecto registrado correctamente.");
            cargarTabla();
            vista.limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar proyecto.");
        }
    }

    private void actualizar() {

        int fila = vista.getFilaSeleccionada();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un proyecto.");
            return;
        }

        int id = Integer.parseInt(vista.getTblProyectos().getValueAt(fila, 0).toString());
        modelo.setIdProyecto(id);

        String responsable = vista.getResponsable();
        int idResponsable;

        try {
            idResponsable = Integer.parseInt(responsable);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                    "Debe seleccionar un responsable válido (ID numérico).");
            return;
        }

        modelo.setNombre(vista.getNombre());
        modelo.setDescripcion(vista.getDescripcion());
        modelo.setIdResponsable(idResponsable);
        modelo.setPresupuestoInicial(vista.getPresupuesto());

        try {
            modelo.setFechaInicio(Date.valueOf(vista.getFechaInicio()));
            modelo.setFechaFin(Date.valueOf(vista.getFechaFin()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Las fechas deben tener formato yyyy-MM-dd");
            return;
        }

        if (consultas.actualizar(modelo)) {
            JOptionPane.showMessageDialog(vista, "Proyecto actualizado correctamente.");
            cargarTabla();
            vista.limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar.");
        }
    }

    private void eliminar() {

        int fila = vista.getFilaSeleccionada();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un proyecto.");
            return;
        }

        int id = Integer.parseInt(vista.getTblProyectos().getValueAt(fila, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el proyecto?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (consultas.eliminarPorId(id)) {
            JOptionPane.showMessageDialog(vista, "Proyecto eliminado.");
            cargarTabla();
            vista.limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar proyecto.");
        }
    }

    private void cargarTabla() {
        DefaultTableModel modeloTabla = vista.getModeloTabla();
        consultas.cargarTabla(modeloTabla);
    }

    public void cargarProyectoSeleccionado() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            return;
        }

        vista.setNombre(vista.getTblProyectos().getValueAt(fila, 1).toString());
        vista.setDescripcion(vista.getTblProyectos().getValueAt(fila, 2).toString());
        vista.setFechaInicio(vista.getTblProyectos().getValueAt(fila, 3).toString());
        vista.setFechaFin(vista.getTblProyectos().getValueAt(fila, 4).toString());
        vista.setPresupuesto(Double.parseDouble(vista.getTblProyectos().getValueAt(fila, 5).toString()));
        vista.setResponsable(vista.getTblProyectos().getValueAt(fila, 6).toString());
    }   
    
    private void regresarAlMenu() {
        vista.dispose();

        GestionProyecto vistaGP = new GestionProyecto(usuarioActivo);
        ConsultasProyecto consultasGP = new ConsultasProyecto();

        CtrlGestionProyecto ctrl = new CtrlGestionProyecto(consultasGP, vistaGP, usuarioActivo);
        ctrl.iniciar();
    }
}