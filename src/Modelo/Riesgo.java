package Modelo;


public class Riesgo {
   private int idRiesgo;
    private String descripcion;
    private String probabilidad;
    private String impacto;
    private int idProyecto;

    public Riesgo() {
    }

    public Riesgo(int idRiesgo, String descripcion, String probabilidad, String impacto, int idProyecto) {
        this.idRiesgo = idRiesgo;
        this.descripcion = descripcion;
        this.probabilidad = probabilidad;
        this.impacto = impacto;
        this.idProyecto = idProyecto;
    }

    public int getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
