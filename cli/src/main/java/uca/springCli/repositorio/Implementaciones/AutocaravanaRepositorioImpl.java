package uca.springCli.repositorio.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;
import uca.core.dominio.Autocaravana;
import uca.core.dao.iAutocaravanaRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AutocaravanaRepositorioImpl implements iAutocaravanaRepositorio
{
    private static final String AUTOCARAVANAS_FILE_PATH = "autocaravanas.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Map<Integer, Autocaravana> autocaravanas = cargaInicial();
    private Map<Integer,Autocaravana> cargaInicial()
    {
        try {
            File file = new File(AUTOCARAVANAS_FILE_PATH);
            if (file.exists())
            {
                if (file.length() == 0)
                {
                    return Map.of();
                }
                return objectMapper.readValue(file, new TypeReference<List<Autocaravana>>() {}).stream().collect(Collectors.toMap(Autocaravana::getIdA, autocaravana -> autocaravana));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of();
    }

    @Override
    public void guardarAutocaravana(Collection<Autocaravana> autocaravanas) {
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
            this.autocaravanas= autocaravanas.stream().collect(Collectors.toMap(Autocaravana::getIdA, autocaravana -> autocaravana));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarAutocaravana(Autocaravana autocaravana) {
        autocaravanas.put(autocaravana.getIdA(), autocaravana);
        guardarAutocaravana(new ArrayList<>(autocaravanas.values()));
    }

    @Override
    public Collection<Autocaravana> cargarAutocaravana()
    {
        return autocaravanas.values();
    }

    @Override
    public Autocaravana buscarAutocaravana(int idA) {
        return autocaravanas.get(idA);  }


    @Override
    public Collection<Autocaravana> buscarAutocaravana(String tipo, String dato) {
        if (!autocaravanas.isEmpty()) {
            switch (tipo) {
                case "idA" -> {return autocaravanas.values().stream().filter(c -> c.getIdA() == Integer.parseInt(dato)).collect(Collectors.toList());}
                case "modelo" -> {return autocaravanas.values().stream().filter(c -> c.getModelo().equals(dato)).collect(Collectors.toList());}
                case "matricula" -> {return autocaravanas.values().stream().filter(c -> c.getMatricula().equals(dato)).collect(Collectors.toList());}
                case "estado" -> {return autocaravanas.values().stream().filter(c -> c.getEstadoA().equals(dato)).collect(Collectors.toList());}
                case "plazas" -> {return autocaravanas.values().stream().filter(c -> c.getPlazas() == Integer.parseInt(dato)).collect(Collectors.toList());}
                default -> {throw new IllegalStateException("Tipo no valido: " + tipo +". Debe ser idA, modelo, matricula, estado o plazas"); }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void eliminarAutocaravana(String matricula) {
        guardarAutocaravana(autocaravanas.values().stream().filter(c -> !c.getMatricula().equals(matricula)).collect(Collectors.toList()));
    }

    @Override
    public void eliminarAutocaravana(int idA){
        guardarAutocaravana(autocaravanas.values().stream().filter(c -> c.getIdA() != idA).collect(Collectors.toList()));
    }


}

