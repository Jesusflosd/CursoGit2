/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alexandra
 */
public class Venta {
    private Double subtotal;
    private Double total;
    private Double IVA;

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    public void setIVA(){
        this.IVA = getSubtotal() * 0.16;
    }
    
    public Double getIVA() {
        return IVA;
    }
    
    public Double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = getSubtotal() + getIVA();
    }
}
