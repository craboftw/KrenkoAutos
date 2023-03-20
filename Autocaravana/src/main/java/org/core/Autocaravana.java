package org.core; //para que todos esten juntos no lo entiendo muy bien 


import java.util.ArrayList;
import java.util.List;

//A class to represent a Car booking system
public class Autocaravana{
    private final int idA;
    private String modelo;
    private float precioPorDia;
    private final String matricula;
    private int kilometraje;
    private static int cantidadCaravanas = 0;
    private static int cantidadCaravanasAlquiladas = 0;
    private String estado = "Disponible";

    public static Servidor servidor = new Servidor();
    private static List<Autocaravana> listaAutocaravanas = new ArrayList<>();




    public Autocaravana (String mod, float precio, String matricula, int kilometraje){
        idA = siguienteCaravana();
        modelo = mod;
        precioPorDia = precio;

        //Comprobar que una matricula es correcta, si no es correcta, lanzar excepcion
        if(!ReglasAutocaravana.comprobarMatricula(matricula)){
            throw new IllegalArgumentException("La matricula debe tener 7 caracteres");
        }
        if (listaAutocaravanas.stream().anyMatch(a -> a.getMatricula().equals(matricula))) {
            throw new IllegalArgumentException("La matricula ya existe");
        }

        if(precioPorDia < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        if(kilometraje < 0){
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        }
        this.matricula = matricula;
        this.kilometraje = kilometraje;
        this.estado="Disponible";
        listaAutocaravanas.add(this);
    }

    public Autocaravana(String campo) {
        String[] campos = campo.split(";");
        idA = Integer.parseInt(campos[0]);
        modelo = campos[1];
        precioPorDia = Float.parseFloat(campos[2]);
        matricula = campos[3];
        kilometraje = Integer.parseInt(campos[4]);
        estado = campos[5];
        listaAutocaravanas.add(this);
    }

    public static Autocaravana buscarAutocaravana(int parseInt) {
        for (Autocaravana a : listaAutocaravanas) {
            if (a.getIdA() == parseInt) {
                return a;
            }
        }
        return null;
    }

    public static List<Autocaravana> getListaAutocaravanas() {
        return listaAutocaravanas;
    }

    private int siguienteCaravana() {
        return listaAutocaravanas.size();
    }

    public int getIdA(){
        return idA;
    }

    public String getModelo(){
        return modelo;
    }

    public float getPrecioPorDia(){
        return precioPorDia;
    }

    public String getMatricula(){
        return matricula;
    }

    public int getKilometraje(){
        return kilometraje;
    }


    public void modificarKilometraje(int kilometraje){
        this.kilometraje = kilometraje;
    }

    public void modificarAutocaravana(String mod, float precio){
            modelo = mod;
            precioPorDia = precio;
    }

    public void actualizarkilometraje(int kilometraje) {
        if (kilometraje > this.kilometraje) {
            this.kilometraje = kilometraje;
        } else {
            throw new IllegalArgumentException("El kilometraje no puede ser menor que el anterior");
        }
    }

    public void modificarPrecio(float precio){
        precioPorDia = precio;
    }

    public boolean QuedanCaravanas(){
        return cantidadCaravanasAlquiladas > 0;
    }

    public void AnadirCaravanaReservada(){
        cantidadCaravanasAlquiladas++;
    }

    public void QuitarCaravanaReservada(){
        cantidadCaravanasAlquiladas--;
    }




    public String getEstado() {
        return estado;
    }

    public void cambiarEstado(String Estado)
    {
        if(servidor.comprobarEstado(Estado))
            estado = Estado;
    }


    public String toString(){
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

