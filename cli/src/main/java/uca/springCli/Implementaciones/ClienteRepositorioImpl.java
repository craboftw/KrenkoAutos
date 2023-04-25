package uca.springCli.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import uca.core.dominio.Cliente;
import uca.core.dao.ClienteRepositorio;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ClienteRepositorioImpl implements ClienteRepositorio {

    String CLIENTES_FILE_PATH = "clientes.json";

    @Override
    public void guardarCliente(Collection<Cliente> clientes) {
        try {
            if (!new File(CLIENTES_FILE_PATH).exists()) {
                new File(CLIENTES_FILE_PATH).createNewFile();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void guardarCliente(Cliente cliente) {
        Collection<Cliente> clientes = cargarCliente();
        clientes.add(cliente);
        guardarCliente(clientes);

    }

    @Override
    public Collection<Cliente> cargarCliente() {
        try {
            File file = new File(CLIENTES_FILE_PATH);
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                return objectMapper.readValue(file, new TypeReference<Collection<Cliente>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Cliente> buscarCliente(String estado, String dato) {
        Collection<Cliente> clientes = cargarCliente();
        if (clientes != null) {
            switch (estado) {
                case "idC" -> clientes.removeIf(c -> !(c.getIdC() == (Integer.parseInt(dato))));
                case "nombre" -> clientes.removeIf(c -> !c.getNombre().equals(dato));
                case "dni" -> clientes.removeIf(c -> !c.getDni().equals(dato));
                case "fechaNacimiento" -> clientes.removeIf(c -> !c.getFechaNacimiento().equals(dato));
                case "telefono" -> clientes.removeIf(c -> !c.getTelefono().equals(dato));
                case "email" -> clientes.removeIf(c -> !c.getEmail().equals(dato));
                default -> {
                    throw new IllegalStateException("Estado no valido: " + estado
                            + ". Debe ser idC, nombre, dni, fechaNacimiento, telefono o email");
                }
            }
        }
        return clientes;
    }

    @Override
    public void eliminarCliente(String dni) {
        // delete de cliente por dni
        var clientes = cargarCliente();
        clientes.removeIf(c -> c.getDni().equals(dni));
        guardarCliente(clientes);
    }

    @Override
    public void eliminarCliente(int idC) {
        guardarCliente(cargarCliente().stream().filter(c -> c.getIdC() != idC).toList());
    }

}
