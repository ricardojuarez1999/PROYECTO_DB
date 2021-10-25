/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.vistas.modulo.equipos;

import java.awt.Color;
import static java.awt.Color.white;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectobd.controladores.ControladorEmpleado;
import proyectobd.controladores.ControladorEquipo;
import proyectobd.controladores.ControladorHorario;
import proyectobd.controladores.ControladorPersona;
import proyectobd.controladores.ControladorPuesto;
import proyectobd.dao.mysql.MySQLAsistencia;
import proyectobd.dao.mysql.MySQLDetalleEquipo;
import proyectobd.dao.mysql.MySQLInasistencia;
import proyectobd.excepciones.Excepcion;
import proyectobd.modelos.Asistencia;
import proyectobd.modelos.DetalleEquipo;
import proyectobd.modelos.Empleado;
import proyectobd.modelos.Equipo;
import proyectobd.modelos.Horario;
import proyectobd.modelos.Inasistencia;
import proyectobd.modelos.Persona;
import proyectobd.modelos.Puesto;
import proyectobd.vistas.Admin;
import proyectobd.vistas.Login;
import proyectobd.vistas.custom.CustomUI;
import proyectobd.vistas.modulo.empleados.Consultar;

/**
 *
 * @author israe
 */
public class ModificarEquip extends javax.swing.JFrame {

    private ControladorHorario controladorHorario;
    private ControladorEquipo controladorEquipo;
    private ControladorPersona controladorPersona;
    private ControladorEmpleado controladorEmpleado;
    private ControladorPuesto controladorPuesto;
    private String regreso;
    private Empleado info;

    DefaultTableModel modelo;

    /**
     * Creates new form ModificarEquip
     */
    public ModificarEquip() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.regreso = regreso;
        this.info = info;

        this.jCBEquipos.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBEquipos.setUI(new CustomUI());

        this.controladorPersona = new ControladorPersona();
        this.controladorPuesto = new ControladorPuesto();
        this.controladorEmpleado = new ControladorEmpleado();

        this.jLabel3.setText(String.valueOf(this.info.getIdEmpleado()));
        Persona persona = new ControladorPersona().obtenerPorID(this.info.getIdPersona());
        this.jLabel5.setText(persona.getNombre());
        Puesto puesto = new ControladorPuesto().buscarPuesto(this.info.getIdPuesto());
        this.jLabel7.setText(puesto.getNombre());

