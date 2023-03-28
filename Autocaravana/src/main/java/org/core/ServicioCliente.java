package org.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServicioCliente implements ReglasCliente, RepositorioCliente {
    private static final java.lang.String CLIENTES_FILE = "clientes.txt";
    private static final java.lang.String ESTADOSCLIENTE_FILE = "estadoscliente.txt";
    private static final java.util.List<java.lang.String> listaEstadosCliente = new java.util.ArrayList<>(java.util.Arrays.asList("Activo", "Inactivo", "Sancionado", "Baneado", "VIP"));

    private static final List<Cliente> listaClientes = new ArrayList<>();

    public static java.util.List<java.lang.String> getListaEstadoClientes() {
        return listaEstadosCliente;
    }
    public static List<Cliente> getListaClientes() {return listaClientes;}


    public void altaCliente(String nom, String ape, String telef, String fecha, String dn, String ema){
        comprobarCliente(nom, ape, telef, fecha, dn, ema);
        String estado = listaEstadosCliente.get(0);
        int idC = listaClientes.size();
        Cliente c = new Cliente(idC, nom, ape, telef, fecha, dn, ema);
        listaClientes.add(c);


    }
    public static int getNumeroClientes() { return listaClientes.size(); }

    public static Cliente buscarCliente(int i) {
        return listaClientes.stream().filter(c -> c.getIdC() == i).findFirst().orElse(null);
    }

    public static Cliente buscarCliente(String dni) {
        return listaClientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().orElse(null);
    }

    public void eliminarCliente() {
        if (listaClientes.contains(this))
            listaClientes.remove(this);
        else
            throw new IllegalArgumentException("El cliente no existe");
    }
    public void comprobarCliente(String nom, String ape, String telef, String fecha, String dn, String ema){try {
        LocalDate.parse(fecha);
    } catch (Exception e) {
        throw new IllegalArgumentException("La fecha no es correcta");
    }
        if (nom.isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (ape.isEmpty()) throw new IllegalArgumentException("El apellido no puede estar vacio");
        if (dn.isEmpty()) throw new IllegalArgumentException("El DNI no puede estar vacio");
        if (listaClientes.stream().anyMatch(c -> c.getDni().equals(dn))) throw new IllegalArgumentException("El DNI ya existe");
        if (telef.isEmpty()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (listaClientes.stream().anyMatch(c -> c.getTelefono().equals(telef))) throw new IllegalArgumentException("El telefono ya existe");
        if (ema.isEmpty()) throw new IllegalArgumentException("El email no puede estar vacio");
        if (!(ema.contains("@") & ema.contains("."))) throw new IllegalArgumentException("El email no es correcto");
        if (listaClientes.stream().anyMatch(c -> c.getEmail().equals(ema))) throw new IllegalArgumentException("El email ya existe");
        if (!ReglasCliente.comprobarDNI(dn)) throw new IllegalArgumentException("El DNI no es correcto");

    }

    public void comprobarTelefono(String t){
        if (t.isEmpty() || (listaClientes.stream().anyMatch(c -> c.getTelefono().equals(t) && c.getIdC() != getIdC())))
            throw new IllegalArgumentException("El telefono no puede estar vacio");
    }


}