package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reserva implements ReglasNegocio{
    private int idR;
    private Autocaravana caravana;
    private Cliente cliente;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private String estado;

    private static List<String> listaEstados = new ArrayList<String>(Arrays.asList("Pendiente", "Cancelada", "Finalizada", "En curso"));
    private float precioTotal;

    private static int cantidadReservas = 0;

    public Reserva(int identificador, Autocaravana A, Cliente C, String fechI, String fechF) {

        //Comprobaciones de la caravana
        if (comprobarCaravana(A) == false) {
            System.out.println("La caravana no está disponible");
            throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
        }
        caravana = A;

        //Comprobaciones del cliente
        if (comprobarCliente(C) == false) {
            System.out.println("El cliente no cumple las condiciones");
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }
        cliente = C;
        fechaIni = LocalDate.parse(fechI);
        fechaFin = LocalDate.parse(fechF);
        if (comprobarFecha(fechaIni, fechaFin, A, C) == false) {
            System.out.println("Las fechas no son compatibles");
            throw new IllegalArgumentException("Las fechas no son compatibles");
        }
        precioTotal = calculaPrecioTotal(A, C, fechaIni, fechaFin);
        estado = "pendiente";
        idR = siguienteReserva();

    }

    //añadir listaEstados de reserva

    public void asociarestado(String estado) {
            this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int siguienteReserva() {
        return cantidadReservas++;
    }

    public String toString() {
        return "Reserva: " + idR + " " + caravana + " " + cliente + " " + fechaIni + " " + fechaFin + " " + precioTotal + " €" + " " + estado;
    }

    
}