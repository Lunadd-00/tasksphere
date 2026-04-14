package Modelo;

import java.sql.Date;

public class Proyecto {

    private int idProyecto;          
    private String nombre;
    private String descripcion;
    private Date fechaInicio;       
    private Date fechaFin;          
    private double presupuestoInicial; 
    private int idResponsable;      

    public Proyecto() {
    }

    public Proyecto(int idProyecto, String nombre, String descripcion,
            Date fechaInicio, Date fechaFin,
            double presupuestoInicial, int idResponsable) {

        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuestoInicial = presupuestoInicial;
        this.idResponsable = idResponsable;
    }

    public Proyecto(String nombre, String descripcion,
            Date fechaInicio, Date fechaFin,
            double presupuestoInicial, int idResponsable) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuestoInicial = presupuestoInicial;
        this.idResponsable = idResponsable;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPresupuestoInicial() {
        return presupuestoInicial;
    }

    public void setPresupuestoInicial(double presupuestoInicial) {
        this.presupuestoInicial = presupuestoInicial;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }
}
