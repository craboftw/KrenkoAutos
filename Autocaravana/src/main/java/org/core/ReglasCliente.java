package org.core;

public interface ReglasCliente {

    public default boolean  comprobarCliente(Cliente c) {
        return true;
    }
}
