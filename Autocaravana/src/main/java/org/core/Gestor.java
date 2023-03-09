package org.core;

import java.util.List;

import org.core.Autocaravana;
import org.core.Reserva;


//VAMOS A NECESITAR UN CONTADOR DE RESERVAS PARA PODER ASIGNAR LOS ID A LAS RESERVAS
//VAMOS A NECESITAR UN CONTADOR DE AUTOCARAVANAS PARA PODER ASIGNAR LOS ID A LAS AUTOCARAVANAS
//VAMOS A NECESITAR UN CONTADOR DE CLIENTES PARA PODER ASIGNAR LOS ID A LOS CLIENTES
//Plasmar en el modelo de mermaid

public class Gestor{
    private List<Autocaravana> listaAutocaravana; //= new ArrayList<Autocaravana>();
    private List<Reserva> listaReserva;

    public List<Autocaravana> getAutocaravanas(){
        return listaAutocaravana;
    }

    public List<Reserva> getReservas(){
        return listaReserva;
    }

    public void agregarAutocaravana (Autocaravana C){
        listaAutocaravana.add(C);
    }

    public void eliminarAutocaravana (Autocaravana C){
        if(!listaAutocaravana.empty()){
            if (!listaAutocaravana.remove(C))
              System.out.print("Esa Autocaravana no existe en la lista");
        }
        else System.out.print("Lista Vacia");
    }

    public Autocaravana buscarAutocaravana(int id) {

        for (Autocaravana A : autocaravanas) { //iterador
            if (A.getId() == id) {
                return A;
            }
        }
        return null;
    }

    //CAMBIAR MUCHAS COSAS
    public Reserva crearReserva(Autocaravana A, Cliente C, DateTime fechInicio, DateTime fechFin){
        Reserva R = new Reserva (A, C, fechInicio, fechFin);

        // Validar que la fecha de inicio sea anterior a la fecha de fin
        if (fechInicio.isAfter(fechFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        // Validar que la autocaravana esté disponible durante el periodo de reserva
        if (!A.estaDisponible(fechInicio, fechFin)) {
            throw new IllegalArgumentException("La autocaravana no está disponible para el periodo seleccionado.");
        }

        // Agregar la reserva a la lista de reservas de la autocaravana y del cliente
        A.agregarReserva(R);
        C.agregarReserva(R);

        // Devolver la reserva creada
        return R;
    }
}

