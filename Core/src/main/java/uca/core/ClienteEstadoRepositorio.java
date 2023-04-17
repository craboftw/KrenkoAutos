package uca.core;

import java.util.Collection;

public interface ClienteEstadoRepositorio {
    Collection<String> cargarEstadosCliente() ;
    void guardarEstadoCliente(Collection<String> listaEstados);
    void guardarEstadoCliente(String estado);
    void eliminarEstadoCliente(String estado);
    default String cargarEstadoDefault() {return "Disponible";}


}
