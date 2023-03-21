package org.core; //para que todos esten juntos no lo entiendo muy bien 


import java.util.ArrayList;
import java.util.List;

//A class to represent a Car booking system
public class Autocaravana{
    private final int idA;
    private String modelo;
    private float precioPorDia;
    private String matricula;

    private int plazas;
    private int kilometraje;
    private static int cantidadCaravanasAlquiladas = 0;
    private String estado = "Disponible";

    public static ServicioAutocaravana servidor = new ServicioAutocaravana();
    private static List<Autocaravana> listaAutocaravanas = new ArrayList<>();

    public Autocaravana (String mod, float precio,int plazas, String matricula, int kilometraje){
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
        if(plazas < 0){
            throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        }
        this.plazas= plazas;

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
        plazas = Integer.parseInt(campos[4]);
        kilometraje = Integer.parseInt(campos[5]);
        estado = campos[6];
        listaAutocaravanas.add(this);
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

    public static Autocaravana buscarAutocaravana(int parseInt) {
        for (Autocaravana a : Autocaravana.getListaAutocaravanas()) {
            if (a.getIdA() == parseInt) {
                return a;
            }
        }
        return null;
    }

    public void modificarPrecio(float precio){
        if (precio <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        precioPorDia = precio;
    }

    public boolean quedanCaravanas(){
        return getCantidadCaravanas() > 0;
    }

    public static int getCantidadCaravanas() {
        return listaAutocaravanas.size();
    }




    public String getEstado() {
        return estado;
    }

    public void cambiarEstado(String Estado)
    {
        if(ServicioAutocaravana.comprobarEstadoAutocaravana(Estado))
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


    public int getCapacidad() {
        return plazas;
    }

    public int getPlazas() {
        return plazas;
    }

    public void NuevaReservasRealizadas() {
        cantidadCaravanasAlquiladas++;
    }

    public void eliminarAutocaravana() {
        if (listaAutocaravanas.contains(this))
            listaAutocaravanas.remove(this);
        else
        throw new IllegalArgumentException("La autocaravana ya esta eliminada");
    }
}

