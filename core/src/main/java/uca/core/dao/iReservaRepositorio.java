package uca.core.dao;

import uca.core.dominio.Reserva;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;


public interface iReservaRepositorio extends CrudRepository<Reserva, Integer> {

    Collection<Reserva> findByTipo(String tipo);
    Collection<Reserva> findByDato(String dato);
    Reserva findByIdR(int idR);
    void deleteByIdR(int idR);
}

/*
public interface iReservaRepositorio {

    void guardarReserva(Collection<Reserva> caravanas) ;
    void guardarReserva(Reserva caravana) ;
    Collection<Reserva> cargarReserva() ;
    Collection<Reserva> buscarReserva(String tipo,String dato) ; //
    Reserva buscarReserva(int idR);
    void eliminarReserva(int idR);
}

/*/