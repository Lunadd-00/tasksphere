package Controlador;

import Modelo.ConsultasReporte;
import Modelo.ConsultasProyecto;
import Modelo.ReporteProgreso;
import Modelo.Usuario;
import Vista.GenerarReporte;
import Vista.GestionProyecto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlReporte implements ActionListener {

    private GenerarReporte vista;
    private ConsultasReporte consultas;
    private Usuario usuarioActivo;

    public CtrlReporte(GenerarReporte vista, ConsultasReporte consultas, Usuario usuarioActivo) {
        this.vista = vista;
        this.consultas = consultas;
        this.usuarioActivo = usuarioActivo;

        this.vista.btnGenerar.addActionListener(this);
        this.vista.btnEliminarReporte.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);

        cargarProyectos();
        cargarEstados();
        cargarTablaReportes();
    }

    public void iniciar() {
        vista.setTitle("Generar Reporte de Progreso");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGenerar) {
            generarReporte();
        }

        if (e.getSource() == vista.btnRegresar) {
            regresar();
        }

        if (e.getSource() == vista.btnEliminarReporte) {
            eliminarReporte();
        }

    }

    private void cargarProyectos() {
        vista.cmbProyecto.removeAllItems();

        ConsultasProyecto cp = new ConsultasProyecto();

        for (int id : cp.obtenerIdsProyectos()) {
            vista.cmbProyecto.addItem(id);
        }
    }

    private void cargarEstados() {
        vista.cmbEstadoActual.removeAllItems();

        vista.cmbEstadoActual.addItem("Pendiente");
        vista.cmbEstadoActual.addItem("En Progreso");
        vista.cmbEstadoActual.addItem("Completado");
    }

    private void generarReporte() {

        if (vista.cmbProyecto.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un proyecto.");
            return;
        }

        ReporteProgreso r = new ReporteProgreso();

        r.setIdProyecto((int) vista.cmbProyecto.getSelectedItem());

        try {
            r.setPorcentajeAvance(Double.parseDouble(vista.txtPorcentajeAvance.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Porcentaje inválido.");
            return;
        }

        r.setEstadoActual(vista.cmbEstadoActual.getSelectedItem().toString());
        r.setComentarioAdmin(vista.txtComentario.getText());
        r.setIdAdmin(usuarioActivo.getId());
        r.setFechaReporte(new Date(System.currentTimeMillis()));

        if (consultas.registrar(r)) {
            JOptionPane.showMessageDialog(vista, "Reporte generado con éxito.");
            cargarTablaReportes();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al generar reporte.");
        }
    }

    private void cargarTablaReportes() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblReporte.getModel();
        modelo.setRowCount(0);

        consultas.cargarTablaReportes(modelo);
    }

    private void limpiarCampos() {
        vista.txtComentario.setText("");
        vista.txtPorcentajeAvance.setText("");
        vista.cmbEstadoActual.setSelectedIndex(0);
    }

    private void regresar() {
        if (usuarioActivo == null) {
            JOptionPane.showMessageDialog(vista,
                    "No hay un usuario activo. Inicie sesión nuevamente.",
                    "Sesión inválida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        vista.dispose();

        GestionProyecto gp = new GestionProyecto(usuarioActivo);
        ConsultasProyecto consultasGP = new ConsultasProyecto();

        CtrlGestionProyecto ctrl = new CtrlGestionProyecto(consultasGP, gp, usuarioActivo);

        ctrl.iniciar();
    }

    private void eliminarReporte() {

        int fila = vista.tblReporte.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un reporte.");
            return;
        }

        int id = Integer.parseInt(
                vista.tblReporte.getValueAt(fila, 0).toString()
        );

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el reporte?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (consultas.eliminarPorId(id)) {
            JOptionPane.showMessageDialog(vista, "Reporte eliminado con éxito.");
            cargarTablaReportes();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar el reporte.");
        }
    }

}
