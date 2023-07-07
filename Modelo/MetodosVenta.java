/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Alexandra
 */
public class MetodosVenta {
    private final CSV csv = new CSV();
    private final String nombreArchivo = "Catalogo.csv";
    private Venta venta = new Venta();

    public List<String> getElectronicos() {
        List<String> data = csv.obtenerDatosArchivo(nombreArchivo);
        List<String> electronicos = new ArrayList<>();
        if (data != null) {
            for (String linea : data) {
                /*
                Valores: 0 = id, 1 = nombre, 2 = cantidad, 3= desc, 4 = precio c/u
                */
                String[] valores = linea.split(",");
                electronicos.add(valores[1]);
            }
        }
        return electronicos;
    }
    
    public Double getPrecioProducto(int id) {
        List<String> data = csv.obtenerDatosArchivo(nombreArchivo);
        Double precio = 0.0;
        if (data != null) {
            for (String linea : data) {
                /*
                Valores: 0 = id, 1 = nombre, 2 = cantidad, 3= desc, 4 = precio c/u
                */
                String[] valores = linea.split(",");
                if (valores.length > 0 && Integer.parseInt(valores[0]) == id+1) {
                    precio = Double.valueOf(valores[4]);
                }
            }
        }
        return precio;
    }
        
    public void calcularTotal(Double subtotal){
        venta.setSubtotal(subtotal);
        venta.setIVA();
        venta.setTotal();
    }
    
    public Double getSubtotal(){
      return venta.getSubtotal();
    };
    
    public Double getIVA(){
        return venta.getIVA();
    }
    
    public Double getTotal(){
        return venta.getTotal();
    }
}
