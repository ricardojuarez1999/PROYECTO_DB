/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.vistas.modulo.compraVenta;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import proyectobd.dao.mysql.MySQLDetalleFacturaVenta;
import proyectobd.dao.mysql.MySQLEstadoPieza;
import proyectobd.dao.mysql.MySQLInventarioPieza;
import proyectobd.dao.mysql.MySQLMarcaPieza;
import proyectobd.dao.mysql.MySQLNombrePieza;
import proyectobd.dao.mysql.MySQLPieza;
import proyectobd.modelos.DetalleFacturaVenta;
import proyectobd.modelos.EstadoPieza;
import proyectobd.modelos.InventarioPieza;
import proyectobd.modelos.MarcaPieza;
import proyectobd.modelos.NombrePieza;
import proyectobd.modelos.Pieza;

/**
 *
 * @author ricar
 */
public class DetalleVentaPieza extends javax.swing.JFrame {
    public static float total = 0;
    public static ArrayList <DetalleFacturaVenta> listaDetalleFacturaVenta = new ArrayList();
    /**
     * Creates new form DetalleVentaPieza
     */
    public DetalleVentaPieza() {
        initComponents();
        this.setLocationRelativeTo(null);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cerrar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        Numero = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        Cantidad = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Terminar = new javax.swing.JButton();
        Cnst = new javax.swing.JButton();
        totalDesing = new javax.swing.JLabel();
        IdPieza = new javax.swing.JFormattedTextField();
        jSeparator4 = new javax.swing.JSeparator();
        Agregar = new javax.swing.JButton();
        Errores = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_pieza_mini.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 30, 30));

        jLabel1.setBackground(new java.awt.Color(252, 100, 68));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(252, 100, 68));
        jLabel1.setText("CANTIDAD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, 30));

        jSeparator1.setForeground(new java.awt.Color(108, 108, 108));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 210, 10));

        cerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_Multiply_32px.png"))); // NOI18N
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });
        jPanel1.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, -1, 30));

        tabla.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabla.setForeground(new java.awt.Color(252, 100, 68));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID pieza", "Nombre ", "Precio", "Cantidad", "Marca", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setGridColor(new java.awt.Color(252, 100, 68));
        tabla.setSelectionBackground(new java.awt.Color(252, 100, 68));
        jScrollPane2.setViewportView(tabla);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 490, 180));

        tabla2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabla2.setForeground(new java.awt.Color(252, 100, 68));
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID pieza", "Nombre ", "Precio", "Cantidad", "Marca", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setGridColor(new java.awt.Color(252, 100, 68));
        tabla2.setSelectionBackground(new java.awt.Color(252, 100, 68));
        jScrollPane3.setViewportView(tabla2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 490, 180));

        jLabel3.setBackground(new java.awt.Color(252, 100, 68));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(252, 100, 68));
        jLabel3.setText("DETALLE DE VENTA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, 30));

        Numero.setBorder(null);
        Numero.setForeground(new java.awt.Color(252, 100, 68));
        Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        Numero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumeroKeyTyped(evt);
            }
        });
        jPanel1.add(Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 250, 30));

        jLabel4.setBackground(new java.awt.Color(252, 100, 68));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(252, 100, 68));
        jLabel4.setText("INVENTARIO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 250, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 250, 10));

        Cantidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Cantidad.setBorder(null);
        jPanel1.add(Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 250, 30));

        jLabel5.setBackground(new java.awt.Color(252, 100, 68));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(252, 100, 68));
        jLabel5.setText("DETALLE DE VENTA");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, -1, 30));

        jLabel6.setBackground(new java.awt.Color(252, 100, 68));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(252, 100, 68));
        jLabel6.setText("NUMERO DE PIEZA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, 30));

        Terminar.setBackground(new java.awt.Color(252, 100, 68));
        Terminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Terminar.setText("TERMINAR");
        Terminar.setBorder(null);
        Terminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Terminar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TerminarMouseMoved(evt);
            }
        });
        Terminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TerminarMouseExited(evt);
            }
        });
        Terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerminarActionPerformed(evt);
            }
        });
        jPanel1.add(Terminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 160, 50));

        Cnst.setBackground(new java.awt.Color(252, 100, 68));
        Cnst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cnst.setText("CNST");
        Cnst.setBorder(null);
        Cnst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cnst.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                CnstMouseMoved(evt);
            }
        });
        Cnst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CnstMouseExited(evt);
            }
        });
        Cnst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CnstActionPerformed(evt);
            }
        });
        jPanel1.add(Cnst, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 70, 50));

        totalDesing.setBackground(new java.awt.Color(252, 100, 68));
        totalDesing.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalDesing.setForeground(new java.awt.Color(252, 100, 68));
        totalDesing.setText("TOTAL:          0.00 Q");
        jPanel1.add(totalDesing, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, 180, 30));

        IdPieza.setBorder(null);
        IdPieza.setForeground(new java.awt.Color(252, 100, 68));
        IdPieza.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        IdPieza.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IdPieza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IdPiezaKeyTyped(evt);
            }
        });
        jPanel1.add(IdPieza, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 250, 30));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 250, 10));

        Agregar.setBackground(new java.awt.Color(252, 100, 68));
        Agregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Agregar.setText("AGREGAR");
        Agregar.setBorder(null);
        Agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Agregar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AgregarMouseMoved(evt);
            }
        });
        Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AgregarMouseExited(evt);
            }
        });
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });
        jPanel1.add(Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 160, 50));
        jPanel1.add(Errores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 1020, 20));

        jLabel8.setBackground(new java.awt.Color(252, 100, 68));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(252, 100, 68));
        jLabel8.setText("ID DE PIEZA");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        dispose();
    }//GEN-LAST:event_cerrarMouseClicked

    private void NumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeroKeyTyped
        if(Numero.getText().length() == 9){
            evt.consume();
        }
        
    }//GEN-LAST:event_NumeroKeyTyped

    private void TerminarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TerminarMouseMoved
        Terminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    }//GEN-LAST:event_TerminarMouseMoved

    private void TerminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TerminarMouseExited
        Terminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252,100,68)));
    }//GEN-LAST:event_TerminarMouseExited

    private void TerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminarActionPerformed
        if(!listaDetalleFacturaVenta.isEmpty()){
            VentaPiezaFrame.totalDesign.setText("TOTAL:  "+this.total+" Q");
            this.setVisible(false);
            VentaPiezaFrame.tipoPago.setEnabled(true);
            VentaPiezaFrame.Nit.setEnabled(true);
            VentaPiezaFrame.Nombre.setEnabled(true);
            VentaPiezaFrame.Apellido.setEnabled(true);
            VentaPiezaFrame.DPI.setEnabled(true);
            VentaPiezaFrame.Direccion.setEnabled(true);
            VentaPiezaFrame.agregarDetalle.setEnabled(false);
            VentaPiezaFrame.realizarVenta.setEnabled(true);
            VentaPiezaFrame.empresa.setEnabled(false);
        }else{
            dispose();
        }
    }//GEN-LAST:event_TerminarActionPerformed

    private void CnstMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CnstMouseMoved
        Cnst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    }//GEN-LAST:event_CnstMouseMoved

    private void CnstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CnstMouseExited
        Cnst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252,100,68)));
    }//GEN-LAST:event_CnstMouseExited
    public static String getEstadoPieza(Pieza pieza){
        MySQLEstadoPieza aux = new MySQLEstadoPieza();
        ArrayList <EstadoPieza> lista = aux.listar();
        for(int i = 0; i<lista.size();i++){
            if(lista.get(i).getIdEstadoPieza() == pieza.getIdEstadoPieza() ){
                return "" + lista.get(i).getEstado();
            }
        }
        return "";
    }
    public static String getNombrePieza(Pieza pieza){
        MySQLNombrePieza aux = new MySQLNombrePieza();
        ArrayList <NombrePieza> lista = aux.listar();
        for(int i = 0; i<lista.size();i++){
            if(lista.get(i).getIdNombrePieza() == pieza.getIdNombrePieza() ){
                return "" + lista.get(i).getNombre();
            }
        }
        return "";
    }
    public static String getMarcaPieza(Pieza pieza){
        MySQLMarcaPieza aux = new MySQLMarcaPieza();
        ArrayList <MarcaPieza> lista = aux.listar();
        for(int i = 0; i<lista.size();i++){
            if(lista.get(i).getIdMarcaPieza() == pieza.getIdMarcaPieza()){
                return "" + lista.get(i).getMarca();
            }
        }
        return "";
    }
    
    private void CnstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CnstActionPerformed
        Errores.setText("");
        if(Numero.getValue() != null){
            int numero = Integer.parseInt(Numero.getValue().toString());
            DefaultTableModel model = (DefaultTableModel)tabla.getModel();
            model.setRowCount(0);
            MySQLInventarioPieza auxInventarioPieza = new MySQLInventarioPieza();
            MySQLPieza auxPieza = new MySQLPieza();
            ArrayList <InventarioPieza> listaInventarioPieza = auxInventarioPieza.listar();
            DetalleFacturaVenta detalleFacturaVenta = new DetalleFacturaVenta();
            boolean existePieza = false;
            Pieza pieza = new Pieza();
            for(int i = 0; i < listaInventarioPieza.size();i++){
                pieza = auxPieza.obtenerId(listaInventarioPieza.get(i).getIdPieza());
                if(numero == pieza.getNumeroPieza()){
                    existePieza = true;
                    model.addRow(new String[]{""+pieza.getIdPieza(),getNombrePieza(pieza),""+pieza.getPrecio_venta(),""+listaInventarioPieza.get(i).getCantidad(),getMarcaPieza(pieza),getEstadoPieza(pieza)});
                    
                    
                }
            }
            if(!existePieza){
                Errores.setText("Este numero de pieza no se maneja");
            }
        }else{
            Errores.setText("Debe ingresar un numero de pieza");
        }
    }//GEN-LAST:event_CnstActionPerformed

    private void IdPiezaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdPiezaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_IdPiezaKeyTyped

    private void AgregarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarMouseMoved
        Agregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    }//GEN-LAST:event_AgregarMouseMoved

    private void AgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarMouseExited
        Agregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252,100,68)));
    }//GEN-LAST:event_AgregarMouseExited

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        DefaultTableModel model2 = (DefaultTableModel)tabla2.getModel();
        Errores.setText("");
        if((IdPieza.getValue() != null)&& (Integer.parseInt(Cantidad.getValue().toString()) > 0)){
            
            int cantidad = Integer.parseInt(Cantidad.getValue().toString());
            int idPieza = Integer.parseInt(IdPieza.getText());
            
            MySQLDetalleFacturaVenta auxDetalleFacturaVenta = new MySQLDetalleFacturaVenta();
            DetalleFacturaVenta detalleFacturaVenta = new DetalleFacturaVenta();
            MySQLInventarioPieza auxInventarioPieza = new MySQLInventarioPieza();
            MySQLPieza auxPieza = new MySQLPieza();
            ArrayList <InventarioPieza> listaInventarioPieza = auxInventarioPieza.listar();
            ArrayList <Pieza> listaPieza = auxPieza.listar();
            
            boolean existePieza = false;
            boolean cantidadSuperior = false;
            double subtotal = 0.00;
            Pieza pieza = new Pieza();
            boolean indexIdPieza = false;
            
            for(int i = 0; i < listaPieza.size(); i++){
                if(listaPieza.get(i).getIdPieza() == idPieza){
                    indexIdPieza = true;
                    pieza = auxPieza.obtenerId(idPieza);
                }
            }
            
            
            
            
            for(int i = 0; i < listaInventarioPieza.size();i++){
                if(idPieza == listaInventarioPieza.get(i).getIdPieza()){
                    existePieza = true;
                    cantidadSuperior = listaInventarioPieza.get(i).getCantidad() < cantidad;
                    if(!cantidadSuperior){
                        subtotal = cantidad * pieza.getPrecio_venta();
                        this.total = this.total + (float)subtotal;
                        this.totalDesing.setText("TOTAL:         "+this.total+" Q");
                        model2.addRow(new String[]{""+pieza.getIdPieza(),getNombrePieza(pieza),""+pieza.getPrecio_venta(),""+cantidad,getMarcaPieza(pieza),""+subtotal});
                        listaInventarioPieza.get(i).setCantidad(listaInventarioPieza.get(i).getCantidad() - cantidad);
                        auxInventarioPieza.modificar(listaInventarioPieza.get(i));
                        detalleFacturaVenta.setCantidad(cantidad);
                        detalleFacturaVenta.setIdPieza(idPieza);
                        listaDetalleFacturaVenta.add(detalleFacturaVenta);
                    }
                }
            }
            if(!existePieza){
                Errores.setText("Este numero de pieza no se maneja");
            }else if(cantidadSuperior){
                Errores.setText("La cantidad deseada es mayor a lo que se puede surtir");
            }else if(!indexIdPieza){
                Errores.setText("Este ID de pieza no esta ingresado en la base de datos");
            }
        }else{
            Errores.setText("No se pueden ingresar campos vacios");
        }   
    }//GEN-LAST:event_AgregarActionPerformed

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
            java.util.logging.Logger.getLogger(DetalleVentaPieza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaPieza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaPieza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaPieza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleVentaPieza().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Agregar;
    private javax.swing.JSpinner Cantidad;
    public static javax.swing.JButton Cnst;
    private javax.swing.JLabel Errores;
    private javax.swing.JFormattedTextField IdPieza;
    private javax.swing.JFormattedTextField Numero;
    public static javax.swing.JButton Terminar;
    private javax.swing.JLabel cerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla2;
    private javax.swing.JLabel totalDesing;
    // End of variables declaration//GEN-END:variables
}