package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultasUsuario extends Conexion {

    public boolean validar(String usuario, String password, String rol, Usuario modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=? AND rol=? AND estado=TRUE";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ps.setString(3, rol);

            rs = ps.executeQuery();

            if (rs.next()) {

                modelo.setId(rs.getInt("id_usuario"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setUsuario(rs.getString("usuario"));
                modelo.setPassword(rs.getString("password"));
                modelo.setRol(rs.getString("rol"));
                modelo.setEstado(rs.getBoolean("estado"));

                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error en validar(): " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando conexión: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean registrar(Usuario u) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuarios(nombre, usuario, password, rol, estado) VALUES (?,?,?,?,TRUE)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRol());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en registrar(): " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando conexión: " + e.getMessage());
            }
        }

        return false;
    }

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));
                u.setEstado(rs.getBoolean("estado"));

                lista.add(u);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }

}
