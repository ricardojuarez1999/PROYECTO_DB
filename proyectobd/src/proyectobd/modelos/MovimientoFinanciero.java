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
public class MovimientoFinanciero {

    private int idMovimientoFinanciero;
    private float total;
    private float monto;
    private Date fecha;
    private int idTipoTransaccion;
    private Calendar calendar;

    public MovimientoFinanciero() {

    }

    public MovimientoFinanciero(int idMovimientoFinanciero, float total, float monto, Date fecha, int idTipoTransaccion) {
        this.idMovimientoFinanciero = idMovimientoFinanciero;
        this.total = total;
        this.monto = monto;
        this.fecha = fecha;
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public int getIdMovimientoFinanciero() {
        return idMovimientoFinanciero;
    }

    public void setIdMovimientoFinanciero(int idMovimientoFinanciero) {
        this.idMovimientoFinanciero = idMovimientoFinanciero;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(int idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }
    
    public Calendar obtenerCalendar(){
        calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar;
    }
}
