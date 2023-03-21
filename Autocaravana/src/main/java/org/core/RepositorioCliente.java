package org.core;

import java.util.Collection;

public interface RepositorioCliente{
    public void guardarCliente(java.util.Collection<Cliente> clientes);
    public void guardarEstadosCliente(Collection<String> EstadosCliente);
    void cargarClientes();
    void cargarEstadosCliente();
}
