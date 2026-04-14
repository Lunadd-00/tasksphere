package Vista;

import Controlador.CtrlRegistrarProyecto;
import Modelo.ConsultasProyecto;
import Modelo.Proyecto;
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

public class FrmGestionProyectoNuevo {

    private FrameFixture window;
    private Robot robot;

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        RegistrarProyecto frame = GuiActionRunner.execute(() -> {
            Usuario admin = new Usuario();
            admin.setId(1);
            admin.setRol("Administrador");
            admin.setNombre("Luna Delgado");

            RegistrarProyecto f = new RegistrarProyecto(admin);
            ConsultasProyecto consultas = new ConsultasProyecto();
            CtrlRegistrarProyecto ctrl = new CtrlRegistrarProyecto(consultas, f, admin);
            f.setControlador(ctrl);

            f.txtNombre1.setName("txtNombreProyecto");
            f.txtDescripción.setName("txtDescripcionProyecto");
            f.txtFechaInicio.setName("txtFechaInicioProyecto");
            f.txtFechaFin.setName("txtFechaFinProyecto");
            f.txtPresupuesto.setName("txtPresupuestoProyecto");
            f.cmbResponsables.setName("cmbResponsablesProyecto");
            f.btnGuardarProyecto.setName("btnGuardarProyecto");

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
    public void adminDebeRegistrarProyectoCorrectamente() {
        String nombreProyecto = "AutoProyecto_" + System.currentTimeMillis();

        window.textBox("txtNombreProyecto").enterText(nombreProyecto);
        Pause.pause(800);
        window.textBox("txtDescripcionProyecto").enterText("Proyecto de prueba automatizada");
        Pause.pause(800);
        window.textBox("txtFechaInicioProyecto").enterText("2026-04-10");
        Pause.pause(800);
        window.textBox("txtFechaFinProyecto").enterText("2026-05-10");
        Pause.pause(800);
        window.textBox("txtPresupuestoProyecto").enterText("9999");
        Pause.pause(800);
        window.comboBox("cmbResponsablesProyecto").selectItem("1");
        Pause.pause(1000);

        window.button("btnGuardarProyecto").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("Proyecto registrado correctamente.")
                .okButton()
                .click();

        Pause.pause(1200);

        assertTrue(existeProyecto(nombreProyecto));
        eliminarProyecto(nombreProyecto);
    }

    private boolean existeProyecto(String nombre) {
        ConsultasProyecto consultas = new ConsultasProyecto();

        for (Proyecto p : consultas.listar()) {
            if (nombre.equals(p.getNombre())) {
                return true;
            }
        }

        return false;
    }

    private void eliminarProyecto(String nombre) {
        ConsultasProyecto consultas = new ConsultasProyecto();

        for (Proyecto p : consultas.listar()) {
            if (nombre.equals(p.getNombre()) && !consultas.eliminarPorId(p.getIdProyecto())) {
                throw new RuntimeException("Error limpiando proyecto de prueba");
            }
        }
    }
}
