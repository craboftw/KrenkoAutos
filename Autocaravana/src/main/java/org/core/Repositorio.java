package org.core;

import java.util.Collection;
import java.util.List;

public interface Repositorio {
    void cargarAutocaravanas();
    void cargarClientes();
    void cargarReservas();
    void cargarEstados();
    void cargarTodos();
    void guardarEstado(Collection<String> listaEstados);
    void guardarAutocaravana(Collection<Autocaravana> caravanas);
    void guardarCliente(Collection<Cliente> clientes);
    void guardarReservas(Collection<Reserva> R);
    void guardar(List<Object> lista);





}
