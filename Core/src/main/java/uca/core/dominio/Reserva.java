package uca.core.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reserva {
    private int idR;
    private Autocaravana autocaravanaR;
    private int idCliente;
    private int idAutocaravana;
    private String fechaIni;
    private String fechaFin;
    private String estadoR;
    private BigDecimal precioTotal;
    private BigDecimal pagado;
    //    ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Reserva(int id, String fechI, String fechF,BigDecimal precioTot,BigDecimal paga, int idC, int idA, String estado) {
        idR = id;
        idCliente = idC;
        idAutocaravana = idA;
        fechaIni = fechI;
        fechaFin = fechF;
        estadoR = estado;
        precioTotal = precioTot;
        pagado = paga;
    }

    public Reserva()
    {
        idR = 0;
        idCliente = 0;
        idAutocaravana = 0;
        estadoR = null;
        fechaIni = null;
        fechaFin = null;
        estadoR = null;
        precioTotal = null;
        pagado = null;
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


    public int        getIdR() {return idR;}
    public int        getAutocaravana() {return idAutocaravana;}
    public int        getCliente() {return idCliente;}
    public String     getFechaIni() {return fechaIni;}
    public LocalDate  fechaIniF() {return LocalDate.parse(fechaIni);}
    public String     getFechaFin() { return fechaFin;}
    public LocalDate  fechaFinF() {return LocalDate.parse(fechaFin);}
    public BigDecimal getPrecioTotal() {return precioTotal;}
    public String     getEstadoReserva() {return estadoR;}
    public BigDecimal getPagado() {return pagado;}

    public void setEstadoReserva(String estado) { this.estadoR = estado; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }
    public void setFechaIni(String fechaIni) {this.fechaIni = fechaIni;}
    public void setFechaFin(String fechaFin) {this.fechaFin = fechaFin;}
    public void setPrecioTotal(String precioTotal) {this.precioTotal = new BigDecimal(precioTotal);}
    public void setCliente(int cliente) {idCliente = cliente;}
    public void setAutocaravana(int autocaravana) {idAutocaravana = autocaravana;}
    public void setPagado(BigDecimal pagado) {this.pagado = pagado;}
    public boolean estaPagada() {return pagado.compareTo(precioTotal) == 0;}


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros metodos ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    public String toString() {
        return "Reserva{" +
                "idR=" + idR +
                ", IDautocaravana=" + autocaravanaR +
                ", IDcliente=" + idCliente +
                ", fechaIni='" + fechaIni + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", estadoR='" + estadoR + '\'' +
                ", precioTotal=" + precioTotal +
                ", pagado=" + pagado +
                '}';
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
