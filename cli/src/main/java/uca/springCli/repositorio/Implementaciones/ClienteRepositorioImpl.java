package uca.springCli.repositorio.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uca.core.dominio.Cliente;
import uca.core.dao.iClienteRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ClienteRepositorioImpl implements iClienteRepositorio {
    /*
    * Replicar el comportamiento de AutocaravanaRepositorioImpl
    * */

    private static final String CLIENTES_FILE_PATH = "clientes.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Map<Integer,Cliente> clientes = cargaInicial();

    private Map<Integer,Cliente> cargaInicial(){
        try {
            File file = new File(CLIENTES_FILE_PATH);
            if (file.exists())
            {
                if (file.length() == 0)
                {
                    return Map.of();
                }
                return objectMapper.readValue(file, new TypeReference<List<Cliente>>() {}).stream().collect(Collectors.toMap(Cliente::getIdC, cliente -> cliente));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of();
    }

    @Override
    public void guardarCliente(Collection<Cliente> clientes) {
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
            this.clientes= clientes.stream().collect(Collectors.toMap(Cliente::getIdC, cliente -> cliente));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clientes.put(cliente.getIdC(), cliente);
        guardarCliente(new ArrayList<>(clientes.values()));
    }

    @Override
    public Collection<Cliente> cargarCliente() {return clientes.values();}

    @Override
    public Collection<Cliente> buscarCliente(String tipo, String dato) {
        if (!clientes.isEmpty()) {
         switch (tipo) {
                case "dni" -> { return clientes.values().stream().filter(cliente -> cliente.getDni().equals(dato)).collect(Collectors.toList()); }
                case "nombre" -> { return clientes.values().stream().filter(cliente -> cliente.getNombre().equals(dato)).collect(Collectors.toList()); }
                case "apellidos" -> { return clientes.values().stream().filter(cliente -> cliente.getApellido().equals(dato)).collect(Collectors.toList()); }
                case "telefono" -> { return clientes.values().stream().filter(cliente -> cliente.getTelefono().equals(dato)).collect(Collectors.toList()); }
                case "email" -> { return clientes.values().stream().filter(cliente -> cliente.getEmail().equals(dato)).collect(Collectors.toList()); }
               case "idC" -> { return clientes.values().stream().filter(cliente -> cliente.getIdC() == Integer.parseInt(dato)).collect(Collectors.toList()); }
             default -> {throw new IllegalArgumentException("Tipo no valido: " + tipo + ". Debe ser dni, nombre, apellidos, telefono, email o idC");}
              }
        }
        return new ArrayList<>();
    }

    @Override
    public void eliminarCliente(String dni) {
        if(clientes.values().stream().anyMatch(cliente -> cliente.getDni().equals(dni))){
            clientes.values().removeIf(cliente -> cliente.getDni().equals(dni));
            guardarCliente(new ArrayList<>(clientes.values()));
        }
            }

    @Override
    public void eliminarCliente(int idC) {
        if(clientes.values().stream().anyMatch(cliente -> cliente.getIdC() == idC)){
            clientes.values().removeIf(cliente -> cliente.getIdC() == idC);
            guardarCliente(new ArrayList<>(clientes.values()));
        }
    }
}
