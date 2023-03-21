package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente{

    private final int idC;
    private String nombre;
    private String apellido;

    private String telefono;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private int reservasRealizadas;
    private int multas;

    private static final ServicioCliente servidor = new ServicioCliente();

    private static final List<Cliente> listaClientes = new ArrayList<>();


    public Cliente(String nom, String ape,String telef, String fecha, String dn, String ema) {
        idC = siguienteCliente();
        if (nom.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        nombre = nom;
        if (ape.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        apellido = ape;
        try {
            fechaNacimiento = LocalDate.parse(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es correcta");
        }

        if (dn.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
        if (listaClientes.stream().anyMatch(c -> c.getDni().equals(dn))) {
            throw new IllegalArgumentException("El DNI ya existe");
        }
        dni = dn;

        try {
            if (telef.isEmpty()) throw new IllegalArgumentException("El telefono no puede estar vacio");
            if (listaClientes.stream().anyMatch(c -> c.getTelefono().equals(telef))) {
                throw new IllegalArgumentException("El telefono ya existe");
            }
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("El telefono no es correcto");
        }

        telefono = telef;
        email = ema;
        if (dn.isEmpty()) {
            throw new IllegalArgumentException("El DNI no es correcto");
        }
        reservasRealizadas = 0;
        multas = 0;
        listaClientes.add(this);

    }

    public Cliente(String campo) {
        String[] campos = campo.split(",");
        idC = Integer.parseInt(campos[0]);
        nombre = campos[1];
        apellido = campos[2];
        telefono = campos[3];
        fechaNacimiento = LocalDate.parse(campos[4]);
        dni = campos[5];
        email = campos[6];
        reservasRealizadas = Integer.parseInt(campos[7]);
        multas = Integer.parseInt(campos[8]);
        listaClientes.add(this);
    }

    public static Cliente buscarCliente(int i) {
        return listaClientes.stream().filter(c -> c.getIdC() == i).findFirst().orElse(null);
    }

    public static Cliente buscarCliente(String dni) {
        return listaClientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().orElse(null);
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

    public int getNumeroReservasRealizadas() {
        return reservasRealizadas;
    }

    public int getNumeroMultas() {
        return multas;
    }

    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }



    public void  modificarCliente(String nom, String ape, String telef, String fecha, String dn, String ema){
        if (nom.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        nombre = nom;
        if (ape.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        apellido = ape;
        try {
            fechaNacimiento = LocalDate.parse(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es correcta");
        }

        if (dn.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
        if (listaClientes.stream().anyMatch(c -> c.getDni().equals(dn) && c.getIdC() != getIdC())) {
            throw new IllegalArgumentException("El DNI ya existe");
        }

        dni = dn;

        try {
            if (telef.isEmpty()) throw new IllegalArgumentException("El telefono no puede estar vacio");
            if (listaClientes.stream().anyMatch(c -> c.getTelefono().equals(telef) && c.getIdC() != getIdC())) {
                throw new IllegalArgumentException("El telefono ya existe");
            }
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("El telefono no es correcto");
        }

        telefono = telef;
        email = ema;
        if (dn.isEmpty()) {
            throw new IllegalArgumentException("El DNI no es correcto");
        }
    }

    public int getCantidadCliente() {
        return listaClientes.size();
    }


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


    public String getApellidos() {  // TODO Auto-generated method stub
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getReservasRealizadas() {
        return reservasRealizadas;
    }

    public int getMultas() {
        return multas;
    }
}
