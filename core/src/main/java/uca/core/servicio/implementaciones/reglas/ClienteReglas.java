package uca.core.servicio.implementaciones.reglas;

import uca.core.dominio.Cliente;

import java.util.List;

public class ClienteReglas {

    public boolean comprobarDNI(String dn) {
        return true;
    }

    public boolean comprobarEdad(int edad) {
        return !(edad>=18);
    }

    public void comprobarTelefono(List<Cliente> clientes,Cliente cli, String t){
        if (t.length() != 9) {
            throw new IllegalArgumentException("El telefono debe tener 9 digitos");
        }
        for (Cliente c : clientes) {
            if (c.getTelefono().equals(t)) {
                throw new IllegalArgumentException("El telefono ya existe");
            }
        }
        cli.setTelefono(t);
    }

}
