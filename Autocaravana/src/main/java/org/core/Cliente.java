package org.core;

public class Cliente{

    private int idC;
    private String nombre;
    private String apellido;
    private String email;

public Cliente(int identificador, String nom, String ape, String ema){
    idC = identificador;
    nombre = nom;
    apellido = ape;
    email = ema;
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

    public String toString(){
        return "ID: " + idC + " Nombre: " + nombre + " Apellido: " + apellido + " Email: " + email;
    }
}