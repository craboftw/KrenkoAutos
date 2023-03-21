package org.core;

public interface ReglasCliente {

    default boolean comprobarCliente(Cliente c) {
        return true;
    }
}
