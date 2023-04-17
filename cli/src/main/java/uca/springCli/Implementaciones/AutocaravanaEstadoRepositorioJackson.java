package uca.springCli.Implementaciones;

import java.util.Collection;

import uca.core.AutocaravanaEstadoRepositorio;

public class AutocaravanaEstadoRepositorioJackson implements AutocaravanaEstadoRepositorio {

    @Override
    public Collection<String> cargarEstadosAutocaravana() { //pendiente de borrar
        return null;
    }

    @Override
    public void guardarEstadoAutocaravana(Collection<String> listaEstados) { //pendiente de borrar
    }

    @Override
    public void guardarEstadoAutocaravana(String estado) { //pendiente de borrar

    }


    @Override
    public void eliminarEstadoAutocaravana(String estado) {
        if(existeEstadoAutocaravana(estado)){
            Collection<String> listaEstados = cargarEstadosAutocaravana();
            listaEstados.remove(estado);
            guardarEstadoAutocaravana(listaEstados);
        }
        throw new IllegalArgumentException("El estado no existe");
    }

    public void addEstadoCliente(String estado) {
        if(!existeEstadoAutocaravana(estado)){
            Collection<String> listaEstados = cargarEstadosAutocaravana();
            listaEstados.add(estado);
            guardarEstadoAutocaravana(listaEstados);
        }
        throw new IllegalArgumentException("El estado ya existe");

    }

    public boolean existeEstadoAutocaravana(String estado) {
        return cargarEstadosAutocaravana().stream().anyMatch(e -> e.equals(estado));
    }
}
