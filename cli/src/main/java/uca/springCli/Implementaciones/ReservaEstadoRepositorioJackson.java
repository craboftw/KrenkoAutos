package uca.springCli.Implementaciones;

import java.util.Collection;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import uca.core.ReservaEstadoRepositorio;

import uca.core.ReservaEstadoRepositorio;

public class ReservaEstadoRepositorioJackson implements ReservaEstadoRepositorio {
    private final String ESTADOSRESERVA = "estadosReserva.json";

    @Override
    public Collection<String> cargarReservaEstado() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(ESTADOSRESERVA);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<Collection<String>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    @Override
    public void guardarReservaEstado(Collection<String> listaEstados) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(ESTADOSRESERVA), listaEstados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarReservaEstado(String estado) {
        Collection<String> listaEstados = cargarReservaEstado();
        listaEstados.add(estado);
        guardarReservaEstado(listaEstados);
    }

    @Override
    public void eliminarReservaEstado(String estado) {
        if(cargarReservaEstado().contains(estado)){
            Collection<String> listaEstados = cargarReservaEstado();
            listaEstados.remove(estado);
            guardarReservaEstado(listaEstados);
        }
        throw new IllegalArgumentException("El estado no existe");
    }

    @Override
    public String cargarReservaEstadoDefault() {
        return "Pendiente";
    }

}