package uca.springCli.repositorio.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import uca.core.dominio.Autocaravana;
import uca.core.dao.iAutocaravanaRepositorio;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Repository
public class AutocaravanaRepositorioImpl implements iAutocaravanaRepositorio {

    private static final String AUTOCARAVANAS_FILE_PATH = "autocaravanas.json";

    private ObjectMapper objectMapper;
    private List<Autocaravana> autocaravanas =  findAll();

    public AutocaravanaRepositorioImpl() {
        objectMapper = new ObjectMapper();
        autocaravanas = new ArrayList<>();

        // Cargar los autocaravanas existentes del archivo JSON
        try {
            File file = new File(AUTOCARAVANAS_FILE_PATH);
            if (file.exists()) {
                autocaravanas = objectMapper.readValue(file, new TypeReference<List<Autocaravana>>(){});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Autocaravana> S save(S entity) {
        // Agregar el nuevo autocaravana a la lista
        autocaravanas.add(entity);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public <S extends Autocaravana> List<S> saveAll(Iterable<S> entities) {
        // Agregar los nuevos autocaravanas a la lista
        entities.forEach(autocaravanas::add);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (List<S>) entities;
    }

    @Override
    public Optional<Autocaravana> findById(Long aLong) {
        // Buscar el autocaravana por su id
        Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(aLong)).findFirst();

        return autocaravana;
    }

    @Override
    public boolean existsById(Long aLong) {
        // Verificar si existe el autocaravana por su id
        Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(aLong)).findFirst();
        return autocaravana.isPresent();
    }

    @Override
    public List<Autocaravana> findAll() {
        // Devolver la lista completa de autocaravanas
        return autocaravanas;
    }

    @Override
    public List<Autocaravana> findAllById(Iterable<Long> longs) {
        for (Long id : longs) {
            // Buscar el autocaravana por su id
            Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(id)).findFirst();

            // Si el autocaravana existe, devolverlo
            if (autocaravana.isPresent()) {
                return Collections.singletonList(autocaravana.get());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public long count() {
        // Devolver la cantidad de autocaravanas
        return autocaravanas.size();
    }

    @Override
    public void deleteById(Long aLong) {
        // Eliminar el autocaravana por su id
        autocaravanas.removeIf(c -> c.getIdA().equals(aLong));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Autocaravana entity) {
        // Eliminar el autocaravana por su id
        autocaravanas.removeIf(c -> c.getIdA().equals(entity.getIdA()));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        for (Long id : longs) {
            // Eliminar el autocaravana por su id
            autocaravanas.removeIf(c -> c.getIdA().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll(Iterable<? extends Autocaravana> entities) {
        for (Autocaravana autocaravana : entities) {
            // Eliminar el autocaravana por su id
            autocaravanas.removeIf(c -> c.getIdA().equals(autocaravana.getIdA()));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {
        // Eliminar todos los autocaravanas de la lista
        autocaravanas.clear();

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        // El metodo flush actualiza la lista de autocaravanas con el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Autocaravana> S saveAndFlush(S entity) {
        // Agregar el nuevo autocaravana a la lista y actualizar el archivo JSON
        autocaravanas.add(entity);
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public <S extends Autocaravana> List<S> saveAllAndFlush(Iterable<S> entities) {
        // Agregar los nuevos autocaravanas a la lista y actualizar el archivo JSON
        entities.forEach(autocaravanas::add);
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<S>) entities;
    }

    @Override
    public void deleteAllInBatch(Iterable<Autocaravana> entities) {
        //El metodo deleteAllInBatch elimina todos los autocaravanas de la lista y actualiza el archivo JSON
        autocaravanas.clear();
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        for (Long id : longs) {
            // Eliminar el autocaravana por su id
            autocaravanas.removeIf(c -> c.getIdA().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllInBatch() {
        //El metodo deleteAllInBatch elimina todos los autocaravanas de la lista y actualiza el archivo JSON
        autocaravanas.clear();
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Autocaravana getOne(Long aLong) {
        // Buscar el autocaravana por su id
        Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(aLong)).findFirst();
        return autocaravana.orElse(Autocaravana.AutocaravanaNulo);
    }

    @Override
    public Autocaravana getById(Long aLong) {
        // Buscar el autocaravana por su id
        Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(aLong)).findFirst();
        return autocaravana.orElse(Autocaravana.AutocaravanaNulo);
    }

    @Override
    public Autocaravana getReferenceById(Long aLong) {
        // Buscar el autocaravana por su id
        Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(aLong)).findFirst();
        return autocaravana.orElse(Autocaravana.AutocaravanaNulo);
    }

    @Override
    public <S extends Autocaravana> Optional<S> findOne(Example<S> example) {
        // Buscar el autocaravana por su id
        Optional<Autocaravana> autocaravana = autocaravanas.stream().filter(c -> c.getIdA().equals(example)).findFirst();
        return (Optional<S>) autocaravana;
    }

    @Override
    public <S extends Autocaravana> List<S> findAll(Example<S> example) {
        return (List<S>) autocaravanas;
    }

    @Override
    public <S extends Autocaravana> List<S> findAll(Example<S> example, Sort sort) {
        return (List<S>) autocaravanas;
    }

    @Override
    public <S extends Autocaravana> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Autocaravana> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Autocaravana> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Autocaravana, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Autocaravana> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Autocaravana> findAll(Pageable pageable) {
        return null;
    }
}
