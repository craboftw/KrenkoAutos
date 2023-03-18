package org.core;

import java.time.LocalDate;
public class Main {

    public static void main(String[] args) {
        //Pruebas de cada una de las clases
        //Pruebas de la clase Autocaravana, crear una autocaravana y comprobar que se crea correctamente imprimeindolas por pantalla
        Autocaravana A = new Autocaravana("Vito",2,"0001BCD",120000);
        System.out.println(A.toString());
        //Pruebas de crear Autocaravanas erroneas y ver que las excepciones son lanzadas
        try {
            Autocaravana B = new Autocaravana("Vito",2,"0001ACD",120000);
            System.out.println("Deberia haber saltado una excepcion");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la autocaravana");
        }
        try {
            Autocaravana C = new Autocaravana("Vito",2,"0001BCD",-1);
            System.out.println("Deberia haber saltado una excepcion");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la autocaravana");
        }
        try {
            Autocaravana D = new Autocaravana("Vito",-1,"0001BCD",120000);
            System.out.println("Deberia haber saltado una excepcion");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la autocaravana");
        }
        //Vamos a probar quitarcaravana
        Autocaravana Auto1 = new Autocaravana ("Berlingo",5,"0000KFC",12000);
        try {
            System.out.println(Auto1);
            Auto1.QuitarCaravana();
            System.out.println(Auto1);

        } catch (Throwable e)
        {
            System.out.println("Ha habido un error");
        }

        //Pruebas de la clase Cliente, crear un cliente y comprobar que se crea correctamente imprimeindolas por pantalla
        Cliente C = new Cliente("Pepe","Perez","1996-10-12","01234567B","Pepe@Perez.com");
        System.out.println(C.toString());
        //Pruebas de crear clientes erroneos y ver que las excepciones son lanzadas
        try {
            Cliente Cli1 = new Cliente("Pepe","Perez","1996-10-12","01234567B","PepePerez.com");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el cliente");
        }
    }
}