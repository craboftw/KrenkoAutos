package org.core;

import java.util.Collection;

public interface RepositorioCliente {
    static void guardarCliente(java.util.Collection<Cliente> clientes) {

    }

    static void cargarClientes() {

    }

    static void cargarEstadosCliente() {

    }

    void guardarEstadosCliente(Collection<String> EstadosCliente);
}
