package org.core;

import java.util.Collection;

public interface ClienteRepositorios {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Cliente‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public void guardarCliente(Collection<Cliente> caravanas) ;
    public void guardarCliente(Cliente caravana) ;
    public Collection<Cliente> cargarCliente() ;
    public Collection<Cliente> cargarCliente(String estado,String dato) ;
    public int getCantidadCliente();

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Collection<String> cargarEstadosCliente() ;
    void guardarEstadoCliente(Collection<String> listaEstados);
    public default String cargarEstadoDefault() {
        return "Disponible";
    }


}
