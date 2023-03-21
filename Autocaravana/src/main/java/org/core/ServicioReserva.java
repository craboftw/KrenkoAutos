package org.core;

import java.io.*;
import java.util.*;


public class ServicioReserva implements ReglasReserva,RepositorioReserva {
    private static List<String> listaEstadoReserva = new ArrayList<>(Arrays.asList("En curso", "Finalizada", "Cancelada", "Pendiente"));
    private static final String RESERVAS_FILE = "reservas.txt";
    private static final String ESTADOSRESERVAS_FILE = "estadosreserva.txt";

    @Override
    public void cargarReservas() {
        try {
            Scanner scanner = new Scanner(new File(RESERVAS_FILE));
            while (scanner.hasNextLine()) { //Idr no contenid en ninguna linea en campo[0] convertido a entero
                String linea = scanner.nextLine();
                boolean encontrado = false;
                for (Reserva R : Reserva.getListaReservas())
                {
                    if (Integer.parseInt(linea.split(";")[0]) == R.getIdR()) {
                        encontrado = true;
                    }
                }

                if (encontrado) {
                    new Reserva(linea);
                }
            }
        }
        catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    @Override
    public void cargarEstadosReserva() {
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
        }}


    @Override
    public void guardarReservas(Collection<Reserva> R) throws IOException {

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

        public static List<String> getListaEstadoReservas() {
            return listaEstadoReserva;
        }

        public static void nuevoestado(String estado) {
            if (!estado.isEmpty() & !listaEstadoReserva.contains(estado)) {
                listaEstadoReserva.add(estado);
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
        }

        @Override
        public static void eliminarEstadoReserva(String estado) {
            if (!estado.isEmpty() & listaEstadoReserva.contains(estado)) {
                listaEstadoReserva.remove(estado);
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
        }

        @Override
        public static void comprobarEstado(String estado) {
            if (!estado.isEmpty() & listaEstadoReserva.contains(estado)) {
                System.out.println("El estado es correcto");
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
        }

}
