package uca.core.dao;

import uca.core.dominio.Cliente;
import uca.core.dominio.Estado;

import java.util.Collection;

public interface iClienteRepositorio {

    void guardarCliente(Collection<Cliente> clientes) ;
    void guardarCliente(Cliente cliente) ;
    Collection<Cliente> cargarCliente() ;
    Cliente buscarCliente(int idC);
    Collection<Cliente> buscarCliente(String estado,String dato) ;
    void eliminarCliente(String dni);
    void eliminarCliente(int idC);


}
