package uca.dss.SpringCli.UI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uca.core.Cliente;
import uca.core.ClienteRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteRepositorioJackson implements ClienteRepositorio {

    private static final String CLIENTES_FILE_PATH = "clientes.json";
    private static final String ESTADOS_FILE_PATH = "estados.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void guardarCliente(Collection<Cliente> clientes) {
        try {
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
                return objectMapper.readValue(file, new TypeReference<Collection<Cliente>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Cliente> cargarCliente(String tipo, String dato) {
        Collection<Cliente> clientes = cargarCliente();
        if (clientes != null) {
            switch (tipo) {
                case "nombre":
                    clientes.removeIf(c -> !c.getNombre().equals(dato));
                    break;
                case "apellido":
                    clientes.removeIf(c -> !c.getApellido().equals(dato));
                    break;
                case "telefono":
                    clientes.removeIf(c -> !c.getTelefono().equals(dato));
                    break;
                case "fecha":
                    clientes.removeIf(c -> !c.getFechaNacimiento().equals(dato));
                    break;
                case "dni":
                    clientes.removeIf(c -> !c.getDni().equals(dato));
                    break;
                case "email":
                    clientes.removeIf(c -> !c.getEmail().equals(dato));
                    break;
                case "id":
                    clientes.removeIf(c -> c.getIdC() != Integer.parseInt(dato));
                    break;
            }
        }
        return new ArrayList<Cliente>();
    }

    @Override
    public int getCantidadCliente() {
        return cargarCliente().size();
    }

    @Override
    public Collection<String> cargarEstadosCliente() { //pendiente de borrar
        return null;
    }

    @Override
    public void guardarEstadoCliente(Collection<String> listaEstados) { //pendiente de borrar
    }

    @Override
    public void guardarEstadoCliente(String estado) { //pendiente de borrar

    }

    @Override
    public boolean existeCliente(String dni) {
return cargarCliente().stream().anyMatch(c -> c.getDni().equals(dni));
    }

    @Override
    public void eliminarCliente(Cliente c) {
        guardarCliente((Collection<Cliente>) cargarCliente().stream().filter(cliente -> cliente.getIdC() != c.getIdC()).collect(Collectors.toList()));
    }

    @Override
    public void eliminarEstadoCliente(String estado) { //pendiente de borrar

    }

    @Override
    public boolean existeEstadoCliente(String estado) { //pendiente de borrar
        return false;
    }
}