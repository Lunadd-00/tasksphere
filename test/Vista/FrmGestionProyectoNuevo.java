package Vista;

import Controlador.CtrlLogin;
import Modelo.Usuario;
import Modelo.ConsultasUsuario;
import Vista.GestionProyecto;
import Vista.RegistrarProyecto;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.finder.WindowFinder;
import org.junit.*;

public class FrmGestionProyectoNuevo {
	
	private FrameFixture window;
    private Robot robot;

    @Before
    public void setUp() {

        robot = BasicRobot.robotWithNewAwtHierarchy();

            Login frame = GuiActionRunner.execute(() -> {
            Login f = new Login();

            f.txtUsuario.setName("txtUsuario");
            f.txtContrasena.setName("txtContrasena");
            f.btnLogin.setName("btnLogin");
            f.cmbRol.setName("cmbRol");

            Usuario modelo = new Usuario();
            ConsultasUsuario consultas = new ConsultasUsuario();
            CtrlLogin ctrl = new CtrlLogin(modelo, consultas, f);

            f.setControlador(ctrl);

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
    public void registrarProyectoNuevo() {

        window.textBox("txtUsuario").enterText("Luna");
        window.textBox("txtContrasena").enterText("l123");
        window.comboBox("cmbRol").selectItem("Administrador");

        sleep(1000);

        window.button("btnLogin").click();

        sleep(1000);

        window.optionPane()
            .requireVisible()
            .okButton()
            .click();

        sleep(1000);

        FrameFixture menu = WindowFinder.findFrame("MenuPrincipalTaskSphere")
                .using(robot);

        menu.button("btnGProyectos").click();

        sleep(1000);

        FrameFixture gestion = WindowFinder.findFrame("GestionProyecto")
                .using(robot);

        GestionProyecto gestionTarget = (GestionProyecto) gestion.target();
        GuiActionRunner.execute(() -> {
            gestionTarget.btnRegistrarProyecto1.setName("btnRegistrarProyecto1");
            return null;
        });

        gestion.button("btnRegistrarProyecto1").click();

        sleep(1000);

        FrameFixture registro = WindowFinder.findFrame(RegistrarProyecto.class)
                .using(robot);

        RegistrarProyecto registroTarget = (RegistrarProyecto) registro.target();
        GuiActionRunner.execute(() -> {
            registroTarget.txtNombre1.setName("txtNombre1");
            registroTarget.txtDescripción.setName("txtDescripcion");
            registroTarget.txtFechaInicio.setName("txtFechaInicio");
            registroTarget.txtFechaFin.setName("txtFechaFin");
            registroTarget.txtPresupuesto.setName("txtPresupuesto");
            registroTarget.cmbResponsables.setName("cmbResponsables");
            registroTarget.btnGuardarProyecto.setName("btnGuardarProyecto");
            return null;
        });

        registro.textBox("txtNombre1").enterText("Proyecto Test");
        registro.textBox("txtDescripcion").enterText("Proyecto de prueba");
        registro.textBox("txtFechaInicio").enterText("2026-03-01");
        registro.textBox("txtFechaFin").enterText("2026-03-30");
        registro.textBox("txtPresupuesto").enterText("5000");

        registro.comboBox("cmbResponsables").selectItem(0);

        sleep(1000);

        registro.button("btnGuardarProyecto").click();

        sleep(1500);

        registro.optionPane()
        .requireVisible()
        .requireMessage("Proyecto registrado correctamente.")
        .okButton()
        .click();

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
