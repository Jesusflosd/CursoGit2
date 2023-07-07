
package Modelo;
import java.util.ArrayList;

public class MetodosAdministrador implements MetodosDatosPersona {
    public static final String ADMINISTRADOR = "administrador";
    private final ArrayList<Object> datosAdministrador = new ArrayList<>();
    private final CSV csv = new CSV();
    private final String nombreArchivo = "Administradores.csv";
    // Constructor vacio
    public MetodosAdministrador() {
    }

    @Override
    public void agregarDatosPersona(Object dato) {
        csv.agregarFilaDatos(nombreArchivo, dato, ADMINISTRADOR);
    }

    @Override
    public void modificarDatosPersona(int id, Object objeto) {
        csv.modificarFilaDatos(nombreArchivo, id, objeto, ADMINISTRADOR);
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
