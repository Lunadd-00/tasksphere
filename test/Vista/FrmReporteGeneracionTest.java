package Vista;

import Controlador.CtrlReporte;
import Modelo.ConsultasReporte;
import Modelo.Usuario;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FrmReporteGeneracionTest {

    private FrameFixture window;
    private Robot robot;

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        GenerarReporte frame = GuiActionRunner.execute(() -> {
            Usuario admin = new Usuario();
            admin.setId(1);
            admin.setRol("Administrador");
            admin.setNombre("Luna Delgado");

            GenerarReporte f = new GenerarReporte(admin);
            ConsultasReporte consultas = new ConsultasReporte();
            CtrlReporte ctrl = new CtrlReporte(f, consultas, admin);
            ctrl.iniciar();

            f.cmbProyecto.setName("cmbProyecto");
            f.txtPorcentajeAvance.setName("txtPorcentajeAvance");
            f.cmbEstadoActual.setName("cmbEstadoActual");
            f.txtComentario.setName("txtComentario");
            f.btnGenerar.setName("btnGenerar");
            f.tblReporte.setName("tblReporte");

            return f;
        });

        window = new FrameFixture(robot, frame);
        window.show();
    }

    @After
    public void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    public void administradorDebeGenerarReporteEnTabla() {
        String comentario = "Reporte prueba " + System.currentTimeMillis();

        String proyecto = window.comboBox("cmbProyecto").contents()[0];
        window.comboBox("cmbProyecto").selectItem(proyecto);
        Pause.pause(1200);
        window.textBox("txtPorcentajeAvance").setText("75");
        Pause.pause(1100);
        window.comboBox("cmbEstadoActual").selectItem("En Progreso");
        Pause.pause(1100);
        window.textBox("txtComentario").setText(comentario);
        Pause.pause(1300);

        window.button("btnGenerar").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("Reporte generado con éxito.")
                .okButton()
                .click();

        Pause.pause(1500);

        assertTrue(existeComentarioEnTabla(comentario));
    }

    private boolean existeComentarioEnTabla(String comentario) {
        javax.swing.JTable table = window.table("tblReporte").target();

        for (int i = 0; i < table.getRowCount(); i++) {
            Object valor = table.getValueAt(i, 5);
            if (valor != null && comentario.equals(valor.toString())) {
                return true;
            }
        }

        return false;
    }
}
