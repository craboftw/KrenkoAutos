package uca.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uca.core.dominio.Cliente;


public interface iClienteRepositorio extends JpaRepository<Cliente,Long> {
}