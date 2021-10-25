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
public class Asistencia {
    private int idAsistencia; 
    private String hora_entrada; 
    private String hora_salida;
    private Date fecha; 
    private int idEmpleado;
    private Calendar calendar;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, String hora_entrada, String hora_salida, Date fecha, int idEmpleado) {
        this.idAsistencia = idAsistencia;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Calendar obtenerCalendar(){
        calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar;
    }
}
