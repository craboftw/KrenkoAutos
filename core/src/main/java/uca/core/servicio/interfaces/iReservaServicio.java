package uca.core.servicio.interfaces;

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


public interface iReservaServicio {
    void altaReserva(Long idA, Long idC, String fechI, String fechF);

    String checkout (Long idR);

    String checkin (Long idR);

    Reserva buscarReserva (Long idR);

    Collection<Reserva> buscarReserva(String tipo, String info);

    int getCantidadReservas();

    void eliminarReserva(Long idR);

    void cancelarReserva(Long idR);

    void setEstadoReserva(Long idR, String estado);

    void setPrecioTotal(Long idR, float precioTotal);

    void setAutocaravana(Long idR, Long idA);

    void setCliente(Long idR, Long idC);

    void setFechaIni(Long idR, String fechaIni);

    void setFechaFin(Long idR, String fechaFin);

    void setPrecioTotal(Long idR, BigDecimal precioTotal);

    void modificarReservaEnCurso(Long idR, String fechF);

    void modificarReserva(Long idR, Long idA, String fechI, String fechF);

    Collection<Reserva> getListaReservas();
    String crearEstado(String estado);
    String eliminarEstado(String estado);
    List<Autocaravana> getListaAutocaravanasDisponibles(LocalDate fecha);
}
