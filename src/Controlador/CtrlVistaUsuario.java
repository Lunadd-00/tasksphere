package Controlador;

import Modelo.ConsultasTarea;
import Modelo.Tarea;
import Vista.VistaUsuario;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author lunad
 */
public class CtrlVistaUsuario implements ActionListener {

    private VistaUsuario vista;
    private ConsultasTarea consultas;
    private int idUsuario;

    public CtrlVistaUsuario(VistaUsuario vista, ConsultasTarea consultas, int idUsuario) {
        this.vista = vista;
        this.consultas = consultas;
        this.idUsuario = idUsuario;

        this.vista.btnRegresar.addActionListener(this);

        cargarTabla();
    }

    public void iniciar() {
        vista.setTitle("Vista Usuario");
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVistaUsuario.getModel();
        modelo.setRowCount(0);

        List<Tarea> lista = consultas.listar();

        for (Tarea t : lista) {
            if (t.getIdResponsable() == idUsuario) {
                modelo.addRow(new Object[]{
                    t.getNombre(),
                    t.getIdResponsable(),
                    t.getFechaVencimiento()
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnRegresar) {
            vista.dispose(); 
            vista.getMenuPrincipal().setVisible(true);

        }
    }

}
