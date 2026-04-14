package Controlador;

import Modelo.ConsultasProyecto;
import Modelo.ConsultasRiesgo;
import Modelo.Proyecto;
import Modelo.Riesgo;
import Modelo.Usuario;
import Vista.GestionProyecto;
import Vista.Riesgos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlRiesgos implements ActionListener {

    private Riesgos vista;
    private ConsultasRiesgo consultas;
    private ConsultasProyecto consultasProyecto;
    private Usuario usuarioActivo;
    private List<Riesgo> listaActual;

    public CtrlRiesgos(Riesgos vista, ConsultasRiesgo consultas, ConsultasProyecto consultasProyecto, Usuario usuarioActivo) {
        this.vista = vista;
        this.consultas = consultas;
        this.consultasProyecto = consultasProyecto;
        this.usuarioActivo = usuarioActivo;

        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnAgregarTarea.addActionListener(this);

        this.vista.cbProyecto.addActionListener(this);

        cargarCombos();
        cargarTabla();

        vista.jTable1.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.jTable1.getSelectedRow();
            if (fila >= 0) {
                String prob = vista.jTable1.getValueAt(fila, 3).toString();
                String imp = vista.jTable1.getValueAt(fila, 4).toString();

                Color color;

                if (prob.equalsIgnoreCase("Alta") || imp.equalsIgnoreCase("Alto")) {
                    color = Color.RED;
                } else if (prob.equalsIgnoreCase("Media") || imp.equalsIgnoreCase("Medio")) {
                    color = Color.YELLOW;
                } else {
                    color = Color.GREEN;
                }

                vista.panelIndicador.setBackground(color);
            }
        });
    }

    public void iniciar() {
        vista.setTitle("Riesgos");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    private void cargarCombos() {
        cargarComboImpactoProbabilidad();
        cargarComboProyectos();
    }

    private void cargarComboImpactoProbabilidad() {

        vista.s.removeAllItems();
        vista.s.addItem("Bajo");
        vista.s.addItem("Medio");
        vista.s.addItem("Alto");

        vista.s1.removeAllItems();
        vista.s1.addItem("Baja");
        vista.s1.addItem("Media");
        vista.s1.addItem("Alta");
    }

    private void cargarComboProyectos() {

        vista.cbProyecto.removeAllItems();
        vista.cbProyecto.addItem("Todos los proyectos");

        List<Proyecto> proyectos = consultasProyecto.listar();

        for (Proyecto p : proyectos) {

            vista.cbProyecto.addItem(p.getIdProyecto() + " - " + p.getNombre());
        }
    }

    private Integer obtenerIdProyectoSeleccionado() {

        Object seleccionado = vista.cbProyecto.getSelectedItem();
        if (seleccionado == null) {
            return null;
        }

        String texto = seleccionado.toString();

        if (texto.equals("Todos los proyectos")) {
            return null;
        }

        try {
            String[] partes = texto.split("-");
            return Integer.parseInt(partes[0].trim());
        } catch (Exception e) {
            return null;
        }
    }

    private void cargarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) vista.jTable1.getModel();
        modelo.setRowCount(0);

        Integer idProyecto = obtenerIdProyectoSeleccionado();

        if (idProyecto == null) {
            listaActual = consultas.listar();
        } else {
            listaActual = consultas.listarPorProyecto(idProyecto);
        }

        if (listaActual == null) {
            return;
        }

        for (Riesgo r : listaActual) {
            modelo.addRow(new Object[]{
                r.getIdRiesgo(),
                r.getIdProyecto(),
                r.getDescripcion(),
                r.getProbabilidad(),
                r.getImpacto()
            });
        }
    }

    private void registrar() {

        String descripcion = vista.txtFechaInicioFin.getText();
        Object impactoObj = vista.s.getSelectedItem();
        Object probObj = vista.s1.getSelectedItem();

        if (descripcion == null || descripcion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe escribir la descripción del riesgo.");
            return;
        }

        if (impactoObj == null || probObj == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar probabilidad e impacto.");
            return;
        }

        Integer idProyecto = obtenerIdProyectoSeleccionado();
        if (idProyecto == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un proyecto.");
            return;
        }

        String impacto = impactoObj.toString();
        String probabilidad = probObj.toString();

        Riesgo r = new Riesgo();
        r.setDescripcion(descripcion);
        r.setImpacto(impacto);
        r.setProbabilidad(probabilidad);
        r.setIdProyecto(idProyecto);

        if (consultas.registrar(r)) {
            JOptionPane.showMessageDialog(vista, "Riesgo registrado correctamente.");
            vista.txtFechaInicioFin.setText("");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar el riesgo.");
        }
    }

    private void regresar() {

        vista.dispose();
        GestionProyecto gp = new GestionProyecto(usuarioActivo);
        ConsultasProyecto cp = new ConsultasProyecto();
        CtrlGestionProyecto ctrlGP = new CtrlGestionProyecto(cp, gp, usuarioActivo);

        ctrlGP.iniciar();
        gp.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == vista.btnAgregarTarea) {
            registrar();
            cargarTabla();
        }

        if (src == vista.btnRegresar) {
            regresar();
        }

        if (src == vista.cbProyecto) {
            cargarTabla();
        }
    }
}
