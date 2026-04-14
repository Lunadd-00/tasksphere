package Modelo;

import java.sql.*;

public class ConsultasCostos extends Conexion {

    public boolean registrarCosto(int idProyecto, double costoReal, String observaciones) {
        String sql = "INSERT INTO costos (id_proyecto, costo_real, observaciones) VALUES (?, ?, ?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.setDouble(2, costoReal);
            ps.setString(3, observaciones);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar costo: " + e.getMessage());
            return false;
        }
    }
}
