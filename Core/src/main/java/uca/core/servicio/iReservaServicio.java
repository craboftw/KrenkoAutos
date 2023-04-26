package uca.core.servicio;

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

import java.math.BigDecimal;
import java.util.Collection;

public interface iReservaServicio {
    void altaReserva(int idA, int idC, String fechI, String fechF);

    String checkout (int idR);

    String checkin (int idR);

    Reserva buscarReserva (int i);

    Collection<Reserva> buscarReserva(String tipo, String info);

    int getCantidadReservas();

    void eliminarReserva(int idR);

    void cancelarReserva(int idR);

    void setEstadoReserva(int idR, String estado);

    void setPrecioTotal(int idR, float precioTotal);

    void setAutocaravana(int idR, Autocaravana A);

    void setCliente(int idR, Cliente C);

    void setFechaIni(int idR, String fechaIni);

    void setFechaFin(int idR, String fechaFin);

    void setPrecioTotal(int idR, BigDecimal precioTotal);

    void modificarReservaEnCurso(int idR, String fechF);

    void modificarReserva(int idR, int idA, String fechI, String fechF);

    Collection<Reserva> getListaReservas();
}
