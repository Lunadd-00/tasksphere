package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ConsultasProyecto extends Conexion {

    public boolean registrar(Proyecto p) {

        String sql = "INSERT INTO proyectos (nombre, descripcion, fecha_inicio, fecha_fin, presupuesto_inicial, id_responsable) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDate(3, p.getFechaInicio());
            ps.setDate(4, p.getFechaFin());
            ps.setDouble(5, p.getPresupuestoInicial());
            ps.setInt(6, p.getIdResponsable());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar proyecto: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Proyecto p) {

        String sql = "UPDATE proyectos SET nombre=?, descripcion=?, "
                + "fecha_inicio=?, fecha_fin=?, presupuesto_inicial=?, id_responsable=? "
                + "WHERE id_proyecto=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDate(3, p.getFechaInicio());
            ps.setDate(4, p.getFechaFin());
            ps.setDouble(5, p.getPresupuestoInicial());
            ps.setInt(6, p.getIdResponsable());
            ps.setInt(7, p.getIdProyecto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar proyecto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorId(int id) {

        String sql = "DELETE FROM proyectos WHERE id_proyecto = ?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar proyecto: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Proyecto> listar() {

        ArrayList<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyectos";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Proyecto p = new Proyecto(
                        rs.getInt("id_proyecto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getDouble("presupuesto_inicial"),
                        rs.getInt("id_responsable")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar proyectos: " + e.getMessage());
        }
        return lista;
    }

    public List<Integer> obtenerIdsUsuarios() {

        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT id_usuario FROM usuarios";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getInt("id_usuario"));
            }

        } catch (SQLException e) {
            System.err.println("Error cargando IDs de usuarios: " + e.getMessage());
        }

        return lista;
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        for (Proyecto p : listar()) {

            modeloTabla.addRow(new Object[]{
                p.getIdProyecto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getFechaInicio(),
                p.getFechaFin(),
                p.getPresupuestoInicial(),
                p.getIdResponsable()
            });
        }
    }

    public List<Integer> obtenerIdsProyectos() {
        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT id_proyecto FROM proyectos";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getInt("id_proyecto"));
            }

        } catch (SQLException e) {
            System.err.println("Error cargando IDs de proyectos: " + e.getMessage());
        }

        return lista;
    }
}
