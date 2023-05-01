package uca.core.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;


@Data
public class Reserva {
    private int idR;
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
//       (•ㅅ•)      Aqui antes hubo una lista y muchas cosas mas
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



    public boolean estaPagada() {return pagado.compareTo(precioTotal) == 0;}
    public LocalDate fechaFinF() {return LocalDate.parse(fechaFin);}
    public LocalDate fechaIniF() {return LocalDate.parse(fechaIni);}

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros metodos ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧


    public String toString() {
        return "Reserva{" +
                "idR=" + idR +
                ", IDautocaravana=" + idAutocaravana +
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
