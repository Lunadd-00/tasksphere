package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ConsultasRiesgo extends Conexion {
    
    public boolean registrar(Riesgo r) {

        String sql = "INSERT INTO riesgos (descripcion, probabilidad, impacto, id_proyecto) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getDescripcion());
            ps.setString(2, r.getProbabilidad());
            ps.setString(3, r.getImpacto());
            ps.setInt(4, r.getIdProyecto());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error registrar riesgo: " + e.getMessage());
            return false;
        }
    }

    public List<Riesgo> listar() {

        List<Riesgo> lista = new ArrayList<>();
        String sql = "SELECT * FROM riesgos";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Riesgo r = new Riesgo();
                r.setIdRiesgo(rs.getInt("id_riesgo"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setProbabilidad(rs.getString("probabilidad"));
                r.setImpacto(rs.getString("impacto"));
                r.setIdProyecto(rs.getInt("id_proyecto"));
                lista.add(r);
            }

        } catch (Exception e) {
            System.out.println("Error listar riesgos: " + e.getMessage());
        }

        return lista;
    }

    public List<Riesgo> listarPorProyecto(int idProyecto) {

        List<Riesgo> lista = new ArrayList<>();
        String sql = "SELECT * FROM riesgos WHERE id_proyecto = ?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProyecto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Riesgo r = new Riesgo();
                    r.setIdRiesgo(rs.getInt("id_riesgo"));
                    r.setDescripcion(rs.getString("descripcion"));
                    r.setProbabilidad(rs.getString("probabilidad"));
                    r.setImpacto(rs.getString("impacto"));
                    r.setIdProyecto(rs.getInt("id_proyecto"));
                    lista.add(r);
                }
            }

        } catch (Exception e) {
            System.out.println("Error listar riesgos por proyecto: " + e.getMessage());
        }

        return lista;
    }
}
