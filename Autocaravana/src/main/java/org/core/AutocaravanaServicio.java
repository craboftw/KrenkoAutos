package org.core;

import java.io.*;
import java.util.*;

public class AutocaravanaServicio implements AutocaravanaReglas, AutocaravanaRepositorios {




    public AutocaravanaServicio() {}


    public void altaAutocaravana(String mod, float precio, int plaz, String matr, int kilometraj){
        comprobarAutocaravana(mod, precio, plaz, matr, kilometraj);
        String estado = listaEstadosAutocaravana.get(0);
        int idA = listaAutocaravanas.size();
        Autocaravana A = new Autocaravana(idA,mod, precio, plaz, matr, kilometraj, estado);
        listaAutocaravanas.add(A);

    }
    public void comprobarAutocaravana(String mod, float precio, int plaz, String matr, int kilometraj){
        if (!AutocaravanaReglas.comprobarMatricula(matr)) throw new IllegalArgumentException("La matricula no es valida");
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

    public void setModelo(Autocaravana au, String mod) {
        if (mod.isEmpty())
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        au.setModelo(mod);
    }

    public void setPrecioPorDia(Autocaravana au, float precioPorDia) {
        if (precioPorDia <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        au.setPrecioPorDia(precioPorDia);
    }

    public void setKilometraje(Autocaravana au, int kilometraje) {
        if (kilometraje > au.getKilometraje()) {
            au.setKilometraje(kilometraje);
        } else {
            throw new IllegalArgumentException("El kilometraje no puede ser menor que el anterior");
        }
    }



}
