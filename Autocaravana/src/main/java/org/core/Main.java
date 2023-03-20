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


        //Autocaravana A = Autocaravana.buscarAutocaravana(0);
        //Autocaravana A = new Autocaravana("0001MDM",10,5,"4020PKT",0);
        //Cliente Cli0 = Cliente.buscarCliente(0);
        //System.out.println(A);
        //System.out.println(Cli0);
        //Reserva R = new Reserva(A,Cli0,"2024-11-10","2024-11-20");
        //System.out.println(R);
        //Cliente.getListaClientes().forEach(System.out::println);


        //Cli0.modificarCliente("Manolo","Perez","666777888","1995-03-20","12345678A","Juanito@Perez.fake");


        //Reserva R = new Reserva(A,Cli0,"2024-01-10","2024-01-20");
        //R.asociarestado("Busca y capture");

        Reserva.buscarReserva("0001MDM","matricula").forEach(System.out::println);
        //R.asociarestado("En curso");


        Reserva.getListaEstadoReservas().forEach(System.out::println);
        Cliente.getListaClientes().forEach(System.out::println);
        Autocaravana.getListaAutocaravanas().forEach(System.out::println);
        Reserva.getListaReservas().forEach(System.out::println);

        servidor.guardarAutocaravana(Autocaravana.getListaAutocaravanas());
        servidor.guardarCliente(Cliente.getListaClientes());
        servidor.guardarReservas(Reserva.getListaReservas());
        servidor.guardarEstado(Reserva.getListaEstadoReservas());
    }


}