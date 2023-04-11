package uca.core;

import java.util.Collection;

public interface ReservaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Reserva‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarReserva(Collection<Reserva> caravanas) ;
    void guardarReserva(Reserva caravana) ;
    Collection<Reserva> cargarReserva() ;
    Collection<Reserva> cargarReserva(String tipo,String dato) ; //
    int getCantidadReserva();

    boolean existeReserva(Reserva reserva);

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    Collection<String> cargarEstadosReserva() ;
    void guardarEstadoReserva(Collection<String> listaEstados);
    void guardarEstadoReserva(String estado);
    default String cargarEstadoDefault() {return "Disponible";}

    void eliminarReserva(Reserva r);

    boolean existeEstadoReserva(String estado);

    void eliminarEstadoReserva(String estado);

    boolean comprobarEstadoReserva(String estado);
}
