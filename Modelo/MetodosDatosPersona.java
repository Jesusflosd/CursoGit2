/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author zhuwu1
 */
public interface MetodosDatosPersona {
    void agregarDatosPersona(Object objeto);
    void modificarDatosPersona(int id, Object objeto);
    void eliminarDatosPersona(int id);
    boolean existeId(int id);
}
