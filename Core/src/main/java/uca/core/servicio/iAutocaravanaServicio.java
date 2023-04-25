package uca.core.servicio;

import uca.core.dominio.Autocaravana;

import java.math.BigDecimal;
import java.util.Collection;

public interface iAutocaravanaServicio {
    void altaAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj);

    void comprobarAutocaravana(String mod, BigDecimal precio, int plaz, String matr, int kilometraj);

    Autocaravana buscarAutocarvana(int idA);

    Collection<Autocaravana> buscarAutocaravana(String tipo, String dato);

    Collection<Autocaravana> getListaAutocaravanas();

    Collection<String> getListaEstadoAutocaravana();

    boolean quedanCaravanas();

    boolean comprobarEstadoAutocaravana(String estado);

    void setModelo(int idA, String mod);

    void setModelo(String matricula, String mod);

    void setPrecioPorDia(int idA, BigDecimal precioPorDia);

    void setPrecioPorDia(String matricula, BigDecimal precioPorDia);

    void setPlazas(int idA, int plazas);

    void setPlazas(String matricula, int plazas);

    void setEstado(int idA, String estado);

    void setEstado(String matricula, String estado);

    void setKilometraje(int idA, int kilometraje);

    void setKilometraje(String matricula, int kilometraje);

    void eliminarAutocaravana(int idA);

    void eliminarEstadoAutocaravana(String estado);

    void addEstadoAutocaravana(String estado);

    void setPrecio(String matricula, BigDecimal precio);

    void setPrecio(int idA, BigDecimal precio);
}
