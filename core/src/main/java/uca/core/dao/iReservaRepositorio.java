package uca.core.dao;

import uca.core.dominio.Reserva;

import java.util.Collection;


//public interface iReservaRepositorio extends CrudRepository<Reserva, Integer> {




public interface iReservaRepositorio {

    void guardarReserva(Collection<Reserva> caravanas) ;
    void guardarReserva(Reserva caravana) ;
    Collection<Reserva> cargarReserva() ;
    Collection<Reserva> buscarReserva(String tipo,String dato) ; //
    Reserva buscarReserva(int idR);
    void eliminarReserva(int idR);
}

