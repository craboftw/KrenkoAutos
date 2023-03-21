package org.core;

public class Main {

    public static void main(String[] args)  {

        ServicioCliente.cargarClientes();
        ServicioCliente.cargarEstadosCliente();
        ServicioAutocaravana.cargarAutocaravana();
        ServicioAutocaravana.cargarEstadosAutocaravana();
        ServicioReserva.cargarReservas();
        ServicioReserva.cargarEstadosReserva();

        //PROBAR A IMPRIMIR TODAS LAS CLASES
        System.out.println("Clientes: ");
        for (Cliente cliente : Cliente.getListaClientes()) {
            System.out.println(cliente);
        }
        System.out.println("Estados de los clientes: ");
        for (String estado : ServicioCliente.getListaEstadoClientes()) {
            System.out.println(estado);
        }
        System.out.println("Autocaravanas: ");
        for (Autocaravana autocaravana : Autocaravana.getListaAutocaravanas()) {
            System.out.println(autocaravana);
        }
        System.out.println("Estados de las autocaravanas: ");
        for (String estado : ServicioAutocaravana.getListaEstadoAutocaravana()) {
            System.out.println(estado);
        }
        System.out.println("Reservas: ");
        for (Reserva reserva : Reserva.getListaReservas()) {
            System.out.println(reserva);
        }
        System.out.println("Estados de las reservas: ");
        for (String estado : ServicioReserva.getListaEstadoReservas()) {
            System.out.println(estado);
        }

    }


}