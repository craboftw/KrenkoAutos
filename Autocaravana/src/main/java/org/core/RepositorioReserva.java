package org.core;

public interface RepositorioReserva {
    static void cargarReservas() {

    }

    static void cargarEstadosReserva() {

    }

    void guardarReservas(java.util.Collection<Reserva> R);

    void guardarEstadosReserva(java.util.Collection<String> EstadosReserva);

}
