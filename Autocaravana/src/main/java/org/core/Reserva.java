package org.core;

import org.core.Autocaravana; //para poder usar las clases Autocaravanas y cliente creo
import org.core.Cliente;

public class Reserva{
    private int idR;
    private Autocaravana caravana;
    private Cliente cliente;
    private DateTime fechaIni;
    private DateTime fechaFin;

    public Reserva(int identificador){
        idR = identificador;
    }

    public Reserva(int identificador, Autocaravana A, Cliente C, DateTime fechI, DateTime fechF){
        idR = identificador;
        caravana = A;
        cliente = C;
        fechaIni = fechI;
        fechaFin = fechF;
    }

    public int getIdR(){
        return idR;
    }

    public Autocaravana getAutocaravana(){
        return caravana;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public DateTime getFechaIni(){
        return fechaIni;
    }

    public DateTime getFechaFin(){
        return fechaFin;
    }

}