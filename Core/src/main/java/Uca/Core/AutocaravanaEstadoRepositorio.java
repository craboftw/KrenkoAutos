package Uca.Core;

import java.util.Collection;

public interface AutocaravanaEstadoRepositorio {
    Collection<String> cargarEstadosAutocaravana() ;
    void guardarEstadoAutocaravana(Collection<String> listaEstados);
    void guardarEstadoAutocaravana(String estado);
    void eliminarEstadoAutocaravana(String estado);
    void addEstadoAutocaravana(String estado);

    default String cargarEstadoDefault() {
        return "Disponible";
    }
}
