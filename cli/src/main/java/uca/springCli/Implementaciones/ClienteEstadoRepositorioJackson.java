package uca.springCli.Implementaciones;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import uca.core.Cliente;
import uca.core.ClienteEstadoRepositorio;

public class ClienteEstadoRepositorioJackson implements ClienteEstadoRepositorio{

    String CLIENTES_ESTADOS_FILE_PATH = "clientesEstados.json";

    @Override
    public Collection<String> cargarEstadosCliente() {
        return null;
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

    }


    public void eliminarCliente(Cliente c) {

    }

    @Override
    public void eliminarEstadoCliente(String estado) {

    }


    public void addEstadoCliente(String estado) {

    }
}
