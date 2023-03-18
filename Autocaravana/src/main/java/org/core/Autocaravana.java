package org.core; //para que todos esten juntos no lo entiendo muy bien 


//A class to represent a Car booking system
public class Autocaravana implements ReglasAutocaravana{
    private int idA;
    private String modelo;
    private float precioPorDia;
    private String matricula;
    private int kilometraje;
    private static int cantidadCaravanas = 0;
    private static int cantidadCaravanasAlquiladas = 0;
    private String estado = "Disponible";


    public Autocaravana (String mod, float precio, String matricula, int kilometraje){
        idA = siguienteCaravana();
        modelo = mod;
        precioPorDia = precio;

        //Comprobar que una matricula es correcta, si no es correcta, lanzar excepcion
        if(!ReglasAutocaravana.comprobarMatricula(matricula)){
            throw new IllegalArgumentException("La matricula debe tener 7 caracteres");
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
    }

    private int siguienteCaravana() {
        return cantidadCaravanas++;
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

    public void QuitarCaravana() throws Throwable {
        this.finalize();
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    public String getEstado() {
        return estado;
    }

    public void cambiarEstado(String Estado)
    {
        if(comprobarEstado(Estado))
            estado = Estado;
    }


    public String toString(){
        return "Autocaravana: " + idA + " " + modelo + " " + precioPorDia + " " + matricula + " " + kilometraje;
    }




}

