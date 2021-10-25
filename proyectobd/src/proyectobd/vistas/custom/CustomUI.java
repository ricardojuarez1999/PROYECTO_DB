/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd.vistas.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * 17 * @web https://www.jc-mouse.net/ 18 * @author Mouse 19
 */
public class CustomUI extends BasicComboBoxUI {

    private ImageIcon espacio = new ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_empleado32px.png"));
    private ImageIcon espacio2 = new ImageIcon(getClass().getResource("/proyectobd/vistas/imagenes/icon_empleado_white_32px.png"));
    private Color red = new Color(238, 112, 82);

    public static ComboBoxUI createUI(JComponent c) {
        return new CustomUI();
    }
    public CustomUI(){
        
    }

    @Override   
    protected JButton createArrowButton() {
        BasicArrowButton basicArrowButton = new BasicArrowButton(BasicArrowButton.SOUTH, //Direccion de la flecha
                Color.WHITE, //Color de fondo
                new Color(238, 112, 80),//sombra
                new Color(238, 112, 80),//darkShadow
                Color.WHITE //highlight
        );
        //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
        basicArrowButton.setBorder(BorderFactory.createLineBorder(red, 2));
        basicArrowButton.setContentAreaFilled(false);
        return basicArrowButton;
    }

    //Se puede usar un JButton para usar un icono personalizado en lugar del arrow
    /* 
45  @Override 
46  protected JButton createArrowButton() { 
47  JButton button = new JButton(); 
48  //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
49  button.setText("");
50  button.setBorder(BorderFactory.createLineBorder(red,2));
51  button.setContentAreaFilled(false);
52  button.setIcon( new ImageIcon(getClass().getResource("/org/bolivia/res/estrella.png")) );
53  return button;
54  } 
55  */
    @Override
    public void paintCurrentValueBackground(Graphics g,
            Rectangle bounds,
            boolean hasFocus) {
        g.setColor(red);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    //Pinta los items
    @Override
    protected ListCellRenderer createRenderer() {
        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                list.setSelectionBackground(red);
                if (isSelected) {
                    setBackground(red);
                    setForeground(Color.WHITE);
                    
                } else {
                    setBackground(Color.WHITE);
                    setForeground(new Color(238, 112, 80));
                }
                if (index != -1) {
                    setIcon(espacio);
                    if(isSelected){
                        setIcon(espacio2);
                    }
                }
                return this;
            }
        };
    }
}
