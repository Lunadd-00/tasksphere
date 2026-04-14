package Vista;

import Controlador.CtrlTareas;
import Modelo.ConsultasTarea;
import Modelo.Tarea;
import Modelo.Usuario;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrmTareaComentarioTest {

    private FrameFixture window;
    private Robot robot;
    private final int idTarea = 1;
    private String estadoOriginal;
    private String comentarioOriginal;

    @Before
    public void setUp() {
        Tarea original = new ConsultasTarea().buscar(idTarea);
        if (original == null) {
            throw new RuntimeException("No existe la tarea de prueba con id=" + idTarea);
        }

        estadoOriginal = original.getEstado();
        comentarioOriginal = original.getComentario();

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
            f.tblTareas.setName("tblTareas");

            return f;
        });

        window = new FrameFixture(robot, frame);
        window.show();
    }

    @After
    public void tearDown() {
        Tarea t = new Tarea();
        t.setIdTarea(idTarea);
        t.setEstado(estadoOriginal);
        t.setComentario(comentarioOriginal);
        new ConsultasTarea().actualizarEstadoComentario(t);

        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    public void colaboradorDebeAgregarComentarioATarea() {
        String comentarioNuevo = "Comentario prueba " + System.currentTimeMillis();

        window.comboBox("cmbCodigoTarea").selectItem(String.valueOf(idTarea));
        Pause.pause(1300);
        window.comboBox("cmbEstado").selectItem("En progreso");
        Pause.pause(1100);
        window.textBox("txtComentario").setText(comentarioNuevo);
        Pause.pause(1200);

        window.button("btnActualizar").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("Tarea actualizada correctamente.")
                .okButton()
                .click();

        Pause.pause(1500);

        Tarea actual = new ConsultasTarea().buscar(idTarea);
        assertTrue(actual != null);
        assertEquals(comentarioNuevo, actual.getComentario());

        int fila = buscarFilaPorId(idTarea);
        assertTrue(fila >= 0);
        assertEquals(comentarioNuevo, window.table("tblTareas").valueAt(TableCell.row(fila).column(4)));
    }

    private int buscarFilaPorId(int id) {
        javax.swing.JTable table = window.table("tblTareas").target();

        for (int i = 0; i < table.getRowCount(); i++) {
            Object valor = table.getValueAt(i, 0);
            if (valor != null && String.valueOf(id).equals(valor.toString())) {
                return i;
            }
        }

        return -1;
    }
}
