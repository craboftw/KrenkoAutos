package uca.core.dao;

import uca.core.dominio.Autocaravana;

import java.util.Collection;

public interface AutocaravanaRepositorio {

    // ‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Autocaravana‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧
    void guardarAutocaravana(Collection<Autocaravana> caravanas) ;
    void guardarAutocaravana(Autocaravana caravana) ;
    Collection<Autocaravana> cargarAutocaravana() ;
    Collection<Autocaravana> buscarAutocaravana(String estado,String dato) ;
    void eliminarAutocaravana(String matricula);
    void eliminarAutocaravana(int idA);

}
