package org.core;

public interface RepositorioReserva {
    static void cargarReservas() {

    }

    void guardarReservas(java.util.Collection<Reserva> R);

    static void cargarEstadosReserva() {

    }

    void guardarEstadosReserva(java.util.Collection<String> EstadosReserva);

}
