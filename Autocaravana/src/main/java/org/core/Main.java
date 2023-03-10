package org.core;

import java.time.LocalDate;

public class Main {

    //Creacion y prueba de la clase Autocaravana
    public static void main(String[] args) {
        //*=*=*=*=*=*=*=*=*=*=*=PRUEBA CLASES INDIVIDUALES*=*=*=*=*=*=*=*=*=*=*=*
        Autocaravana A = new Autocaravana(1, "A", 100);
        System.out.println("El modelo de la autocaravana es: " + A.getModelo());
        System.out.println("El precio por dia de la autocaravana es: " + A.getPrecioPorDia());
        System.out.println("El id de la autocaravana es: " + A.getIdA());
        System.out.println(" ");

        Autocaravana B = new Autocaravana(2, "B", 200);
        System.out.println("El modelo de la autocaravana es: " + B.getModelo());
        System.out.println("El precio por dia de la autocaravana es: " + B.getPrecioPorDia());
        System.out.println("El id de la autocaravana es: " + B.getIdA());
        System.out.println(" ");

        Autocaravana C = new Autocaravana(3, "C", 300);
        System.out.println("El modelo de la autocaravana es: " + C.getModelo());
        System.out.println("El precio por dia de la autocaravana es: " + C.getPrecioPorDia());
        System.out.println("El id de la autocaravana es: " + C.getIdA());
        System.out.println(" ");

        //Creacion y prueba de la clase Cliente
        Cliente D = new Cliente(1, "Juan", "Perez", "invetado1@inventig.com");
        System.out.println("El id del cliente es: " + D.getIdC());
        System.out.println(" ");

        Cliente E = new Cliente(2, "Maria", "Gonzalez", "invetado2@inventig.com");
        System.out.println("El id del cliente es: " + E.getIdC());
        System.out.println(" ");

        Cliente F = new Cliente(3, "Pedro", "Martinez", "invetado3@inventig.com");
        System.out.println("El id del cliente es: " + F.getIdC());
        System.out.println(" ");

        //Creacion y prueba de la clase Reserva
        Reserva G = new Reserva(1, A, D, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2));
        System.out.println("El id de la reserva es: " + G.getIdR());
        System.out.println("El id de la autocaravana es: " + G.getAutocaravana().getIdA());
        System.out.println("El id del cliente es: " + G.getCliente().getIdC());
        System.out.println("La fecha de inicio de la reserva es: " + G.getFechaIni());
        System.out.println("La fecha de fin de la reserva es: " + G.getFechaFin());
        System.out.println(" ");

        Reserva H = new Reserva(2, B, E, LocalDate.of(2021, 1, 3), LocalDate.of(2021, 1, 4));
        System.out.println("El id de la reserva es: " + H.getIdR());
        System.out.println("El id de la autocaravana es: " + H.getAutocaravana().getIdA());
        System.out.println("El id del cliente es: " + H.getCliente().getIdC());
        System.out.println("La fecha de inicio de la reserva es: " + H.getFechaIni());
        System.out.println("La fecha de fin de la reserva es: " + H.getFechaFin());
        System.out.println(" ");


        //*=*=*=*=*=*=*=*=*=*=*=PRUEBA CLASE GESTOR*=*=*=*=*=*=*=*=*=*=*=*
        Gestor I = new Gestor();
        I.agregarAutocaravana( "A", 100);
        I.agregarAutocaravana(B);
        I.agregarAutocaravana(C);
        //imprimir autocaravanas en el gestor
        System.out.println("Autocaravanas en el gestor:");
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
System.out.println(" ");

        I.crearCliente("Juan", "Perez", "00000000A","a@a.com");
        I.crearCliente("Maria", "Gonzalez", "00000000B","b@b.com");
        I.crearCliente(F);
        //imprimir clientes en el gestor
        System.out.println("Clientes en el gestor:");
        for (Cliente c : I.getClientes()) {
            System.out.println(c);
        }
        System.out.println(" ");

        I.crearReserva(A, D, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2));
        I.crearReserva(B, E, LocalDate.of(2021, 1, 3), LocalDate.of(2021, 1, 4));
        I.crearReserva(I.buscarAutocaravana(3), I.buscarCliente(3), LocalDate.of(2021, 1, 5), LocalDate.of(2021, 1, 6));
        //imprimir reservas hechas en el gestor
        System.out.println("Reservas hechas en el gestor:");
        for (Reserva r : I.getReservas()) {
            System.out.println(r);
        }
        System.out.println(" ");


    }


}