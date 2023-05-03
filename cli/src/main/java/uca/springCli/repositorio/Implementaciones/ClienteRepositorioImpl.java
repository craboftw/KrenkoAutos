package uca.springCli.repositorio.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import uca.core.dominio.Cliente;
import uca.core.dao.iClienteRepositorio;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Repository
public class ClienteRepositorioImpl implements iClienteRepositorio {

    private static final String CLIENTES_FILE_PATH = "clientes.json";

    private ObjectMapper objectMapper;
    private List<Cliente> clientes =  findAll();

    public ClienteRepositorioImpl() {
        objectMapper = new ObjectMapper();
        clientes = new ArrayList<>();

        // Cargar los clientes existentes del archivo JSON
        try {
            File file = new File(CLIENTES_FILE_PATH);
            if (file.exists()) {
                clientes = objectMapper.readValue(file, new TypeReference<List<Cliente>>(){});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Cliente> S save(S entity) {
        // Agregar el nuevo cliente a la lista
        clientes.add(entity);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public <S extends Cliente> List<S> saveAll(Iterable<S> entities) {
        // Agregar los nuevos clientes a la lista
        entities.forEach(clientes::add);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (List<S>) entities;
    }

    @Override
    public Optional<Cliente> findById(Long aLong) {
        // Buscar el cliente por su id
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(aLong)).findFirst();

        return cliente;
    }

    @Override
    public boolean existsById(Long aLong) {
        // Verificar si existe el cliente por su id
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(aLong)).findFirst();
        return cliente.isPresent();
    }

    @Override
    public List<Cliente> findAll() {
        // Devolver la lista completa de clientes
        return clientes;
    }

    @Override
    public List<Cliente> findAllById(Iterable<Long> longs) {
        for (Long id : longs) {
            // Buscar el cliente por su id
            Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(id)).findFirst();

            // Si el cliente existe, devolverlo
            if (cliente.isPresent()) {
                return Collections.singletonList(cliente.get());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public long count() {
        // Devolver la cantidad de clientes
        return clientes.size();
    }

    @Override
    public void deleteById(Long aLong) {
        // Eliminar el cliente por su id
        clientes.removeIf(c -> c.getIdC().equals(aLong));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cliente entity) {
        // Eliminar el cliente por su id
        clientes.removeIf(c -> c.getIdC().equals(entity.getIdC()));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        for (Long id : longs) {
            // Eliminar el cliente por su id
            clientes.removeIf(c -> c.getIdC().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll(Iterable<? extends Cliente> entities) {
        for (Cliente cliente : entities) {
            // Eliminar el cliente por su id
            clientes.removeIf(c -> c.getIdC().equals(cliente.getIdC()));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {
        // Eliminar todos los clientes de la lista
        clientes.clear();

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        // El metodo flush actualiza la lista de clientes con el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Cliente> S saveAndFlush(S entity) {
        // Agregar el nuevo cliente a la lista y actualizar el archivo JSON
        clientes.add(entity);
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public <S extends Cliente> List<S> saveAllAndFlush(Iterable<S> entities) {
        // Agregar los nuevos clientes a la lista y actualizar el archivo JSON
        entities.forEach(clientes::add);
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<S>) entities;
    }

    @Override
    public void deleteAllInBatch(Iterable<Cliente> entities) {
        //El metodo deleteAllInBatch elimina todos los clientes de la lista y actualiza el archivo JSON
        clientes.clear();
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        for (Long id : longs) {
            // Eliminar el cliente por su id
            clientes.removeIf(c -> c.getIdC().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllInBatch() {
        //El metodo deleteAllInBatch elimina todos los clientes de la lista y actualiza el archivo JSON
        clientes.clear();
        try {
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Cliente getOne(Long aLong) {
        // Buscar el cliente por su id
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(aLong)).findFirst();
        return cliente.orElse(Cliente.ClienteNulo);
    }

    @Override
    public Cliente getById(Long aLong) {
        // Buscar el cliente por su id
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(aLong)).findFirst();
        return cliente.orElse(Cliente.ClienteNulo);
    }

    @Override
    public Cliente getReferenceById(Long aLong) {
        // Buscar el cliente por su id
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(aLong)).findFirst();
        return cliente.orElse(Cliente.ClienteNulo);
    }

    @Override
    public <S extends Cliente> Optional<S> findOne(Example<S> example) {
        // Buscar el cliente por su id
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getIdC().equals(example)).findFirst();
        return (Optional<S>) cliente;
    }

    @Override
    public <S extends Cliente> List<S> findAll(Example<S> example) {
        return (List<S>) clientes;
    }

    @Override
    public <S extends Cliente> List<S> findAll(Example<S> example, Sort sort) {
        return (List<S>) clientes;
    }

    @Override
    public <S extends Cliente> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Cliente> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Cliente> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Cliente, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Cliente> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return null;
    }
}
