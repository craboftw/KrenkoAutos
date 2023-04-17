package uca.core;


import java.math.BigDecimal;
import java.util.Random;

public class Autocaravana {

    private int vecesReservada = 0;
    private final int idA;
    private final String matricula;
    private int plazas;
    private String modelo;
    private BigDecimal precioPorDia;
    private int kilometraje;
    private String estadoA = "Disponible";


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Autocaravana(int id, String mod, BigDecimal precio, int plaz, String matr, int kilometraj, String estado) {

        idA = id;
        modelo = mod;
        precioPorDia = precio;
        plazas = plaz;
        matricula = matr;
        kilometraje = kilometraj;
        estadoA = estado;
        vecesReservada = 0;
    }

    public Autocaravana() {

        idA = 0;
        modelo = "";
        precioPorDia = BigDecimal.valueOf(-1);
        plazas = 0;
        matricula = "";
        kilometraje = -1;
        estadoA = "";
        vecesReservada = -1;
    }

    /*public Autocaravana(String cadena){    //Constructor creado para la lectura de ficheros

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
    }*/

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧





    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public int        getIdA() {
        return idA;
    }
    public String     getModelo() {
        return modelo;
    }
    public BigDecimal      getPrecioPorDia() {
        return precioPorDia;
    }
    public String     getMatricula() {
        return matricula;
    }
    public int        getKilometraje() {
        return kilometraje;
    }
    public String     getEstado() {
        return estadoA;
    }
    public int        getPlazas() {
        return plazas;
    }
    public int        getVecesReservada() {return vecesReservada;}

    void setModelo(String mod) {this.modelo = mod;}

    void setPrecioPorDia(BigDecimal precioPorDia) {this.precioPorDia = precioPorDia;}

    void setKilometraje(int kilometraje){this.kilometraje = kilometraje;}

    public void setPlazas(int plazas) {
        this.plazas= plazas;
    }
    void setEstado(String Estado) {
            estadoA = Estado;
    }

    public void setNuevaReservaRealizada() {vecesReservada++;}

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public String toString() {
        String output = String.format("╔═══════════════════╗\n"
                        + "║ AUTOCARAVANA %d\n"
                        + "║═══════════════════║\n"
                        + "║ Modelo: %s\n"
                        + "║ Precio por día: %.2f €\n"
                        + "║ Matrícula: %s\n"
                        + "║ Kilometraje: %d km\n"
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
