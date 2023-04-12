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

    boolean existeAutocaravana(String matricula);

    boolean existeAutocaravana(int idA);

    void eliminarAutocaravana(String matricula);

    void eliminarAutocaravana(int idA);


}
