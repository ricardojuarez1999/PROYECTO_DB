/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.vistas.modulo.compraVenta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectobd.controladores.ControladorPersona;
import proyectobd.dao.mysql.MySQLDetalleFacturaCompra;
import proyectobd.dao.mysql.MySQLEmpresa;
import proyectobd.dao.mysql.MySQLFacturaCompra;
import proyectobd.dao.mysql.MySQLFacturaGeneral;
import proyectobd.dao.mysql.MySQLInventarioPieza;
import proyectobd.dao.mysql.MySQLMovimientoFinanciero;
import proyectobd.dao.mysql.MySQLNombrePieza;
import proyectobd.dao.mysql.MySQLPieza;
import proyectobd.dao.mysql.MySQLProveedor;
import proyectobd.dao.mysql.MySQLTipoFactura;
import proyectobd.dao.mysql.MySQLTipoPago;
import proyectobd.modelos.Empleado;
import proyectobd.modelos.Empresa;
import proyectobd.modelos.FacturaCompra;
import proyectobd.modelos.FacturaGeneral;
import proyectobd.modelos.InventarioPieza;
import proyectobd.modelos.MovimientoFinanciero;
import proyectobd.modelos.NombrePieza;
import proyectobd.modelos.Pieza;
import proyectobd.modelos.Proveedor;
import proyectobd.modelos.TipoFactura;
import proyectobd.modelos.TipoPago;
/**
 *
 * @author ricar
 */




