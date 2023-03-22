package org.core;

public class Main {

    public static void main(String[] args) {

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

        Cliente cli = new Cliente("12345678A", "Juan", "Perez", "1960-02-02", "666666666", "A@B.com");
        Autocaravana car = new Autocaravana("1234ABC", 9f,4,"5050MLR",100);
        Reserva res = new Reserva(car, cli, "2023-03-22", "2024-01-10");
        System.out.println(res);
        ServicioReserva.checkIn(res);
        System.out.println(res);
        ServicioReserva.checkOut(res);
        System.out.println(res);



    }


}