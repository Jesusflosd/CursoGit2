/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jesus Estrella
 */
public class Producto {
    
    // Atributos de clase
    private String nombreProducto;
    private int cantidad;
    private int idProducto;
    private String descripcion;
    private double precio;
    private double importe;
    
    //Constructor
    
    public Producto(String nombreProducto, int cantidad, int idProducto, String descripcion, double precio){
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precio = precio;    
        
    }
    
    public Producto(){
        
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
