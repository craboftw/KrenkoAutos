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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;

import uca.core.Autocaravana;
import uca.core.Cliente;
import uca.core.Reserva;
import uca.core.ReservaRepositorio;


public class ReservaRepositorioJackson implements ReservaRepositorio {

    String RESERVAS_FILE_PATH = "reservas.json";

    @Override
    public Collection<Reserva> cargarReserva() {
        try {
            File file = new File(RESERVAS_FILE_PATH);
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                return arreglarReservas( objectMapper.readValue(file, new TypeReference<Collection<Reserva>>() {
                }));
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
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.writeValue(new File(RESERVAS_FILE_PATH), arreglarReservas(listaReservas));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        List<Reserva> listaReservas = new ArrayList<>(cargarReserva());
        //Si existe una reserva tiene el mismo id, la borramos y la volvemos a guardar
        if (listaReservas.stream().anyMatch(r -> r.getIdR() == reserva.getIdR()))  {
            listaReservas.removeIf(r -> r.getIdR() == reserva.getIdR());
            guardarReserva(listaReservas);
        }
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
                    if ((reserva.fechaIniF().equals(fecha)) || (reserva.fechaIniF().isBefore(fecha)) && ((reserva.fechaFinF().isAfter(fecha) || (reserva.fechaFinF().equals(fecha))))) {
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
    }

    private Collection<Reserva> arreglarReservas(Collection<Reserva> listaReservas) {
            List<Autocaravana> listaAutocaravanas = new ArrayList<>(new AutocaravanaRepositorioJackson().cargarAutocaravana());
            List<Cliente> listaClientes = new ArrayList<>(new ClienteRepositorioJackson().cargarCliente());
            for  (Reserva reserva : listaReservas) {
                for (Autocaravana autocaravana : listaAutocaravanas) {
                    if (reserva.getAutocaravana().getIdA() == autocaravana.getIdA()) {
                        reserva.setAutocaravana(autocaravana);
                    }
                }
                for (Cliente cliente : listaClientes) {
                    if (reserva.getCliente().getIdC() == cliente.getIdC()) {
                        reserva.setCliente(cliente);
                    }
                }
            
        }
return listaReservas;
    }


}


