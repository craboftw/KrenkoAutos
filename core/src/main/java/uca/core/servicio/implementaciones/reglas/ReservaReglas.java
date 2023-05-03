package uca.core.servicio.implementaciones.reglas;

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;


import uca.core.dao.iReservaRepositorio;
import uca.core.servicio.interfaces.iAutocaravanaServicio;
import uca.core.servicio.interfaces.iClienteServicio;

public class ReservaReglas
{
    iReservaRepositorio reservaRepositorio;
    iClienteServicio clienteServicio;
    iAutocaravanaServicio autocaravanaServicio;
    //Separar funciones en distintas clases por ambito

    public ReservaReglas(iReservaRepositorio reservaRepositorio, iClienteServicio clienteServicio, iAutocaravanaServicio autocaravanaServicio){
        this.reservaRepositorio = reservaRepositorio;
        this.clienteServicio = clienteServicio;
        this.autocaravanaServicio = autocaravanaServicio;
    }

    //Funciones que calculan el precio total de la reserva
    public BigDecimal calculaPrecioTotal(Autocaravana A, Cliente C, LocalDate fechaIni, LocalDate fechaFin)
    //devuelve el precio total de la reserva;
    {
        //penalizacion por multa de un 10%, si la caravana tiene mas de 700000 km se descuenta un 5% del precio total
        BigDecimal precio= A.getPrecioPorDia().multiply(BigDecimal.valueOf((int) (fechaFin.toEpochDay() - fechaIni.toEpochDay()))).multiply(BigDecimal.valueOf(1.1));
        if (A.getKilometraje() > 700000)
            precio =  precio.multiply(BigDecimal.valueOf(0.95));
        return precio;
    }


    public boolean condicionesCancelacion(Reserva R)
    //Comprobar si se permite cancelar la reserva;
    {
        return true;
    }

    public boolean condicionesModificacion(Reserva R)
    //Comprobar si se permite modificar la reserva;
    {
        //si quedan 3 dias pa que acabe la reserva no se puede modificar
        return (int) (R.fechaFinF().toEpochDay() - LocalDate.now().toEpochDay()) > 3;
    }

    public boolean condicionesFinalizacion(Reserva R)
    {
        return ((int) (R.fechaFinF().toEpochDay() - LocalDate.now().toEpochDay())) <= 3;
    }


    //Funciones que calculan la tasa de cancelacion, modificacion y finalizacion

    public BigDecimal calcularTasaCancelacion(Reserva R)
    //Tasa  por cancelar antes de comenzar la reserva
    {
        //si cancelo la reserva con dias de antelacion me devuelven el 90% del precio total.
        return R.getPrecioTotal().multiply(BigDecimal.valueOf(0.9));
    }

    public BigDecimal calcularTasaModificacion(Reserva R)
    //Tasa por modificar tras comenzar la reserva
    {
        int diasRestantes = (int) (R.fechaFinF().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getPrecioTotal().subtract(autocaravanaServicio.buscarAutocaravana(R.getIdAutocaravana()).getPrecioPorDia().multiply(BigDecimal.valueOf(diasRestantes)));
    }

    public BigDecimal calcularTasaFinalizacion(Reserva R) {
        //si cancelo la reserva con dias de antelacion me devuelven el 50% del precio de los dias que me queden por utilizar
        int diasRestantes = (int) (R.fechaFinF().toEpochDay() - LocalDate.now().toEpochDay());
        return R.getPrecioTotal().subtract(autocaravanaServicio.buscarAutocaravana(R.getIdAutocaravana()).getPrecioPorDia().multiply(BigDecimal.valueOf(diasRestantes)).multiply(BigDecimal.valueOf(0.5)));
    }

    //Funciones que calculan la multa por incumplimiento.
    public BigDecimal calcularMulta(Reserva R)
    //Calcula la multa por incumplimiento de la reserva
    {
        int diasRetraso = (int) (LocalDate.now().toEpochDay() - R.fechaFinF().toEpochDay());
        return R.getPrecioTotal().multiply(BigDecimal.valueOf(0.1)).multiply(BigDecimal.valueOf(diasRetraso));
    }

    public BigDecimal calcularTasaAcabadaSinCheckOut(Reserva R) {
        return R.getPrecioTotal();
    }

    //Funciones que comprueban si la caravana y el cliente son compatibles para la reserva
    public boolean comprobarAutocaravana(Autocaravana A)
    //comprueba si una caravana es valida para la reserva
    {
        return A.getKilometraje() <= 10000000;
    }

    public boolean comprobarCliente(Cliente c)
    //comprueba si un cliente es valido para la reserva
    {
        return (c.Edad() >= 18) & (c.Edad() <= 80 & c.getMultas() < 2);

    }

    //funcion que compruebe si la caravana, el cliente y las fechas son compatibles
    public boolean comprobarReserva(LocalDate fechaIni, LocalDate fechaFin, Autocaravana A, Cliente C)
    //comprueba si una caravana y un cliente son validos para la reserva.
    {
        if (true) return true;
        if (reservaRepositorio.findAll().isEmpty())
            return true;
        for (Reserva R : reservaRepositorio.findAll()) {
            if (autocaravanaServicio.buscarAutocaravana(R.getIdAutocaravana()).equals(A) & R.getEstadoR() != "Cancelada" & R.getEstadoR() != "Finalizada") {
                //comprobar si las fechas de la Reserva R se solapan con las fechas de la reserva que se quiere hacer
                if (
                        (fechaIni.isBefore(R.fechaFinF()) & fechaIni.isAfter(R.fechaIniF())) |
                                (fechaFin.isBefore(R.fechaFinF()) & fechaFin.isAfter(R.fechaIniF())) |
                                fechaIni.isEqual(R.fechaIniF()) | fechaFin.isEqual(R.fechaFinF()) |
                                fechaFin.isEqual(R.fechaIniF()) | fechaIni.isEqual(R.fechaFinF()))
                {
                    return false;
                }
            }
        }
        return true;
    }




}
