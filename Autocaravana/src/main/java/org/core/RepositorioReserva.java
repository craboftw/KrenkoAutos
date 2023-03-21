package org.core;

public interface RepositorioReserva {
    void cargarReservas();
    void guardarReservas(java.util.Collection<Reserva> R);
    void cargarEstadosReserva();
    void guardarEstadosReserva(java.util.Collection<String> EstadosReserva);

}
