package uca.core.dao;

import uca.core.dominio.Estado;

import java.util.List;

public interface iEstadoRepositorio {
    public void guardarEstado(Estado estado);
    public void guardarEstado(List<Estado> lista);
    public void eliminarEstado(Estado estado);
    public Estado cargarEstadoDefault(String tipo);
    public List<String> cargarEstado(String tipo);



}
