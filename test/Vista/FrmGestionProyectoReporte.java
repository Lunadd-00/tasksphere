package Vista;

import Controlador.CtrlLogin;
import Modelo.Usuario;
import Modelo.ConsultasUsuario;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.junit.*;

public class FrmGestionProyectoReporte {
	
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
	    public void colaboradorNoDebeAccederAReportes() {
	        window.textBox("txtUsuario").enterText("Carlos");
	        Pause.pause(900);
	        window.textBox("txtContrasena").enterText("2025");
	        Pause.pause(900);
	        window.comboBox("cmbRol").selectItem("Colaborador");
	        Pause.pause(1100);
	        window.button("btnLogin").click();

	        window.optionPane()
	              .requireVisible()
	              .okButton()
	              .click();

	        Pause.pause(1400);

	        FrameFixture menu = WindowFinder.findFrame("MenuPrincipalTaskSphere")
	                                        .using(robot);

	        menu.button("btnGProyectos").click();

	        Pause.pause(1400);

	        FrameFixture gestion = WindowFinder.findFrame("GestionProyecto")
	                                           .using(robot);

	        gestion.button("btnReportes").click();

	        gestion.optionPane()
	               .requireVisible()
	               .requireMessage("Solo los ADMINISTRADORES pueden acceder a la generación de reportes.")
	               .okButton()
	               .click();

	        Pause.pause(1400);
	    }
}
