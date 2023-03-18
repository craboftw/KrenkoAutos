package org.core;

public class Cliente{

    private int idC;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;

    private static int cantidadClientes = 0;


    public Cliente( String nom, String ape,String dn ,String ema){
    idC = siguienteCliente();
    nombre = nom;
    apellido = ape;
    dni = dn;
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

    public String getDni(){
        return dni;
    }

    public void modificarCliente(String nombre, String apellidos ,String dni, String email){
        this.nombre = nombre;
        this.apellido = apellidos;
        this.email = email;
        this.dni = dni;

    }

    public int siguienteCliente(){
        return cantidadClientes++;
    }


    public String toString() {
        return "Cliente{" +
                "idC=" + idC +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}

