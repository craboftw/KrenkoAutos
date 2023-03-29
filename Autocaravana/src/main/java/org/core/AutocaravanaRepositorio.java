package org.core;

import java.util.Collection;

public interface AutocaravanaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Autocaravana‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public void guardarAutocaravana(Collection<Autocaravana> caravanas) ;
    public void guardarAutocaravana(Autocaravana caravana) ;
    public Collection<Autocaravana> cargarAutocaravana() ;
    public Collection<Autocaravana> cargarAutocaravana(String estado,String dato) ;
    public int getCantidadAutocaravanas(String tipo,String dato);
    public int getCantidadAutocaravanas();

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    public Collection<String> cargarEstadosAutocaravana() ;
    void guardarEstadoAutocaravana(Collection<String> listaEstados);
    void guardarEstadoAutocaravana(String estado);
    public default String cargarEstadoDefault() {
        return "Disponible";
    }


    void eliminarAutocaravana(Autocaravana a);

    boolean existeAutocaravana(Autocaravana a);

    boolean existeEstadoAutocaravana(String estado);

    void eliminarEstadoAutocaravana(String estado);
}
