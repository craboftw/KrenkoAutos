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

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;
import uca.core.dao.ReservaRepositorio;


public class ReservaRepositorioImpl implements ReservaRepositorio {

    String RESERVAS_FILE_PATH = "reservas.json";

    @Override
    public Collection<Reserva> cargarReserva() {        try {
        File file = new File(RESERVAS_FILE_PATH);
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.readValue(file, new TypeReference<Collection<Reserva>>() { });
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        return new ArrayList<>();
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
            case "fechaInicio":
                for (Reserva reserva : listaReservas) {
                    if (reserva.fechaIniF().equals(LocalDate.parse(dato))) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "fechaFin":
                for (Reserva reserva : listaReservas) {
                    if (reserva.fechaFinF().equals(LocalDate.parse(dato))) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "precio":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getPrecioTotal().equals(new BigDecimal(dato))) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
            break;
            case "idC":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getCliente() == Integer.parseInt(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            case "idA":
                for (Reserva reserva : listaReservas) {
                    if (reserva.getAutocaravana() == Integer.parseInt(dato)) {
                        listaReservasFiltrada.add(reserva);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("No existe el tipo de busqueda. Debe ser id|fechaInicio|fechaFin|precio|idC|idA");
        }
    return listaReservasFiltrada;
    }

    private Collection<Reserva> arreglarReservas(Collection<Reserva> listaReservas) {
            List<Autocaravana> listaAutocaravanas = new ArrayList<>(new AutocaravanaRepositorioImpl().cargarAutocaravana());
            List<Cliente> listaClientes = new ArrayList<>(new ClienteRepositorioImpl().cargarCliente());
            for  (Reserva reserva : listaReservas) {
                for (Autocaravana autocaravana : listaAutocaravanas) {
                    if (reserva.getAutocaravana() == autocaravana.getIdA()) {
                        reserva.setAutocaravana(autocaravana.getIdA());
                    }
                }
                for (Cliente cliente : listaClientes) {
                    if (reserva.getCliente() == cliente.getIdC()) {
                        reserva.setCliente(cliente.getIdC());
                    }
                }
            
        }
return listaReservas;
    }


}


