package Modelo;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ConsultasReporte extends Conexion {

    public boolean registrar(ReporteProgreso r) {
        String sql = "INSERT INTO reportes_progreso "
                + "(id_proyecto, fecha_reporte, porcentaje_avance, estado_actual, comentario_admin, id_admin) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getIdProyecto());
            ps.setDate(2, new java.sql.Date(r.getFechaReporte().getTime())); 
            ps.setDouble(3, r.getPorcentajeAvance());                        
            ps.setString(4, r.getEstadoActual());
            ps.setString(5, r.getComentarioAdmin());
            ps.setInt(6, r.getIdAdmin());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error registrar reporte: " + e);
            return false;
        }
    }

    public void cargarPorProyecto(DefaultTableModel modelo, int idProyecto) {

        modelo.setRowCount(0);

        String sql = "SELECT * FROM reportes_progreso WHERE id_proyecto = ? ORDER BY fecha_reporte DESC";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProyecto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_reporte"),
                    rs.getInt("id_proyecto"),
                    rs.getDate("fecha_reporte"),
                    rs.getDouble("porcentaje_avance"), 
                    rs.getString("estado_actual"),
                    rs.getString("comentario_admin"),
                    rs.getInt("id_admin")
                });
            }

        } catch (Exception e) {
            System.out.println("Error cargar reporte: " + e);
        }
    }

    public void cargarTablaReportes(DefaultTableModel modelo) {

        String sql = "SELECT id_reporte, id_proyecto, fecha_reporte, porcentaje_avance, estado_actual, comentario_admin "
                + "FROM reportes_progreso";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_reporte"),
                    rs.getInt("id_proyecto"),
                    rs.getDate("fecha_reporte"),
                    rs.getDouble("porcentaje_avance"),
                    rs.getString("estado_actual"),
                    rs.getString("comentario_admin")
                });
            }

        } catch (Exception e) {
            System.out.println("Error cargar tabla reportes: " + e);
        }
    }

    public boolean eliminarPorId(int idReporte) {

        String sql = "DELETE FROM reportes_progreso WHERE id_reporte = ?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReporte);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar reporte: " + e.getMessage());
            return false;
        }
    }
}
