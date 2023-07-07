
package Modelo;

import java.io.Serializable;




public abstract class Persona implements Serializable {
    // Atributos
    public String nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public int id;

    // Constructor
    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int id){
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.id = id;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public int getId() {
        return id;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Nombre: " + getNombre() + " " + getApellidoPaterno() + " " + getApellidoMaterno() + " " + getId();
    }

}
