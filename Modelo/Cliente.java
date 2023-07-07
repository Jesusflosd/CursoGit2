
package Modelo;

public class Cliente extends Persona {
    // Atributos

    
    // Constructor
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, int idCliente) {
        super(nombre, apellidoPaterno, apellidoMaterno, idCliente);
    }


    // Metodo toString
    @Override
    public String toString() {
        // Llamamos al metodo toString de la super clase
        return super.toString();
    }
    
}


