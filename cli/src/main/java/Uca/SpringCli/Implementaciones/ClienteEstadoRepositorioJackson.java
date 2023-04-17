package Uca.SpringCli.Implementaciones;
import Uca.Core.Cliente;
import Uca.Core.ClienteEstadoRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Override
    public void eliminarCliente(Cliente c) {

    }

    @Override
    public void eliminarEstadoCliente(String estado) {

    }

    @Override
    public void addEstadoCliente(String estado) {

    }
}
