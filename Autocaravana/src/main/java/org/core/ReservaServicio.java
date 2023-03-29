package org.core;

import java.time.LocalDate;
import java.util.*;


public class ReservaServicio{
    private static ReservaReglas reservaReglas;
    private static ReservaRepositorio reservaRepositorio;

    public void comprobarReserva(Autocaravana A, Cliente C, String fechI, String fechF)
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

       if ( reservaReglas.comprobarReserva(fechaIni, fechaFin, A, C)){
              throw new IllegalArgumentException("La reserva ya existe");
            }
        Reserva reserva = new Reserva(reservaRepositorio.getCantidadReserva(), A, C, fechaIni, fechaFin, reservaReglas.calculaPrecioTotal(A, C, fechaIni, fechaFin), reservaRepositorio.cargarEstadoDefault());
        reservaRepositorio.guardarReserva(reserva);
    }


    public static void nuevoestado(String estado) {
        if (!estado.isEmpty())//& !listaEstadoReserva.contains(estado))
        {
            //listaEstadoReserva.add(estado);
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

    public void eliminarReserva(Reserva reserva) {
        if (reservaRepositorio.existeReserva(reserva)) {
            reservaRepositorio.eliminarReserva(reserva);
        } else throw new IllegalArgumentException("La reserva ya esta eliminada");
    }

    public void cancelarReserva(Reserva reserva)
    {
        if (reserva.getEstadoReserva().equals("Cancelada"))
            throw new IllegalArgumentException("La reserva ya está cancelada");
        if (reserva.getEstadoReserva().equals("Finalizada"))
            throw new IllegalArgumentException("La reserva ya está finalizada");
        if(reservaReglas.condicionesCancelacion(reserva))
            reserva.setEstadoReserva("Cancelada");
            reserva.setPrecioTotal(reserva.getPrecioTotal() - reservaReglas.calcularTasaCancelacion(reserva));
    }

    public void setEstadoReserva(Reserva R,String estado) {
        if (reservaRepositorio.comprobarEstadoReserva(estado))
            throw new IllegalArgumentException("El estado no es correcto");
        R.setEstadoReserva(estado); }

    public void setPrecioTotal(Reserva R,float precioTotal) {
        if (precioTotal < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        setPrecioTotal(R,precioTotal); }

    public void modificarReservaEnCurso(Reserva reserva, String fechF) {
        LocalDate fechaIni;
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
            //Comprobaciones del cliente
            if (!reservaReglas.comprobarCliente(reserva.getCliente())) {
                throw new IllegalArgumentException("El cliente no cumple las condiciones.");
            }
            if (!reservaReglas.comprobarAutocaravana(reserva.getAutocaravana())) {
                throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones.");
            }
            if (reservaReglas.comprobarReserva(reserva.getFechaIni(), fechaFin, reserva.getAutocaravana(), reserva.getCliente())) {
                throw new IllegalArgumentException("La autocaravana no está disponible en las fechas seleccionadas.");
            }
            if(!reservaReglas.condicionesModificacion(reserva))
                throw new IllegalArgumentException("No se puede modificar la reserva.");
            reserva.setFechaFin(fechaFin);

            reserva.setPrecioTotal(reservaReglas.calculaPrecioTotal(reserva.getAutocaravana(), reserva.getCliente(), reserva.getFechaIni(), fechaFin) + reservaReglas.calcularTasaModificacion(reserva));

        }
    }

    public void modificarReserva(Reserva reserva, String fechI, String fechF){
        LocalDate fechaIni;
        LocalDate fechaFin;
        if (reserva.getEstadoReserva().equals("Cancelada")) {
            throw new IllegalArgumentException("La reserva está cancelada");
        }
        if (reserva.getEstadoReserva().equals("Finalizada")) {
            throw new IllegalArgumentException("La reserva está finalizada");
        }
        if (reserva.getEstadoReserva().equals("En curso")) {


            try {
                fechaIni = LocalDate.parse(fechI);
                fechaFin = LocalDate.parse(fechF);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error en el formato de las fechas");
            }
            //Comprobaciones del cliente
            if (!reservaReglas.comprobarCliente(reserva.getCliente())) {
                throw new IllegalArgumentException("El cliente no cumple las condiciones");
            }
            if (!reservaReglas.comprobarAutocaravana(reserva.getAutocaravana())) {
                throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
            }
            if (fechaIni.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha inicial no puede ser anterior a la fecha actual");
            }
            if (reservaReglas.comprobarReserva(fechaIni, fechaFin, reserva.getAutocaravana(), reserva.getCliente())) {
                throw new IllegalArgumentException("La autocaravana no está disponible en las fechas seleccionadas");
            }

            reserva.setFechaIni(fechaIni);
            reserva.setFechaFin(fechaFin);
            reserva.setPrecioTotal(reservaReglas.calculaPrecioTotal(reserva.getAutocaravana(), reserva.getCliente(), fechaIni, fechaFin));
        } else
            throw new IllegalArgumentException("La reserva no está en curso");
    }


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de estados ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

        void eliminarEstadoReserva(String estado)
        {
            if (!estado.isEmpty() & reservaRepositorio.comprobarEstadoReserva(estado)) {
                reservaRepositorio.eliminarEstadoReserva(estado);
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
        }

        void addEstadoReserva(String estado) {
            if (!estado.isEmpty() & !reservaRepositorio.comprobarEstadoReserva(estado)) {
                reservaRepositorio.guardarEstadoReserva(estado);
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
        }

        public static Collection<String> getListaEstadoReservas() {
            return reservaRepositorio.cargarEstadosReserva();
        }


    }
