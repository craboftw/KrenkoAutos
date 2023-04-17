package Uca.Core;

import java.util.Collection;

public interface ClienteEstadoRepositorio {
    Collection<String> cargarEstadosCliente() ;
    void guardarEstadoCliente(Collection<String> listaEstados);
    void guardarEstadoCliente(String estado);
    void eliminarCliente(Cliente c);
    void eliminarEstadoCliente(String estado);
    void addEstadoCliente(String estado);
    default String cargarEstadoDefault() {return "Disponible";}
}
