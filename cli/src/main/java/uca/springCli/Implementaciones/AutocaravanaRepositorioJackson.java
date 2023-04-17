package uca.springCli.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uca.core.Autocaravana;
import uca.core.AutocaravanaRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AutocaravanaRepositorioJackson  implements AutocaravanaRepositorio
{
    private static final String AUTOCARAVANAS_FILE_PATH = "autocaravanas.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void guardarAutocaravana(Collection<Autocaravana> autocaravanas) {
        try {
            objectMapper.writeValue(new File(AUTOCARAVANAS_FILE_PATH), autocaravanas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarAutocaravana(Autocaravana autocaravana) {
        Collection<Autocaravana> autocaravanas = cargarAutocaravana();
        autocaravanas.add(autocaravana);
        guardarAutocaravana(autocaravanas);
    }

    @Override
    public Collection<Autocaravana> cargarAutocaravana()
    {
        try {
            File file = new File(AUTOCARAVANAS_FILE_PATH);
            if (file.exists())
            {
                return objectMapper.readValue(file, new TypeReference<Collection<Autocaravana>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Autocaravana> buscarAutocaravana(String tipo, String dato) {
        Collection<Autocaravana> autocaravanas = cargarAutocaravana();
        if (!autocaravanas.isEmpty()) {
            switch (tipo) {
                case "idA" -> autocaravanas.removeIf(c -> !(c.getIdA() == (Integer.parseInt(dato))));
                case "modelo" -> autocaravanas.removeIf(c -> !c.getModelo().equals(dato));
                case "matricula" -> autocaravanas.removeIf(c -> !c.getMatricula().equals(dato));
                case "estado" -> autocaravanas.removeIf(c -> !c.getEstado().equals(dato));
                case "plazas" -> autocaravanas.removeIf(c -> !(c.getPlazas() == (Integer.parseInt(dato))));
                default -> {throw new IllegalStateException("Tipo no valido: " + tipo +". Debe ser idA, modelo, matricula, estado o plazas"); }
            }
        }
        return autocaravanas;
    }

    @Override
    public void eliminarAutocaravana(String matricula) {
        guardarAutocaravana(cargarAutocaravana().stream().filter(c -> !c.getMatricula().equals(matricula)).collect(Collectors.toList()));
    }

    @Override
    public void eliminarAutocaravana(int idA){
        guardarAutocaravana(cargarAutocaravana().stream().filter(c -> c.getIdA() != idA).collect(Collectors.toList()));
    }


}

