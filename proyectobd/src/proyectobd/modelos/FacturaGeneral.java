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
public class FacturaGeneral {
    private int idFacturaGeneral; 
    private Date fecha; 
    private float total;
    private int idEmpleado; 
    private int idEmpresa; 
    private int idTipoPago; 
    private int idTipoFactura; 
    private Calendar calendar;

    public FacturaGeneral() {
    }

    public FacturaGeneral(int idFacturaGeneral, Date fecha, float total, int idEmpleado, int idEmpresa, int idTipoPago, int idTipoFactura) {
        this.idFacturaGeneral = idFacturaGeneral;
        this.fecha = fecha;
        this.total = total;
        this.idEmpleado = idEmpleado;
        this.idEmpresa = idEmpresa;
        this.idTipoPago = idTipoPago;
        this.idTipoFactura = idTipoFactura;
    }

    public int getIdFacturaGeneral() {
        return idFacturaGeneral;
    }

    public void setIdFacturaGeneral(int idFacturaGeneral) {
        this.idFacturaGeneral = idFacturaGeneral;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getIdTipoFactura() {
        return idTipoFactura;
    }

    public void setIdTipoFactura(int idTipoFactura) {
        this.idTipoFactura = idTipoFactura;
    }
     public Calendar obtenerCalendar(){
        calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar;
    }    
}
