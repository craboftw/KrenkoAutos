package uca.core.dao;

import java.util.Collection;

public interface ReservaEstadoRepositorio {
    //package Uca.Core;
    //
    //import java.util.Collection;
    //
    //public interface ReservaRepositorio {
    //
    //    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Reserva‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    //    void guardarReserva(Collection<Reserva> caravanas) ;
    //    void guardarReserva(Reserva caravana) ;
    //    Collection<Reserva> cargarReserva() ;
    //    Collection<Reserva> cargarReserva(String tipo,String dato) ; //
    //    int getCantidadReserva();
    //    void eliminarReserva(Reserva r);
    //}

    public void guardarReservaEstado(Collection<String> listaEstados) ;

    public void guardarReservaEstado(String estado) ;
    public void eliminarReservaEstado(String estado) ;

    public default String cargarReservaEstadoDefault() {
        return "Por confirmar";
    }

    public Collection<String> cargarReservaEstado();



}
