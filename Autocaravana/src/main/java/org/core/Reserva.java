package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reserva{
    private final int idR;
    private final Autocaravana caravana;
    private final Cliente cliente;
    private final LocalDate fechaIni;
    private final LocalDate fechaFin;
    private String estadoReserva;

    private static final List<String> listaEstados = new ArrayList<>(Arrays.asList("Pendiente", "Cancelada", "Finalizada", "En curso"));

    private float precioTotal;

    private static final Servidor servidor = new Servidor();
    private static List<Reserva> listaReservas = new ArrayList<>();


    private static int cantidadReservas = 0;

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
        if (!servidor.comprobarFecha(fechaIni, fechaFin, A, C)) {
            System.out.println("Las fechas no son compatibles");
            throw new IllegalArgumentException("Las fechas no son compatibles");
        }
        precioTotal = servidor.calculaPrecioTotal(A, C, fechaIni, fechaFin);
        estadoReserva = "pendiente";
        idR = siguienteReserva();
        listaReservas.add(this);

    }

    public Reserva(String cadena) {
        String[] campos = cadena.split(";");
        idR = Integer.parseInt(campos[0]);
        caravana = Autocaravana.buscarAutocaravana(Integer.parseInt(campos[1]));
        cliente = Cliente.buscarCliente(campos[2]) ;
        fechaIni = LocalDate.parse(campos[3]);
        fechaFin = LocalDate.parse(campos[4]);
        estadoReserva = campos[5];
        listaReservas.add(this);

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

    public static List<String> getListaEstadoReservas() {
        return listaEstados;
    }

    //añadir listaEstados de reserva

    public void asociarestado(String estado) {
        if (listaEstados.contains(estado)) {
            this.estadoReserva = estado;
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    public static void nuevoestado(String estado) {
        if (!listaEstados.contains(estado) && !estado.isEmpty() ) {
            listaEstados.add(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
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






}