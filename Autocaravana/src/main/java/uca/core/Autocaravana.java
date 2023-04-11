package uca.core;


public class Autocaravana {

    private int vecesReservada = 0;
    private final int idA;
    private final String matricula;
    private final int plazas;
    private String modelo;
    private float precioPorDia;
    private int kilometraje;
    private String estadoA = "Disponible";


    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Constructores‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Autocaravana(int id, String mod, float precio, int plaz, String matr, int kilometraj, String estado) {

        idA = id;
        modelo = mod;
        precioPorDia = precio;
        plazas = plaz;
        matricula = matr;
        kilometraje = kilometraj;
        estadoA = estado;

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
        return estadoA;
    }
    public int        getPlazas() {
        return plazas;
    }
    public int        getVecesReservada() {return vecesReservada;}

    void setModelo(String mod) {this.modelo = mod;}

    void setPrecioPorDia(float precioPorDia) {this.precioPorDia = precioPorDia;}

    void setKilometraje(int kilometraje){this.kilometraje = kilometraje;}

    void setEstado(String Estado) {
        if (AutocaravanaServicio.comprobarEstadoAutocaravana(Estado))
            estadoA = Estado;
    }

    public void setNuevaReservaRealizada() {vecesReservada++;}

    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Otros métodos‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

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
