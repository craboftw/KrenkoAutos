package org.core;

import java.time.LocalDate;

public interface ReglasNegocio{
    //Funciones que calculan el precio total de la reserva
    public default float calculaPrecioTotal(Autocaravana A, Cliente C, LocalDate fechaIni, LocalDate fechaFin)
    //devuelve el precio total de la reserva;
    {
        calculaPrecioTotal(A, fechaIni, fechaFin);
    }


    public default float calculaPrecioTotal(Autocaravana A, LocalDate fechaIni, LocalDate fechaFin)
    //devuelve el precio total de la reserva sin tener en cuenta el cliente;
    {
        int dias = (int) (fechaFin.toEpochDay() - fechaIni.toEpochDay());
        return dias * A.getPrecioPorDia();
    }




    public default boolean condicionesCancelacion(Reserva R)
            //Comprobar si se permite cancelar la reserva;
    {
        return true;
    }
    public default boolean condicionesModificacion(Reserva R)
            //Comprobar si se permite modificar la reserva;
    {
        //si quedan 3 dias pa que acabe la reserva no se puede modificar
        return (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay()) > 3;
    }
    public default boolean condicionesFinalizacion(Reserva R)
    //Funcion que comprueba si la reserva se permite finalizar antes de la fecha de finalizacion
    {
        return true;
    }

    //Funciones que calculan la tasa de cancelacion, modificacion y finalizacion

    public default float calcularTasaCancelacion(Reserva R)
            //Tasa  por cancelar antes de comenzar la reserva
    {
        //si cancelo la reserva con dias de antelacion me devuelven el 90% del precio total.
        return R.getPrecioTotal() * (float) 0.90;
    }

    public default float calcularTasaModificacion(Reserva R)
            //Tasa por modificar tras comenzar la reserva
    {
        int diasRestantes = (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getAutocaravana().getPrecioPorDia() * diasRestantes * (float) 0.05;
    }

    public default float calcularTasaFinalizacion(Reserva R)
    {
        //si cancelo la reserva con dias de antelacion me devuelven el 50% del precio de los dias que me queden por utilizar
        int diasRestantes = (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getPrecioTotal() - R.getAutocaravana().getPrecioPorDia() * diasRestantes * (float) 0.50;
    }

    //Funciones que calculan la multa por incumplimiento.
    public default float calcularMulta(Reserva R) {
    int diasRetraso = (int) (LocalDate.now().toEpochDay() - R.getFechaFin().toEpochDay());
    return R.getAutocaravana().getPrecioPorDia() * diasRetraso * (float) 0.1;
    }

    //Funciones que comprueban si la caravana y el cliente son compatibles para la reserva
    public default boolean comprobarCaravana(Autocaravana A) {return A.getKilometraje() <= 10000000;}

    public default boolean comprobarCliente(Cliente c){ return (c.getedad() >= 18) & (c.getedad() <= 80);

    }

    //funcion que compruebe si la caravana, el cliente y las fechas son compatibles
    public default boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin, Autocaravana A, Cliente C) {return comprobarFecha(fechaIni,fechaFin);}
    public default boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin,Autocaravana A) {return comprobarFecha(fechaIni,fechaFin);}
    public default boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin,Cliente C) {return comprobarFecha(fechaIni,fechaFin);}
    public default boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin) {return fechaIni.isBefore(fechaFin);}
    public default boolean comprobarMatricula(String matricula) {return matricula.matches("[0-9]{4}[A-D,F-H,J-N,P-T,V-Z]{3}") ;}
    public default boolean comprobarKilometraje(int kilometraje) {return kilometraje >= 0;}
}