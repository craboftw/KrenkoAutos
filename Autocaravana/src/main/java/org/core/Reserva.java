package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private static final ServicioReserva servidor = new ServicioReserva();
    private static final List<Reserva> listaReservas = new ArrayList<>();
    private static final int cantidadReservas = 0;
    private final int idR;
    private final Autocaravana caravana;
    private final Cliente cliente;
    private final LocalDate fechaIni;
    private final LocalDate fechaFin;
    private String estadoReserva;
    private float precioTotal;

    public Reserva(Autocaravana A, Cliente C, String fechI, String fechF) {

        //Comprobaciones de la caravana
        if (!servidor.comprobarCaravana(A)) {
            System.out.println("La caravana no está disponible");
            throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
        }
        caravana = A;

        //Comprobaciones del cliente
        if (!servidor.comprobarCliente(C)) {
            System.out.println("El cliente no cumple las condiciones");
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }
        cliente = C;
        fechaIni = LocalDate.parse(fechI);
        fechaFin = LocalDate.parse(fechF);
        if (!servidor.comprobarReserva(fechaIni, fechaFin, A, C)) {
            System.out.println("Las fechas no son compatibles");
            throw new IllegalArgumentException("Las fechas no son compatibles");
        }
        precioTotal = servidor.calculaPrecioTotal(A, C, fechaIni, fechaFin);
        estadoReserva = "pendiente";
        idR = siguienteReserva();
        C.NuevaReservasRealizadas();
        A.NuevaReservasRealizadas();
        listaReservas.add(this);
    }

    public Reserva(String cadena) {

        String[] campos = cadena.split(";");

        idR = Integer.parseInt(campos[0]);
        caravana = Autocaravana.buscarAutocaravana(Integer.parseInt(campos[1]));
        cliente = Cliente.buscarCliente(campos[2]);
        fechaIni = LocalDate.parse(campos[3]);
        fechaFin = LocalDate.parse(campos[4]);
        estadoReserva = campos[5];
        listaReservas.add(this);
        System.out.println("Error al leer el fichero");


    }

    public static List<Reserva> getListaReservas() {
        return listaReservas;
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
                    if (r.getFechaIni().isAfter(fecha) || r.getFechaFin().isBefore(fecha)) {
                        lista.add(r);
                    }
                }
                break;
        }
        return lista;

    }


    //añadir listaEstados de reserva

    public void asociarestado(String estado) {
        if (servidor.comprobarCambioEstado(this, estado)) {
            this.estadoReserva = estado;
        }
    }


    public int getIdR() {
        return idR;
    }

    public Autocaravana getAutocaravana() {
        return caravana;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }


    public int siguienteReserva() {
        return listaReservas.size();
    }

    public void checkOut() {
        switch (estadoReserva) {
            case "Pendiente":
                estadoReserva = "Finalizada";
                break;
            case "Cancelada":
                System.out.println("La reserva está cancelada");
                break;
            case "Finalizada":
                System.out.println("La reserva ya está finalizada");
                break;
            case "En curso":
                if (LocalDate.now().isAfter(fechaFin)) {
                    precioTotal += servidor.calcularMulta(this);
                }
                if (LocalDate.now().isBefore(fechaFin) & servidor.condicionesFinalizacion(this)) {
                    precioTotal += servidor.calcularTasaFinalizacion(this);
                }
                if (LocalDate.now().isBefore(fechaFin) & !servidor.condicionesFinalizacion(this)) {
                    System.out.println("No se puede finalizar la reserva");
                    break;
                } else {
                    estadoReserva = "Finalizada";
                }
                break;
        }
    }

    public String toString() {
        // usar String.format() para dar formato a la salida
        String output = String.format("╔═%s═╗\n"
                        + "║ RESERVA %d\n"
                        + "║══%s══║\n"
                        + "║ Caravana: %s\n"
                        + "║ Cliente: %s %s\n"
                        + "║ Fecha Inicio: %s\n"
                        + "║ Fecha Fin: %s\n"
                        + "║ Precio Total: %.2f\n"
                        + "║ Estado: %s\n"
                        + "╚═%s═╝\n",
                "═".repeat(13 + String.valueOf(idR).length()),
                idR,
                "═".repeat(13 + String.valueOf(idR).length() - 2),
                caravana.getMatricula(),
                cliente.getNombre(),
                cliente.getApellido(),
                fechaIni,
                fechaFin,
                precioTotal,
                estadoReserva,
                "═".repeat(13 + String.valueOf(idR).length()));
        return output;
    }


    public void eliminarReserva() {
        if (listaReservas.contains(this)) {
            listaReservas.remove(this);
        } else throw new IllegalArgumentException("La reserva ya esta eliminada");
    }
}