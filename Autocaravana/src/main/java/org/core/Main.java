package org.core;

import java.time.LocalDate;

public class Main {

    //Creacion y prueba de la clase Autocaravana
    public static void main(String[] args) {
        //*=*=*=*=*=*=*=*=*=*=*=PRUEBA CLASES INDIVIDUALES*=*=*=*=*=*=*=*=*=*=*=*
        Autocaravana A = new Autocaravana( "A", 100);
        System.out.println("El modelo de la autocaravana es: " + A.getModelo());
        System.out.println("El precio por dia de la autocaravana es: " + A.getPrecioPorDia());
        System.out.println("El id de la autocaravana es: " + A.getIdA());
        System.out.println(" ");

        Autocaravana B = new Autocaravana("B", 200);
        System.out.println("El modelo de la autocaravana es: " + B.getModelo());
        System.out.println("El precio por dia de la autocaravana es: " + B.getPrecioPorDia());
        System.out.println("El id de la autocaravana es: " + B.getIdA());
        System.out.println(" ");

        Autocaravana C = new Autocaravana( "C", 300);
        System.out.println("El modelo de la autocaravana es: " + C.getModelo());
        System.out.println("El precio por dia de la autocaravana es: " + C.getPrecioPorDia());
        System.out.println("El id de la autocaravana es: " + C.getIdA());
        System.out.println(" ");

        //Creacion y prueba de la clase Cliente
        Cliente D = new Cliente( "Juan", "Perez","000000000A", "invetado1@inventig.com");
        System.out.println("El id del cliente es: " + D.getIdC());
        System.out.println(" ");

        Cliente E = new Cliente( "Maria", "Gonzalez","000000000B", "invetado2@inventig.com");
        System.out.println("El id del cliente es: " + E.getIdC());
        System.out.println(" ");

        Cliente F = new Cliente( "Pedro", "Martinez","000000000C", "invetado3@inventig.com");
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

        //Pruebas de modificar las clases y luego imprimir reservas
        I.modificarCliente(I.buscarCliente(1),"Mario", "Gonzalez", "00000000F","b@be.com");
        I.modificarAutocaravana(I.buscarAutocaravana(1), "Z", 200);
        //imprimir la reserva
        System.out.println(I.buscarReserva(1));

        //imprimir clientes en el gestor
        System.out.println("Clientes en el gestor ordenados por id Ascendente:");
        I.ordenarclientesIdAsc();
        for (Cliente c : I.getClientes()) {
            System.out.println(c);
        }
        System.out.println(" ");
        System.out.println("Clientes en el gestor ordenados por id Descendente:");
        I.ordenarclientesIdDesc();
        for (Cliente c : I.getClientes()) {
            System.out.println(c);
        }
        System.out.println(" ");
        System.out.println("Clientes en el gestor ordenados por nombre Ascendente:");
        I.ordenarclientesNombreAsc();
        for (Cliente c : I.getClientes()) {
            System.out.println(c);
        }
        System.out.println(" ");
        System.out.println("Clientes en el gestor ordenados por nombre Descendente:");
        I.ordenarclientesNombreDesc();
        for (Cliente c : I.getClientes()) {
            System.out.println(c);
        }
        System.out.println(" ");


        //imprimir autorcaravanas en el gestor
        System.out.println("Autocaravanas en el gestor:");
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }


        //prueba todos los metodos ordenar de Gestor

        System.out.println("Autocaravanas en el gestor ordenadas por id Ascendente:");
        I.ordenarAutocaravanasIdAsc();
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
        System.out.println(" ");
        System.out.println("Autocaravanas en el gestor ordenadas por id Descendente:");
        I.ordenarAutocaravanasIdDesc();
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
        System.out.println(" ");
        System.out.println("Autocaravanas en el gestor ordenadas por modelo Ascendente:");
        I.ordenarAutocaravanasModeloAsc();
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
        System.out.println(" ");
        System.out.println("Autocaravanas en el gestor ordenadas por modelo Descendente:");
        I.ordenarAutocaravanasModeloDesc();
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
        System.out.println(" ");
        System.out.println("Autocaravanas en el gestor ordenadas por precio Ascendente:");
        I.ordenarAutocaravanasPrecioAsc();
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
        /*
        System.out.println(" ");
        System.out.println("Autocaravanas en el gestor ordenadas por precio Descendente:");
        I.ordenarAutocaravanasPrecioDesc();
        for (Autocaravana a : I.getAutocaravanas()) {
            System.out.println(a);
        }
        System.out.println(" ");

        System.out.println("Reservas en el gestor ordenadas por estado Ascendente:");
        I.ordenarReservasEstadoAsc();
        for (Reserva r : I.getReservas()) {
            System.out.println(r);
        }
        System.out.println(" ");*/

    }


}