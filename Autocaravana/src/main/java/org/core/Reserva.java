package main.java.org.core;

import org.core.Autocaravana; //para poder usar las clases Autocaravanas y cliente creo
import org.core.Cliente;

public class Reserva{
    private int idR;
    private Autocaravana caravanita;
    private Cliente clientito;
    private DateTime fechaIni;
    private DateTime fechaFin;

    public Reserva(int identificador){
        idR = identificador;
    }

    public Reserva(int identificador, Autocaravana A, Cliente C, DateTime fechI, DateTime fechF){
        idR = identificador;
        caravanita = A;
        clientito = C;
        fechaIni = fechI;
        fechaFin = fechF;
    }

    public int getIdR(){
        return idR;
    }

    public Autocaravana getAutocaravana(){
        return caravanita;
    }

    public Cliente getCliente(){
        return clientito;
    }

    public DateTime getFechaIni(){
        return fechaIni;
    }

    public DateTime getFechaFin(){
        return fechaFin;
    }

}