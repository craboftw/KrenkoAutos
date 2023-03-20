package org.core;

import java.time.LocalDate;

public interface ReglasNegocio{

    static List<String> listaEstados = new ArrayList<>(Arrays.asList("Pendiente", "Cancelada", "Finalizada", "En curso"));
   
    //Funciones que calculan el precio total de la reserva
    default float calculaPrecioTotal(Autocaravana A, Cliente C, LocalDate fechaIni, LocalDate fechaFin)
    //devuelve el precio total de la reserva;
    {
        //penalizacion por multa de un 10%, si la caravana tiene mas de 700000 km se descuenta un 5% del precio total
        return (float) (A.getPrecioPorDia() * (fechaFin.toEpochDay() - fechaIni.toEpochDay()) * (1 + (C.getNumeroMultas() * 0.1)) * (1 - (A.getKilometraje() > 700000 ? 0.05 : 0)));
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
    //Funcion que comprueba si la reserva se permite finalizar antes de la fecha de finalizacion
    {
        return true;
    }

    //Funciones que calculan la tasa de cancelacion, modificacion y finalizacion

    default float calcularTasaCancelacion(Reserva R)
            //Tasa  por cancelar antes de comenzar la reserva
    {
        //si cancelo la reserva con dias de antelacion me devuelven el 90% del precio total.
        return R.getPrecioTotal() * (float) 0.90;
    }

    default float calcularTasaModificacion(Reserva R)
            //Tasa por modificar tras comenzar la reserva
    {
        int diasRestantes = (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getAutocaravana().getPrecioPorDia() * diasRestantes * (float) 0.05;
    }

    default float calcularTasaFinalizacion(Reserva R)
    {
        //si cancelo la reserva con dias de antelacion me devuelven el 50% del precio de los dias que me queden por utilizar
        int diasRestantes = (int) (R.getFechaFin().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getPrecioTotal() - R.getAutocaravana().getPrecioPorDia() * diasRestantes * (float) 0.50;
    }

    //Funciones que calculan la multa por incumplimiento.
    default float calcularMulta(Reserva R)
    //Calcula la multa por incumplimiento de la reserva
    {
    int diasRetraso = (int) (LocalDate.now().toEpochDay() - R.getFechaFin().toEpochDay());
    return R.getAutocaravana().getPrecioPorDia() * diasRetraso * (float) 0.1;
    }

    //Funciones que comprueban si la caravana y el cliente son compatibles para la reserva
    default boolean comprobarCaravana(Autocaravana A)
    //comprueba si una caravana es valida para la reserva
    {return A.getKilometraje() <= 10000000 ;}

    default boolean comprobarCliente(Cliente c)
    //comprueba si un cliente es valido para la reserva
    { return (c.getedad() >= 18) & (c.getedad() <= 80 & c.getNumeroMultas()<2);

    }

    //funcion que compruebe si la caravana, el cliente y las fechas son compatibles
    default boolean comprobarFecha(LocalDate fechaIni, LocalDate fechaFin, Autocaravana A, Cliente C)
    //comprueba si una caravana y un cliente son validos para la reserva.
    {
        for (Reserva R : Reserva.getListaReservas()) {
            if (R.getAutocaravana().equals(A)) {
                //comprobar si las fechas de la Reserva R se solapan con las fechas de la reserva que se quiere hacer
                if (fechaIni.isBefore(R.getFechaFin()) && fechaIni.isAfter(R.getFechaIni()) || fechaFin.isBefore(R.getFechaFin()) && fechaFin.isAfter(R.getFechaIni()) || fechaIni.isBefore(R.getFechaIni()) && fechaFin.isAfter(R.getFechaFin()) || fechaIni.isEqual(R.getFechaIni()) || fechaFin.isEqual(R.getFechaFin())) {
                    return false;

                }
            }

        }
        return true;

    }



    static boolean comprobarMatricula(String matricula) {
        String regex = "^[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}$"; // Expresión regular para matrículas de turismos

        // Verificar si la matrícula coincide con la expresión regular
        if (!matricula.matches(regex)) {
            return false;
        }

        // Verificar que la matrícula tenga una letra final válida
        String letrasValidas = "BCDFGHJKLMNPRSTVWXYZ";
        if (!letrasValidas.contains(matricula.substring(7))) {
            return false;
        }

        // Verificar que el número de matrícula sea válido
        int numMatricula = Integer.parseInt(matricula.substring(0, 4));
        return numMatricula != 0 && numMatricula <= 9999;
    }

}