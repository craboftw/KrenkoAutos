package org.core;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class ServicioReserva implements ReglasReserva  {

    private static List<Reserva> listaReservas = new ArrayList<>();
    private static final List<String> listaEstadoReserva = new ArrayList<>(Arrays.asList("Pendiente", "Finalizada", "Cancelada", "En curso"));

    public void comprobarReserva(Autocaravana A, Cliente C, String fechI, String fechF)
    {
        if (!ReglasReserva.comprobarAutocaravana(A)) {
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
        if (!ReglasReserva.comprobarCliente(C)) {
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
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    //@Override
    public static void eliminarEstadoReserva(String estado) {
        if (!estado.isEmpty() & listaEstadoReserva.contains(estado)) {
            listaEstadoReserva.remove(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }


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
                    reserva.setPrecioTotal(reserva.getPrecioTotal() + ReglasReserva.calcularMulta(reserva));;
                }
                else
                if (LocalDate.now().isBefore(reserva.getFechaFin()) & ReglasReserva.condicionesFinalizacion(reserva)) {
                    reserva.setPrecioTotal(reserva.getPrecioTotal() - ReglasReserva.calcularTasaFinalizacion(reserva));;
                }
                else //POR AQUI
                if (LocalDate.now().isBefore(reserva.getFechaFin()) & !ReglasReserva.condicionesFinalizacion(reserva)) {
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


    public static Reserva buscarReserva(int i) {
        for (Reserva r : listaReservas) {
            if (r.getIdR() == i) {
                return r;
            }
        }
        return null;
    }

    public static List<Reserva> buscarReserva(String info, String tipo) {
        List<Reserva> lista = new ArrayList<>();
        switch (tipo) {
            case "cliente":
                for (Reserva r : listaReservas) {
                    if (r.getCliente().getDni().equals(info)) {
                        lista.add(r);
                    }
                }
                break;
            case "matricula":
                for (Reserva r : listaReservas) {
                    if (r.getAutocaravana().getMatricula().equals(info)) {
                        lista.add(r);
                    }
                }
                break;
            case "estado":
                for (Reserva r : listaReservas) {
                    if (r.getEstadoReserva().equals(info)) {
                        lista.add(r);
                    }
                }
                break;
            case "fecha":
                for (Reserva r : listaReservas) {
                    LocalDate fecha = LocalDate.parse(info);
                    if (r.getFechaIni().isAfter(fecha)  || r.getFechaIni().isEqual(fecha )|| r.getFechaFin().isBefore(fecha) || r.getFechaFin().isEqual(fecha)){
                        lista.add(r);
                    }
                }
                break;
        }
        return lista;
    }

    public int          getCantidadReservas() {return listaReservas.size();}


    public void eliminarReserva() {
        if (listaReservas.contains(this)) {
            listaReservas.remove(this);
        } else throw new IllegalArgumentException("La reserva ya esta eliminada");
    }



}
