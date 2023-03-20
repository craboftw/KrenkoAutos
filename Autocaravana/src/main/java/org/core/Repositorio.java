package org.core;

import java.util.Collection;
import java.util.List;

public interface Repositorio {
    void cargarAutocaravanas();
    void cargarClientes();
    void cargarReservas();
    void cargarEstados();
    void cargarTodos();
    void guardar(Object o);
    void guardarColeccionEstados(Collection<String> listaEstados);
    void guardarEstado(String Estado);
    void guardarAutocaravana(Collection<Autocaravana> caravanas);
    void guardarAutocaravana(Autocaravana caravana);
    void guardarCliente(Collection<Cliente> clientes);
    void guardarCliente(Cliente cliente);


    void guardarReglasNegocio(ReglasNegocio RN);
    void guardarReservas(Collection<Reserva> R);
    void guardarReservas(Reserva R);
    void guardarTodos(List<Object> lista);





}
