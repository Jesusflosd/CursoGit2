/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



public class Administrador extends Persona{
    
    // Atributos
    
    private double sueldo;
    private String usuario;
    private int idAdministrador;
    private String contrasenia;

    // Constructor
    public Administrador(String nombre, String apellidoPaterno, String apellidoMaterno, double sueldo, String usuario, int idAdministrador, String contrasenia) {
        
        super(nombre, apellidoPaterno, apellidoMaterno, idAdministrador);
        this.sueldo = sueldo;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
     
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public double getSueldo() {
        return sueldo;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
     @Override
    public String toString() {
        return // Llamamos al metodo toString de la super clase
                super.toString()
                + "\n" + "Sueldo: $" + getSueldo()
                + "\n" + "Usuario: " + getUsuario();
    }
}
