/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.modelos;

import java.util.Date;

/**
 *
 * @author israe
 */
public class ConsultaInnerEmpleado {
    private int idEmpleado;
    private String dpi;
    private String nombre;
    private String apellido; 
    private String direccion;
    private String nit;
    private Date fechaInicio;
    private Date fechaFin; 
    private String puesto;
    private Float sueldo;
    private String HoraEntrada;
    private String HoraSalida; 

    public ConsultaInnerEmpleado() {
    }

    public ConsultaInnerEmpleado(int idEmpleado, String dpi, String nombre, String apellido, String direccion, String nit, Date fechaInicio, Date fechaFin, String puesto, Float sueldo, String HoraEntrada, String HoraSalida) {
        this.idEmpleado = idEmpleado;
        this.dpi = dpi;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.nit = nit;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.HoraEntrada = HoraEntrada;
        this.HoraSalida = HoraSalida;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }

    public String getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(String HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }

    public String getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(String HoraSalida) {
        this.HoraSalida = HoraSalida;
    }
    
    
}
