package org.core; //para que todos esten juntos no lo entiendo muy bien 


//A class to represent a Car booking system
public class Autocaravana {
    private int idA;
    private String modelo;
    private float precioPorDia;

    public Autocaravana (int identificador, String mod, float precio){ //constructor
        idA = identificador;
        modelo = mod;
        precioPorDia = precio;
    }

    //observadores

    public int getIdA(){
        return idA;
    }

    public String getModelo(){
        return modelo;
    }

    public float getPrecioPorDia(){
        return precioPorDia;
    }

    //metodo para imprimir caravana por pantalla

    public String toString(){
        return "ID: " + idA + " Modelo: " + modelo + " Precio por dia: " + precioPorDia;
    }
}
