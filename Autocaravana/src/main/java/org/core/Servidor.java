package org.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Servidor implements Repositorio , ReglasNegocio, ReglasAutocaravana {

    Servidor() {
    }

    private final String AUTOCARAVANAS_FILE = "autocaravanas.txt";
    private final String CLIENTES_FILE = "clientes.txt";
    private final String RESERVAS_FILE = "reservas.txt";
    private final String ESTADOS_FILE = "estados.txt";
    private final String REGLASNEGOCIO_FILE = "reglasnegocio.txt";

    @Override
    public void cargarAutocaravanas() {
        try (Scanner scanner = new Scanner(new File(AUTOCARAVANAS_FILE))) {
            while (scanner.hasNextLine()) {
                new Autocaravana(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    @Override
    public void cargarClientes() {
        try (Scanner scanner = new Scanner(new File(CLIENTES_FILE))) {
            while (scanner.hasNextLine()) {
                new Cliente(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    @Override
    public void cargarReservas() {
        try (Scanner scanner = new Scanner(new File(RESERVAS_FILE))) {
            while (scanner.hasNextLine()) {
                new Reserva(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }

    }

    @Override
    public void cargarEstados() {
        try {
            Scanner scanner = new Scanner(new File(ESTADOS_FILE));
                String linea = scanner.nextLine().replaceAll("[\\[\\]]", "");

                List<String> estadosList = Arrays.asList(linea.split(","));
                for (String estado : estadosList)
                {
                    Reserva.nuevoestado(estado);
                }

        }catch (NoSuchElementException e) {
            // Archivo no encontrado, lista vacia
        } catch (FileNotFoundException e) {

        }
    }

    @Override
    public void cargarTodos() {
        cargarClientes();
        cargarAutocaravanas();
        cargarReservas();
        cargarEstados();
    }


    @Override
    public void guardarEstado(Collection<String> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ESTADOS_FILE, false))) {
            for (String estado : lista) {
                pw.println(estado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarAutocaravana(Collection<Autocaravana> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(AUTOCARAVANAS_FILE))) {
            for (Autocaravana autocaravana : lista) {
                pw.println(autocaravana.getIdA() + ";" + autocaravana.getModelo() + ";" +
                        autocaravana.getPrecioPorDia() + ";" + autocaravana.getMatricula() + ";" +
                        autocaravana.getPlazas() + ";" + autocaravana.getKilometraje() + ";" +
                        autocaravana.getEstado());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void guardarCliente(Collection<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CLIENTES_FILE))) {
            for (Cliente cliente : clientes) {
                pw.println(cliente.getIdC() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," +
                        cliente.getTelefono() + "," + cliente.getFechaNacimiento() + "," + cliente.getDni() + "," +
                        cliente.getEmail() + "," + cliente.getReservasRealizadas() + "," + cliente.getMultas());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarReservas(Collection<Reserva> R) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RESERVAS_FILE))) {
            for (Reserva reserva : R) {
                pw.println(reserva.getIdR() + ";" + reserva.getAutocaravana().getIdA() + ";" + reserva.getCliente().getDni()
                        + ";" + reserva.getFechaIni() + ";" + reserva.getFechaFin() + ";" + reserva.getEstadoReserva());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void guardar(List<Object> lista) {

    }
}


