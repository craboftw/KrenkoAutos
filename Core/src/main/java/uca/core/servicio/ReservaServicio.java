package uca.core;

import uca.core.dao.ReservaEstadoRepositorio;
import uca.core.dao.ReservaRepositorio;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class ReservaServicio{
    private final ReservaReglas reservaReglas;
    private final ReservaRepositorio reservaRepositorio;
    private final ReservaEstadoRepositorio reservaEstadoRepositorio;

    public ReservaServicio(ReservaReglas reservaReglas, ReservaRepositorio reservaRepositorio, ReservaEstadoRepositorio reservaEstadoRepositorio) {
        this.reservaReglas = reservaReglas;
        this.reservaRepositorio = reservaRepositorio;
        this.reservaEstadoRepositorio = reservaEstadoRepositorio;
    }

    public void altaReserva(Autocaravana A, Cliente C, String fechI, String fechF)
    {
        LocalDate fechaIni;
        LocalDate fechaFin;
        if (!reservaReglas.comprobarAutocaravana(A)) {
            throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
        }
        try
        {
            fechaIni = LocalDate.parse(fechI);
            fechaFin = LocalDate.parse(fechF);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error en el formato de las fechas");
        }
        //Comprobaciones del cliente
        if (!reservaReglas.comprobarCliente(C)) {
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }

       if ( !reservaReglas.comprobarReserva(fechaIni, fechaFin, A, C)){
              throw new IllegalArgumentException("La reserva ya existe");
            }
        Reserva reserva = new Reserva(reservaRepositorio.cargarReserva().size(), A, C, fechI, fechF, reservaReglas.calculaPrecioTotal(A, C, fechaIni, fechaFin), reservaEstadoRepositorio.cargarReservaEstadoDefault());
        reservaRepositorio.guardarReserva(reserva);
    }

    public String checkout(Reserva R) {
        switch (R.getEstadoReserva()) {
            case "Cancelada":
                return ("La reserva está cancelada");
            case "Finalizada":
                return ("La reserva ya está finalizada");
            case "En curso":
                if (LocalDate.now().isAfter(R.fechaFinF())
                        || LocalDate.now().isEqual(R.fechaFinF())) {
                    R.setPrecioTotal((reservaReglas.calcularTasaFinalizacion(R)));
                    R.setEstadoReserva("Finalizada");
                    reservaRepositorio.guardarReserva(R);
                    String mensaje = "La reserva ha sido finalizada.";
                } else {
                    if (LocalDate.now().isBefore(R.fechaFinF())) {
                        R.setPrecioTotal((reservaReglas.calcularTasaCancelacion(R)));
                        R.setEstadoReserva("Cancelada");
                        reservaRepositorio.guardarReserva(R);
                        String mensaje = "La reserva ha sido cancelada por haber finalizado antes de la fecha prevista.";
                        if (R.getPrecioTotal().compareTo( R.getPagado()) < 0)
                            mensaje += "Queda pendiente de pagar " + (R.getPrecioTotal().subtract(R.getPagado())).toString() + "€";
                        if (R.getPrecioTotal().compareTo( R.getPagado()) > 0)
                            mensaje += "Se le devuelve " + (R.getPagado().subtract(R.getPrecioTotal())).toString() + "€";
                        return mensaje;
                    } else
                        return ("La reserva no ha acabado");
                }
                break;
            case "Pendiente":
                return ("La reserva no ha empezado");
            default:
                return ("El estado de la reserva es " + R.getEstadoReserva());
        }
        return ("El estado de la reserva es " + R.getEstadoReserva());    }

    public String checkin(Reserva R) {
        switch (R.getEstadoReserva()) {
        case "Cancelada":
            return ("La reserva está cancelada");
        case "Finalizada":
            return ("La reserva ya está finalizada");
        case "En curso":
            return ("La reserva ya está en curso");
        case "Pendiente":
            if ((LocalDate.now().isAfter(R.fechaIniF())
                    || LocalDate.now().isEqual(R.fechaIniF()))
                    & LocalDate.now().isBefore(R.fechaFinF())) {
                R.setEstadoReserva("En curso");
                reservaRepositorio.guardarReserva(R);
            } else {
                if (LocalDate.now().isAfter(R.fechaFinF())) {
                    R.setEstadoReserva("Finalizada");
                    R.setPrecioTotal(reservaReglas.calcularTasaAcabadaSinCheckOut(R));
                    reservaRepositorio.guardarReserva(R);
                } else
                    return ("La reserva no ha empezado");
            }
            break;
        default:
            return ("El estado de la reserva es " + R.getEstadoReserva());
    }
        return ("El estado de la reserva es " + R.getEstadoReserva());
    }



    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public Reserva buscarReserva(int i) {
        for (Reserva r : reservaRepositorio.cargarReserva()) {
            if (r.getIdR() == i) {
                return r;
            }
        }
        return null;
    }

    public Collection<Reserva> buscarReserva(String tipo, String info) {
        return reservaRepositorio.buscarReserva(tipo, info);
    }

    public int getCantidadReservas() {return reservaRepositorio.cargarReserva().size();}

    public void eliminarReserva(Reserva reserva) {
            reservaRepositorio.eliminarReserva(reserva);
    }

    public void cancelarReserva(Reserva reserva)
    {
        if (reserva.getEstadoReserva().equals("Cancelada"))
            throw new IllegalArgumentException("La reserva ya está cancelada");
        if (reserva.getEstadoReserva().equals("Finalizada"))
            throw new IllegalArgumentException("La reserva ya está finalizada");
        if(reservaReglas.condicionesCancelacion(reserva))
            reserva.setEstadoReserva("Cancelada");
        reserva.setPrecioTotal(reserva.getPrecioTotal().subtract(reservaReglas.calcularTasaCancelacion(reserva)));
    }

    public void setEstadoReserva(Reserva R,String estado) {
       if( reservaEstadoRepositorio.cargarReservaEstado().contains(estado) && reservaRepositorio.cargarReserva().contains(R)){
        reservaRepositorio.eliminarReserva(R);
        R.setEstadoReserva(estado);
        reservaRepositorio.guardarReserva(R);
    }
        else
            throw new IllegalArgumentException("El estado o la reserva no son correctos");
    }

    public void setPrecioTotal(Reserva R,float precioTotal)
    {
        if (!reservaRepositorio.cargarReserva().contains(R))
            throw new IllegalArgumentException("La reserva no existe");
        reservaRepositorio.eliminarReserva(R);
        R.setPrecioTotal(BigDecimal.valueOf(precioTotal));
        reservaRepositorio.guardarReserva(R);
    }

    public void setAutocaravana(Reserva R, Autocaravana A) {
        if (reservaRepositorio.cargarReserva().contains(A))
            throw new IllegalArgumentException("La autocaravana no existe");
        if (!reservaReglas.comprobarReserva(R.fechaIniF(), R.fechaFinF(),A, R.getCliente()))
            throw new IllegalArgumentException("La autocaravana no puede reservarse");
        reservaRepositorio.eliminarReserva(R);
        R.setAutocaravana(A);
        reservaRepositorio.guardarReserva(R);

    }

    public void setCliente(Reserva R, Cliente C) {
        if (reservaRepositorio.cargarReserva().contains(C))
            throw new IllegalArgumentException("El cliente no existe");
        if (!reservaReglas.comprobarReserva(R.fechaIniF(), R.fechaFinF(),R.getAutocaravana(), C))
            throw new IllegalArgumentException("El cliente no puede reservar la autocaravana");
        reservaRepositorio.eliminarReserva(R);
        R.setCliente(C);
        reservaRepositorio.guardarReserva(R);
    }

    public void setFechaIni(Reserva R, String fechaIni) {
        try {
            LocalDate.parse(fechaIni);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de las fechas.");
        }
        reservaRepositorio.eliminarReserva(R);
        R.setFechaIni(fechaIni);
        reservaRepositorio.guardarReserva(R);
    }

    public void setFechaFin(Reserva R, String fechaFin) {
        try {
            LocalDate.parse(fechaFin);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de las fechas.");
        }
        reservaRepositorio.eliminarReserva(R);
        R.setFechaFin(fechaFin);
        reservaRepositorio.guardarReserva(R);}

    public void setPrecioTotal(Reserva R, BigDecimal precioTotal) {
        if (precioTotal.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        R.setPrecioTotal(precioTotal); }


    public void modificarReservaEnCurso(Reserva reserva, String fechF) {
        LocalDate fechaFin;
        if (reserva.getEstadoReserva().equals("Cancelada")) {
            throw new IllegalArgumentException("La reserva está cancelada.");
        }
        if (reserva.getEstadoReserva().equals("Finalizada")) {
            throw new IllegalArgumentException("La reserva está finalizada.");
        }
        if (reserva.getEstadoReserva().equals("En curso")) {

            try {
                fechaFin = LocalDate.parse(fechF);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error en el formato de las fechas.");
            }

            if (reservaReglas.comprobarReserva(reserva.fechaIniF(), fechaFin, reserva.getAutocaravana(), reserva.getCliente())) {
                throw new IllegalArgumentException("La autocaravana no está disponible en las fechas seleccionadas.");
            }
            if(!reservaReglas.condicionesModificacion(reserva))
                throw new IllegalArgumentException("No se puede modificar la reserva.");
            reserva.setFechaFin(fechF);
            reserva.setPrecioTotal(reservaReglas.calculaPrecioTotal(reserva.getAutocaravana(), reserva.getCliente(), reserva.fechaIniF(), fechaFin).add( reservaReglas.calcularTasaModificacion(reserva)));
        }
    }

    public void modificarReserva(Reserva reserva, Autocaravana a, String fechI, String fechF){ //todavia no esta en curso
        LocalDate fechaIni;
        LocalDate fechaFin;
        if (reserva.getEstadoReserva().equals("Cancelada")) {
            throw new IllegalArgumentException("La reserva está cancelada");
        }
        if (reserva.getEstadoReserva().equals("Finalizada")) {
            throw new IllegalArgumentException("La reserva está finalizada");
        }
        if (!reserva.getEstadoReserva().equals("En curso")) {

            try {
                fechaIni = LocalDate.parse(fechI);
                fechaFin = LocalDate.parse(fechF);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error en el formato de las fechas");
            }

            if (!reservaReglas.comprobarAutocaravana(a)) {
                throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
            }
            if (fechaIni.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha inicial no puede ser anterior a la fecha actual");
            }
            if (reservaReglas.comprobarReserva(fechaIni, fechaFin, a, reserva.getCliente())) {
                throw new IllegalArgumentException("La autocaravana no está disponible en las fechas seleccionadas");
            }

            reserva.setFechaIni(fechI);
            reserva.setFechaFin(fechF);
            reserva.setPrecioTotal(reservaReglas.calculaPrecioTotal(reserva.getAutocaravana(), reserva.getCliente(), fechaIni, fechaFin));
        } else
            throw new IllegalArgumentException("La reserva no está en curso");
    }


    public Collection<Reserva> getListaReservas() {
        return reservaRepositorio.cargarReserva();
    }
}
