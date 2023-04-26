package uca.springCli.repositorio.Implementaciones;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;
import uca.core.dominio.Reserva;
import uca.core.dao.iReservaRepositorio;

@Repository
public class ReservaRepositorioImpl implements iReservaRepositorio {

    String RESERVAS_FILE_PATH = "reservas.json";
    ObjectMapper objectMapper = new ObjectMapper();
    Map<Integer,Reserva> reservas = cargaInicial();

    private Map<Integer,Reserva> cargaInicial() {
        try {
            File file = new File(RESERVAS_FILE_PATH);
            if (file.exists())
            {
                if (file.length() == 0)
                {
                    return Map.of();
                }
                return objectMapper.readValue(file, new TypeReference<List<Reserva>>() {}).stream().collect(Collectors.toMap(Reserva::getIdR, reserva -> reserva));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of();
    }


    @Override
    public void guardarReserva(Collection<Reserva> caravanas) {
        try {
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), caravanas);
            this.reservas= caravanas.stream().collect(Collectors.toMap(Reserva::getIdR, reserva -> reserva));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarReserva(Reserva caravana) {
        reservas.put(caravana.getIdR(), caravana);
        guardarReserva(new ArrayList<>(reservas.values()));
    }

    @Override
    public Collection<Reserva> cargarReserva() {return reservas.values();  }

    @Override
    public Collection<Reserva> buscarReserva(String tipo, String dato) {
        if (!reservas.isEmpty()) {
            switch (tipo) {
                case "id" -> {  return reservas.values().stream().filter(reserva -> reserva.getIdR() == Integer.parseInt(dato)).collect(Collectors.toList());}
                case "idC" -> { return reservas.values().stream().filter(reserva -> reserva.getIdCliente() == Integer.parseInt(dato)).collect(Collectors.toList());}
                case "idA" -> {return reservas.values().stream().filter(reserva -> reserva.getIdAutocaravana() == Integer.parseInt(dato)).collect(Collectors.toList());}
                case "fecha" -> {  return reservas.values().stream().filter(reserva -> reserva.fechaIniF().isBefore(LocalDate.parse(dato)) && reserva.fechaFinF().isAfter(LocalDate.parse(dato))).collect(Collectors.toList());}
                case "precio" -> { return reservas.values().stream().filter(reserva -> reserva.getPrecioTotal().equals(BigDecimal.valueOf(Double.parseDouble(dato)))).collect(Collectors.toList());}
                default -> {throw new IllegalStateException("Tipo no valido: " + tipo + ". Debe ser idR, idC, idA, fecha o precio");}
            }
        }
        return new ArrayList<>();
    }

    public Reserva buscarReserva(int idR) {
        return reservas.get(idR);
    }

    @Override
    public void eliminarReserva(int idR) {
        reservas.remove(idR);
        guardarReserva(new ArrayList<>(reservas.values()));
    }


}


