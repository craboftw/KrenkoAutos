package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private static final ServicioCliente servidor = new ServicioCliente();
    private final int idC;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private int reservasRealizadas;
    private int multas;

//    ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Cliente(int id, String nom, String ape, String telef, String fecha, String dn, String ema) {
        idC = id;
        nombre = nom;
        apellido = ape;
        telefono = telef;
        dni = dn;
        email = ema;
        reservasRealizadas = 0;
        multas = 0;
    }

    public Cliente(String cadena) {
        String[] campos = cadena.split(",");
        idC = Integer.parseInt(campos[0]);
        nombre = campos[1];
        apellido = campos[2];
        telefono = campos[3];
        fechaNacimiento = LocalDate.parse(campos[4]);
        dni = campos[5];
        email = campos[6];
        reservasRealizadas = Integer.parseInt(campos[7]);
        multas = Integer.parseInt(campos[8]);
    }
    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y Setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public int getIdC() {return idC;}
    public String getNombre() {return nombre;}
    public String getApellido() { return apellido;}
    public String getEmail() {return email;}
    public String getDni() {return dni;}
    public String getTelefono() {return telefono;}
    public LocalDate getFechaNacimiento() {return fechaNacimiento;}
    public int getCantidadReservasRealizadas() {return reservasRealizadas;}
    public int getMultas() {return multas;}
    public int getEdad() {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        if (hoy.getMonthValue() < fechaNacimiento.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == fechaNacimiento.getMonthValue() && hoy.getDayOfMonth() < fechaNacimiento.getDayOfMonth()) {
            edad--;
        }
        return edad;
    }

    public void setNombre(String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    public void setEmail(String email) {
        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        if (listaClientes.stream().anyMatch(c -> c.getEmail().equals(email) && c.getIdC() != getIdC()))
            throw new IllegalArgumentException("El email ya existe");
        this.email = email;
    }

    public void setDni(String dni) {
        if (dni.isEmpty() || (listaClientes.stream().anyMatch(c -> c.getDni().equals(dni) && c.getIdC() != getIdC())))
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        this.dni = dni;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        LocalDate nueva;
        try {
            nueva = LocalDate.parse(fechaNacimiento);
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es correcta");
        }
        //Calculo su edad
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - nueva.getYear();
        if (hoy.getMonthValue() < nueva.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == nueva.getMonthValue() && hoy.getDayOfMonth() < nueva.getDayOfMonth()) {
            edad--;
        }
        if (ReglasCliente.comprobarEdad(edad)) throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        this.fechaNacimiento = nueva;
    }

    public void setNuevaMulta() {this.multas++; }
    public void setNuevaReservaRealizada() {this.reservasRealizadas++; }

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public String toString() {
        String output = String.format("╔═%s═╗\n"
                        + "║ CLIENTE %d\n"
                        + "║═%s═║\n"
                        + "║ Nombre: %s %s\n"
                        + "║ Email: %s\n"
                        + "║ DNI: %s\n"
                        + "║ Fecha de nacimiento: %s\n"
                        + "║ Reservas realizadas: %d\n",
                "═".repeat(22 + String.valueOf(idC).length()),
                idC,
                "═".repeat(22 + String.valueOf(idC).length()),
                nombre,
                apellido,
                email,
                dni,
                fechaNacimiento,
                reservasRealizadas);
        if (multas > 0) {
            output += String.format("║ Multas: %d\n", multas);
        }
        output += String.format("╚═%s═╝\n", "═".repeat(22 + String.valueOf(idC).length()));
        return output;
    }
}


//              ▓▓▓▓▓▓▓▓▓▓▓▓                                              ████████
//  ▓▓▓▓      ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓                                              ██    ████
//  ▓▓      ▓▓▓▓    ▓▓██    ██                                                ██░░    ██
//  ▓▓▓▓    ▓▓▓▓    ████              ▓▓▓▓                                      ██░░    ██
//    ▓▓    ▓▓▓▓                        ▓▓▓▓                                      ██░░    ██
//    ▓▓▓▓  ▓▓████        ▓▓        ▓▓    ██                                      ██░░░░    ██
//      ▓▓▓▓  ██▓▓▓▓▓▓▓▓▓▓          ▓▓▓▓▓▓██                                        ██░░    ██
//▓▓      ████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓▓    ▓▓▓▓██                                        ██░░░░    ██
//▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓        ▓▓██                                        ██░░░░    ██
//  ▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓      ▓▓██                                        ██░░░░░░    ██
//          ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ████  ▓▓                                    ██░░░░░░    ██
//    ▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████    ██                                    ██░░░░░░    ██
//  ▓▓▓▓      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████    ████  ██                              ██░░░░░░░░    ██
//  ▓▓  ▓▓▓▓██████▓▓▓▓▓▓▓▓▓▓████████  ▓▓▓▓▓▓██    ████                            ██░░░░░░      ██
//    ▓▓▓▓        ████▓▓▓▓██████████▓▓            ██  ██                        ██░░░░░░░░    ██
//    ▓▓▓▓            ▓▓████████    ▓▓▓▓    ██    ██  ░░██                    ██░░░░░░░░░░    ██
//      ▓▓▓▓              ▓▓  ▓▓▓▓    ▓▓▓▓▓▓██      ██  ░░████            ████░░░░░░░░░░░░    ██
//                        ▓▓    ▓▓▓▓                ██    ░░░░████████████░░░░░░░░░░░░░░    ██
//                      ████      ██                  ██    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░    ██
//                  ▓▓████      ████                    ██    ░░░░░░░░░░░░░░░░░░░░░░      ██
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                        ██        ░░░░░░░░░░          ██
//Francisco López Guerrero                                  ████                    ████
//Miriam Armario Cantos                                         ████          ██████
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                                     ██████████

