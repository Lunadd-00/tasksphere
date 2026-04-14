package Controlador;

import Modelo.ConsultasProyecto;
import Modelo.Tarea;
import Modelo.ConsultasTarea;
import Modelo.Usuario;
import Vista.GestionProyecto;
import Vista.Tareas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlTareas implements ActionListener {

    private Tareas vista;
    private ConsultasTarea consultas;
    private int idColaborador;
    private Usuario usuarioActivo;

    public CtrlTareas(Tareas vista, ConsultasTarea consultas, int idColaborador, Usuario usuarioActivo) {
        this.vista = vista;
        this.consultas = consultas;
        this.idColaborador = idColaborador;
        this.usuarioActivo = usuarioActivo;

        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.cmbCodigoTarea.addActionListener(this);

        cargarComboTareas();
        cargarTabla();

        vista.cmbEstado.removeAllItems();
        vista.cmbEstado.addItem("Pendiente");
        vista.cmbEstado.addItem("En progreso");
        vista.cmbEstado.addItem("Completada");
        vista.cmbEstado.addItem("Atrasada");
    }

    public void iniciar() {
        vista.setTitle("Tareas");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
    }

    private void cargarComboTareas() {
        vista.cmbCodigoTarea.removeAllItems();

        List<Tarea> lista = consultas.listar();

        for (Tarea t : lista) {
            if (t.getIdResponsable() == idColaborador) {
                vista.cmbCodigoTarea.addItem(String.valueOf(t.getIdTarea()));
            }
        }
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblTareas.getModel();
        modelo.setRowCount(0);

        List<Tarea> lista = consultas.listar();

        for (Tarea t : lista) {
            if (t.getIdResponsable() == idColaborador) {
                modelo.addRow(new Object[]{
                    t.getIdTarea(),
                    t.getNombre(),
                    t.getIdProyecto(),
                    t.getEstado(),
                    t.getComentario()
                });
            }
        }
    }

    private void cargarDatosSeleccion() {
        if (vista.cmbCodigoTarea.getSelectedItem() == null) {
            return;
        }

        int id = Integer.parseInt(vista.cmbCodigoTarea.getSelectedItem().toString());

        Tarea t = consultas.buscar(id);

        if (t != null) {
            vista.cmbEstado.setSelectedItem(t.getEstado());
            vista.txtComentario.setText(t.getComentario());
        }
    }

    private void actualizar() {
        if (vista.cmbCodigoTarea.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una tarea.");
            return;
        }

        int id = Integer.parseInt(vista.cmbCodigoTarea.getSelectedItem().toString());

        String estado = vista.cmbEstado.getSelectedItem().toString();
        String comentario = vista.txtComentario.getText();

        Tarea t = new Tarea();
        t.setIdTarea(id);
        t.setEstado(estado);
        t.setComentario(comentario);

        if (consultas.actualizarEstadoComentario(t)) {
            JOptionPane.showMessageDialog(null, "Tarea actualizada correctamente.");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar la tarea.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.cmbCodigoTarea) {
            cargarDatosSeleccion();
        }

        if (e.getSource() == vista.btnActualizar) {
            actualizar();
        }

        if (e.getSource() == vista.btnRegresar) {
            ConsultasProyecto consultasGP = new ConsultasProyecto();
            GestionProyecto gp = new GestionProyecto(usuarioActivo);

            CtrlGestionProyecto ctrl = new CtrlGestionProyecto(consultasGP, gp, usuarioActivo);
            ctrl.iniciar();

            gp.setVisible(true);
            vista.dispose();
        }
    }
}
