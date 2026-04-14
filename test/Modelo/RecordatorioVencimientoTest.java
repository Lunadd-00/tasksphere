package Modelo;

import java.sql.Date;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RecordatorioVencimientoTest {

    @Test
    public void debeGenerarRecordatorioParaTareaConVencimientoCercano() throws Exception {
        ConsultasTarea consultasTarea = new ConsultasTarea();
        ConsultasRecordatorio consultasRecordatorio = new ConsultasRecordatorio();

        int idTarea = 1;
        int idUsuario = 2;

        System.out.println("[TC10] Iniciando prueba de recordatorio 48h...");

        Tarea original = consultasTarea.buscar(idTarea);
        if (original == null) {
            throw new RuntimeException("No existe tarea de prueba para recordatorio");
        }

        Date fechaOriginal = original.getFechaVencimiento();

        try {
            Tarea actualizar = new Tarea();
            actualizar.setIdTarea(idTarea);
            actualizar.setNombre(original.getNombre());
            actualizar.setDescripcion(original.getDescripcion());
            actualizar.setEstado("Pendiente");
            actualizar.setFechaInicio(original.getFechaInicio());
            actualizar.setFechaVencimiento(Date.valueOf(LocalDate.now().plusDays(1)));
            actualizar.setIdProyecto(original.getIdProyecto());
            actualizar.setIdResponsable(idUsuario);

            System.out.println("[TC10] Preparando tarea con vencimiento cercano...");
            consultasRecordatorio.eliminarPorTareaYUsuario(idTarea, idUsuario);

            assertTrue("No se pudo preparar tarea para prueba", consultasTarea.actualizarAdmin(actualizar));

            Thread.sleep(1200);
            System.out.println("[TC10] Ejecutando generador de recordatorios...");
            int generados = consultasRecordatorio.generarRecordatoriosPorVencimiento(48);
            Thread.sleep(1200);

            boolean existe = consultasRecordatorio.existeRecordatorio(idTarea, idUsuario);
            System.out.println("[TC10] Recordatorios generados: " + generados + " | Existe en BD: " + existe);
            assertTrue("No se genero recordatorio esperado en ventana de 48 horas", generados > 0 || existe);

            System.out.println("[TC10] Prueba completada correctamente.");
        } finally {
            Tarea restaurar = new Tarea();
            restaurar.setIdTarea(idTarea);
            restaurar.setNombre(original.getNombre());
            restaurar.setDescripcion(original.getDescripcion());
            restaurar.setEstado(original.getEstado());
            restaurar.setFechaInicio(original.getFechaInicio());
            restaurar.setFechaVencimiento(fechaOriginal);
            restaurar.setIdProyecto(original.getIdProyecto());
            restaurar.setIdResponsable(original.getIdResponsable());

            consultasTarea.actualizarAdmin(restaurar);
            consultasRecordatorio.eliminarPorTareaYUsuario(idTarea, idUsuario);
            System.out.println("[TC10] Datos restaurados y limpieza final aplicada.");
        }
    }
}
