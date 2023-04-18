package uca.core;

import java.time.LocalDate;
import java.util.Collection;

public class Cliente {
    private int idC;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String dni;
    private String fechaNacimiento;
    private int cantidadReservasRealizadas;
    private int multas;

    private String estado;

    public static final Cliente ClienteNulo = new Cliente(-1, "0", "0", "0", "0001-01-01", "0", "0", "0");

//    ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Cliente(int id, String nom, String ape, String telef, String fech, String dn, String ema, String est) {
        idC = id;
        nombre = nom;
        apellido = ape;
        telefono = telef;
        fechaNacimiento = fech;
        dni = dn;
        email = ema;
        cantidadReservasRealizadas = 0;
        multas = 0;
        estado = est;
    }
    public Cliente() {
        idC = 0;
        nombre = "";
        apellido = "";
        telefono = "";
        dni = "";
        email = "";
        cantidadReservasRealizadas = 0;
        multas = 0;
    }



    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧



    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y Setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public int getIdC() {return idC;}
    public String getNombre() {return nombre;}
    public String getApellido() { return apellido;}
    public String getEmail() {return email;}
    public String getDni() {return dni;}
    public String getTelefono() {return telefono;}
    public String getFechaNacimiento() {return fechaNacimiento.toString();}
    public int getCantidadReservasRealizadas() {return cantidadReservasRealizadas;}
    public int getMultas() {return multas;}
    public int Edad() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaparseada = LocalDate.parse(fechaNacimiento);
        int edad = hoy.getYear() - fechaparseada.getYear();
        if (hoy.getMonthValue() < fechaparseada.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == fechaparseada.getMonthValue() && hoy.getDayOfMonth() < fechaparseada.getDayOfMonth()) {
            edad--;
        }
        return edad;
    }




    void setNombre(String nombre){this.nombre = nombre;}

    void setApellido(String apellido) {this.apellido = apellido;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    void setEmail(String email){this.email = email;}

    void setDni(String dni){this.dni = dni;}

    void setFechaNacimiento(String fecha) {this.fechaNacimiento = fecha;}

    public void setNuevaMulta() {this.multas++; }

    public void setNuevaReservaRealizada() {this.cantidadReservasRealizadas++; }



    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    public String toString() {
        String bold = "\033[1m";
        String red = "\033[31m";
        String green = "\033[32m";
        String white = "\033[37m";
        String reset = "\033[0m";

        String output = String.format(red + "╔═%s═\n"
                        + red+"║ CLIENTE "+reset+"%d"+red+"\n"
                        + red+"║═%s═"+reset+"\n"
                        + red+"║ Nombre: %s%s %s%s\n"
                        + red+"║ Email: %s%s%s\n"
                        + red+"║ DNI: %s%s%s\n"
                        + red+"║ Fecha de nacimiento: %s%s%s\n"
                        + red+"║ Reservas realizadas: %s%d%s\n",
                "═".repeat(36+ String.valueOf(idC).length()),
                idC,
                "═".repeat(36 + String.valueOf(idC).length()),
                reset, nombre, apellido, red,
                reset, email, red,
                reset, dni, red,
                reset, fechaNacimiento, red,
                reset,cantidadReservasRealizadas, red);

        if (multas > 0) {
            output += String.format("║ Multas: %s%d%s\n", green, multas, reset);
        }
        output += String.format(red + "╚═%s═%s\n", "═".repeat(36 + String.valueOf(idC).length()), reset);

        String finalOutput = "";
        for (int i = 0; i < output.lines().toList().size(); i++){
            finalOutput += output.lines().toList().get(i);
            if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 1)

            //add a " " until every line has the same length
            for (int j = 0; j < 54 - output.lines().toList().get(i).length(); j++){
                finalOutput += " ";
            }
            if (i == 0)
            finalOutput += red+"╗"+reset+"\n";
            else if (i == output.lines().toList().size() - 1)
            finalOutput += red+"╝"+reset+"\n";
            else
            finalOutput += red+"║"+reset+"\n";}

        return finalOutput;


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

