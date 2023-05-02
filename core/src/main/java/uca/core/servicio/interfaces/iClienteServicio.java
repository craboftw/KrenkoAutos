package uca.core.servicio.interfaces;

import uca.core.dominio.Cliente;

import java.util.Collection;


public interface iClienteServicio {
    void altaCliente(String nom, String ape, String telef, String fecha, String dn, String ema);

    int getNumeroClientes();

    Cliente buscarCliente(Long idC);

    Cliente buscarCliente(String dni);

    Collection<Cliente> buscarCliente(String campo, String dato);

    void eliminarCliente(String dni);

    void eliminarCliente(Long idC);

    void comprobarCliente(String nom, String ape, String telef, String cumpleanos, String dn, String ema);

    void setNombre(Long idC, String nombre);

    void setNombre(String dni, String nombre);

    void setApellido(Long idC, String apellido);

    void setApellido(String dni, String apellido);

    void setEmail(String dni, String email);

    void setEmail(Long idC, String email);

    void setDni(Long idC, String dni);

    void setFechaNacimiento(String dni, String fechaNacimiento);

    void setFechaNacimiento(Long idC, String fechaNacimiento);

    void setTelefono(Long idC, String telefono);

    void setTelefono(String dni, String telefono);

    Collection<String> getListaEstadoclientes();

    Collection<Cliente> getListaClientes();

    void guardarCliente(Cliente c);

    String crearEstado(String valor);

    String eliminarEstado(String valor);
}
