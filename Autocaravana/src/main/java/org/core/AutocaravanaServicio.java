package org.core;

import java.io.*;
import java.util.*;

public class AutocaravanaServicio{

    private static AutocaravanaRepositorio autocaravanaRepositorio;
    private static AutocaravanaReglas autocaravanaReglas;



    public AutocaravanaServicio() {}


    public void altaAutocaravana(String mod, float precio, int plaz, String matr, int kilometraj){
        comprobarAutocaravana(mod, precio, plaz, matr, kilometraj);
        String estado = autocaravanaRepositorio.cargarEstadoDefault();
        int idA = autocaravanaRepositorio.getCantidadAutocaravanas();
        Autocaravana A = new Autocaravana(idA,mod, precio, plaz, matr, kilometraj, estado);
        autocaravanaRepositorio.guardarAutocaravana(A);

    }
    public void comprobarAutocaravana(String mod, float precio, int plaz, String matr, int kilometraj){
        if (!AutocaravanaReglas.comprobarMatricula(matr)) throw new IllegalArgumentException("La matricula no es valida");
        if (autocaravanaRepositorio.cargarAutocaravana().stream().anyMatch(a -> a.getMatricula().equals(matr))) throw new IllegalArgumentException("La matricula ya existe");
        if (precio <= 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (plaz <= 0) throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        if (kilometraj < 0) throw new IllegalArgumentException("El kilometraje no puede ser negativo");

    }
    public static Autocaravana buscarAutocaravana(int parseInt) {
        for (Autocaravana a : autocaravanaRepositorio.cargarAutocaravana()) {
            if (a.getIdA() == parseInt) {
                return a;
            }
        }
        return null;
    }

    public void eliminarAutocaravana(Autocaravana a) {
       if (autocaravanaRepositorio.existeAutocaravana(a))
            autocaravanaRepositorio.eliminarAutocaravana(a);
        else
            throw new IllegalArgumentException("La autocaravana ya esta eliminada");
    }

    public static Collection<Autocaravana> getListaAutocaravanas() {
        return autocaravanaRepositorio.cargarAutocaravana();
    }

    static Collection<String> getListaEstadoAutocaravana() {
        return autocaravanaRepositorio.cargarEstadosAutocaravana();
    }
    public boolean quedanCaravanas() {
        return autocaravanaRepositorio.getCantidadAutocaravanas("Estado","Disponible") > 0;
    }

    public static boolean comprobarEstadoAutocaravana(String estado) {
        return autocaravanaRepositorio.existeEstadoAutocaravana(estado);
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
