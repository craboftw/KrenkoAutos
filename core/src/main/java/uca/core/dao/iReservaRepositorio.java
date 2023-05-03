package uca.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

import java.util.Collection;


//public interface iReservaRepositorio extends CrudRepository<Reserva, Integer> {




public interface iReservaRepositorio extends JpaRepository<Reserva,Long> {
}

