package org.core;

import java.util.Collection;

public interface ReservaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Reserva‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public void guardarReserva(Collection<Reserva> caravanas) ;
    public void guardarReserva(Reserva caravana) ;
    public Collection<Reserva> cargarReserva() ;
    public Collection<Reserva> cargarReserva(String tipo,String dato) ; //
    public int getCantidadReserva();

    public boolean existeReserva(Reserva reserva);

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Collection<String> cargarEstadosReserva() ;
    void guardarEstadoReserva(Collection<String> listaEstados);
    void guardarEstadoReserva(String estado);
    public default String cargarEstadoDefault() {return "Disponible";}

    void eliminarReserva(Reserva r);

    boolean existeEstadoReserva(String estado);

    void eliminarEstadoReserva(String estado);

    boolean comprobarEstadoReserva(String estado);
}
