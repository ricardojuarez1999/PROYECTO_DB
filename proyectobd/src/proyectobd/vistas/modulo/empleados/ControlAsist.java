/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.vistas.modulo.empleados;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectobd.controladores.ControladorHorario;
import proyectobd.controladores.ControladorPersona;
import proyectobd.controladores.ControladorPuesto;
import proyectobd.dao.mysql.MySQLAsistencia;
import proyectobd.dao.mysql.MySQLInasistencia;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Asistencia;
import proyectobd.modelos.Empleado;
import proyectobd.modelos.Horario;
import proyectobd.modelos.Inasistencia;
import proyectobd.modelos.Persona;
import proyectobd.modelos.Puesto;
import proyectobd.vistas.Admin;
import proyectobd.vistas.Login;
import proyectobd.vistas.custom.CustomUI;

/**
 *
 * @author israe
 */
public class ControlAsist extends javax.swing.JFrame {

    private ControladorHorario controladorHorario;
    private String regreso;
    DefaultTableModel modelo;
    private Empleado info;

    /**
     * Creates new form ControlAsist
     */
    public ControlAsist() {
        initComponents();
        this.setLocationRelativeTo(null);
        controladorHorario = new ControladorHorario();

        this.modelo.addColumn("ID Asistencia");
        this.modelo.addColumn("Hora Entrada");
        this.modelo.addColumn("Hora Salida");
        this.modelo.addColumn("Fecha");
        this.modelo.addColumn("ID Empleado");

        this.jTable1.setModel(modelo);
    }

    public ControlAsist(String regreso, Empleado info) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.regreso = regreso;
        this.info = info;

        controladorHorario = new ControladorHorario();

        this.jLabel14.setText(String.valueOf(this.info.getIdEmpleado()));
        Persona persona = new ControladorPersona().obtenerPorID(this.info.getIdPersona());
        this.jLabel5.setText(persona.getNombre());
        Puesto puesto = new ControladorPuesto().buscarPuesto(this.info.getIdPuesto());
        this.jLabel7.setText(puesto.getNombre());

        modelo = new DefaultTableModel();
        this.modelo.addColumn("ID Asistencia");
        this.modelo.addColumn("Hora Entrada");
        this.modelo.addColumn("Hora Salida");
        this.modelo.addColumn("Fecha");
        this.modelo.addColumn("ID Empleado");

