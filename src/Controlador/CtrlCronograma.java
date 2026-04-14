package Controlador;

import Modelo.ConsultasProyecto;
import Modelo.ConsultasTarea;

import Modelo.Tarea;
import Modelo.Usuario;
import Vista.Cronograma;
import Vista.GestionProyecto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlCronograma implements ActionListener {

    private Cronograma vista;
    private ConsultasTarea consultasTarea;
    private Usuario usuarioActivo;

    private List<Tarea> listaTareas;

    public CtrlCronograma(Cronograma vista, ConsultasTarea consultasTarea, Usuario usuarioActivo) {
        this.vista = vista;
        this.consultasTarea = consultasTarea;
        this.usuarioActivo = usuarioActivo;

        this.vista.btnAgregarTarea.addActionListener(this);
        this.vista.btnAgregarTarea1.addActionListener(this);

        this.vista.s.addActionListener(this);

        cargarComboTareas();
        limpiarTabla();
    }

    public void iniciar() {
        vista.setTitle("Cronograma");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    private void cargarComboTareas() {

        vista.s.removeAllItems();
        vista.s.addItem("Seleccione una tarea");

        listaTareas = consultasTarea.listar();

        if (listaTareas == null || listaTareas.isEmpty()) {
            return;
        }

        for (Tarea t : listaTareas) {
            vista.s.addItem(t.getIdTarea() + " - " + t.getNombre());
        }
    }

    private Tarea obtenerTareaSeleccionada() {

        int index = vista.s.getSelectedIndex();

        if (index <= 0 || listaTareas == null) {
            return null;
        }

        return listaTareas.get(index - 1);
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.jTable1.getModel();
        modelo.setRowCount(0);
    }

    private void agregarTareaATabla() {

        Tarea t = obtenerTareaSeleccionada();

        if (t == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una tarea.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) vista.jTable1.getModel();

        String fechas = "";
        if (t.getFechaInicio() != null && t.getFechaVencimiento() != null) {
            fechas = t.getFechaInicio() + " / " + t.getFechaVencimiento();
        }

        String estado = t.getEstado();
        String progreso = calcularProgreso(estado);

        modelo.addRow(new Object[]{
            t.getNombre(),
            fechas,
            estado,
            progreso
        });
    }

    private String calcularProgreso(String estado) {

        if (estado == null) {
            return "";
        }

        switch (estado) {
            case "Pendiente":
                return "0%";
            case "En progreso":
                return "50%";
            case "Completada":
                return "100%";
            case "Atrasada":
                return "25%";
            default:
                return "";
        }
    }

    private void regresar() {
        vista.dispose();

        GestionProyecto gp = new GestionProyecto(usuarioActivo);
        ConsultasProyecto consultasGP = new ConsultasProyecto();
        CtrlGestionProyecto ctrlGP = new CtrlGestionProyecto(consultasGP, gp, usuarioActivo);

        ctrlGP.iniciar();
        gp.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == vista.btnAgregarTarea) {
            agregarTareaATabla();
        }

        if (src == vista.btnAgregarTarea1) {
            regresar();
        }

    }
}
