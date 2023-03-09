package main.java.org.core; //para que todos esten juntos no lo entiendo muy bien 

//A class to represent a Car booking system
public class Autocaravana {
    private int idA;
    private String modelo;
    private float precioPorDia;

    public Autocaravana (int identificador){ //constructor
        idA = identificador;
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

}
