/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.CSV;
import Modelo.MetodosProducto;
import Modelo.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandra
 */
public class frmCatalogo extends javax.swing.JFrame {

    
    //Variables de instancia
    private CSV csv = new CSV();
    private final String nombreArchivo = "Catalogo.csv";
    
    public void inicializarTablaProductos(){
        DefaultTableModel titulos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }
        };
        
        titulos.addColumn("ID Producto");
        titulos.addColumn("Nombre Producto");
        titulos.addColumn("Cantidad");
        titulos.addColumn("Descripción");
        titulos.addColumn("Precio");
    
        List<String> data = csv.obtenerDatosArchivo(nombreArchivo);
        if (data != null) {
            for (String line : data) {
                String[] row = line.split(",");
                if (row.length >= 5) {
                    Object[] fila = new Object[5];
                    fila[0] = row[0];
                    fila[1] = row[1];
                    fila[2] = row[2];
                    fila[3] = row[3];
                    fila[4] = row[4];

                    titulos.addRow(fila);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }

        tabla_consultaCatalogo.setModel(titulos);
        tabla_consultaCatalogo.setRowHeight(60);
        
    }
    
    public int leerIDTextField() {
        try {
            int idProducto = Integer.parseInt(texto_ID.getText().trim());
            return idProducto;
        } catch (Exception exception) {
            return -666;
        }
    }
    
    public String leerNombreTextField() {
        try {
            String nombre = texto_nombre.getText().trim().replace(" ", " ");
            return nombre;
        } catch (Exception exception) {
            return null;
        }
    }
    
    public int leerCantidadTextField() {
        try {
            int cantidad = Integer.parseInt(texto_cantidad.getText().trim());
            return cantidad;
        } catch (Exception exception) {
            return -666;
        }
    }
    
    public String leerDescripcionTextField() {
        try {
            String descripcion = texto_descripcion.getText().trim().replace(" ", " ");
            return descripcion;
        } catch (Exception exception) {
            return null;
        }
    }
    
    
     public double leerPrecioTextField() {
        try {
            double precio = Double.parseDouble(texto_precio.getText().trim());
            return precio;
        } catch (Exception exception) {
            return -666;
        }
    }
    
    //Metodo para limpiar las celdas
    public void limpiarCeldas(JPanel jPanel) {
        for (int i = 0; jPanel.getComponents().length > i; i++) {
            if (jPanel.getComponents()[i] instanceof JTextField) {
                ((JTextField) jPanel.getComponents()[i]).setText("");
            }
        }
    }
    
    Producto producto;
    MetodosProducto metodosProducto;
    
    public void guardarProducto() {
        try {
            // que es -666
            //Cambiar el manejo de errores
            if (leerIDTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero entero");
            } else if (leerNombreTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            } else if (leerDescripcionTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese una descripción");
            } else if (leerCantidadTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
            } else if (leerPrecioTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese el precio  ");
            
            } else {
                producto = new Producto(leerNombreTextField(), 
                        leerCantidadTextField(), 
                        leerIDTextField(), 
                        leerDescripcionTextField(), 
                        leerPrecioTextField());
                
                if (metodosProducto.existeId(producto.getIdProducto())) {
                    JOptionPane.showMessageDialog(null, "Este ID ya ha sido asginado");
                } else {
                    metodosProducto.agregarDatosProducto(producto);
                    inicializarTablaProductos();
                    limpiarCeldas(panelRegistro);
                }

            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error al guardar producto");
        }
    }
    
    public void modificarProducto() {
        try {
            // que es -666
            //Cambiar el manejo de errores
            if (leerIDTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero entero");
            } else if (leerNombreTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            } else if (leerDescripcionTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese una descripción");
            } else if (leerCantidadTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
            } else if (leerPrecioTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese el precio  ");
            
            } else {
                producto = new Producto(leerNombreTextField(), leerCantidadTextField(), leerIDTextField(), leerDescripcionTextField(), leerPrecioTextField());
                if (metodosProducto.existeId(producto.getIdProducto())) {
                    metodosProducto.modificarDatosProducto((int) leerIDTextField(),producto);

                    inicializarTablaProductos();
                    limpiarCeldas(panelRegistro);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar");
        }
    }
        
    public void eliminarProducto() {
        try {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar al administrador?", "Aviso", 0);
            if (confirmacion == 0) {
                metodosProducto.eliminarDatosProducto(Integer.parseInt(texto_ID.getText()));
                inicializarTablaProductos();
                limpiarCeldas(panelRegistro);
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }
    
    
    /**
     * Creates new form frmCatalogo
     */
    public frmCatalogo() {
        initComponents();
        setLocationRelativeTo(null);
        metodosProducto = new MetodosProducto();
        try {
            inicializarTablaProductos();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "El archivo de texto no existe");
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

        boton_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_consultaCatalogo = new javax.swing.JTable();
        panelRegistro = new javax.swing.JPanel();
        label_apellidoP = new javax.swing.JLabel();
        label_sueldo = new javax.swing.JLabel();
        label_ID = new javax.swing.JLabel();
        texto_ID = new javax.swing.JTextField();
        texto_nombre = new javax.swing.JTextField();
        texto_descripcion = new javax.swing.JTextField();
        texto_precio = new javax.swing.JTextField();
        label_nombre = new javax.swing.JLabel();
        label_titulo = new javax.swing.JLabel();
        label_apellidoP1 = new javax.swing.JLabel();
        texto_cantidad = new javax.swing.JTextField();
        btnRegresarAMenu = new javax.swing.JButton();
        boton_guardar = new javax.swing.JButton();
        boton_modificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        boton_eliminar.setBackground(new java.awt.Color(224, 255, 255));
        boton_eliminar.setForeground(new java.awt.Color(1, 1, 1));
        boton_eliminar.setText("Eliminar");
        boton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_eliminarActionPerformed(evt);
            }
        });

        tabla_consultaCatalogo.setBackground(new java.awt.Color(224, 255, 255));
        tabla_consultaCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Cantidad", "Descripcion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_consultaCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consultaCatalogoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_consultaCatalogo);
        if (tabla_consultaCatalogo.getColumnModel().getColumnCount() > 0) {
            tabla_consultaCatalogo.getColumnModel().getColumn(0).setResizable(false);
        }

        label_apellidoP.setText("Descripción");

        label_sueldo.setText("Precio");

        label_ID.setText("ID Producto:");

        texto_ID.setBackground(new java.awt.Color(224, 255, 255));
        texto_ID.setForeground(new java.awt.Color(1, 1, 1));
        texto_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_IDActionPerformed(evt);
            }
        });

        texto_nombre.setBackground(new java.awt.Color(224, 255, 255));
        texto_nombre.setForeground(new java.awt.Color(1, 1, 1));

        texto_descripcion.setBackground(new java.awt.Color(224, 255, 255));
        texto_descripcion.setForeground(new java.awt.Color(1, 1, 1));

        texto_precio.setBackground(new java.awt.Color(224, 255, 255));
        texto_precio.setForeground(new java.awt.Color(1, 1, 1));

        label_nombre.setText("Nombre:");

        label_titulo.setFont(new java.awt.Font("Jamrul", 1, 18)); // NOI18N
        label_titulo.setText("Catalogo");

        label_apellidoP1.setText("Cantidad");

        texto_cantidad.setBackground(new java.awt.Color(224, 255, 255));
        texto_cantidad.setForeground(new java.awt.Color(1, 1, 1));

        javax.swing.GroupLayout panelRegistroLayout = new javax.swing.GroupLayout(panelRegistro);
        panelRegistro.setLayout(panelRegistroLayout);
        panelRegistroLayout.setHorizontalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                        .addComponent(label_titulo)
                        .addGap(112, 112, 112))
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(texto_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelRegistroLayout.createSequentialGroup()
                                    .addComponent(label_sueldo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(texto_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistroLayout.createSequentialGroup()
                                    .addComponent(label_ID)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(texto_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelRegistroLayout.createSequentialGroup()
                                    .addComponent(label_nombre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistroLayout.createSequentialGroup()
                                    .addComponent(label_apellidoP)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(texto_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(137, Short.MAX_VALUE))))
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addComponent(label_apellidoP1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRegistroLayout.setVerticalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ID)
                    .addComponent(texto_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_nombre)
                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_apellidoP)
                    .addComponent(texto_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(label_apellidoP1))
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_sueldo)
                    .addComponent(texto_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnRegresarAMenu.setText("Regresar");
        btnRegresarAMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarAMenuActionPerformed(evt);
            }
        });

        boton_guardar.setBackground(new java.awt.Color(224, 255, 255));
        boton_guardar.setForeground(new java.awt.Color(1, 1, 1));
        boton_guardar.setText("Guardar");
        boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardarActionPerformed(evt);
            }
        });

        boton_modificar.setBackground(new java.awt.Color(224, 255, 255));
        boton_modificar.setForeground(new java.awt.Color(1, 1, 1));
        boton_modificar.setText("Modificar");
        boton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_modificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnRegresarAMenu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(boton_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boton_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boton_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(boton_guardar)
                        .addGap(30, 30, 30)
                        .addComponent(boton_modificar)
                        .addGap(30, 30, 30)
                        .addComponent(boton_eliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresarAMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void boton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_eliminarActionPerformed
        // TODO add your handling code here:
        this.eliminarProducto();
    }//GEN-LAST:event_boton_eliminarActionPerformed

    private void tabla_consultaCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consultaCatalogoMouseClicked
        // TODO add your handling code here:
        int mouseClick = tabla_consultaCatalogo.rowAtPoint(evt.getPoint());

        int idProducto = Integer.parseInt((String) tabla_consultaCatalogo.getValueAt(mouseClick, 0));
        String nombre = "" + tabla_consultaCatalogo.getValueAt(mouseClick, 1);
        int cantidad = Integer.parseInt((String)tabla_consultaCatalogo.getValueAt(mouseClick, 2));
        String descripcion = "" + tabla_consultaCatalogo.getValueAt(mouseClick, 3);
        double precio = Double.parseDouble((String) tabla_consultaCatalogo.getValueAt(mouseClick, 4));

        texto_ID.setText(String.valueOf(idProducto));
        texto_nombre.setText(nombre);
        texto_cantidad.setText(String.valueOf(cantidad));
        texto_descripcion.setText(descripcion);
        texto_precio.setText(String.valueOf(precio));
    }//GEN-LAST:event_tabla_consultaCatalogoMouseClicked

    private void texto_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_IDActionPerformed

    private void btnRegresarAMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarAMenuActionPerformed
        // TODO add your handling code here:
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarAMenuActionPerformed

    private void boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardarActionPerformed
        // TODO add your handling code here:
        this.guardarProducto();
    }//GEN-LAST:event_boton_guardarActionPerformed

    private void boton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_modificarActionPerformed
        // TODO add your handling code here:
        this.modificarProducto();
    }//GEN-LAST:event_boton_modificarActionPerformed

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
            java.util.logging.Logger.getLogger(frmCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCatalogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_eliminar;
    private javax.swing.JButton boton_guardar;
    private javax.swing.JButton boton_modificar;
    private javax.swing.JButton btnRegresarAMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_ID;
    private javax.swing.JLabel label_apellidoP;
    private javax.swing.JLabel label_apellidoP1;
    private javax.swing.JLabel label_nombre;
    private javax.swing.JLabel label_sueldo;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JPanel panelRegistro;
    private javax.swing.JTable tabla_consultaCatalogo;
    private javax.swing.JTextField texto_ID;
    private javax.swing.JTextField texto_cantidad;
    private javax.swing.JTextField texto_descripcion;
    private javax.swing.JTextField texto_nombre;
    private javax.swing.JTextField texto_precio;
    // End of variables declaration//GEN-END:variables
}
