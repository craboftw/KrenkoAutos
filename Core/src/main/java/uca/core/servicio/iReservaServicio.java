package uca.core.servicio;

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

import java.math.BigDecimal;
import java.util.Collection;

public interface iReservaServicio {
    void altaReserva(Autocaravana A, Cliente C, String fechI, String fechF);

    String checkout(Reserva R);

    String checkin(Reserva R);

    Reserva buscarReserva(int i);

    Collection<Reserva> buscarReserva(String tipo, String info);

    int getCantidadReservas();

    void eliminarReserva(Reserva reserva);

    void cancelarReserva(Reserva reserva);

    void setEstadoReserva(Reserva R, String estado);

    void setPrecioTotal(Reserva R, float precioTotal);

    void setAutocaravana(Reserva R, Autocaravana A);

    void setCliente(Reserva R, Cliente C);

    void setFechaIni(Reserva R, String fechaIni);

    void setFechaFin(Reserva R, String fechaFin);

    void setPrecioTotal(Reserva R, BigDecimal precioTotal);

    void modificarReservaEnCurso(Reserva reserva, String fechF);

    void modificarReserva(Reserva reserva, Autocaravana a, String fechI, String fechF);

    Collection<Reserva> getListaReservas();
}
