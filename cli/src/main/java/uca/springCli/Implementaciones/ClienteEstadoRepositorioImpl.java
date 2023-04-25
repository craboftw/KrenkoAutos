package uca.springCli.Implementaciones;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uca.core.dao.ClienteEstadoRepositorio;

public class ClienteEstadoRepositorioImpl implements ClienteEstadoRepositorio{

    String CLIENTES_ESTADOS_FILE_PATH = "clientesEstados.json";

    @Override
    public Collection<String> cargarEstadosCliente() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(CLIENTES_ESTADOS_FILE_PATH);
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
    public void guardarEstadoCliente(Collection<String> listaEstados) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(CLIENTES_ESTADOS_FILE_PATH), listaEstados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarEstadoCliente(String estado) {
        Collection<String> listaEstados = cargarEstadosCliente();
        listaEstados.add(estado);
        guardarEstadoCliente(listaEstados);
    }

    @Override
    public void eliminarEstadoCliente(String estado) {
        if(cargarEstadosCliente().contains(estado)){
            Collection<String> listaEstados = cargarEstadosCliente();
            listaEstados.remove(estado);
            guardarEstadoCliente(listaEstados);
        }
        throw new IllegalArgumentException("El estado no existe");
    }


}
