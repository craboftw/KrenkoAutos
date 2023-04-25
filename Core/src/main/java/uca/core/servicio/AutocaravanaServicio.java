package uca.core.servicio;

import uca.core.dao.AutocaravanaEstadoRepositorio;
import uca.core.dao.AutocaravanaRepositorio;
import uca.core.dominio.Autocaravana;
import uca.core.servicio.reglas.AutocaravanaReglas;

import java.math.BigDecimal;
import java.util.*;

public class AutocaravanaServicio implements iAutocaravanaServicio {

    private final AutocaravanaRepositorio autocaravanaRepositorio;
    private final AutocaravanaEstadoRepositorio autocaravanaEstadoRepositorio;
    private final AutocaravanaReglas autocaravanaReglas = new AutocaravanaReglas();



    public AutocaravanaServicio(AutocaravanaRepositorio autocaravanaRepositorio, AutocaravanaEstadoRepositorio autocaravanaEstadoRepositorio) {
        this.autocaravanaRepositorio = autocaravanaRepositorio;
        this.autocaravanaEstadoRepositorio = autocaravanaEstadoRepositorio;

    }


    @Override
    public void altaAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj) {
        comprobarAutocaravana(mod, precio, plaz, matr, kilometraj);
        String estado = autocaravanaEstadoRepositorio.cargarEstadoDefault();
        int idA = autocaravanaRepositorio.cargarAutocaravana().stream().mapToInt(Autocaravana::getIdA).max().orElse(0) + 1;
        Autocaravana A = new Autocaravana(idA, mod, precio, plaz, matr, kilometraj, estado);
        autocaravanaRepositorio.guardarAutocaravana(A);

    }

    @Override
    public void comprobarAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj) {
        if (!autocaravanaReglas.comprobarMatricula(matr))
            throw new IllegalArgumentException("La matricula no es valida");
        if (autocaravanaRepositorio.cargarAutocaravana().stream().anyMatch(a -> a.getMatricula().equals(matr)))
            throw new IllegalArgumentException("La matricula ya existe");
        if (precio.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (plaz <= 0) throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        if (kilometraj < 0) throw new IllegalArgumentException("El kilometraje no puede ser negativo");
    }

    @Override
    public Autocaravana buscarAutocarvana(int idA){
        return autocaravanaRepositorio.cargarAutocaravana().stream().filter(a -> a.getIdA() == idA).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Collection<Autocaravana> buscarAutocaravana(String tipo, String dato){
        switch (tipo){
            case "idA":
                return autocaravanaRepositorio.buscarAutocaravana("idA",dato);
            case "Matricula":
                return autocaravanaRepositorio.buscarAutocaravana("Matricula",dato);
            case "Modelo":
                return autocaravanaRepositorio.buscarAutocaravana("Modelo",dato);
            case "Estado":
                return autocaravanaRepositorio.buscarAutocaravana("Estado",dato);
            default:
                throw new IllegalArgumentException("El tipo de busqueda no es valido");
        }
    }

    @Override
    public Collection<Autocaravana> getListaAutocaravanas() {
        return autocaravanaRepositorio.cargarAutocaravana();
    }

    @Override
    public Collection<String> getListaEstadoAutocaravana() {
        return autocaravanaEstadoRepositorio.cargarEstadosAutocaravana();
    }

    @Override
    public boolean quedanCaravanas() {
        return autocaravanaRepositorio.buscarAutocaravana("Estado", "Disponible").size() > 0;
    }

    @Override
    public boolean comprobarEstadoAutocaravana(String estado) {
        return autocaravanaEstadoRepositorio.cargarEstadosAutocaravana().contains(estado);
    }


    @Override
    public void setModelo(int idA, String mod) {
        if (mod.isEmpty())
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getIdA() == idA).forEach(a -> a.setModelo(mod));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setModelo(String matricula, String mod){
        if (mod.isEmpty())
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setModelo(mod));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setPrecioPorDia(int idA, BigDecimal precioPorDia) {
        if (precioPorDia.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getIdA() == idA).forEach(a -> a.setPrecioPorDia(precioPorDia));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setPrecioPorDia(String matricula, BigDecimal precioPorDia) {
        if (precioPorDia.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setPrecioPorDia(precioPorDia));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setPlazas(int idA, int plazas) {
        if (plazas <= 0)
            throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getIdA() == idA).forEach(a -> a.setPlazas(plazas));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setPlazas(String matricula, int plazas) {
        if (plazas <= 0)
            throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setPlazas(plazas));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setEstado(int idA, String estado) {
        if (estado.isEmpty())
            throw new IllegalArgumentException("El estado no puede estar vacio");
        if (!autocaravanaEstadoRepositorio.cargarEstadosAutocaravana().contains(estado))
            throw new IllegalArgumentException("El estado no es correcto");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getIdA() == idA).forEach(a -> a.setEstado(estado));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setEstado(String matricula, String estado) {
        if (estado.isEmpty())
            throw new IllegalArgumentException("El estado no puede estar vacio");
        if (!autocaravanaEstadoRepositorio.cargarEstadosAutocaravana().contains(estado))
            throw new IllegalArgumentException("El estado no es correcto");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setEstado(estado));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setKilometraje(int idA, int kilometraje) {
        if (kilometraje <= 0)
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getIdA() == idA).forEach(a -> a.setKilometraje(kilometraje));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setKilometraje(String matricula, int kilometraje) {
        if (kilometraje <= 0)
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setKilometraje(kilometraje));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void eliminarAutocaravana(int idA) {
        var autocaravanas = new ArrayList<>(autocaravanaRepositorio.cargarAutocaravana().stream().toList());
        autocaravanas.removeIf(a -> a.getIdA() == idA);
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }


    @Override
    public void eliminarEstadoAutocaravana(String estado) {
           if (!estado.isEmpty() & autocaravanaEstadoRepositorio.cargarEstadosAutocaravana().contains(estado)) {
               autocaravanaEstadoRepositorio.eliminarEstadoAutocaravana(estado);
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
    }

    @Override
    public void addEstadoAutocaravana(String estado) {
        if (!estado.isEmpty() & !autocaravanaEstadoRepositorio.cargarEstadosAutocaravana().contains(estado)) {
            autocaravanaEstadoRepositorio.guardarEstadoAutocaravana(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    @Override
    public void setPrecio(String matricula, BigDecimal precio) {
        if (precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setPrecioPorDia(precio));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }

    @Override
    public void setPrecio(int idA, BigDecimal precio) {
        if (precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.cargarAutocaravana().stream().toList();
        autocaravanas.stream().filter(a -> a.getIdA() == idA).forEach(a -> a.setPrecioPorDia(precio));
        autocaravanaRepositorio.guardarAutocaravana(autocaravanas);
    }
}
