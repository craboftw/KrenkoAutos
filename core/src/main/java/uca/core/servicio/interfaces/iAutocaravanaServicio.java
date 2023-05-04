package uca.core.servicio.interfaces;

import uca.core.dominio.Autocaravana;

import java.math.BigDecimal;
import java.util.Collection;

public interface iAutocaravanaServicio {
    void altaAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj);

    void comprobarAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj);

    Autocaravana buscarAutocaravana(Long idA);

    Collection<Autocaravana> buscarAutocaravana(String tipo, String dato);

    Collection<Autocaravana> getListaAutocaravanas();

    Collection<String> getListaEstadoAutocaravana();


    boolean comprobarEstadoAutocaravana(String estado);

    void setModelo(Long idA, String mod);

    void setModelo(String matricula, String mod);

    void setPrecioPorDia(Long idA, BigDecimal precioPorDia);

    void setPrecioPorDia(String matricula, BigDecimal precioPorDia);

    void setPlazas(Long idA, int plazas);

    void setPlazas(String matricula, int plazas);

    void setEstado(Long idA, String estado);

    void setEstado(String matricula, String estado);

    void setKilometraje(Long idA, int kilometraje);

    void setKilometraje(String matricula, int kilometraje);

    void eliminarAutocaravana(Long idA);
    void eliminarAutocaravana(String matricula);

    void eliminarEstadoAutocaravana(String estado);

    void addEstadoAutocaravana(String estado);

    void setPrecio(String matricula, BigDecimal precio);

    void setPrecio(Long idA, BigDecimal precio);

    void guardarAutocaravana(Autocaravana autocaravana);

    String crearEstado(String estado);
    String eliminarEstado(String estado);
}
