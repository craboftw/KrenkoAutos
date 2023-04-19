package uca.core;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReservaReglas {
    public BigDecimal calculaPrecioTotal(Autocaravana A, Cliente C, LocalDate fechaIni, LocalDate fechaFin);
    public boolean condicionesCancelacion(Reserva R);
    public boolean condicionesModificacion(Reserva R);
    public boolean condicionesFinalizacion(Reserva R);
    public BigDecimal calcularTasaCancelacion(Reserva R);
    public BigDecimal calcularTasaModificacion(Reserva R);
    public BigDecimal calcularTasaFinalizacion(Reserva R);
    public BigDecimal calcularMulta(Reserva R);
    public BigDecimal calcularTasaAcabadaSinCheckOut(Reserva R);
    public boolean comprobarAutocaravana(Autocaravana A);
    public boolean comprobarCliente(Cliente c);
    public boolean comprobarReserva(LocalDate fechaIni, LocalDate fechaFin, Autocaravana A, Cliente C);

}