        this.jTable1.setModel(modelo);
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
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDCFecha = new com.toedter.calendar.JDateChooser();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_id_32px.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(238, 112, 82));
        jSeparator2.setForeground(new java.awt.Color(238, 112, 82));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 230, 10));

        jSeparator3.setBackground(new java.awt.Color(238, 112, 82));
        jSeparator3.setForeground(new java.awt.Color(238, 112, 82));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 230, 10));

        jLabel5.setBackground(new java.awt.Color(238, 112, 82));
        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(238, 112, 82));
        jLabel5.setText("Nombre Apellido");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 180, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_customer_32px_1.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(238, 112, 82));
        jSeparator4.setForeground(new java.awt.Color(238, 112, 82));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 230, 10));

        jLabel7.setBackground(new java.awt.Color(238, 112, 82));
        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(238, 112, 82));
        jLabel7.setText("Puesto");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 180, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_empleado32px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_User_96px_2.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, 90));

        jLabel14.setBackground(new java.awt.Color(238, 112, 82));
        jLabel14.setFont(new java.awt.Font("Microsoft JhengHei", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(238, 112, 82));
        jLabel14.setText("ID Empleado");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 180, 30));

        jButton2.setBackground(new java.awt.Color(238, 112, 82));
        jButton2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cerrar Sesion ");
        jButton2.setBorder(null);
        jButton2.setFocusPainted(false);
        jButton2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton2MouseMoved(evt);
            }
        });
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 230, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 700));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_regreso_32px.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 30, 50));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 30, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 760, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(238, 112, 82));
        jLabel16.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(238, 112, 82));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Resultados");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 370, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Asistencia", "Hora Entrada", "Hora Salida", "Fecha"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 680, 280));

        jDCFecha.setBackground(new java.awt.Color(255, 255, 255));
        jDCFecha.setForeground(new java.awt.Color(238, 112, 82));
        jPanel2.add(jDCFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 270, 30));

        jSeparator12.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator12.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 270, 10));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_fecha_32px.png"))); // NOI18N
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 50, -1));

        jLabel34.setBackground(new java.awt.Color(238, 112, 82));
        jLabel34.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(238, 112, 82));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Buscar Por");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 310, 30));

        jButton3.setBackground(new java.awt.Color(238, 112, 82));
        jButton3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("A??o");
        jButton3.setBorder(null);
        jButton3.setFocusPainted(false);
        jButton3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton3MouseMoved(evt);
            }
        });
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 90, 50));

        jButton4.setBackground(new java.awt.Color(238, 112, 82));
        jButton4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Mes / A??o");
        jButton4.setBorder(null);
        jButton4.setFocusPainted(false);
        jButton4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton4MouseMoved(evt);
            }
        });
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 90, 50));

        jButton5.setBackground(new java.awt.Color(238, 112, 82));
        jButton5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Borrar Tabla");
        jButton5.setBorder(null);
        jButton5.setFocusPainted(false);
        jButton5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton5MouseMoved(evt);
            }
        });
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 680, 50));

        jLabel35.setBackground(new java.awt.Color(238, 112, 82));
        jLabel35.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(238, 112, 82));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Fecha Inicio");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 310, 30));

        jLabel17.setBackground(new java.awt.Color(238, 112, 82));
        jLabel17.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(238, 112, 82));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Control  de Asistencia");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 370, -1));

        jButton6.setBackground(new java.awt.Color(238, 112, 82));
        jButton6.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Fecha");
        jButton6.setBorder(null);
        jButton6.setFocusPainted(false);
        jButton6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton6MouseMoved(evt);
            }
        });
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 90, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 760, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        if (this.regreso.equalsIgnoreCase("ModuloEmpleados")) {
            this.dispose();
            ModuloEmpleados modEmpleado = new ModuloEmpleados("admin", this.info);
            modEmpleado.setVisible(true);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        this.setState(IngresoEmpleado.ICONIFIED);//Arreglar
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jButton3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseMoved

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        if (this.jDCFecha.getDate() != null) {
            Date fecha = this.jDCFecha.getDate();
            long f = fecha.getTime();
            java.sql.Date aux = new java.sql.Date(f);
            String dato = String.valueOf(aux);
            String[] partes = dato.split("-");

            int a??o = Integer.parseInt(partes[0]);

            ArrayList<Asistencia> lista = null;
            try {
                lista = new MySQLAsistencia().listarPorA??o(a??o);
                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        String[] valores = new String[5];
                        valores[0] = String.valueOf(lista.get(i).getIdAsistencia());
                        valores[1] = lista.get(i).getHora_entrada();
                        if (lista.get(i).getHora_salida() == null) {
                            valores[2] = "SIN INGRESAR";
                        } else {
                            valores[2] = lista.get(i).getHora_salida();
                        }
                        f = lista.get(i).getFecha().getTime();

                        valores[3] = String.valueOf(new java.sql.Date(f));
                        valores[4] = String.valueOf(lista.get(i).getIdEmpleado());

                        modelo.addRow(valores);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontro ningun registro");
                }
            } catch (Excepcion e) {
                JOptionPane.showMessageDialog(this, "No se logro realizar la consulta correctamente intente mas tarde");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseMoved

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        if (this.jDCFecha.getDate() != null) {
            Date fecha = this.jDCFecha.getDate();
            long f = fecha.getTime();
            java.sql.Date aux = new java.sql.Date(f);
            String dato = String.valueOf(aux);
            String[] partes = dato.split("-");

            int a??o = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);

            ArrayList<Asistencia> lista = null;
            try {
                lista = new MySQLAsistencia().listarPorMesA??o(mes, a??o);
                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        String[] valores = new String[5];
                        valores[0] = String.valueOf(lista.get(i).getIdAsistencia());
                        valores[1] = lista.get(i).getHora_entrada();
                        if (lista.get(i).getHora_salida() == null) {
                            valores[2] = "SIN INGRESAR";
                        } else {
                            valores[2] = lista.get(i).getHora_salida();
                        }
                        f = lista.get(i).getFecha().getTime();

                        valores[3] = String.valueOf(new java.sql.Date(f));
                        valores[4] = String.valueOf(lista.get(i).getIdEmpleado());

                        modelo.addRow(valores);
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "No se encontro ningun registro");
                }
            } catch (Excepcion e) {
                JOptionPane.showMessageDialog(this, "No se logro realizar la consulta correctamente intente mas tarde");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseMoved

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton6MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseMoved

    }//GEN-LAST:event_jButton6MouseMoved

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        if (this.jDCFecha.getDate() != null) {
            Date fecha = this.jDCFecha.getDate();
            long f = fecha.getTime();
            java.sql.Date aux = new java.sql.Date(f);
            String dato = String.valueOf(aux);

            ArrayList<Asistencia> lista = null;
            try {
                lista = new MySQLAsistencia().listarPorFecha(dato);
                if (!lista.isEmpty()) {
                    System.out.println("Entro");
                    for (int i = 0; i < lista.size(); i++) {
                        String[] valores = new String[5];
                        valores[0] = String.valueOf(lista.get(i).getIdAsistencia());
                        valores[1] = lista.get(i).getHora_entrada();
                        if (lista.get(i).getHora_salida() == null) {
                            valores[2] = "SIN INGRESAR";
                        } else {
                            valores[2] = lista.get(i).getHora_salida();
                        }
                        f = lista.get(i).getFecha().getTime();

                        valores[3] = String.valueOf(new java.sql.Date(f));
                        valores[4] = String.valueOf(lista.get(i).getIdEmpleado());

                        modelo.addRow(valores);
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "No se encontro ningun registro");
                }

            } catch (Excepcion e) {
                JOptionPane.showMessageDialog(this, "No se logro realizar la consulta correctamente intente mas tarde");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha");
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseMoved
        jButton2.setBackground(new Color(204, 51, 0));
    }//GEN-LAST:event_jButton2MouseMoved

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(238, 112, 82));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Horario horario = controladorHorario.obtenerHorario(this.info.getIdHorario());

        if (horario != null) {
            Calendar calendario = Calendar.getInstance();
            String horaActual = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) + ":" + calendario.get(Calendar.SECOND);
            /*System.out.println("horaActual: " + horaActual);
            System.out.println("HoraSalida:" + horario.getSalida());*/

            try {
                DateFormat dateFormat = new SimpleDateFormat("hh:mm");

                Date DHoraSalida = dateFormat.parse(horario.getSalida());
                Date DHoraActual = dateFormat.parse(horaActual);

                if (DHoraSalida.compareTo(DHoraActual) > 0) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Tu hora de salida es: " + horario.getSalida() + ". Estas seguro que deseas salir? (se te contara como inasistencia)", "Cerrando Sesion", JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        try {
                            Inasistencia inasistencia = new Inasistencia();
                            inasistencia.setIdEmpleado(this.info.getIdEmpleado());
                            inasistencia.setIdTipoInasistencia(2);

                            MySQLInasistencia myinasistencia = new MySQLInasistencia();
                            myinasistencia.insertar(inasistencia);

                            MySQLAsistencia myasistencia = new MySQLAsistencia();
                            Asistencia aux = myasistencia.obtenerIdyFecha(this.info.getIdEmpleado());
                            aux.setHora_salida(horaActual);
                            myasistencia.modificar(aux);
                            JOptionPane.showMessageDialog(null, "Cerro Sesion correctamente");
                            dispose();
                            Login login = new Login();
                            login.setVisible(true);
                        } catch (Excepcion e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }
                } else {
                    MySQLAsistencia myasistencia = new MySQLAsistencia();
                    Asistencia aux = myasistencia.obtenerIdyFecha(this.info.getIdEmpleado());
                    aux.setHora_salida(horaActual);
                    myasistencia.modificar(aux);
                    JOptionPane.showMessageDialog(null, "Cerro Sesion correctamente");
                    dispose();
                    Login login = new Login();
                    login.setVisible(true);
                }

            } catch (ParseException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ControlAsist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlAsist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlAsist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlAsist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlAsist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDCFecha;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
