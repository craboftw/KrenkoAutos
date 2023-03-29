package org.core;

import java.time.LocalDate;
import java.util.*;


public class ReservaServicio{
    private static ReservaReglas reservaReglas;
    private static ReservaRepositorio reservaRepositorio;

    private static final List<String> listaEstadoReserva = new ArrayList<>(Arrays.asList("Pendiente", "Finalizada", "Cancelada", "En curso"));

    public void comprobarReserva(Autocaravana A, Cliente C, String fechI, String fechF)
    {
        if (!reservaReglas.comprobarAutocaravana(A)) {
            throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
        }
        try
        {
            LocalDate fechaIni = LocalDate.parse(fechI);
            LocalDate fechaFin = LocalDate.parse(fechF);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error en el formato de las fechas");
        }
        //Comprobaciones del cliente
        if (!reservaReglas.comprobarCliente(C)) {
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }
    }

    public static List<String> getListaEstadoReservas() {
        return listaEstadoReserva;
    }

    //@Override
    public static void nuevoestado(String estado) {
        if (!estado.isEmpty() & !listaEstadoReserva.contains(estado)) {
            listaEstadoReserva.add(estado);
            reservaRepositorio.guardarEstadoReserva(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    //@Override



    public static void checkOut(Reserva reserva) {
        String estadoReserva = reserva.getEstadoReserva();
        switch (reserva.getEstadoReserva()) {
            case "Cancelada":
                System.out.println("La reserva está cancelada");
                break;
            case "Finalizada":
                System.out.println("La reserva ya está finalizada");
                break;
            case "En curso":
                if (LocalDate.now().isAfter(reserva.getFechaFin())) {
                    reserva.setPrecioTotal(reserva.getPrecioTotal() + reservaReglas.calcularMulta(reserva));;
                }
                else
                if (LocalDate.now().isBefore(reserva.getFechaFin()) & reservaReglas.condicionesFinalizacion(reserva)) {
                    reserva.setPrecioTotal(reserva.getPrecioTotal() - reservaReglas.calcularTasaFinalizacion(reserva));;
                }
                else //POR AQUI
                if (LocalDate.now().isBefore(reserva.getFechaFin()) & !reservaReglas.condicionesFinalizacion(reserva)) {
                    System.out.println("No se puede finalizar la reserva");
                    break;
                }
                    reserva.setEstadoReserva("Finalizada");

            default:
                System.out.println("El estado de la reserva es " + estadoReserva);
                break;
        }
    }

    public static void  checkIn(Reserva reserva) {
        String estadoReserva = reserva.getEstadoReserva();
        switch (estadoReserva) {
            case "Cancelada":
                System.out.println("La reserva está cancelada");
                break;
            case "Finalizada":
                System.out.println("La reserva ya está finalizada");
                break;
            case "En curso":
                System.out.println("La reserva ya está en curso");
                break;
            case "Pendiente":
                if ((LocalDate.now().isAfter(reserva.getFechaIni())
            || LocalDate.now().isEqual(reserva.getFechaIni()))
                        & LocalDate.now().isBefore(reserva.getFechaFin())) {
                    reserva.setEstadoReserva("En curso");
                } else {
                    if (LocalDate.now().isAfter(reserva.getFechaFin())) {
                        reserva.setEstadoReserva("Cancelada");
                    }
                    else
                       System.out.println("La reserva no ha empezado");
                }
                break;
            default:
                System.out.println("El estado de la reserva es " + estadoReserva);
                break;
        }
    }


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public static Reserva buscarReserva(int i) {
        for (Reserva r : reservaRepositorio.cargarReserva()) {
            if (r.getIdR() == i) {
                return r;
            }
        }
        return null;
    }

    public static Collection<Reserva> buscarReserva(String tipo, String info) {
        return reservaRepositorio.cargarReserva(tipo, info);
    }

    public int getCantidadReservas() {return reservaRepositorio.getCantidadReserva();}

    public void eliminarReserva(Reserva R) {
        if (reservaRepositorio.existeReserva(R)) {
            reservaRepositorio.eliminarReserva(R);
        } else throw new IllegalArgumentException("La reserva ya esta eliminada");
    }

    public static void eliminarEstadoReserva(String estado) {
        if (!estado.isEmpty() & reservaRepositorio.existeReservaEstado(estado)){
            listaEstadoReserva.remove(estado);
            reservaRepositorio.eliminarEstadoReserva(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    public void setEstadoReserva(Reserva R,String estado) {
        if (listaEstadoReserva.contains(estado))
            throw new IllegalArgumentException("El estado no es correcto");
        R.setEstadoReserva(estado); }

    public void setPrecioTotal(Reserva R,float precioTotal) {
        if (precioTotal < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        setPrecioTotal(R,precioTotal); }




}
