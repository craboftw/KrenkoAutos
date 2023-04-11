package uca.core;

import java.time.LocalDate;
import java.util.Collection;

public class ClienteServicio {
    public ClienteServicio(ClienteRepositorio clienteRepositorio, ClienteReglas clienteReglas) {
        this.clienteRepositorio = clienteRepositorio;
        this.clienteReglas = clienteReglas;
    }



    //crear-cliente miriam armario 666777666 2000-12-21 440999333R armaricon@gmail.com



    private final ClienteReglas clienteReglas;
    private final ClienteRepositorio clienteRepositorio;

    public void altaCliente(String nom, String ape, String telef, String fecha, String dn, String ema){
        comprobarCliente(nom, ape, telef, fecha, dn, ema);
        String estado = clienteRepositorio.cargarEstadoDefault();
        int idC = clienteRepositorio.getCantidadCliente();
        Cliente c = new Cliente(idC, nom, ape, telef, fecha, dn, ema);
        clienteRepositorio.guardarCliente(c);    }

    public int getNumeroClientes() { return clienteRepositorio.getCantidadCliente(); }

    public Cliente buscarCliente(int i) {
        return clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC() == i).findFirst().orElse(null);
    }
    
    public Cliente buscarCliente(String dni) {
        return clienteRepositorio.cargarCliente().stream().filter(c -> c.getDni().equals(dni)).findFirst().orElse(null);
    }

    public void eliminarCliente(Cliente c) {
        if (clienteRepositorio.existeCliente(c))
            clienteRepositorio.eliminarCliente(c);
        else
            throw new IllegalArgumentException("El cliente no existe");
    }
    public void comprobarCliente(String nom, String ape, String telef, String cumpleanos, String dn, String ema){
        LocalDate diaDeCum;
        try {
            diaDeCum = LocalDate.parse(cumpleanos);
    } catch (Exception e) {
        throw new IllegalArgumentException("La fecha no es correcta");
    }
        //Calculo su edad
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - diaDeCum.getYear();
        if (hoy.getMonthValue() < diaDeCum.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == diaDeCum.getMonthValue() && hoy.getDayOfMonth() < diaDeCum.getDayOfMonth()) {
            edad--; }

        if (clienteReglas.comprobarEdad(edad)) throw new IllegalArgumentException("La edad no es valida");
        if (nom.isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (ape.isEmpty()) throw new IllegalArgumentException("El apellido no puede estar vacio");
        if (dn.isEmpty()) throw new IllegalArgumentException("El DNI no puede estar vacio");
        if (telef.isEmpty()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (ema.isEmpty()) throw new IllegalArgumentException("El email no puede estar vacio");
        if (!(ema.contains("@") & ema.contains("."))) throw new IllegalArgumentException("El email no es correcto");
        if (!clienteRepositorio.cargarCliente().isEmpty()) {
            if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getDni().equals(dn)))
                throw new IllegalArgumentException("El DNI ya existe");
            if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getEmail().equals(ema)))
                throw new IllegalArgumentException("El email ya existe");
            if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(telef)))
                throw new IllegalArgumentException("El telefono ya existe");
        }
        if (!clienteReglas.comprobarDNI(dn)) throw new IllegalArgumentException("El DNI no es correcto");

    }
    //



    public void setNombre(Cliente cli, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        cli.setNombre(nombre);
    }

    public void setApellido(Cliente cli, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        cli.setApellido(apellido);
    }

    public void setEmail(Cliente cli, String email) {
        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getEmail().equals(email) && c.getIdC() != cli.getIdC()))
            throw new IllegalArgumentException("El email ya existe");
        cli.setEmail(email);
    }

    public void setDni(Cliente cli, String dni) {
        if (dni.isEmpty() || (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getDni().equals(dni) && c.getIdC() != cli.getIdC())))
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        cli.setDni(dni);
    }

    public void setFechaNacimiento(Cliente cli, String fechaNacimiento) {
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
        if (clienteReglas.comprobarEdad(edad)) throw new IllegalArgumentException("La edad no es valida");
        cli.setFechaNacimiento(nueva);
    }

    void eliminarEstadoCliente(String estado)
    {
        if (!estado.isEmpty() & clienteRepositorio.existeEstadoCliente(estado)) {
            clienteRepositorio.eliminarEstadoCliente(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    void addEstadocliente(String estado) {
        if (!estado.isEmpty() & !clienteRepositorio.existeEstadoCliente(estado)) {
            clienteRepositorio.guardarEstadoCliente(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    public  Collection<String> getListaEstadoclientes() {
        return clienteRepositorio.cargarEstadosCliente();
    }
    public  Collection<Cliente> getListaClientes() {
        return clienteRepositorio.cargarCliente();
    }

}
