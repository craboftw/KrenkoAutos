package main.java.org.core;

import org.core.Caravana; //para poder usar las clases caravanas y cliente creo
import org.core.Cliente;

public class Reserva{
    private int idR;
    private Caravana caravanita;
    private Cliente clientito;
    private DateTime fechaIni;
    private DateTime fechaFin;

    public Reserva(int identificador){
        idR = identificador;
    }

    public int getIdR(){
        return idR;
    }

    public Caravana getCaravana(){
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