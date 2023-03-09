package main.java.org.core;

import java.util.List;

import org.core.Caravana;
import org.core.Reserva;

public class Gestor{
    List<Caravana> listaCaravana; //= new ArrayList<Caravana>();
    List<Reserva> listaReserva;

    void agregarCaravana(Caravana C){
        listaCaravana.add(C);
    }

    void eliminarCaravana(Caravana C){
        boolean existe = listaCaravana.contains(C);
        if (existe) listaCaravana.remove(C);
        else System.out.print("Esa caravana no existe en la lista");
    }
}
