/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.modelos;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author israe
 */
public class Inasistencia {
    private int idInasistencia; 
    private Date fecha; 
    private int idTipoInasistencia; 
    private int idEmpleado;
    private Calendar calendar;
    public Inasistencia() {
    }

    public Inasistencia(int idInasistencia, Date fecha, int idTipoInasistencia, int idEmpleado) {
        this.idInasistencia = idInasistencia;
        this.fecha = fecha;
        this.idTipoInasistencia = idTipoInasistencia;
        this.idEmpleado = idEmpleado;
    }

    public int getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(int idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTipoInasistencia() {
        return idTipoInasistencia;
    }

    public void setIdTipoInasistencia(int idTipoInasistencia) {
        this.idTipoInasistencia = idTipoInasistencia;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Calendar obtenerCalendarInicio(){
        calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar;
    }
    
}
