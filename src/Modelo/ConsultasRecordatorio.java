package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConsultasRecordatorio extends Conexion {

    public boolean registrar(Recordatorio r) {
        String sql = "INSERT INTO recordatorios(id_tarea, id_usuario, fecha_hora) VALUES (?,?,?)";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getIdTarea());
            ps.setInt(2, r.getIdUsuario());
            ps.setTimestamp(3, r.getFechaHora());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error registrar recordatorio: " + e.getMessage());
            return false;
        }
    }

    public boolean existeRecordatorio(int idTarea, int idUsuario) {
        String sql = "SELECT COUNT(1) FROM recordatorios WHERE id_tarea=? AND id_usuario=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTarea);
            ps.setInt(2, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error verificando recordatorio: " + e.getMessage());
        }

        return false;
    }

    public List<Recordatorio> listarPorTareaYUsuario(int idTarea, int idUsuario) {
        List<Recordatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatorios WHERE id_tarea=? AND id_usuario=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTarea);
            ps.setInt(2, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Recordatorio r = new Recordatorio();
                    r.setIdRecordatorio(rs.getInt("id_recordatorio"));
                    r.setIdTarea(rs.getInt("id_tarea"));
                    r.setIdUsuario(rs.getInt("id_usuario"));
                    r.setFechaHora(rs.getTimestamp("fecha_hora"));
                    lista.add(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error listando recordatorios: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminarPorTareaYUsuario(int idTarea, int idUsuario) {
        String sql = "DELETE FROM recordatorios WHERE id_tarea=? AND id_usuario=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTarea);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error eliminando recordatorios: " + e.getMessage());
            return false;
        }
    }

    public int generarRecordatoriosPorVencimiento(int horasAntes) {
        int insertados = 0;

        String sql = "SELECT t.id_tarea, t.id_responsable, t.fecha_vencimiento "
                + "FROM tareas t "
                + "LEFT JOIN recordatorios r ON r.id_tarea=t.id_tarea AND r.id_usuario=t.id_responsable "
                + "WHERE t.id_responsable IS NOT NULL "
                + "AND t.estado <> 'Completada' "
                + "AND r.id_recordatorio IS NULL";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idTarea = rs.getInt("id_tarea");
                int idUsuario = rs.getInt("id_responsable");
                java.sql.Date fechaVenc = rs.getDate("fecha_vencimiento");

                if (fechaVenc == null) {
                    continue;
                }

                Timestamp fechaVencTs = Timestamp.valueOf(fechaVenc.toLocalDate().atStartOfDay());
                long horasRestantes = (fechaVencTs.getTime() - System.currentTimeMillis()) / (1000L * 60L * 60L);

                if (horasRestantes >= 0 && horasRestantes <= horasAntes) {
                    Recordatorio r = new Recordatorio();
                    r.setIdTarea(idTarea);
                    r.setIdUsuario(idUsuario);
                    r.setFechaHora(new Timestamp(System.currentTimeMillis()));

                    if (registrar(r)) {
                        insertados++;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error generando recordatorios: " + e.getMessage());
        }

        return insertados;
    }
}
