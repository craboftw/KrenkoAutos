package uca.springCli.repositorio.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import uca.core.dominio.Reserva;
import uca.core.dao.iReservaRepositorio;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Repository
public class ReservaRepositorioImpl implements iReservaRepositorio {

    private static final String RESERVAS_FILE_PATH = "reservas.json";

    private ObjectMapper objectMapper;
    private List<Reserva> reservas =  findAll();

    public ReservaRepositorioImpl() {
        objectMapper = new ObjectMapper();
        reservas = new ArrayList<>();

        // Cargar los reservas existentes del archivo JSON
        try {
            File file = new File(RESERVAS_FILE_PATH);
            if (file.exists()) {
                reservas = objectMapper.readValue(file, new TypeReference<List<Reserva>>(){});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Reserva> S save(S entity) {
        // Agregar el nuevo reserva a la lista
        reservas.add(entity);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public <S extends Reserva> List<S> saveAll(Iterable<S> entities) {
        // Agregar los nuevos reservas a la lista
        entities.forEach(reservas::add);

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (List<S>) entities;
    }

    @Override
    public Optional<Reserva> findById(Long aLong) {
        // Buscar el reserva por su id
        Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(aLong)).findFirst();

        return reserva;
    }

    @Override
    public boolean existsById(Long aLong) {
        // Verificar si existe el reserva por su id
        Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(aLong)).findFirst();
        return reserva.isPresent();
    }

    @Override
    public List<Reserva> findAll() {
        // Devolver la lista completa de reservas
        return reservas;
    }

    @Override
    public List<Reserva> findAllById(Iterable<Long> longs) {
        for (Long id : longs) {
            // Buscar el reserva por su id
            Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(id)).findFirst();

            // Si el reserva existe, devolverlo
            if (reserva.isPresent()) {
                return Collections.singletonList(reserva.get());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public long count() {
        // Devolver la cantidad de reservas
        return reservas.size();
    }

    @Override
    public void deleteById(Long aLong) {
        // Eliminar el reserva por su id
        reservas.removeIf(c -> c.getIdR().equals(aLong));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reserva entity) {
        // Eliminar el reserva por su id
        reservas.removeIf(c -> c.getIdR().equals(entity.getIdR()));

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        for (Long id : longs) {
            // Eliminar el reserva por su id
            reservas.removeIf(c -> c.getIdR().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll(Iterable<? extends Reserva> entities) {
        for (Reserva reserva : entities) {
            // Eliminar el reserva por su id
            reservas.removeIf(c -> c.getIdR().equals(reserva.getIdR()));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {
        // Eliminar todos los reservas de la lista
        reservas.clear();

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        // El metodo flush actualiza la lista de reservas con el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends Reserva> S saveAndFlush(S entity) {
        // Agregar el nuevo reserva a la lista y actualizar el archivo JSON
        reservas.add(entity);
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public <S extends Reserva> List<S> saveAllAndFlush(Iterable<S> entities) {
        // Agregar los nuevos reservas a la lista y actualizar el archivo JSON
        entities.forEach(reservas::add);
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<S>) entities;
    }

    @Override
    public void deleteAllInBatch(Iterable<Reserva> entities) {
        //El metodo deleteAllInBatch elimina todos los reservas de la lista y actualiza el archivo JSON
        reservas.clear();
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        for (Long id : longs) {
            // Eliminar el reserva por su id
            reservas.removeIf(c -> c.getIdR().equals(id));
        }

        // Guardar la lista actualizada en el archivo JSON
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllInBatch() {
        //El metodo deleteAllInBatch elimina todos los reservas de la lista y actualiza el archivo JSON
        reservas.clear();
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Reserva getOne(Long aLong) {
        // Buscar el reserva por su id
        Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(aLong)).findFirst();
        return reserva.orElse(Reserva.ReservaNulo);
    }

    @Override
    public Reserva getById(Long aLong) {
        // Buscar el reserva por su id
        Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(aLong)).findFirst();
        return reserva.orElse(Reserva.ReservaNulo);
    }

    @Override
    public Reserva getReferenceById(Long aLong) {
        // Buscar el reserva por su id
        Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(aLong)).findFirst();
        return reserva.orElse(Reserva.ReservaNulo);
    }

    @Override
    public <S extends Reserva> Optional<S> findOne(Example<S> example) {
        // Buscar el reserva por su id
        Optional<Reserva> reserva = reservas.stream().filter(c -> c.getIdR().equals(example)).findFirst();
        return (Optional<S>) reserva;
    }

    @Override
    public <S extends Reserva> List<S> findAll(Example<S> example) {
        return (List<S>) reservas;
    }

    @Override
    public <S extends Reserva> List<S> findAll(Example<S> example, Sort sort) {
        return (List<S>) reservas;
    }

    @Override
    public <S extends Reserva> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Reserva> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Reserva> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Reserva, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Reserva> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Reserva> findAll(Pageable pageable) {
        return null;
    }
}
