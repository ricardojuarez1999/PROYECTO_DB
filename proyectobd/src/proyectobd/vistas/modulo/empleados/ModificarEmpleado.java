/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.vistas.modulo.empleados;

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
import proyectobd.controladores.ControladorEmpleado;
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
public class ModificarEmpleado extends javax.swing.JFrame {

    private ControladorEmpleado controladorEmpleado;
    private ControladorPuesto controladorPuesto;
    private ControladorHorario controladorHorario;
    private ControladorPersona controladorPersona;
    private String regreso;
    private Empleado empleado;
    private Persona persona;
    private Puesto puesto;
    private Horario horario;

    private Empleado info; // Datos del login

    public ModificarEmpleado() {
        initComponents();
        this.setLocationRelativeTo(null);

        this.jCBPuesto.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBPuesto.setUI(new CustomUI());
        this.jCBHorario.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBHorario.setUI(new CustomUI());

        controladorEmpleado = new ControladorEmpleado();
        controladorPersona = new ControladorPersona();

        controladorPuesto = new ControladorPuesto();
        ArrayList<Puesto> puestos = controladorPuesto.obtenerPuestos();

        for (int i = 0; i < puestos.size(); i++) {
            jCBPuesto.addItem(puestos.get(i).getNombre());
        }

        controladorHorario = new ControladorHorario();
        ArrayList<Horario> horarios = controladorHorario.obtenerHorarios();

        for (int i = 0; i < horarios.size(); i++) {
            jCBHorario.addItem(horarios.get(i).getEntrada() + " a " + horarios.get(i).getSalida());
        }

        bloquearCampos();

    }

    public ModificarEmpleado(String regreso, Empleado info) {
        initComponents();
        this.setLocationRelativeTo(null);

        this.regreso = regreso;
        this.info = info;

        this.jLabel14.setText(String.valueOf(this.info.getIdEmpleado()));
        Persona auxPersona = new ControladorPersona().obtenerPorID(this.info.getIdPersona());
        this.jLabel5.setText(auxPersona.getNombre());
        Puesto auxPuesto = new ControladorPuesto().buscarPuesto(this.info.getIdPuesto());
        this.jLabel7.setText(auxPuesto.getNombre());

        this.jCBPuesto.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBPuesto.setUI(new CustomUI());
        this.jCBHorario.setBackground(new java.awt.Color(255, 255, 255));
        this.jCBHorario.setUI(new CustomUI());

        controladorEmpleado = new ControladorEmpleado();

        controladorPersona = new ControladorPersona();

        controladorPuesto = new ControladorPuesto();
        ArrayList<Puesto> puestos = controladorPuesto.obtenerPuestos();

        for (int i = 0; i < puestos.size(); i++) {
            jCBPuesto.addItem(puestos.get(i).getNombre());
        }

        controladorHorario = new ControladorHorario();
        ArrayList<Horario> horarios = controladorHorario.obtenerHorarios();

        for (int i = 0; i < horarios.size(); i++) {
            jCBHorario.addItem(horarios.get(i).getEntrada() + " a " + horarios.get(i).getSalida());
        }
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.jTFDpi.setEnabled(false);
        this.jTFNit.setEnabled(false);
        this.jTFNombre.setEnabled(false);
        this.jTFApellido.setEnabled(false);
        this.jTFDireccion.setEnabled(false);
        this.jPFContrasenia.setEnabled(false);
        this.jCBPuesto.setEnabled(false);
        this.jCBHorario.setEnabled(false);
        this.jDCFechaInicio.setEnabled(false);
        this.jDCFechaDespido.setEnabled(false);
        this.jBNuevoHorario.setEnabled(false);
        this.jBModificar.setEnabled(false);
    }

