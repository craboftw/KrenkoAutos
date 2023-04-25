package uca.core.dao;

import uca.core.dominio.Reserva;

import java.io.IOException;
import java.util.Collection;

public interface ReservaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Reserva‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarReserva(Collection<Reserva> caravanas) ;
    void guardarReserva(Reserva caravana) ;
    Collection<Reserva> cargarReserva() ;
    Collection<Reserva> buscarReserva(String tipo,String dato) ; //
    void eliminarReserva(Reserva r);
}
