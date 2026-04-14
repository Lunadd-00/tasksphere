package Vista;

import Controlador.CtrlRegistroUsuario;
import Modelo.ConsultasUsuario;
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

public class FrmRegistroUsuarioRolTest {

    private FrameFixture window;
    private Robot robot;

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        RegistroUsuario frame = GuiActionRunner.execute(() -> {
            Usuario admin = new Usuario();
            admin.setId(1);
            admin.setNombre("Luna Delgado");
            admin.setRol("Administrador");

            RegistroUsuario f = new RegistroUsuario("menu", admin);
            ConsultasUsuario consultas = new ConsultasUsuario();
            CtrlRegistroUsuario ctrl = new CtrlRegistroUsuario(consultas, f);
            f.setControlador(ctrl);

            f.txtNombre.setName("txtNombre");
            f.txtUsuario.setName("txtUsuario");
            f.txtContrasena.setName("txtContrasena");
            f.txtCContrasena.setName("txtCContrasena");
            f.cmbRol.setName("cmbRol");
            f.btnRegistrar.setName("btnRegistrar");

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
    public void administradorDebeRegistrarUsuarioConRol() {
        String sufijo = String.valueOf(System.currentTimeMillis());
        String usuarioNuevo = "auto_user_" + sufijo;
        String password = "abc123";

        window.textBox("txtNombre").setText("Usuario Prueba " + sufijo);
        Pause.pause(1000);
        window.textBox("txtUsuario").setText(usuarioNuevo);
        Pause.pause(1000);
        window.textBox("txtContrasena").setText(password);
        Pause.pause(1000);
        window.textBox("txtCContrasena").setText(password);
        Pause.pause(1000);
        window.comboBox("cmbRol").selectItem("Colaborador");
        Pause.pause(1300);

        window.button("btnRegistrar").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("Usuario registrado correctamente.")
                .okButton()
                .click();

        Pause.pause(1500);

        Usuario modelo = new Usuario();
        boolean valido = new ConsultasUsuario().validar(usuarioNuevo, password, "Colaborador", modelo);
        assertTrue(valido);
    }
}
