package uca.core;

import java.util.Collection;

public interface ClienteRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Cliente‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarCliente(Collection<Cliente> clientes) ;
    void guardarCliente(Cliente cliente) ;
    Collection<Cliente> cargarCliente() ;
    Collection<Cliente> cargarCliente(String estado,String dato) ;
    int getCantidadCliente();

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    Collection<String> cargarEstadosCliente() ;
    void guardarEstadoCliente(Collection<String> listaEstados);
    void guardarEstadoCliente(String estado);

    default String cargarEstadoDefault() {return "Disponible";}


    boolean existeCliente(Cliente c);

    void eliminarCliente(Cliente c);

    void eliminarEstadoCliente(String estado);

    boolean existeEstadoCliente(String estado);
}
