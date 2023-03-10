package org.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//VAMOS A NECESITAR UN CONTADOR DE RESERVAS PARA PODER ASIGNAR LOS ID A LAS RESERVAS
//VAMOS A NECESITAR UN CONTADOR DE AUTOCARAVANAS PARA PODER ASIGNAR LOS ID A LAS AUTOCARAVANAS
//VAMOS A NECESITAR UN CONTADOR DE CLIENTES PARA PODER ASIGNAR LOS ID A LOS CLIENTES
//Plasmar en el modelo de mermaid

public class Gestor{
    private List<Autocaravana> autocaravanas = new ArrayList<Autocaravana>();
    private List<Reserva> reservas = new ArrayList<Reserva>();
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private int cantidadAutocaravanas = 0;
    private int cantidadReservas = 0;
    private int cantidadClientes = 0;

    public List<Autocaravana> getAutocaravanas(){
        return autocaravanas;
    }

    public List<Reserva> getReservas(){
        return reservas;
    }

    public void agregarAutocaravana (Autocaravana C){
        autocaravanas.add(C);
    }

    public Autocaravana agregarAutocaravana (String modelo, float precioPorDia)
    {
        Autocaravana C = new Autocaravana(cantidadAutocaravanas++, modelo, precioPorDia);
        autocaravanas.add(C);
        return C;
    }

    public void eliminarAutocaravana (Autocaravana C){
        if(!autocaravanas.isEmpty()){
            if (!autocaravanas.remove(C))
              System.out.print("Esa Autocaravana no existe en la lista");
        }
        else System.out.print("Lista Vacia");
    }

    public Autocaravana buscarAutocaravana(int id) {

        for (Autocaravana A : autocaravanas) { //iterador
            if (A.getIdA() == id) {
                return A;
            }
        }
        return null;
    }

    //CAMBIAR MUCHAS COSAS
    public Reserva crearReserva(Autocaravana A, Cliente C, LocalDate fechInicio, LocalDate fechFin){
        Reserva R = new Reserva (cantidadReservas++,A, C, fechInicio, fechFin);

        // Validar que la fecha de inicio sea anterior a la fecha de fin
        if (fechInicio.isAfter(fechFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        // Validar que la autocaravana esté disponible durante el periodo de reserva
        if (!estaDisponible(A,fechInicio, fechFin)) {
            throw new IllegalArgumentException("La autocaravana no está disponible para el periodo seleccionado.");
        }

        // Agregar la reserva a la lista de reservas
        reservas.add(R);

        // Devolver la reserva creada
        return R;
    }

    public Cliente crearCliente(String nombre, String apellidos, String dni, String email){
        Cliente C = new Cliente (cantidadClientes++,nombre, apellidos, email);
        clientes.add(C);
        return C;
    }

    public void crearCliente(Cliente C){
        clientes.add(C);
    }
    private boolean estaDisponible(Autocaravana A, LocalDate fechInicio, LocalDate fechFin){
        for (Reserva R : reservas) {
            if (R.getAutocaravana().equals(A)) {
                if (R.getFechaIni().isBefore(fechFin) && R.getFechaFin().isAfter(fechInicio)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cliente buscarCliente(int id) {

        for (Cliente C : clientes) { //iterador
            if (C.getIdC() == id) {
                return C;
            }
        }
        return null;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    //Funcion que muestre por pantalla una prueba de la clase
    public void mostrar(){
        System.out.println("Autocaravanas: ");
        for (Autocaravana A : autocaravanas) {
            System.out.println(A.getIdA());
        }
        System.out.println("Reservas: ");
        for (Reserva R : reservas) {
            System.out.println(R.getIdR());
        }
    }

}

