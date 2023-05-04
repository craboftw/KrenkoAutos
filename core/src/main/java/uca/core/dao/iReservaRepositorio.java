package uca.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uca.core.dominio.Reserva;


//public interface iReservaRepositorio extends CrudRepository<Reserva, Integer> {


public interface iReservaRepositorio extends JpaRepository<Reserva,Long> {
}

