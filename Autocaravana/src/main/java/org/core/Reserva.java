package org.core;

import org.core.Autocaravana; //para poder usar las clases Autocaravanas y cliente creo
import org.core.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reserva {
    private int idR;
    private Autocaravana caravana;
    private Cliente cliente;
    private LocalDate fechaIni;
    private LocalDate fechaFin;

    private String estado;

    private ReglasNegocio RN;

    private static List<String> listaEstados = new ArrayList<String>(Arrays.asList("Pendiente", "Cancelada", "Finalizada", "En curso"));
    private float precioTotal;

    private static int cantidadReservas = 0;

    public Reserva(int identificador) {
        idR = identificador;
    }



    public Reserva(int identificador, Autocaravana A, Cliente C, LocalDate fechI, LocalDate fechF) {

        //Comprobaciones de la caravana
        if (RN.comprobarCaravana(A) == false) {
            System.out.println("La caravana no está disponible");
            throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
        }
        caravana = A;

        //Comprobaciones del cliente
        if (RN.comprobarCliente(C) == false) {
            System.out.println("El cliente no cumple las condiciones");
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }
        cliente = C;
        fechaIni = fechI;
        fechaFin = fechF;
        if (RN.comprobarFecha(fechI, fechF, A, C) == false) {
            System.out.println("Las fechas no son compatibles");
            throw new IllegalArgumentException("Las fechas no son compatibles");
        }

        precioTotal = RN.calculaPrecioTotal(A, C, fechI, fechF);
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