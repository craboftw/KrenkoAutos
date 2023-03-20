package org.core;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Pruebas de cada una de las clases
        Servidor servidor = new Servidor();
        servidor.cargarClientes();
        servidor.cargarAutocaravanas();
        servidor.cargarReservas();
        servidor.cargarEstados();


        Autocaravana A = Autocaravana.buscarAutocaravana(1);
        Cliente Cli0 = Cliente.buscarCliente(0);
        Reserva R = Reserva.buscarReserva(0);
        Reserva.nuevoestado("Busca y captura");
        //System.out.println(A);
        //System.out.println(Cli0);
        //Reserva R = new Reserva(A,Cli0,"2024-11-10","2024-11-20");
        //System.out.println(R);
        Cliente.getListaClientes().forEach(System.out::println);


        Cliente.modificarCliente(Cli0,"Manolo","Perez","666777888","1995-03-20","12345678A","Juanito@Perez.fake");

        servidor.guardarAutocaravana(Autocaravana.getListaAutocaravanas());
        servidor.guardarCliente(Cliente.getListaClientes());
        servidor.guardarReservas(Reserva.getListaReservas());
        servidor.guardarEstado(Reserva.getListaEstadoReservas());


        R.asociarestado("Busca y captura");

        Reserva.getListaEstadoReservas().forEach(System.out::println);
        Cliente.getListaClientes().forEach(System.out::println);
        Autocaravana.getListaAutocaravanas().forEach(System.out::println);
        Reserva.getListaReservas().forEach(System.out::println);

    }


}