package uca.core;

import java.util.Collection;

public interface AutocaravanaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Autocaravana‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarAutocaravana(Collection<Autocaravana> caravanas) ;
    void guardarAutocaravana(Autocaravana caravana) ;
    Collection<Autocaravana> cargarAutocaravana() ;
    Collection<Autocaravana> cargarAutocaravana(String estado,String dato) ;
    int getCantidadAutocaravanas(String tipo,String dato);
    int getCantidadAutocaravanas();

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Estados‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    Collection<String> cargarEstadosAutocaravana() ;
    void guardarEstadoAutocaravana(Collection<String> listaEstados);
    void guardarEstadoAutocaravana(String estado);
    default String cargarEstadoDefault() {
        return "Disponible";
    }


    void eliminarAutocaravana(Autocaravana a);

    boolean existeAutocaravana(Autocaravana a);

    boolean existeEstadoAutocaravana(String estado);

    void eliminarEstadoAutocaravana(String estado);
}
