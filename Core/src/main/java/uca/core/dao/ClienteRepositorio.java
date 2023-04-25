package uca.core.dao;

import uca.core.dominio.Cliente;

import java.util.Collection;


public interface ClienteRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Cliente‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarCliente(Collection<Cliente> clientes) ;
    void guardarCliente(Cliente cliente) ;
    Collection<Cliente> cargarCliente() ;
    Collection<Cliente> buscarCliente(String estado,String dato) ;
    void eliminarCliente(String dni);
    void eliminarCliente(int idC);


}