public class CompraPiezaFrame extends javax.swing.JFrame {
    public static int tipo = 0;
    public static Empleado info;
    /**
     * Creates new form CompraPiezaFrame
     */
    public CompraPiezaFrame() {
        initComponents();    
        this.setLocationRelativeTo(null);
        this.empleado.setText(new ControladorPersona().obtenerPorID(info.getIdPersona()).getNombre());
        this.idEmpleado.setText(String.valueOf(info.getIdEmpleado()));
        Calendar c = Calendar.getInstance();
        int mes = c.get(Calendar.MONTH) + 1;
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int año = c.get(Calendar.YEAR);
        String fecha = dia + "/" + mes + "/" + año;
        this.fecha.setText(fecha.toString());
        
        
        MySQLEmpresa aux3 = new MySQLEmpresa();
        MySQLTipoPago aux4 = new MySQLTipoPago();
        

        ArrayList <Empresa> listaEmpresa = aux3.listar();
        ArrayList <TipoPago> listaTipoPago = aux4.listar();
       
        for(int i = 0; i<listaEmpresa.size();i++){
            empresa.addItem(listaEmpresa.get(i).getNombre());
        }
        for(int i = 0; i<listaTipoPago.size();i++){
            tipoPago.addItem(listaTipoPago.get(i).getTipo());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        regresar = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        cerrar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        agregarDetalle = new javax.swing.JButton();
        empleado = new javax.swing.JLabel();
        idEmpleado = new javax.swing.JLabel();
        totalFacturaCompra = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        empresa = new javax.swing.JComboBox<>();
        AgregarEmpresa = new javax.swing.JButton();
        tipoPago = new javax.swing.JComboBox<>();
        fecha = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Errores = new javax.swing.JLabel();
        realizarVenta = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(1050, 575));
        jPanel1.setMinimumSize(new java.awt.Dimension(1050, 575));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 575));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_regreso_32px.png"))); // NOI18N
        regresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regresarMouseClicked(evt);
            }
        });
        jPanel1.add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, -1, 30));

        minimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizarMouseClicked(evt);
            }
        });
        jPanel1.add(minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, 30));

        cerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_Multiply_32px.png"))); // NOI18N
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });
        jPanel1.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, -1, 30));

        jLabel1.setBackground(new java.awt.Color(252, 100, 68));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(252, 100, 68));
        jLabel1.setText("COMPRA DE PIEZAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, 30));

        jSeparator1.setForeground(new java.awt.Color(108, 108, 108));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 220, 10));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_pieza_mini.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 30, 30));

        agregarDetalle.setBackground(new java.awt.Color(252, 100, 68));
        agregarDetalle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregarDetalle.setText("AGREGAR DETALLE");
        agregarDetalle.setBorder(null);
        agregarDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregarDetalle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                agregarDetalleMouseMoved(evt);
            }
        });
        agregarDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                agregarDetalleMouseExited(evt);
            }
        });
        agregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarDetalleActionPerformed(evt);
            }
        });
        jPanel1.add(agregarDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 180, 50));

        empleado.setBackground(new java.awt.Color(252, 100, 68));
        empleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        empleado.setForeground(new java.awt.Color(252, 100, 68));
        empleado.setText("EMPLEADO X1");
        jPanel1.add(empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 600, 30));

        idEmpleado.setBackground(new java.awt.Color(252, 100, 68));
        idEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idEmpleado.setForeground(new java.awt.Color(252, 100, 68));
        idEmpleado.setText("1");
        jPanel1.add(idEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 200, 30));

        totalFacturaCompra.setBackground(new java.awt.Color(252, 100, 68));
        totalFacturaCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalFacturaCompra.setForeground(new java.awt.Color(252, 100, 68));
        totalFacturaCompra.setText("TOTAL A PAGAR: Q  0.00");
        totalFacturaCompra.setToolTipText("");
        jPanel1.add(totalFacturaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, 270, 30));

        jLabel9.setBackground(new java.awt.Color(252, 100, 68));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(252, 100, 68));
        jLabel9.setText("TIPO DE PAGO:");
        jLabel9.setToolTipText("");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, 30));

        jLabel10.setBackground(new java.awt.Color(252, 100, 68));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(252, 100, 68));
        jLabel10.setText("EMPRESA:");
        jLabel10.setToolTipText("");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, 30));

        empresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EMPRESA" }));
        empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaActionPerformed(evt);
            }
        });
        jPanel1.add(empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 150, 30));

        AgregarEmpresa.setText("+");
        AgregarEmpresa.setToolTipText("");
        AgregarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarEmpresaActionPerformed(evt);
            }
        });
        jPanel1.add(AgregarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 50, 30));

        tipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TIPO DE PAGO" }));
        tipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoPagoActionPerformed(evt);
            }
        });
        jPanel1.add(tipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 150, 30));

        fecha.setBackground(new java.awt.Color(252, 100, 68));
        fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fecha.setForeground(new java.awt.Color(252, 100, 68));
        fecha.setToolTipText("");
        jPanel1.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 200, 30));

        jLabel12.setBackground(new java.awt.Color(252, 100, 68));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(252, 100, 68));
        jLabel12.setText("FECHA:");
        jLabel12.setToolTipText("");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, 30));
        jPanel1.add(Errores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 1030, -1));

        realizarVenta.setBackground(new java.awt.Color(252, 100, 68));
        realizarVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        realizarVenta.setText("REALIZAR COMPRA");
        realizarVenta.setBorder(null);
        realizarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        realizarVenta.setEnabled(false);
        realizarVenta.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                realizarVentaMouseMoved(evt);
            }
        });
        realizarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                realizarVentaMouseExited(evt);
            }
        });
        realizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarVentaActionPerformed(evt);
            }
        });
        jPanel1.add(realizarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 180, 50));

        jLabel8.setBackground(new java.awt.Color(252, 100, 68));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(252, 100, 68));
        jLabel8.setText("EMPLEADO:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 110, 30));

        jLabel11.setBackground(new java.awt.Color(252, 100, 68));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(252, 100, 68));
        jLabel11.setText("ID EMPLEADO:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarEmpresaActionPerformed
        Agregar.tipo = 3;
        Agregar append = new Agregar();
        append.setVisible(true);
    }//GEN-LAST:event_AgregarEmpresaActionPerformed

    private void empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empresaActionPerformed

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        dispose(); // regresar
        Compra.info = info;
        Compra compra = new Compra();
        compra.setVisible(true);
    }//GEN-LAST:event_cerrarMouseClicked

    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        this.setState(CompraPiezaFrame.ICONIFIED); // minimizar
    }//GEN-LAST:event_minimizarMouseClicked

    private void regresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regresarMouseClicked
        dispose(); // regresar
        Compra.info = info;
        Compra compra = new Compra();
        compra.setVisible(true);
        
    }//GEN-LAST:event_regresarMouseClicked

    private void agregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarDetalleActionPerformed
        boolean vacio1 = tipoPago.getSelectedIndex() == 0;
        boolean vacio2 = empresa.getSelectedIndex() == 0;
        Errores.setText("");
        if(!vacio1 && !vacio2){
            tipoPago.setEnabled(false);
            empresa.setEnabled(false);
            Detalle detalle = new Detalle();
            detalle.setVisible(true);
        }else{
            Errores.setText("No puede haber campos vacios.");
        }
    }//GEN-LAST:event_agregarDetalleActionPerformed

    private void realizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarVentaActionPerformed
        float saldo = (float)0.00;
        boolean piezaExistente = false;
        
        MySQLMovimientoFinanciero auxMovimientoFinanciero = new MySQLMovimientoFinanciero();
        MySQLFacturaGeneral auxFacturaGeneral = new MySQLFacturaGeneral();
        MySQLEmpresa auxEmpresa = new MySQLEmpresa();
        MySQLTipoPago auxTipoPago = new MySQLTipoPago();
        MySQLTipoFactura auxTipoFactura = new MySQLTipoFactura();
        MySQLFacturaCompra auxFacturaCompra = new MySQLFacturaCompra();
        MySQLPieza auxPieza = new MySQLPieza();
        MySQLDetalleFacturaCompra auxDetalleFacturaCompra = new MySQLDetalleFacturaCompra();
        MySQLInventarioPieza auxInventarioPieza = new MySQLInventarioPieza();
        
        ArrayList<Empresa> listaEmpresa = auxEmpresa.listar();
        ArrayList<TipoPago> listaTipoPago = auxTipoPago.listar();
        ArrayList<TipoFactura> listaTipoFactura = auxTipoFactura.listar();
        ArrayList<MovimientoFinanciero> listaMovimientoFinanciero = auxMovimientoFinanciero.listar();
        ArrayList<InventarioPieza> listaInventarioPieza = auxInventarioPieza.listar();
        
        MovimientoFinanciero movimientoFinanciero = new MovimientoFinanciero();
        
        for(int i = 0; i < listaMovimientoFinanciero.size();i++){
            movimientoFinanciero = listaMovimientoFinanciero.get(i);
        }
        
        
        
        saldo = movimientoFinanciero.getTotal();
        
        
        if((float)saldo > (float)Detalle.total){
            
            Pieza pieza = new Pieza();
            movimientoFinanciero = new MovimientoFinanciero();
            FacturaGeneral facturaGeneral = new FacturaGeneral();
            FacturaCompra facturaCompra = new FacturaCompra();

            float total = (float)Detalle.total;
            Integer idEmpleado = Integer.parseInt(this.idEmpleado.getText());
            int idEmpresa = 0;
            int idTipoPago = 0;
            int idTipoFactura = 0;
            int idFacturaGeneral = 0;
            int idFacturaCompra = 0;
            for(int i = 0; i<listaEmpresa.size();i++){
                if(empresa.getSelectedItem().toString().equals(listaEmpresa.get(i).getNombre())){
                    idEmpresa = listaEmpresa.get(i).getIdEmpresa();
                }
            }
            for(int i = 0; i<listaTipoPago.size();i++){
                if(tipoPago.getSelectedItem().toString().equals(listaTipoPago.get(i).getTipo())){
                    idTipoPago = listaTipoPago.get(i).getIdTipoPago();
                }
            }
            for(int i = 0; i<listaTipoFactura.size();i++){
                if("COMPRA".equals(listaTipoFactura.get(i).getTipo())){
                    idTipoFactura = listaTipoFactura.get(i).getIdTipoFactura();
                }
            }
            facturaGeneral.setIdEmpleado(idEmpleado);
            facturaGeneral.setIdEmpresa(idEmpresa);
            facturaGeneral.setIdTipoFactura(idTipoFactura);
            facturaGeneral.setIdTipoPago(idTipoPago);
            facturaGeneral.setTotal(total);
            auxFacturaGeneral.insertar(facturaGeneral);
            ArrayList<FacturaGeneral> listaFacturaGeneral = auxFacturaGeneral.listar();
            for(int i = 0; i<listaFacturaGeneral.size();i++){
                idFacturaGeneral = listaFacturaGeneral.get(i).getIdFacturaGeneral();
            }
            pieza = auxPieza.obtenerId(Detalle.listaDetalleFacturaCompra.get(0).getIdPieza());

            facturaCompra.setIdProveedor(pieza.getIdProveedor());
            facturaCompra.setIdFacturaGeneral(idFacturaGeneral);
            auxFacturaCompra.insertar(facturaCompra);
            ArrayList<FacturaCompra> listaFacturaCompra = auxFacturaCompra.listar();
            for(int i = 0; i<listaFacturaCompra.size();i++){
                idFacturaCompra = listaFacturaCompra.get(i).getIdFacturaCompra();
            }
            for(int i = 0; i < Detalle.listaDetalleFacturaCompra.size(); i++){
                piezaExistente = false;
                Detalle.listaDetalleFacturaCompra.get(i).setIdFacturaCompra(idFacturaCompra);
                auxDetalleFacturaCompra.insertar(Detalle.listaDetalleFacturaCompra.get(i));
                listaInventarioPieza = auxInventarioPieza.listar();
                InventarioPieza inventarioPieza = new InventarioPieza();
                for(int j = 0; j < listaInventarioPieza.size(); j++){
                    if(Detalle.listaDetalleFacturaCompra.get(i).getIdPieza() == (listaInventarioPieza.get(j).getIdPieza())){
                        listaInventarioPieza.get(j).setCantidad(listaInventarioPieza.get(j).getCantidad() + Detalle.listaDetalleFacturaCompra.get(i).getCantidad());
                        auxInventarioPieza.modificar(listaInventarioPieza.get(j));
                        piezaExistente = true;
                    }
                }
                if(!piezaExistente){
                    inventarioPieza.setIdPieza(Detalle.listaDetalleFacturaCompra.get(i).getIdPieza());
                    inventarioPieza.setCantidad(Detalle.listaDetalleFacturaCompra.get(i).getCantidad());
                    auxInventarioPieza.insertar(inventarioPieza);
                }
            }
            dispose();
            Compra compra = new Compra();
            compra.setVisible(true);
            compra.Errores.setText("COMPRA EXITOSA");
            
            movimientoFinanciero.setMonto((float)Detalle.total);
            movimientoFinanciero.setIdTipoTransaccion(2);
            movimientoFinanciero.setTotal((float)saldo - (float)Detalle.total);
            auxMovimientoFinanciero.insertar(movimientoFinanciero);            
        }else{
            dispose();
            Compra compra = new Compra();
            compra.setVisible(true);
            compra.Errores.setText("COMPRA FALLIDA: NO CUENTA CON EL SALDO SUFICIENTE");
        }
    }//GEN-LAST:event_realizarVentaActionPerformed

    private void agregarDetalleMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarDetalleMouseMoved
        agregarDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    }//GEN-LAST:event_agregarDetalleMouseMoved

    private void agregarDetalleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarDetalleMouseExited
        agregarDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252,100,68)));
    }//GEN-LAST:event_agregarDetalleMouseExited

    private void realizarVentaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_realizarVentaMouseMoved
        realizarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    }//GEN-LAST:event_realizarVentaMouseMoved

    private void realizarVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_realizarVentaMouseExited
        realizarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252,100,68)));
    }//GEN-LAST:event_realizarVentaMouseExited

    private void tipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoPagoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CompraPiezaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompraPiezaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompraPiezaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompraPiezaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompraPiezaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarEmpresa;
    private javax.swing.JLabel Errores;
    public static javax.swing.JButton agregarDetalle;
    private javax.swing.JLabel cerrar;
    private javax.swing.JLabel empleado;
    public static javax.swing.JComboBox<String> empresa;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel idEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel minimizar;
    public static javax.swing.JButton realizarVenta;
    private javax.swing.JLabel regresar;
    public static javax.swing.JComboBox<String> tipoPago;
    public static javax.swing.JLabel totalFacturaCompra;
    // End of variables declaration//GEN-END:variables
}
