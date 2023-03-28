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

    //    ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Reserva(Autocaravana A, Cliente C, String fechI, String fechF) {

        //Comprobaciones de la caravana
        if (!ReglasReserva.comprobarAutocaravana(A)) {
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
        if (!ReglasReserva.comprobarCliente(C)) {
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }
        if (!servidor.comprobarReserva(fechaIni, fechaFin, A, C)) {
            throw new IllegalArgumentException("Las fechas no son compatibles");
        }

        cliente = C;
        caravana = A;
        precioTotal = servidor.calculaPrecioTotal(A, C, fechaIni, fechaFin);
        estadoReserva = ServicioReserva.getListaEstadoReservas().get(0);
        idR = getCantidadReservas();
        C.setNuevaReservaRealizada();
        A.setNuevaReservaRealizada();
        listaReservas.add(this);
    }

    public Reserva(String cadena) {

        String[] campos = cadena.split(",");
        //
        idR = Integer.parseInt(campos[0]);
        caravana = Autocaravana.buscarAutocaravana(Integer.parseInt(campos[1]));
        cliente = Cliente.buscarCliente(Integer.parseInt(campos[2]));
        fechaIni = LocalDate.parse(campos[3]);
        fechaFin = LocalDate.parse(campos[4]);
        estadoReserva = campos[5];
        listaReservas.add(this);
    }

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

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

    public void eliminarReserva() {
        if (listaReservas.contains(this)) {
            listaReservas.remove(this);
        } else throw new IllegalArgumentException("La reserva ya esta eliminada");
    }

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y Setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public static List<Reserva> getListaReservas() {return listaReservas;}

    public int          getIdR() {return idR;}
    public Autocaravana getAutocaravana() {return caravana;}
    public Cliente      getCliente() {return cliente;}
    public LocalDate    getFechaIni() {return fechaIni;}
    public LocalDate    getFechaFin() { return fechaFin;}
    public float        getPrecioTotal() {return precioTotal;}
    public String       getEstadoReserva() {return estadoReserva;}
    public int          getCantidadReservas() {return listaReservas.size();}

    public void setEstadoReserva(String estado) {
        if (servidor.comprobarCambioEstado(this, estado)) {
            this.estadoReserva = estado;
        }
    }

    void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros metodos ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public void checkIn () {
        ServicioReserva.checkIn(this);
    }

    public void checkOut () {
        ServicioReserva.checkOut(this);
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

//              ▓▓▓▓▓▓▓▓▓▓▓▓                                              ████████
//  ▓▓▓▓      ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓                                              ██    ████
//  ▓▓      ▓▓▓▓    ▓▓██    ██                                                ██░░    ██
//  ▓▓▓▓    ▓▓▓▓    ████              ▓▓▓▓                                      ██░░    ██
//    ▓▓    ▓▓▓▓                        ▓▓▓▓                                      ██░░    ██
//    ▓▓▓▓  ▓▓████        ▓▓        ▓▓    ██                                      ██░░░░    ██
//      ▓▓▓▓  ██▓▓▓▓▓▓▓▓▓▓          ▓▓▓▓▓▓██                                        ██░░    ██
//▓▓      ████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓▓    ▓▓▓▓██                                        ██░░░░    ██
//▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓        ▓▓██                                        ██░░░░    ██
//  ▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓      ▓▓██                                        ██░░░░░░    ██
//          ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ████  ▓▓                                    ██░░░░░░    ██
//    ▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████    ██                                    ██░░░░░░    ██
//  ▓▓▓▓      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████    ████  ██                              ██░░░░░░░░    ██
//  ▓▓  ▓▓▓▓██████▓▓▓▓▓▓▓▓▓▓████████  ▓▓▓▓▓▓██    ████                            ██░░░░░░      ██
//    ▓▓▓▓        ████▓▓▓▓██████████▓▓            ██  ██                        ██░░░░░░░░    ██
//    ▓▓▓▓            ▓▓████████    ▓▓▓▓    ██    ██  ░░██                    ██░░░░░░░░░░    ██
//      ▓▓▓▓              ▓▓  ▓▓▓▓    ▓▓▓▓▓▓██      ██  ░░████            ████░░░░░░░░░░░░    ██
//                        ▓▓    ▓▓▓▓                ██    ░░░░████████████░░░░░░░░░░░░░░    ██
//                      ████      ██                  ██    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░    ██
//                  ▓▓████      ████                    ██    ░░░░░░░░░░░░░░░░░░░░░░      ██
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                        ██        ░░░░░░░░░░          ██
//Francisco López Guerrero                                  ████                    ████
//Miriam Armario Cantos                                         ████          ██████
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                                  ██████████
