package org.core;

public class Cliente{

    private int idC;
    private String nombre;
    private String apellido;
    private String email;

    public Cliente(int identificador){ //constructor
        idC = identificador;
    }

    public int getIdC(){
        return idC;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getEmail(){
        return email;
    }
}