package org.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class ServicioCliente implements ReglasCliente, RepositorioCliente {
    private static final java.lang.String CLIENTES_FILE = "clientes.txt";
    private static final java.lang.String ESTADOSCLIENTE_FILE = "estadoscliente.txt";
    private static final java.util.List<java.lang.String> listaEstadosCliente = new java.util.ArrayList<>(java.util.Arrays.asList("Activo", "Inactivo", "Sancionado", "Baneado", "VIP"));


    public static java.util.List<java.lang.String> getListaEstadoClientes() {
        return listaEstadosCliente;
    }

    //@Override
    public static void cargarClientes() {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(CLIENTES_FILE))) {
            while (scanner.hasNextLine()) {
                new org.core.Cliente(scanner.nextLine());
            }
        } catch (java.io.FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }


    //@Override
    public static void cargarEstadosCliente() {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(ESTADOSCLIENTE_FILE))) {
            while (scanner.hasNextLine()) {
                listaEstadosCliente.add(scanner.nextLine());
            }
        } catch (java.io.FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    public static void guardarCliente(Collection<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CLIENTES_FILE))) {
            for (Cliente cliente : clientes) {
                pw.println(cliente.getIdC() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," +
                        cliente.getTelefono() + "," + cliente.getFechaNacimiento() + "," + cliente.getDni() + "," +
                        cliente.getEmail() + "," + cliente.getCantidadReservasRealizadas() + "," + cliente.getMultas());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void guardarEstadosCliente(Collection<String> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ESTADOSCLIENTE_FILE, false))) {
            for (String estado : lista) {
                pw.println(estado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}