package uca.core;

import java.util.Collection;

public interface ReservaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Reserva‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarReserva(Collection<Reserva> caravanas) ;
    void guardarReserva(Reserva caravana) ;
    Collection<Reserva> cargarReserva() ;
    Collection<Reserva> buscarReserva(String tipo,String dato) ; //
    void eliminarReserva(Reserva r);
}
