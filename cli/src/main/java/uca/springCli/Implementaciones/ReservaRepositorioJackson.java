package uca.springCli.Implementaciones;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uca.core.Reserva;
import uca.core.ReservaRepositorio;


public class ReservaRepositorioJackson implements ReservaRepositorio {

    String RESERVAS_FILE_PATH = "reservas.json";

    @Override
    public Collection<Reserva> cargarReserva() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(RESERVAS_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<Collection<Reserva>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void guardarReserva(Collection<Reserva> listaReservas) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), listaReservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        Collection<Reserva> listaReservas = cargarReserva();
        listaReservas.add(reserva);
        guardarReserva(listaReservas);
    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        if (cargarReserva().contains(reserva)) {
            Collection<Reserva> listaReservas = cargarReserva();
            listaReservas.remove(reserva);
            guardarReserva(listaReservas);
        }
        throw new IllegalArgumentException("La reserva no existe");
    }

    @Override
    public Collection<Reserva> buscarReserva(String tipo, String dato)
    {
        Collection<Reserva> listaReservas = cargarReserva();
        Collection<Reserva> listaReservasFiltrada = new ArrayList<>();
        switch (tipo) {
            case "id":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getIdR() == Integer.parseInt(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "nombre":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getCliente().getNombre().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "apellidos":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getCliente().getApellido().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "dni":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getCliente().getDni().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "telefono":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getCliente().getTelefono().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "email":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getCliente().getEmail().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "fecha":
                for (Reserva reserva : listaReservas) {
                    LocalDate fecha = LocalDate.parse(dato);
                    //between fechaInicio y fechaFin
                    if ((reserva.getFechaIni().equals(fecha)) || (reserva.getFechaIni().isBefore(fecha)) && ((reserva.getFechaFin().isAfter(fecha) || (reserva.getFechaFin().equals(fecha))))) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "matricula":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getAutocaravana().getMatricula().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "modelo":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getAutocaravana().getModelo().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "precioPorDia":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getAutocaravana().getPrecioPorDia().equals(BigDecimal.valueOf(Integer.parseInt(dato)))) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "estado":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getEstadoReserva().equals(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("No existe el tipo de busqueda");

        }
    return listaReservasFiltrada;
    }}
