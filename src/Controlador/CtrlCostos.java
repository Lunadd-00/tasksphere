package Controlador;

import Modelo.ConsultasCostos;
import Modelo.ConsultasProyecto;
import Modelo.Proyecto;
import Vista.Costos;
import Vista.GestionProyecto;
import Modelo.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class CtrlCostos implements ActionListener {

    private Costos vista;
    private Usuario usuarioActivo;
    private GestionProyecto vistaAnterior;
    private ConsultasProyecto consultasProyecto;

    public CtrlCostos(Costos vista, GestionProyecto vistaAnterior, Usuario usuarioActivo) {
        this.vista = vista;
        this.usuarioActivo = usuarioActivo;
        this.vistaAnterior = vistaAnterior;
        this.consultasProyecto = new ConsultasProyecto();

        this.vista.btnEvaluarC.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);

        this.vista.txtVariacion.setEditable(false);

        cargarComboProyectos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnEvaluarC) {
            evaluarCostos();
        }

        if (e.getSource() == vista.btnRegresar) {
            vista.dispose();
            vistaAnterior.setVisible(true);
        }
    }

    private void cargarComboProyectos() {
        vista.cmbProyecto.removeAllItems();
        List<Proyecto> lista = consultasProyecto.listar(); 
        for (Proyecto p : lista) {
            vista.cmbProyecto.addItem(p.getIdProyecto());
        }
    }

    private void evaluarCostos() {
        try {
            if (vista.cmbProyecto.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vista, "Seleccione un proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idProyecto = Integer.parseInt(vista.cmbProyecto.getSelectedItem().toString());
            double presupuesto = obtenerPresupuesto(idProyecto);

            if (presupuesto <= 0) {
                JOptionPane.showMessageDialog(vista, "No se pudo obtener el presupuesto del proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double costoActual = Double.parseDouble(vista.txtCostoActual.getText().trim());
            double variacion = ((costoActual - presupuesto) / presupuesto) * 100;
            vista.txtVariacion.setText(String.format("%.2f", variacion));

            String observaciones = vista.txtObservaciones.getText().trim();

            ConsultasCostos consultasCostos = new ConsultasCostos();
            boolean guardado = consultasCostos.registrarCosto(idProyecto, costoActual, observaciones);

            if (guardado) {
                JOptionPane.showMessageDialog(vista, "Costo registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar el costo.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El costo ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al evaluar costos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double obtenerPresupuesto(int idProyecto) {
        double presupuesto = 0;
        List<Proyecto> proyectos = consultasProyecto.listar();
        for (Proyecto p : proyectos) {
            if (p.getIdProyecto() == idProyecto) {
                presupuesto = p.getPresupuestoInicial();
                break;
            }
        }
        return presupuesto;
    }
}
