package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultasTarea extends Conexion {

    public boolean registrar(Tarea t) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO tareas (nombre, descripcion, estado, fecha_inicio, fecha_vencimiento, id_proyecto, id_responsable, comentario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDescripcion());
            ps.setString(3, t.getEstado());
            ps.setDate(4, t.getFechaInicio());
            ps.setDate(5, t.getFechaVencimiento());
            ps.setInt(6, t.getIdProyecto());
            ps.setInt(7, t.getIdResponsable());
            ps.setString(8, t.getComentario());  

            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarAdmin(Tarea t) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE tareas SET nombre=?, descripcion=?, estado=?, fecha_inicio=?, fecha_vencimiento=?, "
                + "id_proyecto=?, id_responsable=? WHERE id_tarea=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDescripcion());
            ps.setString(3, t.getEstado());
            ps.setDate(4, t.getFechaInicio());
            ps.setDate(5, t.getFechaVencimiento());
            ps.setInt(6, t.getIdProyecto());
            ps.setInt(7, t.getIdResponsable());
            ps.setInt(8, t.getIdTarea());

            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error actualizarAdmin: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarEstadoComentario(Tarea t) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE tareas SET estado=?, comentario=? WHERE id_tarea=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getEstado());
            ps.setString(2, t.getComentario());
            ps.setInt(3, t.getIdTarea());

            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error actualizar comentario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM tareas WHERE id_tarea=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }


    public Tarea buscar(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM tareas WHERE id_tarea=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Tarea t = new Tarea();

                t.setIdTarea(rs.getInt("id_tarea"));
                t.setNombre(rs.getString("nombre"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setEstado(rs.getString("estado"));
                t.setFechaInicio(rs.getDate("fecha_inicio"));
                t.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                t.setIdProyecto(rs.getInt("id_proyecto"));
                t.setIdResponsable(rs.getInt("id_responsable"));
                t.setComentario(rs.getString("comentario"));

                return t;
            }

        } catch (Exception e) {
            System.out.println("Error buscar: " + e.getMessage());
        }
        return null;
    }


    public List<Tarea> listar() {
        List<Tarea> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM tareas";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tarea t = new Tarea();

                t.setIdTarea(rs.getInt("id_tarea"));
                t.setNombre(rs.getString("nombre"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setEstado(rs.getString("estado"));
                t.setFechaInicio(rs.getDate("fecha_inicio"));
                t.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                t.setIdProyecto(rs.getInt("id_proyecto"));
                t.setIdResponsable(rs.getInt("id_responsable"));
                t.setComentario(rs.getString("comentario"));

                lista.add(t);
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }

    public List<Tarea> listarPorResponsable(int idResponsable) {
        List<Tarea> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM tareas WHERE id_responsable=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idResponsable);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tarea t = new Tarea();

                t.setIdTarea(rs.getInt("id_tarea"));
                t.setNombre(rs.getString("nombre"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setEstado(rs.getString("estado"));
                t.setFechaInicio(rs.getDate("fecha_inicio"));
                t.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                t.setIdProyecto(rs.getInt("id_proyecto"));
                t.setIdResponsable(rs.getInt("id_responsable"));
                t.setComentario(rs.getString("comentario"));

                lista.add(t);
            }

        } catch (Exception e) {
            System.out.println("Error listarPorResponsable: " + e.getMessage());
        }

        return lista;
    }
}
