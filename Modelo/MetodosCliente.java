
package Modelo;


import java.util.ArrayList;


public class MetodosCliente implements MetodosDatosPersona {
    private static final String CLIENTE = "cliente";
    private ArrayList<Object> datosCliente = new ArrayList<>();
    private CSV csv = new CSV();
    private final String nombreArchivo = "Clientes.csv";
    // Constructor vacio
    public MetodosCliente() {
    }

    @Override
    public void agregarDatosPersona(Object objeto) {
        csv.agregarFilaDatos(nombreArchivo, objeto, CLIENTE);
    }

    @Override
    public void modificarDatosPersona(int id, Object objeto) {
        csv.modificarFilaDatos(nombreArchivo, id, objeto, CLIENTE);
    }

    @Override
    public void eliminarDatosPersona(int id) {
        csv.eliminarFilaDatos(nombreArchivo, id);
    }

    @Override
    public boolean existeId(int id) {
        return csv.existeId(nombreArchivo, id);
    }
    
}
