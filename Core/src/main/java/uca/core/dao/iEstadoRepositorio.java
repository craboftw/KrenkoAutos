package uca.core.dao;

import uca.core.dominio.Estado;

import java.util.List;

public interface iEstadoRepositorio {
    void guardarEstado(Estado estado);
    void guardarEstado(List<Estado> lista);
    void eliminarEstado(Estado estado);
    Estado cargarEstadoDefault(String tipo);
    List<String> cargarEstado(String tipo);



}
