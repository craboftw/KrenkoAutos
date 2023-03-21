package org.core;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static <ServidorAutocaravana> void main(String[] args) throws IOException {
        //Pruebas de cada una de las clases
        Servidor servidor = new Servidor();
        ServidorAutocaravana servAutocaravana = new ServidorAutocaravana();
        servidor.cargarClientes();
        //servidor.cargarAutocaravanas();
        servidor.cargarReservas();
        servidor.cargarEstados();

        Servidor.getListaEstadoReservas().forEach(System.out::println);

        Autocaravana A = Autocaravana.buscarAutocaravana(0);
        //Autocaravana A = new Autocaravana("0001MDM",10,5,"4020PKT",0);
        //System.out.println(A);
        Cliente Cli0 = Cliente.buscarCliente(0);
        //System.out.println(A);
        //System.out.println(Cli0);
        //Reserva R = new Reserva(A,Cli0,"2024-11-10","2024-11-20");
        //System.out.println(R);
        //Cliente.getListaClientes().forEach(System.out::println);


        //Cli0.modificarCliente("Manolo","Perez","666777888","1995-03-20","12345678A","Juanito@Perez.fake");


        //Reserva Res = new Reserva(A,Cli0,"2024-01-10","2024-01-20");
        //R.asociarestado("Busca y capture");
        String s = "Busca y capture";
        //Servidor.nuevoestado(s);
        for (Reserva R : Reserva.buscarReserva("4020PKT","matricula")) {
            R.asociarestado(s);  //Aqui es donde deberia asociar el estado y por alguna condicion puesta no lo hace
            System.out.println(R);
        }


        /*
        Servidor.getListaEstadoReservas().forEach(System.out::println);
        Cliente.getListaClientes().forEach(System.out::println);
        Autocaravana.getListaAutocaravanas().forEach(System.out::println);
        Reserva.getListaReservas().forEach(System.out::println);
*/
        servi.guar(Autocaravana.getListaAutocaravanas());
        servidor.guardarCliente(Cliente.getListaClientes());
        servidor.guardarReservas(Reserva.getListaReservas());
        servidor.guardarEstado(Servidor.getListaEstadoReservas());
    }


}