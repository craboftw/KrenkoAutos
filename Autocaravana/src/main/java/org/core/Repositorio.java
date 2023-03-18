package org.core;

import java.util.Collection;
import java.util.List;

public interface Repositorio {
    public Collection<Autocaravana> cargarAutocaravanas();
    public Collection<Cliente> cargarClientes();
    public Collection<Reserva> cargarReservas();
    public Collection<String> cargarEstados();
    public ReglasNegocio cargarReglasNegocio();
    public Collection<Object> cargarTodos();
    public void guardar(Object o);
    public void guardarColeccionEstados(Collection<String> listaEstados);
    public void guardarEstado(String Estado);
    public void guardarColeccionAutocaravana(Collection<Autocaravana> caravanas);
    public void guardarAutocaravana(Autocaravana caravana);
    public void guardarColeccionClientes(Collection<Cliente> clientes);
    public void guardarClente(Cliente cliente);
    public void guardarReglasNegocio(ReglasNegocio RN);
    public void guardarReservas(Collection<Reserva> R);
    public void guardarReservas(Reserva R);
    public void guardarTodos(List<Object> lista);




}