        controladorEquipo = new ControladorEquipo();
        ArrayList<Equipo> equipos = controladorEquipo.obtenerEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            jCBEquipos.addItem(equipos.get(i).getNombre());
        }

        this.jCBEquipos1.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBEquipos1.setUI(new CustomUI());

        controladorEquipo = new ControladorEquipo();
        ArrayList<Equipo> equipos1 = controladorEquipo.obtenerEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            jCBEquipos1.addItem(equipos.get(i).getNombre());
        }
        controladorHorario = new ControladorHorario();

        modelo = new DefaultTableModel();
        modelo.addColumn("ID Empleado");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Puesto");

        this.jTable1.setModel(modelo);

        this.jTFIdEquipo.setEnabled(false);
        this.jTFIdEmpleado.setEnabled(false);
        this.jTFIdEncargado.setEnabled(false);
        this.jTFNombreEquipo.setEnabled(false);
        this.jCBEquipos1.setEnabled(false);

        this.jButton4.setEnabled(false);
        this.jButton10.setEnabled(false);
        this.jButton12.setEnabled(false);
        this.jButton11.setEnabled(false);
    }

    public ModificarEquip(String regreso, Empleado info) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.regreso = regreso;
        this.info = info;

        controladorHorario = new ControladorHorario();
        this.jLabel3.setText(String.valueOf(this.info.getIdEmpleado()));
        Persona persona = new ControladorPersona().obtenerPorID(this.info.getIdPersona());
        this.jLabel5.setText(persona.getNombre());
        Puesto puesto = new ControladorPuesto().buscarPuesto(this.info.getIdPuesto());
        this.jLabel7.setText(puesto.getNombre());

        this.jCBEquipos.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBEquipos.setUI(new CustomUI());

        this.controladorPersona = new ControladorPersona();
        this.controladorPuesto = new ControladorPuesto();
        this.controladorEmpleado = new ControladorEmpleado();

        controladorEquipo = new ControladorEquipo();
        ArrayList<Equipo> equipos = controladorEquipo.obtenerEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            jCBEquipos.addItem(equipos.get(i).getNombre());
        }

        this.jCBEquipos1.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBEquipos1.setUI(new CustomUI());

        controladorEquipo = new ControladorEquipo();
        ArrayList<Equipo> equipos1 = controladorEquipo.obtenerEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            jCBEquipos1.addItem(equipos.get(i).getNombre());
        }
        controladorHorario = new ControladorHorario();

        modelo = new DefaultTableModel();
        modelo.addColumn("ID Empleado");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Puesto");

        this.jTable1.setModel(modelo);

        this.jTFIdEquipo.setEnabled(false);
        this.jTFIdEmpleado.setEnabled(false);
        this.jTFIdEncargado.setEnabled(false);
        this.jTFNombreEquipo.setEnabled(false);
        this.jCBEquipos1.setEnabled(false);

        this.jButton4.setEnabled(false);
        this.jButton10.setEnabled(false);
        this.jButton12.setEnabled(false);
        this.jButton11.setEnabled(false);
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
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTFNombreEquipo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jTFIdEquipo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTFIdEncargado = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jCBEquipos = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jTFIdEmpleado = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCBEquipos1 = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

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

        jLabel3.setBackground(new java.awt.Color(238, 112, 82));
        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 112, 82));
        jLabel3.setText("ID Empleado");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 180, 30));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 900));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(238, 112, 82));
        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(238, 112, 82));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Equipos");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_id_32px.png"))); // NOI18N
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jSeparator5.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 120, 10));

        jLabel23.setBackground(new java.awt.Color(238, 112, 82));
        jLabel23.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(238, 112, 82));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Nombre del Equipo");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 310, 30));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_customer_32px_1.png"))); // NOI18N
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jTFNombreEquipo.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFNombreEquipo.setForeground(new java.awt.Color(153, 153, 153));
        jTFNombreEquipo.setBorder(null);
        jTFNombreEquipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFNombreEquipoFocusGained(evt);
            }
        });
        jPanel2.add(jTFNombreEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 260, 40));

        jSeparator8.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator8.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 310, 10));

        jTFIdEquipo.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFIdEquipo.setBorder(null);
        jTFIdEquipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFIdEquipoKeyTyped(evt);
            }
        });
        jPanel2.add(jTFIdEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 70, 40));

        jLabel16.setBackground(new java.awt.Color(238, 112, 82));
        jLabel16.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(238, 112, 82));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("ID Encargado");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 120, 30));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_id_32px.png"))); // NOI18N
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, -1, -1));

        jTFIdEncargado.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFIdEncargado.setBorder(null);
        jTFIdEncargado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFIdEncargadoKeyTyped(evt);
            }
        });
        jPanel2.add(jTFIdEncargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 70, 40));

        jSeparator6.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 120, 10));

        jButton3.setBackground(new java.awt.Color(238, 112, 82));
        jButton3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Obtener  Info.");
        jButton3.setBorder(null);
        jButton3.setFocusPainted(false);
        jButton3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton3MouseMoved(evt);
            }
        });
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 160, 50));

        jLabel24.setBackground(new java.awt.Color(238, 112, 82));
        jLabel24.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(238, 112, 82));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Integrantes");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 370, -1));

        jButton4.setBackground(new java.awt.Color(238, 112, 82));
        jButton4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Agregar");
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
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 190, 50));

        jCBEquipos.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jCBEquipos.setForeground(new java.awt.Color(238, 112, 82));
        jCBEquipos.setBorder(null);
        jCBEquipos.setFocusable(false);
        jCBEquipos.setOpaque(false);
        jCBEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEquiposActionPerformed(evt);
            }
        });
        jPanel2.add(jCBEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 310, 30));

        jLabel43.setBackground(new java.awt.Color(238, 112, 82));
        jLabel43.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(238, 112, 82));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("ID Equipo");
        jPanel2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, 30));

        jButton10.setBackground(new java.awt.Color(238, 112, 82));
        jButton10.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Guardar cambios");
        jButton10.setBorder(null);
        jButton10.setFocusPainted(false);
        jButton10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton10MouseMoved(evt);
            }
        });
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 690, 50));

        jLabel44.setBackground(new java.awt.Color(238, 112, 82));
        jLabel44.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(238, 112, 82));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Modificar Equipo");
        jPanel2.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 370, -1));

        jLabel45.setBackground(new java.awt.Color(238, 112, 82));
        jLabel45.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(238, 112, 82));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Equipo de destino");
        jPanel2.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 160, 30));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_id_32px.png"))); // NOI18N
        jPanel2.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jTFIdEmpleado.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFIdEmpleado.setBorder(null);
        jPanel2.add(jTFIdEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 70, 40));

        jSeparator15.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator15.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 120, 10));

        jButton11.setBackground(new java.awt.Color(238, 112, 82));
        jButton11.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Mover empleado");
        jButton11.setBorder(null);
        jButton11.setFocusPainted(false);
        jButton11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton11MouseMoved(evt);
            }
        });
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11MouseExited(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, 140, 50));

        jButton12.setBackground(new java.awt.Color(238, 112, 82));
        jButton12.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Consultar Empleado");
        jButton12.setBorder(null);
        jButton12.setFocusPainted(false);
        jButton12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton12MouseMoved(evt);
            }
        });
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12MouseExited(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 320, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 680, 140));

        jCBEquipos1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jCBEquipos1.setForeground(new java.awt.Color(238, 112, 82));
        jCBEquipos1.setBorder(null);
        jCBEquipos1.setFocusable(false);
        jCBEquipos1.setOpaque(false);
        jCBEquipos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEquipos1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCBEquipos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 310, 30));

        jLabel47.setBackground(new java.awt.Color(238, 112, 82));
        jLabel47.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(238, 112, 82));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("ID Empleado");
        jPanel2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 120, 30));

        jLabel25.setBackground(new java.awt.Color(238, 112, 82));
        jLabel25.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(238, 112, 82));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Detalle  del Equipo");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 370, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 760, 850));

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jTFNombreEquipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFNombreEquipoFocusGained
        this.jTFNombreEquipo.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jTFNombreEquipoFocusGained

    private void jTFIdEquipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIdEquipoKeyTyped
        char a = evt.getKeyChar();
        if (!Character.isDigit(a)) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIdEquipoKeyTyped

    private void jTFIdEncargadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIdEncargadoKeyTyped
        char a = evt.getKeyChar();

        if (!Character.isDigit(a)) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIdEncargadoKeyTyped

    private void jButton3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseMoved
        jButton3.setBackground(new Color(204, 51, 0));
    }//GEN-LAST:event_jButton3MouseMoved

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setBackground(new Color(238, 112, 82));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        ArrayList<Equipo> equipos = controladorEquipo.obtenerEquipos();
        Equipo auxEquipo = new Equipo();
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getNombre().equals((String) this.jCBEquipos.getSelectedItem())) {
                auxEquipo = equipos.get(i);
            }
        }

        auxEquipo = controladorEquipo.buscarEquipoPorID(auxEquipo.getIdEquipo());

        if (auxEquipo != null) {
            this.jTFIdEquipo.setText(String.valueOf(auxEquipo.getIdEquipo()));
            this.jTFIdEquipo.setEnabled(false);
            this.jTFNombreEquipo.setText(auxEquipo.getNombre());
            this.jTFIdEncargado.setText(String.valueOf(auxEquipo.getIdEmpleado()));

            ArrayList<DetalleEquipo> lista = new MySQLDetalleEquipo().listarIdEquipo(Integer.parseInt(this.jTFIdEquipo.getText()));

            String[] valores = new String[4];
            for (int i = 0; i < lista.size(); i++) {
                Empleado e;
                e = controladorEmpleado.buscarEmpleado(lista.get(i).getIdEmpleado());
                valores[0] = String.valueOf(e.getIdEmpleado());

                if (e != null) {

                    Persona p;
                    p = controladorPersona.obtenerPorID(e.getIdPersona());
                    if (p != null) {
                        valores[1] = p.getNombre();
                        valores[2] = p.getApellido();
                    }

                    Puesto pu;
                    pu = controladorPuesto.buscarPuesto(e.getIdPuesto());

                    valores[3] = pu.getNombre();
                    modelo.addRow(valores);
                }
            }

            this.jTFIdEncargado.setEnabled(true);
            this.jTFNombreEquipo.setEnabled(true);
            this.jTFIdEmpleado.setEnabled(true);
            this.jCBEquipos1.setEnabled(true);
            this.jButton4.setEnabled(true);
            this.jButton10.setEnabled(true);
            this.jButton12.setEnabled(true);
            this.jButton11.setEnabled(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseMoved

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MySQLDetalleEquipo conexionDetalle = new MySQLDetalleEquipo();

        if (!this.jTFIdEmpleado.getText().equals("")) {
            Empleado auxEmp = controladorEmpleado.buscarEmpleado(Integer.parseInt(this.jTFIdEmpleado.getText()));

            if (auxEmp != null) {
                DetalleEquipo auxDetalle = conexionDetalle.obtenerIdEmpleado(Integer.parseInt(this.jTFIdEmpleado.getText()));
                if (auxDetalle == null) {
                    auxDetalle = new DetalleEquipo();
                    auxDetalle.setIdEmpleado(Integer.parseInt(this.jTFIdEmpleado.getText()));
                    auxDetalle.setIdEquipo(Integer.parseInt(this.jTFIdEquipo.getText()));

                    conexionDetalle.insertar(auxDetalle);
                    JOptionPane.showMessageDialog(this, "Se le asigno el equipo correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "El empleado ya pertenece a un equipo");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No existe dicho empleado");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Rellene el campo ID empleado para realizar la accion");
        }
        this.jTFIdEmpleado.setText("");
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        Equipo auxEquipo = controladorEquipo.buscarEquipoPorID(Integer.parseInt(this.jTFIdEquipo.getText()));
        if (auxEquipo != null) {
            this.jTFIdEquipo.setText(String.valueOf(auxEquipo.getIdEquipo()));
            this.jTFIdEquipo.setEnabled(false);
            this.jTFNombreEquipo.setText(auxEquipo.getNombre());
            this.jTFIdEncargado.setText(String.valueOf(auxEquipo.getIdEmpleado()));

            ArrayList<DetalleEquipo> lista = new MySQLDetalleEquipo().listarIdEquipo(Integer.parseInt(this.jTFIdEquipo.getText()));

            String[] valores = new String[4];
            for (int i = 0; i < lista.size(); i++) {
                Empleado e;
                e = controladorEmpleado.buscarEmpleado(lista.get(i).getIdEmpleado());
                valores[0] = String.valueOf(e.getIdEmpleado());

                if (e != null) {

                    Persona p;
                    p = controladorPersona.obtenerPorID(e.getIdPersona());
                    if (p != null) {
                        valores[1] = p.getNombre();
                        valores[2] = p.getApellido();
                    }

                    Puesto pu;
                    pu = controladorPuesto.buscarPuesto(e.getIdPuesto());

                    valores[3] = pu.getNombre();
                    modelo.addRow(valores);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        if (this.regreso.equalsIgnoreCase("Equipos")) {
            this.dispose();
            Equipos modEmpleado = new Equipos("admin", this.info);
            modEmpleado.setVisible(true);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        this.setState(CrearEquip.ICONIFIED);//Arreglar
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jCBEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBEquiposActionPerformed

    private void jButton10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseMoved

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int cont = this.jTable1.getRowCount();
        for (int i = cont - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        Equipo auxEquipo = new Equipo();
        auxEquipo.setIdEquipo(Integer.parseInt(this.jTFIdEquipo.getText()));
        auxEquipo.setNombre(this.jTFNombreEquipo.getText());
        auxEquipo.setIdEmpleado(Integer.parseInt(this.jTFIdEncargado.getText()));

        if (this.controladorEquipo.modificarEquipo(auxEquipo)) {
            JOptionPane.showMessageDialog(this, "Se modifico correctamente");
            this.jTFIdEncargado.setText("");
            this.jTFIdEquipo.setText("");
            this.jTFNombreEquipo.setText("");

            ArrayList<Equipo> equipos = controladorEquipo.obtenerEquipos();
            jCBEquipos.removeAllItems();
            for (int i = 0; i < equipos.size(); i++) {
                jCBEquipos.addItem(equipos.get(i).getNombre());
            }

            this.jCBEquipos1.removeAllItems();
            for (int i = 0; i < equipos.size(); i++) {
                jCBEquipos1.addItem(equipos.get(i).getNombre());
            }

        }
        this.jTFIdEquipo.setEnabled(false);
        this.jTFIdEmpleado.setEnabled(false);
        this.jTFIdEncargado.setEnabled(false);
        this.jTFNombreEquipo.setEnabled(false);
        this.jCBEquipos1.setEnabled(false);

        this.jButton4.setEnabled(false);
        this.jButton10.setEnabled(false);
        this.jButton12.setEnabled(false);
        this.jButton11.setEnabled(false);

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseMoved

    private void jButton11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseExited

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (!this.jTFIdEmpleado.getText().equals("")) {
            Equipo auxEquipo = controladorEquipo.buscarEquipoPorID(Integer.parseInt(this.jTFIdEquipo.getText()));

            if (auxEquipo != null) {
                ArrayList<DetalleEquipo> lista = new MySQLDetalleEquipo().listarIdEquipo(auxEquipo.getIdEquipo());
                for (int i = 0; i < lista.size(); i++) {
                    if (Integer.parseInt(this.jTFIdEmpleado.getText()) == lista.get(i).getIdEmpleado()) {
                        int idEquipo = 0;
                        ArrayList<Equipo> listaEquipos = controladorEquipo.obtenerEquipos();
                        for (int j = 0; j < listaEquipos.size(); j++) {
                            if (listaEquipos.get(j).getNombre().equals((String) this.jCBEquipos1.getSelectedItem())) {
                                idEquipo = listaEquipos.get(j).getIdEquipo();
                                break;
                            }
                        }

                        lista.get(i).setIdEquipo(idEquipo);

                        new MySQLDetalleEquipo().modificar(lista.get(i));
                        JOptionPane.showMessageDialog(this, "Se modifico correctamente");

                    }
                }
                this.jTFIdEmpleado.setText("");
                int cont = this.jTable1.getRowCount();
                for (int i = cont - 1; i >= 0; i--) {
                    modelo.removeRow(i);
                }
                auxEquipo = controladorEquipo.buscarEquipoPorID(Integer.parseInt(this.jTFIdEquipo.getText()));
                if (auxEquipo != null) {
                    this.jTFIdEquipo.setText(String.valueOf(auxEquipo.getIdEquipo()));
                    this.jTFIdEquipo.setEnabled(false);
                    this.jTFNombreEquipo.setText(auxEquipo.getNombre());
                    this.jTFIdEncargado.setText(String.valueOf(auxEquipo.getIdEmpleado()));

                    lista = new MySQLDetalleEquipo().listarIdEquipo(Integer.parseInt(this.jTFIdEquipo.getText()));

                    String[] valores = new String[4];
                    for (int i = 0; i < lista.size(); i++) {
                        Empleado e;
                        e = controladorEmpleado.buscarEmpleado(lista.get(i).getIdEmpleado());
                        valores[0] = String.valueOf(e.getIdEmpleado());

                        if (e != null) {

                            Persona p;
                            p = controladorPersona.obtenerPorID(e.getIdPersona());
                            if (p != null) {
                                valores[1] = p.getNombre();
                                valores[2] = p.getApellido();
                            }

                            Puesto pu;
                            pu = controladorPuesto.buscarPuesto(e.getIdPuesto());

                            valores[3] = pu.getNombre();
                            modelo.addRow(valores);
                        }
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Ingresa el ID del empleado");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseMoved

    private void jButton12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseExited

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.dispose();
        Consultar consultar = new Consultar("ModificarEquip",this.info);
        consultar.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jCBEquipos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEquipos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBEquipos1ActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarEquip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jCBEquipos;
    private javax.swing.JComboBox<String> jCBEquipos1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTFIdEmpleado;
    private javax.swing.JTextField jTFIdEncargado;
    private javax.swing.JTextField jTFIdEquipo;
    private javax.swing.JTextField jTFNombreEquipo;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
