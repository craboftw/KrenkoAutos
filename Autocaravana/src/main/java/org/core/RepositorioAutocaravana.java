package org.core;

import java.util.Collection;

public interface RepositorioAutocaravana {


    static void guardarAutocaravana(Collection<Autocaravana> caravanas){}

    static void cargarAutocaravana() {
        return;
    }

    static void cargarEstadosAutocaravana(){}
    static void guardarEstadoAutocaravana(Collection<String> listaEstados){}
}
