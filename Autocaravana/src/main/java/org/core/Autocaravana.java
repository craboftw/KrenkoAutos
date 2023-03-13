package org.core; //para que todos esten juntos no lo entiendo muy bien 


//A class to represent a Car booking system
public class Autocaravana {
    private int idA;
    private String modelo;
    private float precioPorDia;

    private static int cantidadCaravanas = 0;


    public Autocaravana (String mod, float precio){ //constructor
        idA = cantidadCaravanas++;
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

public void modificarAutocaravana(String mod, float precio){
        modelo = mod;
        precioPorDia = precio;
    }
    public String toString(){
        return "ID: " + idA + " Modelo: " + modelo + " Precio por dia: " + precioPorDia;
    }
}

