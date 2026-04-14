package Controlador;

import Modelo.ConsultasProyecto;
import Modelo.Tarea;
import Modelo.ConsultasTarea;
import Modelo.ConsultasUsuario;
import Modelo.Proyecto;
import Modelo.Usuario;
import Vista.GestionTareas;
import Vista.MenuPrincipalTaskSphere;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlGestionTareas implements ActionListener, MouseListener {

    private ConsultasTarea consultasTarea;
    private ConsultasProyecto consultasProyecto;
    private ConsultasUsuario consultasUsuario;
    private GestionTareas vista;

    private int idSeleccionado = -1;

    public CtrlGestionTareas(GestionTareas vista,
            ConsultasTarea consultasTarea,
            ConsultasProyecto consultasProyecto,
            ConsultasUsuario consultasUsuario) {

        this.vista = vista;
        this.consultasTarea = consultasTarea;
        this.consultasProyecto = consultasProyecto;
        this.consultasUsuario = consultasUsuario;

        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnRegresar.addActionListener(this);

        vista.tblTareas.addMouseListener(this);

        cargarCombos();
        cargarTabla();
    }

    public void iniciar() {
        vista.setTitle("Gestión de Tareas");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            guardar();
        }

        if (e.getSource() == vista.btnEditar) {
            editar();
        }

        if (e.getSource() == vista.btnEliminar) {
            eliminar();
        }

        if (e.getSource() == vista.btnRegresar) {

            vista.dispose();                  
            vista.getMenuPrincipal().setVisible(true);  
        }

    }

    private void guardar() {
        try {
            Tarea t = new Tarea();

            t.setNombre(vista.getNombre());
            t.setDescripcion(vista.getDescripcion());
            t.setEstado(vista.cmbEstado.getSelectedItem().toString());
            t.setFechaInicio(Date.valueOf(vista.txtFechaInicio.getText()));
            t.setFechaVencimiento(Date.valueOf(vista.txtFechaVencimiento.getText()));
            t.setIdProyecto(vista.getProyectoSeleccionado());
            t.setIdResponsable(vista.getResponsableSeleccionado());

            if (consultasTarea.registrar(t)) {
                JOptionPane.showMessageDialog(null, "Tarea registrada correctamente");
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datos inválidos: " + ex.getMessage());
        }
    }

    private void editar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una tarea");
            return;
        }

        try {
            Tarea t = new Tarea();
            t.setIdTarea(idSeleccionado);
            t.setNombre(vista.getNombre());
            t.setDescripcion(vista.getDescripcion());
            t.setEstado(vista.cmbEstado.getSelectedItem().toString());
            t.setFechaInicio(Date.valueOf(vista.txtFechaInicio.getText()));
            t.setFechaVencimiento(Date.valueOf(vista.txtFechaVencimiento.getText()));
            t.setIdProyecto(vista.getProyectoSeleccionado());
            t.setIdResponsable(vista.getResponsableSeleccionado());

            if (consultasTarea.actualizarAdmin(t)) {
                JOptionPane.showMessageDialog(null, "Tarea actualizada");
                limpiar();
                idSeleccionado = -1;
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una tarea");
            return;
        }

        if (consultasTarea.eliminar(idSeleccionado)) {
            JOptionPane.showMessageDialog(null, "Tarea eliminada");
            limpiar();
            cargarTabla();
            idSeleccionado = -1;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }

    private void cargarTabla() {
        DefaultTableModel m = vista.getModeloTabla();
        m.setRowCount(0);

        List<Tarea> lista = consultasTarea.listar();

        for (Tarea t : lista) {
            m.addRow(new Object[]{
                t.getIdTarea(), 
                t.getNombre(), 
                t.getIdProyecto(), 
                t.getIdResponsable(), 
                t.getDescripcion(), 
                t.getEstado(), 
                t.getFechaInicio(), 
                t.getFechaVencimiento() 
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tblTareas) {
            int fila = vista.getFilaSeleccionada();

            if (fila != -1) {

                idSeleccionado = Integer.parseInt(
                        vista.tblTareas.getValueAt(fila, 0).toString()
                );

                vista.txtNombre.setText(vista.tblTareas.getValueAt(fila, 1).toString());
                vista.cmbProyecto.setSelectedItem(vista.tblTareas.getValueAt(fila, 2).toString());
                vista.cmbResponsable.setSelectedItem(vista.tblTareas.getValueAt(fila, 3).toString());
                vista.txtDescripcion.setText(vista.tblTareas.getValueAt(fila, 4).toString());
                vista.cmbEstado.setSelectedItem(vista.tblTareas.getValueAt(fila, 5).toString());
                vista.txtFechaInicio.setText(vista.tblTareas.getValueAt(fila, 6).toString());
                vista.txtFechaVencimiento.setText(vista.tblTareas.getValueAt(fila, 7).toString());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void limpiar() {
        vista.txtNombre.setText("");
        vista.txtDescripcion.setText("");
        vista.txtFechaInicio.setText("");
        vista.txtFechaVencimiento.setText("");
        vista.cmbEstado.setSelectedIndex(0);
    }

    private void cargarCombos() {

        vista.cmbEstado.removeAllItems();
        vista.cmbEstado.addItem("Pendiente");
        vista.cmbEstado.addItem("En progreso");
        vista.cmbEstado.addItem("Completada");
        vista.cmbEstado.addItem("Atrasada");

        cargarComboProyectos();
        cargarComboResponsables();
    }

    private void cargarComboProyectos() {
        vista.cmbProyecto.removeAllItems();

        List<Proyecto> lista = consultasProyecto.listar();

        for (Proyecto p : lista) {
            vista.cmbProyecto.addItem(String.valueOf(p.getIdProyecto()));
        }
    }

    private void cargarComboResponsables() {
        vista.cmbResponsable.removeAllItems();

        List<Usuario> lista = consultasUsuario.listar();

        for (Usuario u : lista) {
            vista.cmbResponsable.addItem(String.valueOf(u.getId()));
        }
    }

}