    public void habilitarCampos() {
        this.jTFDpi.setEnabled(true);
        this.jTFNit.setEnabled(true);
        this.jTFNombre.setEnabled(true);
        this.jTFApellido.setEnabled(true);
        this.jTFDireccion.setEnabled(true);
        this.jPFContrasenia.setEnabled(true);
        this.jCBPuesto.setEnabled(true);
        this.jCBHorario.setEnabled(true);
        this.jDCFechaInicio.setEnabled(true);
        this.jDCFechaDespido.setEnabled(true);
        this.jBNuevoHorario.setEnabled(true);
        this.jBModificar.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTFIdEmpleado = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jBModificar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTFDpi = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTFNit = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTFApellido = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTFDireccion = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPFContrasenia = new javax.swing.JPasswordField();
        jSeparator11 = new javax.swing.JSeparator();
        jCBPuesto = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jDCFechaInicio = new com.toedter.calendar.JDateChooser();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jDCFechaDespido = new com.toedter.calendar.JDateChooser();
        jLabel35 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        jCBHorario = new javax.swing.JComboBox<>();
        jBNuevoHorario = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(238, 112, 82));
        jLabel16.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(238, 112, 82));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Modificar empleado");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 370, -1));

        jLabel3.setBackground(new java.awt.Color(238, 112, 82));
        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 112, 82));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ID Empleado");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_id_32px.png"))); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jTFIdEmpleado.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFIdEmpleado.setBorder(null);
        jTFIdEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFIdEmpleadoKeyTyped(evt);
            }
        });
        jPanel3.add(jTFIdEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 70, 40));

        jSeparator5.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 120, 10));

        jBModificar.setBackground(new java.awt.Color(238, 112, 82));
        jBModificar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jBModificar.setForeground(new java.awt.Color(255, 255, 255));
        jBModificar.setText("Modificar");
        jBModificar.setBorder(null);
        jBModificar.setFocusPainted(false);
        jBModificar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jBModificarMouseMoved(evt);
            }
        });
        jBModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBModificarMouseExited(evt);
            }
        });
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });
        jPanel3.add(jBModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 680, 40));

        jLabel17.setBackground(new java.awt.Color(238, 112, 82));
        jLabel17.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(238, 112, 82));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("DPI");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 310, 30));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_id_32px.png"))); // NOI18N
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 50, -1));

        jTFDpi.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFDpi.setForeground(new java.awt.Color(153, 153, 153));
        jTFDpi.setBorder(null);
        jTFDpi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFDpiFocusGained(evt);
            }
        });
        jTFDpi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFDpiMouseClicked(evt);
            }
        });
        jTFDpi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDpiKeyTyped(evt);
            }
        });
        jPanel3.add(jTFDpi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 260, 40));

        jSeparator6.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 310, 10));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_nit_32px.png"))); // NOI18N
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 112, -1, 20));

        jLabel19.setBackground(new java.awt.Color(238, 112, 82));
        jLabel19.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(238, 112, 82));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("NIT");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 190, 30));

        jTFNit.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFNit.setForeground(new java.awt.Color(153, 153, 153));
        jTFNit.setBorder(null);
        jTFNit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFNitFocusGained(evt);
            }
        });
        jPanel3.add(jTFNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 140, 40));

        jSeparator7.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator7.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 190, 10));

        jLabel26.setBackground(new java.awt.Color(238, 112, 82));
        jLabel26.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(238, 112, 82));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Apellidos");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 310, 30));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_customer_32px_1.png"))); // NOI18N
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        jTFApellido.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFApellido.setForeground(new java.awt.Color(153, 153, 153));
        jTFApellido.setBorder(null);
        jTFApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFApellidoFocusGained(evt);
            }
        });
        jPanel3.add(jTFApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 260, 40));

        jSeparator10.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator10.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 310, 10));

        jLabel24.setBackground(new java.awt.Color(238, 112, 82));
        jLabel24.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(238, 112, 82));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Dirección");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 310, 30));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_direccion_32px.png"))); // NOI18N
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, -1));

        jTFDireccion.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFDireccion.setForeground(new java.awt.Color(153, 153, 153));
        jTFDireccion.setBorder(null);
        jTFDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFDireccionFocusGained(evt);
            }
        });
        jPanel3.add(jTFDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 260, 40));

        jSeparator9.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator9.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 310, 10));

        jLabel22.setBackground(new java.awt.Color(238, 112, 82));
        jLabel22.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(238, 112, 82));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Nombres");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 310, 30));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_customer_32px_1.png"))); // NOI18N
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jTFNombre.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jTFNombre.setForeground(new java.awt.Color(153, 153, 153));
        jTFNombre.setBorder(null);
        jTFNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFNombreFocusGained(evt);
            }
        });
        jPanel3.add(jTFNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 260, 40));

        jSeparator8.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator8.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 310, 10));

        jLabel29.setBackground(new java.awt.Color(238, 112, 82));
        jLabel29.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(238, 112, 82));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Contraseña");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 310, 30));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_Key_32px.png"))); // NOI18N
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jPFContrasenia.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jPFContrasenia.setForeground(new java.awt.Color(153, 153, 153));
        jPFContrasenia.setBorder(null);
        jPFContrasenia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPFContraseniaFocusGained(evt);
            }
        });
        jPanel3.add(jPFContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 260, 40));

        jSeparator11.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator11.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 310, 10));

        jCBPuesto.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jCBPuesto.setForeground(new java.awt.Color(238, 112, 82));
        jCBPuesto.setBorder(null);
        jCBPuesto.setFocusable(false);
        jCBPuesto.setOpaque(false);
        jCBPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBPuestoActionPerformed(evt);
            }
        });
        jPanel3.add(jCBPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 310, 30));

        jLabel31.setBackground(new java.awt.Color(238, 112, 82));
        jLabel31.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(238, 112, 82));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Puesto");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 310, 30));

        jLabel32.setBackground(new java.awt.Color(238, 112, 82));
        jLabel32.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(238, 112, 82));
        jLabel32.setText("Fecha Inicio");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 310, 30));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_fecha_32px.png"))); // NOI18N
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, -1, -1));

        jDCFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        jDCFechaInicio.setForeground(new java.awt.Color(238, 112, 82));
        jPanel3.add(jDCFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, 250, 30));

        jSeparator12.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator12.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, 250, 10));

        jLabel34.setBackground(new java.awt.Color(238, 112, 82));
        jLabel34.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(238, 112, 82));
        jLabel34.setText("Fecha de despido");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 310, 30));

        jDCFechaDespido.setBackground(new java.awt.Color(255, 255, 255));
        jDCFechaDespido.setForeground(new java.awt.Color(238, 112, 82));
        jPanel3.add(jDCFechaDespido, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 250, 30));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_fecha_32px.png"))); // NOI18N
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, -1, -1));

        jSeparator13.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator13.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, 250, 10));

        jLabel37.setBackground(new java.awt.Color(238, 112, 82));
        jLabel37.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(238, 112, 82));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Horario");
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 310, 30));

        jCBHorario.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jCBHorario.setForeground(new java.awt.Color(238, 112, 82));
        jCBHorario.setBorder(null);
        jCBHorario.setFocusable(false);
        jCBHorario.setOpaque(false);
        jPanel3.add(jCBHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 310, 30));

        jBNuevoHorario.setBackground(new java.awt.Color(238, 112, 82));
        jBNuevoHorario.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jBNuevoHorario.setForeground(new java.awt.Color(255, 255, 255));
        jBNuevoHorario.setText("Nuevo Horario");
        jBNuevoHorario.setBorder(null);
        jBNuevoHorario.setFocusPainted(false);
        jBNuevoHorario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jBNuevoHorarioMouseMoved(evt);
            }
        });
        jBNuevoHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBNuevoHorarioMouseExited(evt);
            }
        });
        jBNuevoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNuevoHorarioActionPerformed(evt);
            }
        });
        jPanel3.add(jBNuevoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 610, 310, 30));

        jButton3.setBackground(new java.awt.Color(238, 112, 82));
        jButton3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Buscar");
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
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 110, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 760, 750));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 800));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_regreso_32px.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 30, 50));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 30, 50));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 760, 50));

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
        this.setState(ModificarEmpleado.ICONIFIED);//Arreglar
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jBModificarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBModificarMouseMoved
        jBModificar.setBackground(new Color(204, 51, 0));
    }//GEN-LAST:event_jBModificarMouseMoved

    private void jBModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBModificarMouseExited
        jBModificar.setBackground(new Color(238, 112, 82));
    }//GEN-LAST:event_jBModificarMouseExited

    private void jTFDpiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFDpiFocusGained
        this.jTFDpi.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jTFDpiFocusGained

    private void jTFDpiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFDpiMouseClicked

    }//GEN-LAST:event_jTFDpiMouseClicked

    private void jTFDpiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFDpiKeyTyped
        char a = evt.getKeyChar();
        String cadena = this.jTFDpi.getText();

        if (!Character.isDigit(a) || cadena.length() + 1 > 13) {
            evt.consume();
        }
    }//GEN-LAST:event_jTFDpiKeyTyped

    private void jTFNitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFNitFocusGained
        this.jTFNit.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jTFNitFocusGained

    private void jTFApellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFApellidoFocusGained
        this.jTFApellido.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jTFApellidoFocusGained

    private void jTFDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFDireccionFocusGained
        this.jTFDireccion.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jTFDireccionFocusGained

    private void jPFContraseniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPFContraseniaFocusGained
        this.jPFContrasenia.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jPFContraseniaFocusGained

    private void jCBPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBPuestoActionPerformed

    private void jBNuevoHorarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBNuevoHorarioMouseMoved
        jBNuevoHorario.setBackground(new Color(204, 51, 0));
    }//GEN-LAST:event_jBNuevoHorarioMouseMoved

    private void jBNuevoHorarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBNuevoHorarioMouseExited
        jBNuevoHorario.setBackground(new Color(238, 112, 82));
    }//GEN-LAST:event_jBNuevoHorarioMouseExited

    private void jButton3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseMoved

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseExited

    private void jTFIdEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIdEmpleadoKeyTyped
        char a = evt.getKeyChar();
        if (!Character.isDigit(a)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTFIdEmpleadoKeyTyped

    private void jTFNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFNombreFocusGained
        this.jTFNombre.setBorder(BorderFactory.createLineBorder(white, 1));
    }//GEN-LAST:event_jTFNombreFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        empleado = null;
        persona = null;
        puesto = null;
        horario = null;
        if (!this.jTFIdEmpleado.getText().equals("")) {
            int dato = Integer.parseInt(jTFIdEmpleado.getText());

            empleado = controladorEmpleado.buscarEmpleado(dato);
            if (empleado != null) {
                persona = controladorPersona.obtenerPorID(empleado.getIdPersona());

                jDCFechaInicio.setDate(empleado.getFecha_inico());
                if (empleado.getFecha_fin() != null) {
                    this.jDCFechaDespido.setDate(empleado.getFecha_fin());
                }
                puesto = controladorPuesto.buscarPuesto(empleado.getIdPuesto());
                if (puesto != null) {
                    for (int i = 0; i < this.jCBPuesto.getItemCount(); i++) {
                        if (this.jCBPuesto.getItemAt(i).equals(puesto.getNombre())) {
                            this.jCBPuesto.setSelectedIndex(i);
                            break;
                        }
                    }
                }
                horario = controladorHorario.obtenerHorario(empleado.getIdHorario());
                if (horario != null) {
                    for (int i = 0; i < this.jCBHorario.getItemCount(); i++) {
                        String aux = horario.getEntrada() + " a " + horario.getSalida();
                        if (this.jCBHorario.getItemAt(i).equals(aux)) {
                            this.jCBHorario.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }

            if (persona != null) {
                this.jTFDpi.setText(persona.getDpi());
                this.jTFNombre.setText(persona.getNombre());
                this.jTFApellido.setText(persona.getApellido());
                this.jTFDireccion.setText(persona.getDireccion());
                this.jTFNit.setText(persona.getNit());
            }
            habilitarCampos();

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBNuevoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoHorarioActionPerformed
        this.dispose();
        CrearHorario nuevo = new CrearHorario("ModificarEmpleado", this.info);
        nuevo.setVisible(true);
    }//GEN-LAST:event_jBNuevoHorarioActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        String contra = new String(this.jPFContrasenia.getPassword());

        if (!this.jTFDpi.getText().equals("") && !this.jTFIdEmpleado.getText().equals("") && !this.jTFNit.getText().equals("")
                && !this.jTFNombre.getText().equals("") && !this.jTFApellido.getText().equals("") && !this.jTFDireccion.getText().equals("") && !(this.jTFDpi.getText().length() < 13)) {
            persona.setDpi(this.jTFDpi.getText());
            persona.setNit(this.jTFNit.getText());
            persona.setNombre(this.jTFNombre.getText());
            persona.setApellido(this.jTFApellido.getText());
            persona.setDireccion(this.jTFDireccion.getText());

            if (controladorPersona.modificarPersona(persona)) {
                Date fechaInicio = this.jDCFechaInicio.getDate();
                Date fechaFinal = this.jDCFechaDespido.getDate();

                empleado.setFecha_inico(fechaInicio);
                empleado.setFecha_fin(fechaFinal);
                empleado.setIdHorario(controladorHorario.buscarIdHorario((String) this.jCBHorario.getSelectedItem()));
                empleado.setIdPuesto(controladorPuesto.buscarIdPuesto((String) this.jCBPuesto.getSelectedItem()));
                empleado.setContrasenia(contra);

                controladorEmpleado.modificarEmpleado(empleado);
                borrarInputs();
                JOptionPane.showMessageDialog(null, "Se ingreso correctamente");
            }

        } else {
            String mensaje = "";
            if (this.jTFDpi.getText().length() < 13) {
                mensaje = "En DPI ingrese 13 digitos. | ";
            }
            if (this.jTFDpi.getText().equals("")) {

                this.jTFDpi.setBorder(BorderFactory.createLineBorder(new Color(238, 112, 82), 1));
            }
            if (this.jTFNit.getText().equals("")) {
                this.jTFNit.setBorder(BorderFactory.createLineBorder(new Color(238, 112, 82), 1));
            }
            if (this.jTFNombre.getText().equals("")) {
                this.jTFNombre.setBorder(BorderFactory.createLineBorder(new Color(238, 112, 82), 1));
            }
            if (this.jTFApellido.getText().equals("")) {
                this.jTFApellido.setBorder(BorderFactory.createLineBorder(new Color(238, 112, 82), 1));
            }
            if (this.jTFDireccion.getText().equals("")) {
                this.jTFDireccion.setBorder(BorderFactory.createLineBorder(new Color(238, 112, 82), 1));
            }
            if (contra.equals("")) {
                this.jPFContrasenia.setBorder(BorderFactory.createLineBorder(new Color(238, 112, 82), 1));
            }
            mensaje += "Rellene todos los campos.";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBModificarActionPerformed

    private void jButton2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseMoved
        jButton2.setBackground(new Color(204, 51, 0));
    }//GEN-LAST:event_jButton2MouseMoved

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(238, 112, 82));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Horario auxHora = controladorHorario.obtenerHorario(this.info.getIdHorario());

        if (auxHora != null) {
            Calendar calendario = Calendar.getInstance();
            String horaActual = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) + ":" + calendario.get(Calendar.SECOND);
            /*System.out.println("horaActual: " + horaActual);
            System.out.println("HoraSalida:" + horario.getSalida());*/

            try {
                DateFormat dateFormat = new SimpleDateFormat("hh:mm");

                Date DHoraSalida = dateFormat.parse(auxHora.getSalida());
                Date DHoraActual = dateFormat.parse(horaActual);

                if (DHoraSalida.compareTo(DHoraActual) > 0) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Tu hora de salida es: " + auxHora.getSalida() + ". Estas seguro que deseas salir? (se te contara como inasistencia)", "Cerrando Sesion", JOptionPane.YES_NO_OPTION);
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
    private void borrarInputs() {
        this.jTFDpi.setText("");
        this.jTFNit.setText("");
        this.jTFNombre.setText("");
        this.jTFApellido.setText("");
        this.jTFDireccion.setText("");
        this.jPFContrasenia.setText("");
        this.jDCFechaInicio.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        this.jDCFechaDespido.setDate(null);
        this.jTFIdEmpleado.setText("");
        this.jCBPuesto.setSelectedIndex(0);
        this.jCBHorario.setSelectedIndex(0);
    }

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
            java.util.logging.Logger.getLogger(ModificarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBNuevoHorario;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jCBHorario;
    private javax.swing.JComboBox<String> jCBPuesto;
    private com.toedter.calendar.JDateChooser jDCFechaDespido;
    private com.toedter.calendar.JDateChooser jDCFechaInicio;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPFContrasenia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTFApellido;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFDpi;
    private javax.swing.JTextField jTFIdEmpleado;
    private javax.swing.JTextField jTFNit;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
