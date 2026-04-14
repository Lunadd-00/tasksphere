package Vista;

import Controlador.CtrlTareas;
import Modelo.ConsultasTarea;
import Modelo.Tarea;
import Modelo.Usuario;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FrmTareasColaboradorUpdateTest {

    private FrameFixture window;
    private Robot robot;
    private final int idTarea = 1;
    private String estadoOriginal;
    private String comentarioOriginal;

    @Before
    public void setUp() {
        String[] original = leerEstadoYComentario(idTarea);
        estadoOriginal = original[0];
        comentarioOriginal = original[1];

        robot = BasicRobot.robotWithNewAwtHierarchy();

        Tareas frame = GuiActionRunner.execute(() -> {
            Usuario colaborador = new Usuario();
            colaborador.setId(2);
            colaborador.setRol("Colaborador");
            colaborador.setNombre("Carlos Delgado");

            Tareas f = new Tareas();
            ConsultasTarea consultas = new ConsultasTarea();
            CtrlTareas ctrl = new CtrlTareas(f, consultas, colaborador.getId(), colaborador);
            ctrl.iniciar();

            f.cmbCodigoTarea.setName("cmbCodigoTarea");
            f.cmbEstado.setName("cmbEstado");
            f.txtComentario.setName("txtComentario");
            f.btnActualizar.setName("btnActualizar");

            return f;
        });

        window = new FrameFixture(robot, frame);
        window.show();
    }

    @After
    public void tearDown() {
        restaurarEstadoYComentario(idTarea, estadoOriginal, comentarioOriginal);
        window.cleanUp();
    }

    @Test
    public void colaboradorDebeActualizarTareaACompletada() {
        String comentarioNuevo = "Actualizado por prueba UI";

        window.comboBox("cmbCodigoTarea").selectItem(String.valueOf(idTarea));
        Pause.pause(1100);

        window.comboBox("cmbEstado").selectItem("Completada");
        Pause.pause(900);
        window.textBox("txtComentario").setText(comentarioNuevo);
        Pause.pause(1000);

        window.button("btnActualizar").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("Tarea actualizada correctamente.")
                .okButton()
                .click();

        Pause.pause(1200);

        String[] actual = leerEstadoYComentario(idTarea);
        assertEquals("Completada", actual[0]);
        assertEquals(comentarioNuevo, actual[1]);
    }

    private String[] leerEstadoYComentario(int id) {
        ConsultasTarea consultas = new ConsultasTarea();
        Tarea t = consultas.buscar(id);

        if (t != null) {
            return new String[]{t.getEstado(), t.getComentario()};
        }

        throw new RuntimeException("No existe la tarea de prueba con id=" + id);
    }

    private void restaurarEstadoYComentario(int id, String estado, String comentario) {
        ConsultasTarea consultas = new ConsultasTarea();
        Tarea t = new Tarea();
        t.setIdTarea(id);
        t.setEstado(estado);
        t.setComentario(comentario);

        if (!consultas.actualizarEstadoComentario(t)) {
            throw new RuntimeException("Error restaurando tarea de prueba");
        }
    }
}
