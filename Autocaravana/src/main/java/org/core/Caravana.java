package org.example;

//A class to represent a Car booking system
public class Caravana {
    private int id;
    private String modelo;
    private float precioPorDia;

    public Caravana (int identificador){ //constructor
        id = identificador;
    }

    //observadores

    public String getModelo(){
        return modelo;
    }

    public float getPrecioPorDia(){
        return precioPorDia;
    }

}
