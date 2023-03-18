package org.core;

import java.time.LocalDate;

public interface ReglasNegocio{
    //Funciones que calculan el precio total de la reserva
    public float calculaPrecioTotal(Autocaravana A, Cliente C, LocalDate fechaIni, LocalDate fechaFin);

    public float calculaPrecioTotal(Autocaravana A, LocalDate fechaIni, LocalDate fechaFin);

    public float calculaPrecioTotal(Cliente C, LocalDate fechaIni, LocalDate fechaFin);

    public float calculaPrecioTotal(LocalDate fechaIni, LocalDate fechaFin);

    //Funciones para comprobar si la reserva puede ser modificada.
    public boolean condicionesCancelacion(Reserva R);
    public boolean condicionesModificacion(Reserva R);
    public boolean condicionesFinalizacion(Reserva R);

    //Funciones que calculan la tasa de cancelacion, modificacion y finalizacion

    public float calcularTasaCancelacion(Reserva R);

    public float calcularTasaModificacion(Reserva R);

    public float calcularTasaFinalizacion(Reserva R);

    //Funciones que calculan el reembolso de la reserva

    public float calcularReembolsoCancelacion(Reserva R);

    public float calcularReembolsoModificacion(Reserva R);

    //Funciones que calculan la multa por incumplimiento.
    public float calcularMulta(Reserva R);


    //Funciones que comprueban si la caravana y el cliente son compatibles para la reserva
    public boolean comprobarCaravana(Autocaravana A);

    boolean comprobarCliente(Cliente c);

    //funcion que compruebe si la caravana, el cliente y las fechas son compatibles
    public boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin, Autocaravana A, Cliente C);
    public boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin,Autocaravana A);
    public boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin,Cliente C);
    public boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin);
}