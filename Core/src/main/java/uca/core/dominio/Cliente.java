package uca.core.dominio;

import java.time.LocalDate;

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
    public String getEstado() {return estado;}

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




    public void setNombre(String nombre){this.nombre = nombre;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setEmail(String email){this.email = email;}

    public void setDni(String dni){this.dni = dni;}

    public void setFechaNacimiento(String fecha) {this.fechaNacimiento = fecha;}

    public void setNuevaMulta() {this.multas++; }

    public void setNuevaReservaRealizada() {this.cantidadReservasRealizadas++; }



    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧




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

