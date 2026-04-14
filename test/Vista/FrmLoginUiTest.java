package Vista;

import Controlador.CtrlLogin;
import Modelo.Usuario;
import Modelo.ConsultasUsuario;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.junit.*;
import static org.junit.Assert.*;

public class FrmLoginUiTest {
	
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
    public void loginCorrectoDebeAbrirMenu() {

        window.textBox("txtUsuario").setText("Luna");
        sleep(900);
        window.textBox("txtContrasena").setText("l123");
        sleep(900);
        window.comboBox("cmbRol").selectItem("Administrador");
        sleep(1400);

	        window.button("btnLogin").click();
	        sleep(1500);
	        
	        window.optionPane()
	        .requireVisible()
	        .requireMessage("Bienvenido Luna Delgado – Rol: Administrador")
	        .okButton()
	        .click();
	        
	        sleep(1400);

	        assertFalse(window.target().isShowing());
	    }
	    
	    private void sleep(int millis) {
	        try {
	            Thread.sleep(millis);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	}

