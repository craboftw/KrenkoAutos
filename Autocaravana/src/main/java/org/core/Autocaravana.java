package org.core;


import java.util.ArrayList;
import java.util.List;

public class Autocaravana {
    private static final List<Autocaravana> listaAutocaravanas = new ArrayList<>();

    //public static ServicioAutocaravana servidor = new ServicioAutocaravana();
    private int vecesReservada = 0;
    private final int idA;
    private final String matricula;
    private final int plazas;
    private String modelo;
    private float precioPorDia;
    private int kilometraje;
    private String estado = "Disponible";


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Autocaravana(String mod, float precio, int plaz, String matr, int kilometraj) {

        if (!ReglasAutocaravana.comprobarMatricula(matr)) throw new IllegalArgumentException("La matricula no es valida");
        if (listaAutocaravanas.stream().anyMatch(a -> a.getMatricula().equals(matr))) throw new IllegalArgumentException("La matricula ya existe");
        if (precio <= 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (plaz <= 0) throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        if (kilometraj < 0) throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        idA = getCantidadCaravanas();
        modelo = mod;
        precioPorDia = precio;
        plazas = plaz;
        matricula = matr;
        this.kilometraje = kilometraj;
        this.estado = ServicioAutocaravana.getListaEstadoAutocaravana().get(0);
        listaAutocaravanas.add(this);
    }

    public Autocaravana(String cadena){    //Constructor creado para la lectura de ficheros

        String[] campos = cadena.split(",");
        idA = Integer.parseInt(campos[0]);
        modelo = campos[1];
        precioPorDia = Float.parseFloat(campos[2]);
        matricula = campos[3];
        plazas = Integer.parseInt(campos[4]);
        kilometraje = Integer.parseInt(campos[5]);
        estado = campos[6];
        vecesReservada = Integer.parseInt(campos[7]);
        listaAutocaravanas.add(this);
    }

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public static List<Autocaravana> getListaAutocaravanas() {
        return listaAutocaravanas;
    }

    public static Autocaravana buscarAutocaravana(int parseInt) {
        for (Autocaravana a : Autocaravana.getListaAutocaravanas()) {
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

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public int        getIdA() {
        return idA;
    }
    public String     getModelo() {
        return modelo;
    }
    public float      getPrecioPorDia() {
        return precioPorDia;
    }
    public String     getMatricula() {
        return matricula;
    }
    public int        getKilometraje() {
        return kilometraje;
    }
    public String     getEstado() {
        return estado;
    }
    public int        getPlazas() {
        return plazas;
    }
    public int getVecesReservada() {return vecesReservada;}
    public static int getCantidadCaravanas() {
        return listaAutocaravanas.size();
    }

    public void setModelo(String mod) {
        if (mod.isEmpty())
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        modelo = mod;
    }

    public void setPrecioPorDia(float precioPorDia) {
        if (precioPorDia <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precioPorDia = precioPorDia;
    }

    public void setKilometraje(int kilometraje) {
        if (kilometraje > this.kilometraje) {
            this.kilometraje = kilometraje;
        } else {
            throw new IllegalArgumentException("El kilometraje no puede ser menor que el anterior");
        }
    }
    public void setEstado(String Estado) {
        if (ServicioAutocaravana.comprobarEstadoAutocaravana(Estado))
            estado = Estado;
    }

    void setNuevaReservaRealizada() {
        vecesReservada++;
    }

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public boolean quedanCaravanas() {
        return getCantidadCaravanas() > 0;
    }

    public String toString() {
        String output = String.format("╔═══════════════════╗\n"
                        + "║ AUTOCARAVANA %d\n"
                        + "║═══════════════════║\n"
                        + "║ Modelo: %s\n"
                        + "║ Precio por día: %.2f\n"
                        + "║ Matrícula: %s\n"
                        + "║ Kilometraje: %d\n"
                        + "╚═══════════════════╝\n",
                idA,
                modelo,
                precioPorDia,
                matricula,
                kilometraje);
        return output;
    }

}

//              ▓▓▓▓▓▓▓▓▓▓▓▓                                              ████████
//  ▓▓▓▓      ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓                                              ██    ████
//  ▓▓      ▓▓▓▓    ▓▓██    ██                                                ██░░    ██
//  ▓▓▓▓    ▓▓▓▓    ████              ▓▓▓▓                                      ██░░    ██
//    ▓▓    ▓▓▓▓                        ▓▓▓▓                                      ██░░    ██
//    ▓▓▓▓  ▓▓████        ▓▓        ▓▓    ██                                      ██░░░░    ██
//      ▓▓▓▓  ██▓▓▓▓▓▓▓▓▓▓          ▓▓▓▓▓▓██                                        ██░░    ██
//▓▓      ████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓▓    ▓▓▓▓██                                        ██░░░░    ██
//▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓        ▓▓██                                        ██░░░░    ██
//  ▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓      ▓▓██                                        ██░░░░░░    ██
//          ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ████  ▓▓                                    ██░░░░░░    ██
//    ▓▓██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████    ██                                    ██░░░░░░    ██
//  ▓▓▓▓      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████    ████  ██                              ██░░░░░░░░    ██
//  ▓▓  ▓▓▓▓██████▓▓▓▓▓▓▓▓▓▓████████  ▓▓▓▓▓▓██    ████                            ██░░░░░░      ██
//    ▓▓▓▓        ████▓▓▓▓██████████▓▓            ██  ██                        ██░░░░░░░░    ██
//    ▓▓▓▓            ▓▓████████    ▓▓▓▓    ██    ██  ░░██                    ██░░░░░░░░░░    ██
//      ▓▓▓▓              ▓▓  ▓▓▓▓    ▓▓▓▓▓▓██      ██  ░░████            ████░░░░░░░░░░░░    ██
//                        ▓▓    ▓▓▓▓                ██    ░░░░████████████░░░░░░░░░░░░░░    ██
//                      ████      ██                  ██    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░    ██
//                  ▓▓████      ████                    ██    ░░░░░░░░░░░░░░░░░░░░░░      ██
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                        ██        ░░░░░░░░░░          ██
//Francisco López Guerrero                                  ████                    ████
//Miriam Armario Cantos                                         ████          ██████
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                                    ██████████
