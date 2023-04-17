package Uca.Core;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReservaReglas {
    ReservaRepositorio reservaRepositorio = null;
    //Separar funciones en distintas clases por ambito


    //Funciones que calculan el precio total de la reserva
    default BigDecimal calculaPrecioTotal(Autocaravana A, Cliente C, LocalDate fechaIni, LocalDate fechaFin)
    //devuelve el precio total de la reserva;
    {
        //penalizacion por multa de un 10%, si la caravana tiene mas de 700000 km se descuenta un 5% del precio total
        BigDecimal precio= A.getPrecioPorDia().multiply(BigDecimal.valueOf((int) (fechaFin.toEpochDay() - fechaIni.toEpochDay()))).multiply(BigDecimal.valueOf(1.1));
        if (A.getKilometraje() > 700000)
            precio =  precio.multiply(BigDecimal.valueOf(0.95));
        return precio;
    }


    default boolean condicionesCancelacion(Reserva R)
    //Comprobar si se permite cancelar la reserva;
    {
        return true;
    }

    default boolean condicionesModificacion(Reserva R)
    //Comprobar si se permite modificar la reserva;
    {
        //si quedan 3 dias pa que acabe la reserva no se puede modificar
        return (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay()) > 3;
    }

    default boolean condicionesFinalizacion(Reserva R)
    {
        return ((int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay())) <= 3;
    }


    //Funciones que calculan la tasa de cancelacion, modificacion y finalizacion

    default float calcularTasaCancelacion(Reserva R)
    //Tasa  por cancelar antes de comenzar la reserva
    {
        //si cancelo la reserva con dias de antelacion me devuelven el 90% del precio total.
        return R.getPrecioTotal().multiply(BigDecimal.valueOf(0.9)).floatValue();
    }

    default BigDecimal calcularTasaModificacion(Reserva R)
    //Tasa por modificar tras comenzar la reserva
    {
        int diasRestantes = (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getPrecioTotal().subtract(R.getAutocaravana().getPrecioPorDia().multiply(BigDecimal.valueOf(diasRestantes)));
    }

    default BigDecimal calcularTasaFinalizacion(Reserva R) {
        //si cancelo la reserva con dias de antelacion me devuelven el 50% del precio de los dias que me queden por utilizar
        int diasRestantes = (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getPrecioTotal().subtract(R.getAutocaravana().getPrecioPorDia().multiply(BigDecimal.valueOf(diasRestantes)).multiply(BigDecimal.valueOf(0.5)));
    }

    //Funciones que calculan la multa por incumplimiento.
    default float calcularMulta(Reserva R)
    //Calcula la multa por incumplimiento de la reserva
    {
        int diasRetraso = (int) (LocalDate.now().toEpochDay() - R.getFechaFin().toEpochDay());
return R.getPrecioTotal().multiply(BigDecimal.valueOf(0.1)).multiply(BigDecimal.valueOf(diasRetraso)).floatValue();
    }

    //Funciones que comprueban si la caravana y el cliente son compatibles para la reserva
    default boolean comprobarAutocaravana(Autocaravana A)
    //comprueba si una caravana es valida para la reserva
    {
        return A.getKilometraje() <= 10000000;
    }

    default boolean comprobarCliente(Cliente c)
    //comprueba si un cliente es valido para la reserva
    {
        return (c.Edad() >= 18) & (c.Edad() <= 80 & c.getMultas() < 2);

    }

    //funcion que compruebe si la caravana, el cliente y las fechas son compatibles
    default boolean comprobarReserva(LocalDate fechaIni, LocalDate fechaFin, Autocaravana A, Cliente C)
    //comprueba si una caravana y un cliente son validos para la reserva.
    {
        for (Reserva R : reservaRepositorio.cargarReserva()) {
            if (R.getAutocaravana().equals(A) & R.getEstadoReserva() != "Cancelada" & R.getEstadoReserva() != "Finalizada") {
                //comprobar si las fechas de la Reserva R se solapan con las fechas de la reserva que se quiere hacer
                if (
                        (fechaIni.isBefore(R.getFechaFin()) & fechaIni.isAfter(R.getFechaIni())) |
                                (fechaFin.isBefore(R.getFechaFin()) & fechaFin.isAfter(R.getFechaIni())) |
                                fechaIni.isEqual(R.getFechaIni()) | fechaFin.isEqual(R.getFechaFin()) |
                                fechaFin.isEqual(R.getFechaIni()) | fechaIni.isEqual(R.getFechaFin()))
                {
                    return false;
                }
            }
        }
        return true;
    }

}
