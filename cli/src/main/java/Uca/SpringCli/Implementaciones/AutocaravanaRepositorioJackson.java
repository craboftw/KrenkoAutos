package Uca.SpringCli.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Uca.Core.Autocaravana;
import Uca.Core.AutocaravanaRepositorio;
import Uca.Core.AutocaravanaEstadoRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AutocaravanaRepositorioJackson  implements AutocaravanaRepositorio
{
    //Igual que AutocaravanaRepositorioJackson pero con Autocaravana


    private static final String AUTOCARAVANAS_FILE_PATH = "autocaravanas.json";
    private static final String ESTADOS_FILE_PATH = "estados.json";

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
    public Collection<Autocaravana> cargarAutocaravana() {
        try {
            File file = new File(AUTOCARAVANAS_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<Collection<Autocaravana>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Autocaravana> cargarAutocaravana(String tipo, String dato) {
        Collection<Autocaravana> autocaravanas = cargarAutocaravana();
        if (autocaravanas != null) {
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
    public int getCantidadAutocaravanas(String tipo, String dato) {
        Collection<Autocaravana> autocaravanas = cargarAutocaravana();
        if (autocaravanas != null) {
            switch (tipo) {
                case "modelo":
                    autocaravanas.removeIf(c -> !c.getModelo().equals(dato));
                    break;
                case "estado":
                    autocaravanas.removeIf(c -> !c.getEstado().equals(dato));
                    break;
                case "plazas":
                    autocaravanas.removeIf(c -> !(c.getPlazas()==(Integer.parseInt(dato))));
                    break;
            }
            return autocaravanas.size();
        }
        return 0;
    }

    @Override
    public int getCantidadAutocaravanas() {
        return cargarAutocaravana().size();
    }

    //@Override
    public int getCantidadAutocaravana() {
        return cargarAutocaravana().size();
    }


    @Override
    public boolean existeAutocaravana(String matricula) {
        return cargarAutocaravana().stream().anyMatch(c -> c.getMatricula().equals(matricula));
    }

    @Override
    public boolean existeAutocaravana(int idA){
        return cargarAutocaravana().stream().anyMatch(c -> c.getIdA() == idA);
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

