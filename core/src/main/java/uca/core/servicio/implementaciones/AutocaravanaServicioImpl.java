package uca.core.servicio.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uca.core.dao.iAutocaravanaRepositorio;
import uca.core.dao.iEstadoRepositorio;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Estado;
import uca.core.servicio.implementaciones.reglas.AutocaravanaReglas;
import uca.core.servicio.interfaces.iAutocaravanaServicio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class AutocaravanaServicioImpl implements iAutocaravanaServicio {

    private iAutocaravanaRepositorio autocaravanaRepositorio;
    private final iEstadoRepositorio autocaravanaEstadoRepositorio;
    private final AutocaravanaReglas autocaravanaReglas = new AutocaravanaReglas();

    @Autowired
    public AutocaravanaServicioImpl(iAutocaravanaRepositorio autocaravanaRepositorio, iEstadoRepositorio autocaravanaEstadoRepositorio) {
        this.autocaravanaRepositorio = autocaravanaRepositorio;
        this.autocaravanaEstadoRepositorio = autocaravanaEstadoRepositorio;
    }


    @Override
    public void altaAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj) {
        comprobarAutocaravana(mod, precio, plaz, matr, kilometraj);
        String estado = autocaravanaEstadoRepositorio.cargarEstadoDefault("Autocaravana").getValor();
        Long idA = autocaravanaRepositorio.findAll().stream().max(Comparator.comparing(Autocaravana::getIdA)).map(Autocaravana::getIdA).orElse(0L) + 1;
        Autocaravana A = new Autocaravana(idA, mod, precio, plaz, matr, kilometraj, estado);
        autocaravanaRepositorio.save(A);

    }

    @Override
    public void comprobarAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj) {
        if (!autocaravanaReglas.comprobarMatricula(matr))
            throw new IllegalArgumentException("La matricula no es valida");
        if (autocaravanaRepositorio.findAll().stream().anyMatch(a -> a.getMatricula().equals(matr)))
            throw new IllegalArgumentException("La matricula ya existe");
        if (precio.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (plaz <= 0) throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        if (kilometraj < 0) throw new IllegalArgumentException("El kilometraje no puede ser negativo");
    }

    @Override
    public Autocaravana buscarAutocaravana(Long id){
        return autocaravanaRepositorio.findAll().stream().filter(a -> a.getIdA() == id).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Collection<Autocaravana> buscarAutocaravana(String tipo, String dato){
        List<Autocaravana> autocaravanas = autocaravanaRepositorio.findAll();
        if (tipo.equals("Modelo")) {
            return autocaravanas.stream().filter(a -> a.getModelo().equals(dato)).toList();
        } else if (tipo.equals("Matricula")) {
            return autocaravanas.stream().filter(a -> a.getMatricula().equals(dato)).toList();
        } else if (tipo.equals("Estado")) {
            return autocaravanas.stream().filter(a -> a.getEstadoA().equals(dato)).toList();
        } else if (tipo.equals("Precio")) {
            return autocaravanas.stream().filter(a -> a.getPrecioPorDia().compareTo(new BigDecimal(dato)) == 0).toList();
        } else if (tipo.equals("Plazas")) {
            return autocaravanas.stream().filter(a -> a.getPlazas() == Integer.parseInt(dato)).toList();
        } else if (tipo.equals("Kilometraje")) {
            return autocaravanas.stream().filter(a -> a.getKilometraje() == Integer.parseInt(dato)).toList();
        } else {
            throw new IllegalArgumentException("El tipo de busqueda no es valido");
        }
    }

    @Override
    public Collection<Autocaravana> getListaAutocaravanas() {
        return autocaravanaRepositorio.findAll();
    }

    @Override
    public Collection<String> getListaEstadoAutocaravana() {
        return autocaravanaEstadoRepositorio.cargarEstado("Autocaravana");
    }



    @Override
    public boolean comprobarEstadoAutocaravana(String estado) {
        return autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado);
    }


    @Override
    public void setModelo(Long id, String mod) {
        if (mod.isEmpty())
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getIdA() == id).forEach(a -> a.setModelo(mod));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setModelo(String matricula, String mod){
        if (mod.isEmpty())
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setModelo(mod));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setPrecioPorDia(Long id, BigDecimal precioPorDia) {
        if (precioPorDia.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getIdA() == id).forEach(a -> a.setPrecioPorDia(precioPorDia));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setPrecioPorDia(String matricula, BigDecimal precioPorDia) {
        if (precioPorDia.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setPrecioPorDia(precioPorDia));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setPlazas(Long id, int plazas) {
        if (plazas <= 0)
            throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getIdA() == id).forEach(a -> a.setPlazas(plazas));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setPlazas(String matricula, int plazas) {
        if (plazas <= 0)
            throw new IllegalArgumentException("Las plazas no pueden ser negativas");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setPlazas(plazas));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setEstado(Long id, String estado) {
        if (estado.isEmpty())
            throw new IllegalArgumentException("El estado no puede estar vacio");
        if (!autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado))
            throw new IllegalArgumentException("El estado no es correcto");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getIdA() == id).forEach(a -> a.setEstadoA(estado));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setEstado(String matricula, String estado) {
        if (estado.isEmpty())
            throw new IllegalArgumentException("El estado no puede estar vacio");
        if (!autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado))
            throw new IllegalArgumentException("El estado no es correcto");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setEstadoA(estado));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setKilometraje(Long id, int kilometraje) {
        if (kilometraje <= 0)
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getIdA() == id).forEach(a -> a.setKilometraje(kilometraje));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setKilometraje(String matricula, int kilometraje) {
        if (kilometraje <= 0)
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setKilometraje(kilometraje));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void eliminarAutocaravana(Long idA) {
        autocaravanaRepositorio.deleteById(idA);
    }

    @Override
    public void eliminarAutocaravana(String matricula) {
        var autocaravanas = new ArrayList<>(autocaravanaRepositorio.findAll());
        autocaravanas.removeIf(a -> a.getMatricula().equals(matricula));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }


    @Override
    public void eliminarEstadoAutocaravana(String estado) {
           if (!estado.isEmpty() & autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado)) {
               autocaravanaEstadoRepositorio.eliminarEstado(new Estado("Autocaravana", estado));
            } else {
                throw new IllegalArgumentException("El estado no es correcto");
            }
    }

    @Override
    public void addEstadoAutocaravana(String estado) {
        if (!estado.isEmpty() & !autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado)) {
            autocaravanaEstadoRepositorio.guardarEstado(new Estado("Autocaravana", estado));
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    @Override
    public void setPrecio(String matricula, BigDecimal precio) {
        if (precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getMatricula().equals(matricula)).forEach(a -> a.setPrecioPorDia(precio));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public void setPrecio(Long id, BigDecimal precio) {
        if (precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        var autocaravanas = autocaravanaRepositorio.findAll();
        autocaravanas.stream().filter(a -> a.getIdA() == id).forEach(a -> a.setPrecioPorDia(precio));
        autocaravanaRepositorio.saveAll(autocaravanas);
    }

    @Override
    public String crearEstado(String estado)
    {
        if (!estado.isEmpty() & !autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado)) {
            autocaravanaEstadoRepositorio.guardarEstado(new Estado("Autocaravana", estado));
        } else {
            return "El estado no es correcto";
        }
        return estado;
    }

    @Override
    public String eliminarEstado(String estado)
    {
        if (!estado.isEmpty() & autocaravanaEstadoRepositorio.cargarEstado("Autocaravana").contains(estado)) {
               autocaravanaEstadoRepositorio.eliminarEstado(new Estado("Autocaravana", estado));
               return "Estado eliminado";
            } else {
                return "El estado no existe";
            }
    }
}
