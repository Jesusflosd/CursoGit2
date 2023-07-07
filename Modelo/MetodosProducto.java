/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Jesus Estrella
 */
public class MetodosProducto {
    
    private static final String PRODUCTO = "producto";
    private ArrayList<Object> datosProducto = new ArrayList<>();
    private CSV csv = new CSV();
    private final String nombreArchivo = "Catalogo.csv";
    // Constructor vacio
    public MetodosProducto() {
    }
    
    // Metodos para agregar, modificar y eliminar datos de los productos en la tabla
    public void agregarDatosProducto(Producto producto) {
        csv.agregarFilaDatos(nombreArchivo, producto, PRODUCTO);
    }

    public void modificarDatosProducto(int idProducto, Producto producto) {
        csv.modificarFilaDatos(nombreArchivo, idProducto, producto, PRODUCTO);
    }

    public void eliminarDatosProducto(int idProducto) {
        csv.eliminarFilaDatos(nombreArchivo, idProducto);
    }

    public boolean existeId(int idProducto){
        return csv.existeId(nombreArchivo, idProducto);
    }
    
    
    //Cantidad de productos registrados
    public int cantidadProductoRegistrados() {
        return this.datosProducto.size();   
    }
    
}
