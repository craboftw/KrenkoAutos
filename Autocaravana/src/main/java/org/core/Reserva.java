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

    private String estado; //pendiente de pensar en curso, cancelada, finalizada

    private float precioTotal;

    public Reserva(int identificador) {
        idR = identificador;
    }



    public Reserva(int identificador, Autocaravana A, Cliente C, LocalDate fechI, LocalDate fechF) {
        idR = identificador;
        caravana = A;
        cliente = C;
        fechaIni = fechI;
        fechaFin = fechF;
        precioTotal = A.getPrecioPorDia()*fechF.getDayOfYear()-fechI.getDayOfYear();
        estado = "pendiente";
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

    public String toString() {
        return "Reserva: " + idR + " " + caravana + " " + cliente + " " + fechaIni + " " + fechaFin + " " + precioTotal + " €" + " " + estado;
    }

    
}