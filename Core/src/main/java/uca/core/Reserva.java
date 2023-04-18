package uca.core;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.print.DocFlavor.STRING;

public class Reserva {
    private final int idR;
    private Autocaravana autocaravanaR;
    private Cliente clienteR;
    private String fechaIni;
    private String fechaFin;
    private String estadoR;
    private BigDecimal precioTotal;

    //lo que queda por pagar

    //    ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Reserva(int id, Autocaravana A, Cliente C, String fechI, String fechF,BigDecimal precioTot, String estado) {
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

    public Reserva()
    {
        idR = 0;
        clienteR = null;
        autocaravanaR = null;
        estadoR = null;
        fechaIni = null;
        fechaFin = null;
        estadoR = null;
        precioTotal = null;
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
    public String    getFechaIni() {return fechaIni;}
    public LocalDate fechaIniF() {return LocalDate.parse(fechaIni);}
    public String    getFechaFin() { return fechaFin;}
    public LocalDate fechaFinF() {return LocalDate.parse(fechaFin);}
    public BigDecimal getPrecioTotal() {return precioTotal;}
    public String       getEstadoReserva() {return estadoR;}

    void setEstadoReserva(String estado) { this.estadoR = estado; }
    void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }
    void setFechaIni(String fechaIni) {this.fechaIni = fechaIni;}
    void setFechaFin(String fechaFin) {this.fechaFin = fechaFin;}
    void setPrecioTotal(String precioTotal) {this.precioTotal = new BigDecimal(precioTotal);}
    public void setCliente(Cliente cliente) {clienteR = cliente;}
    public void setAutocaravana(Autocaravana autocaravana) {autocaravanaR = autocaravana;}

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros metodos ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    public String toString() {
        String bold = "\033[1m";
        String red = "\033[31m";
        String green = "\033[32m";
        String white = "\033[37m";
        String reset = "\033[0m";
        // usar String.format() para dar formato a la salida
        String output = String.format(green +"╔═%s═"+reset +"\n"
                        + green +"║ RESERVA "+reset+"%d"+reset+"\n"
                        + green +"║══%s═" +reset+ "\n"
                        + green +"║ Caravana: " +reset+ "%s"+reset+"\n"
                        + green +"║ Cliente: "+reset+"%s %s"+reset+"\n"
                        + green +"║ Fecha Inicio: "+reset+"%s"+reset+"\n"
                        + green +"║ Fecha Fin: "+reset+"%s"+reset+"\n"
                        + green +"║ Precio Total: "+reset+"%.2f"+reset+"\n"
                        + green +"║ Estado: "+reset+"%s"+reset+"\n"
                        + green +"╚═%s═"+reset+"\n",
                "═".repeat(Math.max(0, 36 + String.valueOf(idR).length() - 2)),
                idR,
                "═".repeat(Math.max(0, 37 + String.valueOf(idR).length() - 4)),
                autocaravanaR.getMatricula(),
                clienteR.getNombre(),
                clienteR.getApellido(),
                fechaIni,
                fechaFin,
                precioTotal,
                estadoR,
                "═".repeat(Math.max(0, 36 + String.valueOf(idR).length() - 2)));


        //add ║ to each line at the end
        String finalOutput = "";
        for (int i = 0; i < output.lines().toList().size(); i++){
            finalOutput += output.lines().toList().get(i);
            if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 1)

            //add a " " until every line has the same length
            for (int j = 0; j < 51 - output.lines().toList().get(i).length(); j++){
                finalOutput += " ";
            }
            if (i == 0)
            finalOutput += green+"╗"+reset+"\n";
            else if (i == output.lines().toList().size() - 1)
            finalOutput += green+"╝"+reset+"\n";
            else
            finalOutput += green+"║"+reset+"\n";}

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
//‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧                                                  ██████████
