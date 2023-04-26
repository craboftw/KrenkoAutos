package uca.core.dominio;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Autocaravana {

    private int vecesReservada;
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

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    //Por culpa de loombok no hace falta ponerlos... pero los dejo por los recuerdos 🦀

    public void setNuevaReservaRealizada() {vecesReservada++;}

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public String toString() {
           return "ID: " + idA + " | Modelo: " + modelo + " | Precio por día: " + precioPorDia + " | Plazas: " + plazas + " | Matrícula: " + matricula + " | Kilometraje: " + kilometraje + " | Estado: " + estadoA + " | Veces reservada: " + vecesReservada;
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
