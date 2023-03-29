package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private final int idR;
    private final Autocaravana autocaravanaR;
    private final Cliente clienteR;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private String estadoR;
    private float precioTotal;

    //    ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Reserva(int id, Autocaravana A, Cliente C, LocalDate fechI, LocalDate fechF,float precioTot, String estado) {
        idR = id;
        clienteR = C;
        autocaravanaR = A;
        estadoR = estado;
        fechaIni = fechI;
        fechaFin = fechF;
        estadoR = estado;
        precioTotal = precioTot;
        C.setNuevaReservaRealizada();
        A.setNuevaReservaRealizada();
    }


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
//
//⠀  ⠀   (\__/)
//       (•ㅅ•)      Aqui antes hubo una lista
//     ＿ノヽ ノ＼＿      ahora solo el y yo.
//` /　`/ ⌒Ｙ⌒ Ｙ  ヽ     Siga circulando
//  ( 　(三ヽ人　 /　  |
//  |　ﾉ⌒＼ ￣￣ヽ   ノ
//   ヽ＿＿＿＞､＿_／
//      ｜( 王 ﾉ〈  (\__/)
//      /ﾐ`ー―彡\  (•ㅅ•)
//     / ╰ ___╯ \ /    \>
//    /  /    \  \\____/
//
    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Getters y Setters‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    public int          getIdR() {return idR;}
    public Autocaravana getAutocaravana() {return autocaravanaR;}
    public Cliente      getCliente() {return clienteR;}
    public LocalDate    getFechaIni() {return fechaIni;}
    public LocalDate    getFechaFin() { return fechaFin;}
    public float        getPrecioTotal() {return precioTotal;}
    public String       getEstadoReserva() {return estadoR;}

    void setEstadoReserva(String estado) { this.estadoR = estado; }
    void setPrecioTotal(float precioTotal) { this.precioTotal = precioTotal; }
    void setFechaIni(LocalDate fechaIni) {this.fechaIni = fechaIni;}
    void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros metodos ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    public String toString() {
        // usar String.format() para dar formato a la salida
        String output = String.format("╔═%s═╗\n"
                        + "║ RESERVA %d\n"
                        + "║══%s══║\n"
                        + "║ Caravana: %s\n"
                        + "║ Cliente: %s %s\n"
                        + "║ Fecha Inicio: %s\n"
                        + "║ Fecha Fin: %s\n"
                        + "║ Precio Total: %.2f\n"
                        + "║ Estado: %s\n"
                        + "╚═%s═╝\n",
                "═".repeat(13 + String.valueOf(idR).length()),
                idR,
                "═".repeat(13 + String.valueOf(idR).length() - 2),
                autocaravanaR.getMatricula(),
                clienteR.getNombre(),
                clienteR.getApellido(),
                fechaIni,
                fechaFin,
                precioTotal,
                estadoR,
                "═".repeat(13 + String.valueOf(idR).length()));
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
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                                  ██████████
