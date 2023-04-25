package uca.core.dao;

import java.util.Collection;

public interface AutocaravanaEstadoRepositorio {
    Collection<String> cargarEstadosAutocaravana() ;
    void guardarEstadoAutocaravana(Collection<String> listaEstados);
    void guardarEstadoAutocaravana(String estado);
    void eliminarEstadoAutocaravana(String estado);

    default String cargarEstadoDefault() {
        return "Disponible";
    }
}
