package org.core;

import java.io.*;
import java.util.*;

public class ServicioAutocaravana implements ReglasAutocaravana, RepositorioAutocaravana {

    private static final String AUTOCARAVANAS_FILE = "autocaravanas.txt";
    private static final String ESTADOSAUTOCARAVANA_FILE = "estadosautocaravana.txt";
    private static final List<String> listaEstadosAutocaravana = new ArrayList<>(Arrays.asList("Operativo", "Sucio", "Averiado", "Fuera de Servicio"));


    public ServicioAutocaravana() {
    }

    //@Override
    public static void cargarAutocaravana() {
        try (Scanner scanner = new Scanner(new File(AUTOCARAVANAS_FILE))) {
            while (scanner.hasNextLine()) {
                new Autocaravana(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    //@Override
    public static void cargarEstadosAutocaravana() {
        try (Scanner scanner = new Scanner(new File(ESTADOSAUTOCARAVANA_FILE))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                boolean encontrado = false;
                for (String estado : listaEstadosAutocaravana) {
                    if (linea.equals(estado)) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    listaEstadosAutocaravana.add(linea);
                }
            }
        } catch (FileNotFoundException e) {
            // Archivo no encontrado, lista vacia
        }
    }

    public static List<String> getListaEstadoAutocaravana() {
        return listaEstadosAutocaravana;
    }

    public static boolean comprobarEstadoAutocaravana(String estado) {
        for (String estadoAutocaravana : listaEstadosAutocaravana) {
            if (estadoAutocaravana.equals(estado)) {
                return true;
            }
        }
        return false;
    }

    //@Override
    public void guardarEstadoAutocaravana(Collection<String> listaEstados) {
        try (PrintWriter pw = new PrintWriter(new File(ESTADOSAUTOCARAVANA_FILE))) {
            for (String estado : listaEstados) {
                pw.println(estado);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //@Override
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


}
