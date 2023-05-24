package uca.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uca.core.dominio.Estado;

public interface iEstadoRepositorio extends JpaRepository<Estado,Long> {
    default public Estado cargarEstadoDefault(String tipo)
    {
        switch (tipo) {
            case "Cliente" -> {
                return new Estado("Cliente", "Activo");
            }
            case "Autocaravana" -> {
                return new Estado("Autocaravana", "Disponible");
            }
            case "Reserva" -> {
                return new Estado("Reserva", "Activa");
            }
            default -> {
                throw new IllegalArgumentException("Tipo no compatible: " + tipo);
            }
        }
    }
}
