package org.core;

import java.util.List;

public interface ReglasAutocaravana
{

    static List<String> Estados = null;
    public static boolean comprobarMatricula(String matricula) {
        String regex = "^(([A-HJ-NP-TV-Z]{2})(\\d{4})([BCDFGHJKLMNPQRSTVWXYZ]{3}))$|^(([0-9]{4})([BCDFGHJKLMNPQRSTVWXYZ]{3}))$|^(([A-HJ-NP-TV-Z]{1})(\\d{4})([BCDFGHJKLMNPQRSTVWXYZ]{2}))$";
        return matricula.matches(regex);
    }

    public default boolean comprobarEstado(String Estado)
    {
        return Estados.contains(Estado);
    }



}
