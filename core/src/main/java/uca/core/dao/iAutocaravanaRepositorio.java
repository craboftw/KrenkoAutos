package uca.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uca.core.dominio.Autocaravana;

@Repository
public interface iAutocaravanaRepositorio extends JpaRepository<Autocaravana,Long> {

}
