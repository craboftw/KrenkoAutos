package uca.core;

import java.util.Collection;

public interface AutocaravanaEstadoRepositorio {

    Collection<String> cargarEstadosAutocaravana() ;
    void guardarEstadoAutocaravana(Collection<String> listaEstados);

    boolean existeEstadoAutocaravana(String estado);

    void eliminarEstadoAutocaravana(String estado);

    void addEstadoAutocaravana(String estado);

    void guardarEstadoAutocaravana(String estado);
    default String cargarEstadoDefault() {
        return "Disponible";
    }
}
