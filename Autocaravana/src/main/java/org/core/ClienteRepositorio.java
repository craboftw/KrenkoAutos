package org.core;

import java.util.Collection;

public interface ClienteRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Cliente‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public void guardarCliente(Collection<Cliente> clientes) ;
    public void guardarCliente(Cliente cliente) ;
    public Collection<Cliente> cargarCliente() ;
    public Collection<Cliente> cargarCliente(String estado,String dato) ;
    public int getCantidadCliente();

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Collection<String> cargarEstadosCliente() ;
    void guardarEstadoCliente(Collection<String> listaEstados);
    public default String cargarEstadoDefault() {
        return "Disponible";
    }


    boolean existeCliente(Cliente c);

    void eliminarCliente(Cliente c);
}
