/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Administrador;
import Modelo.MetodosAdministrador;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import Modelo.CSV;
import java.awt.HeadlessException;

/**
 *
 * @author becky
 */
public class frmAdministrador extends javax.swing.JFrame {

// Variables de instancia    
    private CSV csv = new CSV();
    private final String fileName = "Administradores.csv";

    public void inicializarTablaAdministradores() {
        DefaultTableModel titulos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }
        };

        titulos.addColumn("ID Administrador");
        titulos.addColumn("Nombre");
        titulos.addColumn("Apellido Materno");
        titulos.addColumn("Apellido Paterno");
        titulos.addColumn("Usuario");
        titulos.addColumn("Contraseña");
        titulos.addColumn("Sueldo");

        List<String> data = csv.obtenerDatosArchivo(fileName);
        if (data != null) {
            for (String line : data) {
                String[] row = line.split(",");
                if (row.length >= 7) {
                    Object[] fila = new Object[7];
                    fila[0] = row[0];
                    fila[1] = row[1];
                    fila[2] = row[2];
                    fila[3] = row[3];
                    fila[4] = row[4];
                    fila[5] = row[5];
                    fila[6] = row[6];
                    titulos.addRow(fila);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }

        tabla_consultaAdministradores.setModel(titulos);
        tabla_consultaAdministradores.setRowHeight(60);
    }

    // Metodos para leer los campos ingresados por registrar
    public int leerIDTextField() {
        try {
            int idAdministrador = Integer.parseInt(texto_ID.getText().trim());
            return idAdministrador;
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

    public String leerApellidoPaternoTextField() {
        try {
            String apellidoPaterno = texto_apellidoP.getText().trim().replace(" ", " ");
            return apellidoPaterno;
        } catch (Exception exception) {
            return null;
        }
    }

    public String leerApellidoMaternoTextField() {
        try {
            String apellidoMaterno = texto_apellidoM.getText().trim().replace(" ", " ");
            return apellidoMaterno;
        } catch (Exception exception) {
            return null;
        }
    }

    public double leerSueldoTextField() {
        try {
            double sueldo = Double.parseDouble(texto_sueldo.getText().trim());
            return sueldo;
        } catch (Exception exception) {
            return -666;
        }
    }

    public String leerUsuarioTextField() {
        try {
            String usuarioTxt = usuario.getText().trim();
            return usuarioTxt;
        } catch (Exception exception) {
            return null;
        }
    }

    private String leerContraseniaTextField() {
        try {
            String contraseniaTxt = contrasenia.getText().trim();
            return contraseniaTxt;
        } catch (Exception exception) {
            return null;
        }
    }

    // Metodo para quitar el contenido de las celdas al guardar
    public void limpiarCeldas(JPanel jPanel) {
        for (int i = 0; jPanel.getComponents().length > i; i++) {
            if (jPanel.getComponents()[i] instanceof JTextField) {
                ((JTextField) jPanel.getComponents()[i]).setText("");
            } else if (jPanel.getComponents()[i] instanceof JPasswordField) {
                ((JPasswordField) jPanel.getComponents()[i]).setText("");
            }
        }
    }
    
    Administrador administrador;
    MetodosAdministrador metodosAdministrador;

    public void guardarAdministrador() {
        try {
            // que es -666
            //Cambiar el manejo de errores
            if (leerIDTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero entero");
            } else if (leerNombreTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            } else if (leerApellidoPaternoTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el apellido paterno");
            } else if (leerApellidoMaternoTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el apellido materno");
            } else if (leerSueldoTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese el sueldo");
            } else if (leerUsuarioTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el usuario");
            } else if (leerContraseniaTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese la contraseña");
            } else {
                administrador = new Administrador(leerNombreTextField(), 
                        leerApellidoPaternoTextField(), 
                        leerApellidoMaternoTextField(), 
                        leerSueldoTextField(), 
                        leerUsuarioTextField(), 
                        leerIDTextField(), 
                        leerContraseniaTextField());
                if (metodosAdministrador.existeId(administrador.getId())) {
                    JOptionPane.showMessageDialog(null, "Este ID ya ha sido asginado");
                } else {
                    metodosAdministrador.agregarDatosPersona(administrador);
                    inicializarTablaAdministradores();
                    limpiarCeldas(panelRegistro);
                }

            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error al guardar administrador");
        }
    }

    public void modificarAdministrador() {
        try {
            if (leerIDTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero entero");
            } else if (leerNombreTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            } else if (leerApellidoPaternoTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el apellido paterno");
            } else if (leerApellidoMaternoTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el apellido materno");
            } else if (leerSueldoTextField() == -666) {
                JOptionPane.showMessageDialog(null, "Ingrese el sueldo");
            } else if (leerUsuarioTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario");
            } else if (leerContraseniaTextField().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese la contraseña");
            } else {
                administrador = new Administrador(leerNombreTextField(), leerApellidoPaternoTextField(), leerApellidoMaternoTextField(), leerSueldoTextField(), leerUsuarioTextField(), leerIDTextField(), leerContraseniaTextField());
                if (metodosAdministrador.existeId(administrador.getId())) {
                    metodosAdministrador.modificarDatosPersona((int) leerIDTextField(), administrador);

                    inicializarTablaAdministradores();
                    limpiarCeldas(panelRegistro);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

     public void eliminarAdministrador() {
        try {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar al administrador?", "Aviso", 0);
            if (confirmacion == 0) {
                metodosAdministrador.eliminarDatosPersona(Integer.parseInt(texto_ID.getText()));
                inicializarTablaAdministradores();
                limpiarCeldas(panelRegistro);
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    /**
     * Creates new form
     */
    public frmAdministrador() {
        initComponents();
        setLocationRelativeTo(null);
        metodosAdministrador = new MetodosAdministrador();
        try {
            inicializarTablaAdministradores();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelRegistro = new javax.swing.JPanel();
        label_apellidoP = new javax.swing.JLabel();
        label_apellidoM = new javax.swing.JLabel();
        label_sueldo = new javax.swing.JLabel();
        label_ID = new javax.swing.JLabel();
        texto_ID = new javax.swing.JTextField();
        texto_nombre = new javax.swing.JTextField();
        texto_apellidoP = new javax.swing.JTextField();
        texto_apellidoM = new javax.swing.JTextField();
        texto_sueldo = new javax.swing.JTextField();
        label_nombre = new javax.swing.JLabel();
        label_titulo = new javax.swing.JLabel();
        usuarioLabel = new javax.swing.JLabel();
        contrasenaLabel = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        contrasenia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_consultaAdministradores = new javax.swing.JTable();
        btnRegresarAMenu = new javax.swing.JButton();
        boton_guardar = new javax.swing.JButton();
        boton_modificar = new javax.swing.JButton();
        boton_eliminar = new javax.swing.JButton();

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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label_apellidoP.setText("Apellido Paterno:");

        label_apellidoM.setText("Apellido Materno:");

        label_sueldo.setText("Sueldo:");

        label_ID.setText("ID Administrador:");

        texto_ID.setBackground(new java.awt.Color(224, 255, 255));
        texto_ID.setForeground(new java.awt.Color(1, 1, 1));

        texto_nombre.setBackground(new java.awt.Color(224, 255, 255));
        texto_nombre.setForeground(new java.awt.Color(1, 1, 1));

        texto_apellidoP.setBackground(new java.awt.Color(224, 255, 255));
        texto_apellidoP.setForeground(new java.awt.Color(1, 1, 1));

        texto_apellidoM.setBackground(new java.awt.Color(224, 255, 255));
        texto_apellidoM.setForeground(new java.awt.Color(1, 1, 1));

        texto_sueldo.setBackground(new java.awt.Color(224, 255, 255));
        texto_sueldo.setForeground(new java.awt.Color(1, 1, 1));

        label_nombre.setText("Nombre:");

        label_titulo.setFont(new java.awt.Font("Jamrul", 1, 18)); // NOI18N
        label_titulo.setText("Registro Administrador");

        usuarioLabel.setText("Usuario:");

        contrasenaLabel.setText("Contraseña:");

        tabla_consultaAdministradores.setBackground(new java.awt.Color(224, 255, 255));
        tabla_consultaAdministradores.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_consultaAdministradores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consultaAdministradoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_consultaAdministradores);

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

        boton_eliminar.setBackground(new java.awt.Color(224, 255, 255));
        boton_eliminar.setForeground(new java.awt.Color(1, 1, 1));
        boton_eliminar.setText("Eliminar");
        boton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRegistroLayout = new javax.swing.GroupLayout(panelRegistro);
        panelRegistro.setLayout(panelRegistroLayout);
        panelRegistroLayout.setHorizontalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addComponent(btnRegresarAMenu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistroLayout.createSequentialGroup()
                                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRegistroLayout.createSequentialGroup()
                                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(panelRegistroLayout.createSequentialGroup()
                                                    .addComponent(label_nombre)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panelRegistroLayout.createSequentialGroup()
                                                    .addComponent(label_apellidoP)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(texto_apellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panelRegistroLayout.createSequentialGroup()
                                                .addComponent(label_ID)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(texto_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(46, 46, 46)
                                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                                                .addComponent(usuarioLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                                                .addComponent(contrasenaLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelRegistroLayout.createSequentialGroup()
                                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_apellidoM)
                                            .addComponent(label_sueldo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(texto_sueldo)
                                            .addComponent(texto_apellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(boton_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boton_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boton_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(106, 106, 106))))
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(label_titulo)
                .addContainerGap())
        );
        panelRegistroLayout.setVerticalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(label_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistroLayout.createSequentialGroup()
                                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_ID)
                                    .addComponent(texto_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_nombre)
                                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelRegistroLayout.createSequentialGroup()
                                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(usuarioLabel)
                                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(contrasenaLabel)
                                    .addComponent(contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_apellidoP)
                            .addComponent(texto_apellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texto_apellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_apellidoM)))
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addComponent(boton_guardar)
                        .addGap(30, 30, 30)
                        .addComponent(boton_modificar)
                        .addGap(30, 30, 30)
                        .addComponent(boton_eliminar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_sueldo))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresarAMenu)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardarActionPerformed
        // TODO add your handling code here:
        this.guardarAdministrador();
    }//GEN-LAST:event_boton_guardarActionPerformed

    private void boton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_modificarActionPerformed
        // TODO add your handling code here:
        this.modificarAdministrador();
    }//GEN-LAST:event_boton_modificarActionPerformed

    private void boton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_eliminarActionPerformed
        // TODO add your handling code here:
        this.eliminarAdministrador();
    }//GEN-LAST:event_boton_eliminarActionPerformed

    private void btnRegresarAMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarAMenuActionPerformed
        // TODO add your handling code here:
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarAMenuActionPerformed

    private void tabla_consultaAdministradoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consultaAdministradoresMouseClicked
        // TODO add your handling code here:
        int mouseClick = tabla_consultaAdministradores.rowAtPoint(evt.getPoint());

        int idAdministrador = Integer.parseInt((String) tabla_consultaAdministradores.getValueAt(mouseClick, 0));
        String nombre = "" + tabla_consultaAdministradores.getValueAt(mouseClick, 1);
        String apellidoPaterno = "" + tabla_consultaAdministradores.getValueAt(mouseClick, 2);
        String apellidoMaterno = "" + tabla_consultaAdministradores.getValueAt(mouseClick, 3);
        String texto_usuario = "" + tabla_consultaAdministradores.getValueAt(mouseClick, 4);
        String texto_contrasenia = "" + tabla_consultaAdministradores.getValueAt(mouseClick, 5);
        double sueldo = Double.parseDouble((String) tabla_consultaAdministradores.getValueAt(mouseClick, 6));

        texto_ID.setText(String.valueOf(idAdministrador));
        texto_nombre.setText(nombre);
        texto_apellidoP.setText(apellidoPaterno);
        texto_apellidoM.setText(apellidoMaterno);
        texto_sueldo.setText(String.valueOf(sueldo));
        usuario.setText(String.valueOf(texto_usuario));
        contrasenia.setText(String.valueOf(texto_contrasenia));
    }//GEN-LAST:event_tabla_consultaAdministradoresMouseClicked

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
            java.util.logging.Logger.getLogger(frmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_eliminar;
    private javax.swing.JButton boton_guardar;
    private javax.swing.JButton boton_modificar;
    private javax.swing.JButton btnRegresarAMenu;
    private javax.swing.JLabel contrasenaLabel;
    private javax.swing.JTextField contrasenia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_ID;
    private javax.swing.JLabel label_apellidoM;
    private javax.swing.JLabel label_apellidoP;
    private javax.swing.JLabel label_nombre;
    private javax.swing.JLabel label_sueldo;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JPanel panelRegistro;
    private javax.swing.JTable tabla_consultaAdministradores;
    private javax.swing.JTextField texto_ID;
    private javax.swing.JTextField texto_apellidoM;
    private javax.swing.JTextField texto_apellidoP;
    private javax.swing.JTextField texto_nombre;
    private javax.swing.JTextField texto_sueldo;
    private javax.swing.JTextField usuario;
    private javax.swing.JLabel usuarioLabel;
    // End of variables declaration//GEN-END:variables
}
