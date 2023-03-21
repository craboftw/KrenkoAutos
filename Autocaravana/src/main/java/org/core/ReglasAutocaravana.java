package org.core;

public interface ReglasAutocaravana {


    static boolean comprobarMatricula(String matricula) {
        String regex = "^[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}$"; // Expresión regular para matrículas de turismos

        // Verificar si la matrícula coincide con la expresión regular
        if (!matricula.matches(regex)) {
            return false;
        }

        // Verificar que la matrícula tenga una letra final válida
        String letrasValidas = "BCDFGHJKLMNPRSTVWXYZ";
        if (!letrasValidas.contains(matricula.substring(7))) {
            return false;
        }

        // Verificar que el número de matrícula sea válido
        int numMatricula = Integer.parseInt(matricula.substring(0, 4));

        return numMatricula != 0 && numMatricula <= 9999;
    }


}
