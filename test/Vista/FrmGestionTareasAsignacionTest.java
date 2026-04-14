package Vista;

import Controlador.CtrlLogin;
import Modelo.ConsultasProyecto;
import Modelo.ConsultasTarea;
import Modelo.ConsultasUsuario;
import Modelo.Proyecto;
import Modelo.Tarea;
import Modelo.Usuario;
import javax.swing.JFrame;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FrmGestionTareasAsignacionTest {

    private Robot robot;
    private FrameFixture windowAdminLogin;
    private FrameFixture windowAdminMenu;
    private FrameFixture windowGestionTareas;
    private FrameFixture windowColaboradorLogin;
    private FrameFixture windowColaboradorMenu;
    private FrameFixture windowGestionProyecto;
    private FrameFixture windowTableroColaborador;
    private int idTareaCreada = -1;

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
    }

    @After
    public void tearDown() {
        if (idTareaCreada > 0) {
            new ConsultasTarea().eliminar(idTareaCreada);
        }

        if (windowTableroColaborador != null) {
            windowTableroColaborador.cleanUp();
        }
        if (windowGestionProyecto != null) {
            windowGestionProyecto.cleanUp();
        }
        if (windowColaboradorMenu != null) {
            windowColaboradorMenu.cleanUp();
        }
        if (windowColaboradorLogin != null) {
            windowColaboradorLogin.cleanUp();
        }
        if (windowGestionTareas != null) {
            windowGestionTareas.cleanUp();
        }
        if (windowAdminMenu != null) {
            windowAdminMenu.cleanUp();
        }
        if (windowAdminLogin != null) {
            windowAdminLogin.cleanUp();
        }
    }

    @Test
    public void administradorDebeAsignarTareaAColaboradorYVerseEnSuTablero() {
        int idProyecto = obtenerProyectoDisponible();
        int idColaborador = obtenerColaboradorDisponible();
        String nombreTarea = "AutoTareaAsignada_" + System.currentTimeMillis();

        windowAdminLogin = crearVentanaLogin();
        login(windowAdminLogin, "Luna", "l123", "Administrador");

        windowAdminMenu = WindowFinder.findFrame("MenuPrincipalTaskSphere").using(robot);
        GuiActionRunner.execute(() -> ((MenuPrincipalTaskSphere) windowAdminMenu.target()).btnGTareas.setName("btnGTareas"));
        windowAdminMenu.button("btnGTareas").click();

        Pause.pause(1400);
        windowGestionTareas = buscarFramePorTitulo("Gestión de Tareas");

        GuiActionRunner.execute(() -> {
            GestionTareas frame = (GestionTareas) windowGestionTareas.target();
            frame.txtNombre.setName("txtNombreTarea");
            frame.txtDescripcion.setName("txtDescripcionTarea");
            frame.cmbProyecto.setName("cmbProyectoTarea");
            frame.cmbResponsable.setName("cmbResponsableTarea");
            frame.txtFechaInicio.setName("txtFechaInicioTarea");
            frame.txtFechaVencimiento.setName("txtFechaVencimientoTarea");
            frame.cmbEstado.setName("cmbEstadoTarea");
            frame.btnGuardar.setName("btnGuardarTarea");
        });

        windowGestionTareas.textBox("txtNombreTarea").enterText(nombreTarea);
        Pause.pause(700);
        windowGestionTareas.textBox("txtDescripcionTarea").enterText("Tarea para validar asignacion");
        Pause.pause(700);
        windowGestionTareas.comboBox("cmbProyectoTarea").selectItem(String.valueOf(idProyecto));
        Pause.pause(700);
        windowGestionTareas.comboBox("cmbResponsableTarea").selectItem(String.valueOf(idColaborador));
        Pause.pause(700);
        windowGestionTareas.textBox("txtFechaInicioTarea").enterText("2026-02-20");
        Pause.pause(700);
        windowGestionTareas.textBox("txtFechaVencimientoTarea").enterText("2026-02-25");
        Pause.pause(700);
        windowGestionTareas.comboBox("cmbEstadoTarea").selectItem("Pendiente");
        Pause.pause(900);

        windowGestionTareas.button("btnGuardarTarea").click();
        windowGestionTareas.optionPane()
                .requireVisible()
                .requireMessage("Tarea registrada correctamente")
                .okButton()
                .click();

        Pause.pause(1200);

        idTareaCreada = buscarIdTareaPorNombre(nombreTarea);
        assertTrue(idTareaCreada > 0);

        windowColaboradorLogin = crearVentanaLogin();
        login(windowColaboradorLogin, "Carlos", "2025", "Colaborador");

        windowColaboradorMenu = WindowFinder.findFrame("MenuPrincipalTaskSphere").using(robot);
        windowColaboradorMenu.button("btnGProyectos").click();

        Pause.pause(1400);
        windowGestionProyecto = WindowFinder.findFrame("GestionProyecto").using(robot);
        GuiActionRunner.execute(() -> ((GestionProyecto) windowGestionProyecto.target()).btnTareas1.setName("btnTareasColaborador"));
        windowGestionProyecto.button("btnTareasColaborador").click();

        Pause.pause(1400);
        windowTableroColaborador = buscarFramePorTitulo("Tareas");
        GuiActionRunner.execute(() -> {
            Tareas frame = (Tareas) windowTableroColaborador.target();
            frame.cmbCodigoTarea.setName("cmbCodigoTareaColaborador");
            frame.tblTareas.setName("tblTareasColaborador");
        });

        Pause.pause(1000);

        assertTrue(existeTareaEnComboColaborador(idTareaCreada));
        assertTrue(existeTareaEnTablaColaborador(idTareaCreada));
    }

    private FrameFixture crearVentanaLogin() {
        Login frame = GuiActionRunner.execute(() -> {
            Login f = new Login();
            f.txtUsuario.setName("txtUsuario");
            f.txtContrasena.setName("txtContrasena");
            f.cmbRol.setName("cmbRol");
            f.btnLogin.setName("btnLogin");

            Usuario modelo = new Usuario();
            ConsultasUsuario consultas = new ConsultasUsuario();
            CtrlLogin ctrl = new CtrlLogin(modelo, consultas, f);
            f.setControlador(ctrl);

            return f;
        });

        FrameFixture window = new FrameFixture(robot, frame);
        window.show();
        return window;
    }

    private void login(FrameFixture window, String usuario, String password, String rol) {
        window.textBox("txtUsuario").enterText(usuario);
        Pause.pause(800);
        window.textBox("txtContrasena").enterText(password);
        Pause.pause(800);
        window.comboBox("cmbRol").selectItem(rol);
        Pause.pause(1000);
        window.button("btnLogin").click();

        window.optionPane()
                .requireVisible()
                .okButton()
                .click();

        Pause.pause(1200);
    }

    private FrameFixture buscarFramePorTitulo(String titulo) {
        return WindowFinder.findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            @Override
            protected boolean isMatching(JFrame frame) {
                return titulo.equals(frame.getTitle()) && frame.isShowing();
            }
        }).using(robot);
    }

    private int obtenerProyectoDisponible() {
        for (Proyecto p : new ConsultasProyecto().listar()) {
            return p.getIdProyecto();
        }

        throw new RuntimeException("No hay proyecto disponible para la prueba");
    }

    private int obtenerColaboradorDisponible() {
        for (Usuario u : new ConsultasUsuario().listar()) {
            if ("Colaborador".equalsIgnoreCase(u.getRol()) && u.isEstado()) {
                return u.getId();
            }
        }

        throw new RuntimeException("No hay colaborador activo disponible para la prueba");
    }

    private int buscarIdTareaPorNombre(String nombre) {
        for (Tarea t : new ConsultasTarea().listar()) {
            if (nombre.equals(t.getNombre())) {
                return t.getIdTarea();
            }
        }

        return -1;
    }

    private boolean existeTareaEnComboColaborador(int idTarea) {
        javax.swing.JComboBox<?> combo = windowTableroColaborador.comboBox("cmbCodigoTareaColaborador").target();

        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            if (item != null && String.valueOf(idTarea).equals(item.toString())) {
                return true;
            }
        }

        return false;
    }

    private boolean existeTareaEnTablaColaborador(int idTarea) {
        javax.swing.JTable table = windowTableroColaborador.table("tblTareasColaborador").target();

        for (int i = 0; i < table.getRowCount(); i++) {
            Object valorId = table.getValueAt(i, 0);
            if (valorId != null && String.valueOf(idTarea).equals(valorId.toString())) {
                return true;
            }
        }

        return false;
    }
}
