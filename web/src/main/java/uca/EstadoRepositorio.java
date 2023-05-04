package uca;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.stringtemplate.v4.misc.MultiMap;
import uca.core.dao.iEstadoRepositorio;
import uca.core.dominio.Estado;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EstadoRepositorio implements iEstadoRepositorio {
    ObjectMapper objectMapper = new ObjectMapper();
    private static final String ESTADOS_FILE_PATH = "estados.json";
    MultiMap<String, Estado> estados = cargaInicial();

    private MultiMap<String, Estado> cargaInicial() {
        try {
            File file = new File(ESTADOS_FILE_PATH);
            List<Estado> lista= new ArrayList<>();
            if (file.exists()) {
                if (file.length() == 0) {
                    return new MultiMap<>();
                }
                lista= objectMapper.readValue(file, new TypeReference<List<Estado>>() {
                });
                }
            MultiMap<String, Estado> multiMap = new MultiMap<>();
            List<Estado> listaAutocaravana = new ArrayList<>();
            List<Estado> listaCliente = new ArrayList<>();
            List<Estado> listaReserva = new ArrayList<>();
            for (Estado estado : lista) {
                if (estado.getTipo().equals("Autocaravana"))
                    listaAutocaravana.add(estado);
                if (estado.getTipo().equals("Cliente"))
                    listaCliente.add(estado);
                if (estado.getTipo().equals("Reserva"))
                    listaReserva.add(estado);
            }
                multiMap.put("Autocaravana", listaAutocaravana);
                multiMap.put("Cliente", listaCliente);
                multiMap.put("Reserva", listaReserva);
                return multiMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MultiMap<>();

    }


    @Override
    public void guardarEstado(Estado estado) {
        List<Estado> lista = new ArrayList<>();
        lista = estados.get(estado.getTipo());
        if (!lista.contains(estado))
            lista.add(estado);
        estados.put(estado.getTipo(), lista);
        guardarEstado(lista);
    }

    @Override
    public void guardarEstado(List<Estado> lista) {
        try {
            objectMapper.writeValue(new File(ESTADOS_FILE_PATH), lista);
            for (Estado estado : lista) {
                guardarEstado(estado);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEstado(Estado estado) {
        estados.remove(estado.getTipo(), estado.getValor());
    }

    @Override
    public Estado cargarEstadoDefault(String tipo) {
        switch (tipo) {
            case "Autocaravana":
                return new Estado(tipo, "Disponible");
            case "Cliente":
                return new Estado(tipo, "Activo");
            case "Reserva":
                return new Estado(tipo, "Pendiente");
            default:
                throw new IllegalStateException("Tipo no permitido: " + tipo);
        }
    }

    @Override
    public List<String> cargarEstado(String tipo) {
        //Saca el mapa de estados de un tipo
        List<String> estadosFiltrados = new ArrayList<>();
        for (Map.Entry<String, List<Estado>> entry : estados.entrySet()) {
            if (entry.getKey().equals(tipo)) {
                for (Estado value : entry.getValue()) {
                    estadosFiltrados.add(value.getValor());
                }
            }
        }
        return estadosFiltrados;
    }
}
