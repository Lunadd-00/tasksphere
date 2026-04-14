package Vista;

import static org.junit.Assert.assertEquals;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.junit.*;
import Controlador.CtrlLogin;
import Modelo.Usuario;
import Modelo.ConsultasUsuario;
import Vista.GestionProyecto;
import Vista.Riesgos;

 public class FrmGestionProyectoRiesgos {
	
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
    public void registrarRiesgoNuevo() {

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
            gestionTarget.btnRiesgos1.setName("btnRiesgos1");
            return null;
        });

        gestion.button("btnRiesgos1").click();

	        sleep(1000);

        FrameFixture riesgos = WindowFinder.findFrame(Riesgos.class)
                .using(robot);

        Riesgos riesgosTarget = (Riesgos) riesgos.target();
        GuiActionRunner.execute(() -> {
            riesgosTarget.cbProyecto.setName("cmbProyecto");
            riesgosTarget.txtFechaInicioFin.setName("txtDescripcionRiesgo");
            riesgosTarget.s1.setName("cmbProbabilidad");
            riesgosTarget.s.setName("cmbImpacto");
            riesgosTarget.btnAgregarTarea.setName("btnAgregarTarea");
            riesgosTarget.jTable1.setName("tblRiesgos");
            riesgosTarget.panelIndicador.setName("panelIndicadorRiesgo");
            return null;
        });

	        riesgos.comboBox("cmbProyecto").selectItem(1);

	        sleep(500);

	        riesgos.textBox("txtDescripcionRiesgo").enterText("Riesgo crítico alto");

	        riesgos.comboBox("cmbProbabilidad").selectItem("Alta");
	        riesgos.comboBox("cmbImpacto").selectItem("Alto");

	        sleep(1000);

	        riesgos.button("btnAgregarTarea").click();

	        sleep(1500);

        riesgos.optionPane()
            .requireVisible()
            .requireMessage("Riesgo registrado correctamente.")
            .okButton()
            .click();

	        sleep(1000);

        int filas = riesgos.table("tblRiesgos").target().getRowCount();
        riesgos.table("tblRiesgos").selectRows(filas - 1);

	        sleep(1000);

	        java.awt.Color color = riesgos.panel("panelIndicadorRiesgo")
	                .target()
	                .getBackground();

	        assertEquals(java.awt.Color.RED, color);
	    }

	    private void sleep(int millis) {
	        try {
	            Thread.sleep(millis);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

}
