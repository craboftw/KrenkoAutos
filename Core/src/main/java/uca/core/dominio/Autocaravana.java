package uca.core.dominio;


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

    public void setModelo(String mod) {this.modelo = mod;}

    public void setPrecioPorDia(BigDecimal precioPorDia) {this.precioPorDia = precioPorDia;}

    public void setKilometraje(int kilometraje){this.kilometraje = kilometraje;}

    public void setPlazas(int plazas) {
        this.plazas= plazas;
    }
    public void setEstado(String Estado) {
            estadoA = Estado;
    }

    public void setNuevaReservaRealizada() {vecesReservada++;}

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    public String toString() {
        String bold = "\033[1m";
        String red = "\033[31m";
        String green = "\033[32m";
        String yellow = "\033[33m";
        String white = "\033[37m";
        String reset = "\033[0m";
        // usar String.format() para dar formato a la salida
        String output = String.format(yellow + "╔═%s═\n"
        + yellow+"║ AUTOCARAVANA "+reset+"%d\n"
        + yellow+"║═%s═\n"
        + yellow+"║ Modelo: "+reset+"%s\n"
        + yellow+"║ Precio por día: "+reset+"%.2f €\n"
        + yellow+"║ Matrícula: "+reset+"%s\n"
        + yellow+"║ Kilometraje: "+reset+"%d km\n"
        + yellow+"╚═%s═\n" + reset,
        "═".repeat(27 + String.valueOf(idA).length()),
        idA,
        "═".repeat(27 + String.valueOf(idA).length()),
        modelo,
        precioPorDia,
        matricula,
        kilometraje,
        "═".repeat(27 + String.valueOf(idA).length()));

            //add ║ to each line at the end
    String finalOutput = "";
    for (int i = 0; i < output.lines().toList().size(); i++){
        finalOutput += output.lines().toList().get(i);
        if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 2)
        //add a " " until every line has the same length
        for (int j = 0; j < 40 - output.lines().toList().get(i).length(); j++){
            finalOutput += " ";
        }
        if (i == 0)
        finalOutput += yellow+"╗"+reset+"\n";
        else if (i == output.lines().toList().size() - 2)
        finalOutput += yellow+"╝"+reset+"\n";
        else if (i < output.lines().toList().size() - 2)
        finalOutput += yellow+"║"+reset+"\n";}

    return finalOutput;

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
