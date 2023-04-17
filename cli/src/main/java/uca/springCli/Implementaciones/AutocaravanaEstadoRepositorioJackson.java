package uca.springCli.Implementaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uca.core.AutocaravanaEstadoRepositorio;

public class AutocaravanaEstadoRepositorioJackson implements AutocaravanaEstadoRepositorio {

    String AUTOCARAVANAS_FILE_PATH = "autocaravanas.json";

    @Override
    public Collection<String> cargarEstadosAutocaravana() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(AUTOCARAVANAS_FILE_PATH);
            if (file.exists())
            {
                return objectMapper.readValue(file, new TypeReference<Collection<String>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public void guardarEstadoAutocaravana(Collection<String> listaEstados) { 
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), listaEstados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarEstadoAutocaravana(String estado) { 
        Collection<String> listaEstados = cargarEstadosAutocaravana();
        listaEstados.add(estado);
        guardarEstadoAutocaravana(listaEstados);
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
