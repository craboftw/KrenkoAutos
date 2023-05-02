package uca.core.dao;

import org.springframework.data.repository.CrudRepository;
import uca.core.dominio.Cliente;


/*
public interface iClienteRepositorio {

    void guardarCliente(Collection<Cliente> clientes) ;
    void guardarCliente(Cliente cliente) ;
    Collection<Cliente> cargarCliente() ;
    Cliente buscarCliente(int idC);
    Collection<Cliente> buscarCliente(String estado,String dato) ;
    void eliminarCliente(String dni);
    void eliminarCliente(int idC);


}
*/

public interface iClienteRepositorio extends CrudRepository<Cliente,Long>{
}