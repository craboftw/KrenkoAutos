package org.core;

public interface ClienteReglas {

    static boolean comprobarDNI(String dn) {
        return true;
    }

    static boolean comprobarEdad(int edad) {
        return edad>=18;
    }

    default boolean comprobarCliente(Cliente c) {
        return true;
    }

}
