package org.core;

import java.util.Collection;

public interface RepositorioAutocaravana {


    void cargarAutocaravana();
    void guardarAutocaravana(Collection<Autocaravana> caravanas);
    void cargarEstadosAutocaravana();
    void guardarEstadoAutocaravana(Collection<String> listaEstados);
}
