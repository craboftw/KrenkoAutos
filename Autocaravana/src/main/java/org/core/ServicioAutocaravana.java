package org.core;

import java.io.*;
import java.util.*;

public class ServicioAutocaravana implements ReglasAutocaravana, RepositorioAutocaravana {

    private static final String AUTOCARAVANAS_FILE = "autocaravanas.txt";
    private static final String ESTADOSAUTOCARAVANA_FILE = "estadosautocaravana.txt";
    private static final List<Autocaravana> listaAutocaravanas = new ArrayList<>();
    private static final List<String> listaEstadosAutocaravana = new ArrayList<>(Arrays.asList("Disponible", "Sucio", "Averiado", "Fuera de Servicio"));

    public ServicioAutocaravana() {}


    public void altaAutocaravana(String mod, float precio, int plaz, String matr, int kilometraj){
        comprobarAutocaravana(mod, precio, plaz, matr, kilometraj);
        String estado = listaEstadosAutocaravana.get(0);
        int idA = listaAutocaravanas.size();
        Autocaravana A = new Autocaravana(idA,mod, precio, plaz, matr, kilometraj, estado);
        listaAutocaravanas.add(A);

    }
    public void comprobarAutocaravana(String mod, float precio, int plaz, String matr, int kilometraj){
        if (!ReglasAutocaravana.comprobarMatricula(matr)) throw new IllegalArgumentException("La matricula no es valida");
        if (listaAutocaravanas.stream().anyMatch(a -> a.getMatricula().equals(matr))) throw new IllegalArgumentException("La matricula ya existe");
        if (precio <= 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (plaz <= 0) throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        if (kilometraj < 0) throw new IllegalArgumentException("El kilometraje no puede ser negativo");

    }
    public static Autocaravana buscarAutocaravana(int parseInt) {
        for (Autocaravana a : listaAutocaravanas) {
            if (a.getIdA() == parseInt) {
                return a;
            }
        }
        return null;
    }

    public void eliminarAutocaravana() {
        if (listaAutocaravanas.contains(this))
            listaAutocaravanas.remove(this);
        else
            throw new IllegalArgumentException("La autocaravana ya esta eliminada");
    }

    public static List<Autocaravana> getListaAutocaravanas() {
        return listaAutocaravanas;
    }

    public static int getCantidadCaravanas() {
        return listaAutocaravanas.size();
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

    static List<String> getListaEstadoAutocaravana() {
        return listaEstadosAutocaravana;
    }
    public boolean quedanCaravanas() {
        return getCantidadCaravanas() > 0;
    }


    public static boolean comprobarEstadoAutocaravana(String estado) {
        return listaEstadosAutocaravana.contains(estado);
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
                        autocaravana.getEstado() + ";" + autocaravana.getVecesReservada() + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
