package org.core;

import java.time.LocalDate;

public class Cliente {

    private int idC;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private int reservasRealizadas;
    private int multas;

    private static int cantidadClientes = 0;


    public Cliente(String nom, String ape, LocalDate fecha, String dn, String ema) {
        idC = siguienteCliente();
        nombre = nom;
        apellido = ape;
        fechaNacimiento = fecha;
        dni = dn;
        email = ema;
        reservasRealizadas = 0;
        multasRealizadas = 0;

    }

    public int getIdC() {
        return idC;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public int getedad() {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        if (hoy.getMonthValue() < fechaNacimiento.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == fechaNacimiento.getMonthValue() && hoy.getDayOfMonth() < fechaNacimiento.getDayOfMonth()) {
            edad--;
        }
        return edad;
    }

    public int getNumeroReservasRealizadas() {
        return reservasRealizadas;
    }

    public int getNumeroMultas() {
        return multasRealizadas;
    }

    public void modificarCliente(String nombre, String apellidos, String dni, String email) {
        this.nombre = nombre;
        this.apellido = apellidos;
        this.email = email;
        this.dni = dni;

    }

    public int siguienteCliente() {
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

    public int getedad() {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        if (hoy.getMonthValue() < fechaNacimiento.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == fechaNacimiento.getMonthValue() && hoy.getDayOfMonth() < fechaNacimiento.getDayOfMonth()) {
            edad--;
        }
        return edad;
    }
}
