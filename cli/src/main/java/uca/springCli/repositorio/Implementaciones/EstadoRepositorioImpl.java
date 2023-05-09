package uca.springCli.repositorio.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import uca.core.dao.iEstadoRepositorio;
import uca.core.dominio.Estado;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Repository
public class EstadoRepositorioImpl implements iEstadoRepositorio {

    private static final String ESTADOS_FILE_PATH = "estados.json";

    private ObjectMapper objectMapper;
    private List<Estado> Estados =  findAll();

    public EstadoRepositorioImpl() {
        objectMapper = new ObjectMapper();
        Estados = new ArrayList<>();

        // Cargar los Estados existentes del archivo JSON
        try {
            File file = new File(ESTADOS_FILE_PATH);
            if (file.exists()) {
                Estados = objectMapper.readValue(file, new TypeReference<List<Estado>>(){});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Estado> S save(S entity) {
        // Agregar el nuevo Estado a la lista
        Estados.add(entity);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public <S extends Estado> List<S> saveAll(Iterable<S> entities) {
        // Agregar los nuevos Estados a la lista
        entities.forEach(Estados::add);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (List<S>) entities;
    }

    @Override
    public Optional<Estado> findById(Long aLong) {
        // Buscar el Estado por su id
        Optional<Estado> Estado = Estados.stream().filter(e -> e.getId().equals(aLong)).findFirst();

        return Estado;
    }

    @Override
    public boolean existsById(Long aLong) {
        // Verificar si existe el Estado por su id
        Optional<Estado> Estado = Estados.stream().filter(e -> e.getId().equals(aLong)).findFirst();
        return Estado.isPresent();
    }

    @Override
    public List<Estado> findAll() {
        // Devolver la lista completa de Estados
        return Estados;
    }

    @Override
    public List<Estado> findAllById(Iterable<Long> longs) {
        for (Long id : longs) {
            // Buscar el Estado por su id
            Optional<Estado> Estado = Estados.stream().filter(e -> e.getId().equals(id)).findFirst();

            // Si el Estado existe, devolverlo
            if (Estado.isPresent()) {
                return Collections.singletonList(Estado.get());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public long count() {
        // Devolver la cantidad de Estados
        return Estados.size();
    }

    @Override
    public void deleteById(Long aLong) {
        // Eliminar el Estado por su id
        Estados.removeIf(e -> e.getId().equals(aLong));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Estado entity) {
        // Eliminar el Estado por su id
        Estados.removeIf(e -> e.getId().equals(entity.getId()));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        for (Long id : longs) {
            // Eliminar el Estado por su id
            Estados.removeIf(e -> e.getId().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll(Iterable<? extends Estado> entities) {
        for (Estado Estado : entities) {
            // Eliminar el Estado por su id
            Estados.removeIf(e -> e.getId().equals(Estado.getId()));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {
        // Eliminar todos los Estados de la lista
        Estados.clear();

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        // El metodo flush actualiza la lista de Estados con el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Estado> S saveAndFlush(S entity) {
        // Agregar el nuevo Estado a la lista y actualizar el archivo JSON
        Estados.add(entity);
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public <S extends Estado> List<S> saveAllAndFlush(Iterable<S> entities) {
        // Agregar los nuevos Estados a la lista y actualizar el archivo JSON
        entities.forEach(Estados::add);
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<S>) entities;
    }

    @Override
    public void deleteAllInBatch(Iterable<Estado> entities) {
        //El metodo deleteAllInBatch elimina todos los Estados de la lista y actualiza el archivo JSON
        Estados.clear();
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        for (Long id : longs) {
            // Eliminar el Estado por su id
            Estados.removeIf(e -> e.getId().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllInBatch() {
        //El metodo deleteAllInBatch elimina todos los Estados de la lista y actualiza el archivo JSON
        Estados.clear();
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), Estados);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Estado getOne(Long aLong) {
        // Buscar el Estado por su id
        Optional<Estado> estado = Estados.stream().filter(e -> e.getId().equals(aLong)).findFirst();
        return estado.orElse(Estado.EstadoNulo);
    }

    @Override
    public Estado getById(Long aLong) {
        // Buscar el Estado por su id
        Optional<Estado> estado = Estados.stream().filter(e -> e.getId().equals(aLong)).findFirst();
        return estado.orElse(Estado.EstadoNulo);
    }

    @Override
    public Estado getReferenceById(Long aLong) {
        // Buscar el Estado por su id
        Optional<Estado> estado = Estados.stream().filter(e -> e.getId().equals(aLong)).findFirst();
        return estado.orElse(Estado.EstadoNulo);
    }

    @Override
    public <S extends Estado> Optional<S> findOne(Example<S> example) {
        // Buscar el Estado por su id
        Optional<Estado> estado = Estados.stream().filter(e -> e.getId().equals(example)).findFirst();
        return (Optional<S>) estado;
    }

    @Override
    public <S extends Estado> List<S> findAll(Example<S> example) {
        return (List<S>) Estados;
    }

    @Override
    public <S extends Estado> List<S> findAll(Example<S> example, Sort sort) {
        return (List<S>) Estados;
    }

    @Override
    public <S extends Estado> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Estado> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Estado> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Estado, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Estado> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Estado> findAll(Pageable pageable) {
        return null;
    }
}
