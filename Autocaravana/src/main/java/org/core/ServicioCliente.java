package org.core;public class ServicioCliente{private final java.lang.String CLIENTES_FILE = "clientes.txt";private final java.lang.String ESTADOSCLIENTE_FILE = "estadosCliente.txt";private static final java.util.List<java.lang.String> listaEstadosCliente = new java.util.ArrayList<java.lang.String>(java.util.Arrays.asList("Activo", "Inactivo", "Sancionado", "Baneado", "VIP"));	public ServicioCliente()	{	}public static java.util.List<java.lang.String> getListaEstadoClientes() {
        return listaEstadosCliente;
    }@java.lang.Override
    public void cargarClientes() {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(CLIENTES_FILE))) {
            while (scanner.hasNextLine()) {
                new org.core.Cliente(scanner.nextLine());
            }
        } catch (java.io.FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }@java.lang.Override
    public void guardarCliente(java.util.Collection<org.core.Cliente> clientes) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(CLIENTES_FILE))) {
            for (org.core.Cliente cliente : clientes) {
                pw.println(cliente.getIdC() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," +
                        cliente.getTelefono() + "," + cliente.getFechaNacimiento() + "," + cliente.getDni() + "," +
                        cliente.getEmail() + "," + cliente.getReservasRealizadas() + "," + cliente.getMultas());
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }@java.lang.Override
    public boolean comprobarCliente(org.core.Cliente c) {
        return org.core.ReglasReserva.super.comprobarCliente(c);
    }}