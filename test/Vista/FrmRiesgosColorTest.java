package Vista;

import Controlador.CtrlRiesgos;
import Modelo.ConsultasProyecto;
import Modelo.ConsultasRiesgo;
import Modelo.Usuario;
import java.awt.Color;
import javax.swing.JTable;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrmRiesgosColorTest {

    private FrameFixture window;
    private Robot robot;

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        Riesgos frame = GuiActionRunner.execute(() -> {
            Usuario admin = new Usuario();
            admin.setId(1);
            admin.setRol("Administrador");

            Riesgos f = new Riesgos();
            ConsultasRiesgo consultasR = new ConsultasRiesgo();
            ConsultasProyecto consultasP = new ConsultasProyecto();
            CtrlRiesgos ctrl = new CtrlRiesgos(f, consultasR, consultasP, admin);
            ctrl.iniciar();

            f.jTable1.setName("tblRiesgos");
            f.cbProyecto.setName("cbProyecto");

            return f;
        });

        window = new FrameFixture(robot, frame);
        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void riesgoAltaProbabilidadAltoImpactoDebePintarIndicadorRojo() {
        window.comboBox("cbProyecto").selectItem("Todos los proyectos");
        Pause.pause(1200);

        int fila = buscarFilaRiesgo("Alta", "Alto");
        assertTrue("No se encontro fila de riesgo Alta/Alto", fila >= 0);

        window.table("tblRiesgos").selectRows(fila);
        Pause.pause(1000);

        Color color = GuiActionRunner.execute(() -> ((Riesgos) window.target()).panelIndicador.getBackground());
        assertEquals(Color.RED, color);
    }

    private int buscarFilaRiesgo(String probabilidad, String impacto) {
        JTable table = window.table("tblRiesgos").target();

        for (int i = 0; i < table.getRowCount(); i++) {
            Object prob = table.getValueAt(i, 3);
            Object imp = table.getValueAt(i, 4);

            if (prob != null && imp != null
                    && probabilidad.equalsIgnoreCase(prob.toString())
                    && impacto.equalsIgnoreCase(imp.toString())) {
                return i;
            }
        }

        return -1;
    }
}
