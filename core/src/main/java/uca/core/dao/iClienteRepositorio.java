package uca.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uca.core.dominio.Cliente;

@Repository
public interface iClienteRepositorio extends JpaRepository<Cliente,Long> {

}