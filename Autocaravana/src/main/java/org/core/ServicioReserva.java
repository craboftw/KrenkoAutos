package org.core;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class ServicioReserva implements ReglasReserva, RepositorioReserva {
    private static final List<String> listaEstadoReserva = new ArrayList<>(Arrays.asList("Pendiente", "Finalizada", "Cancelada", "En curso"));
    private static final String RESERVAS_FILE = "reservas.txt";
    private static final String ESTADOSRESERVAS_FILE = "estadosreserva.txt";

    //@Override
    public static void cargarReservas() {
        try {
            Scanner scanner = new Scanner(new File(RESERVAS_FILE));
            while (scanner.hasNextLine()) { //Idr no contenid en ninguna linea en campo[0] convertido a entero
                String linea = scanner.nextLine();
                boolean encontrado = true;
                for (Reserva R : Reserva.getListaReservas()) {
                    if (Integer.parseInt(linea.split(";")[0]) == R.getIdR()) {
                        encontrado = false;
                    }
                }

                if (encontrado) {
                    new Reserva(linea);
                }
            }
        } catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    //@Override
    public static void cargarEstadosReserva() {
        try {
            Scanner scanner = new Scanner(new File(ESTADOSRESERVAS_FILE));
            while (scanner.hasNextLine()) {

                String linea = scanner.nextLine();
                System.out.println(linea);
                if (!listaEstadoReserva.contains(linea))
                    listaEstadoReserva.add(linea);
            }
            System.out.println(listaEstadoReserva);

        } catch (NoSuchElementException | FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
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

    //@Override
    public static boolean comprobarEstadoReserva(String estado) {
        if (!estado.isEmpty() & listaEstadoReserva.contains(estado)) {
            System.out.println("El estado es correcto");
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
        return true;
    }

    @Override
    public void guardarReservas(Collection<Reserva> R) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(RESERVAS_FILE, true))) {
            //Borro todo su contenido
            pw.print("");
            for (Reserva reserva : R) {
                pw.println(reserva.getIdR() + ";" + reserva.getAutocaravana().getIdA() + ";" + reserva.getCliente().getDni()
                        + ";" + reserva.getFechaIni() + ";" + reserva.getFechaFin() + ";" + reserva.getEstadoReserva());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarEstadosReserva(Collection<String> listaEstados) {
        try (PrintWriter pw = new PrintWriter(new File(ESTADOSRESERVAS_FILE))) {
            for (String estado : listaEstados) {
                pw.println(estado);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
                    reserva.setPrecioTotal(reserva.getPrecioTotal() + ReglasReserva.calcularTasaFinalizacion(reserva));;
                }
                else
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

    public static void checkIn(Reserva reserva) {
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
                if (LocalDate.now().isAfter(reserva.getFechaIni()) || LocalDate.now().isEqual(reserva.getFechaIni()) & LocalDate.now().isBefore(reserva.getFechaFin())) {
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

}
