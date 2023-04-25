package uca.core.servicio.reglas;

import uca.core.dao.ClienteRepositorio;
import uca.core.dominio.Cliente;

public class ClienteReglas {
    public ClienteReglas(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }
    ClienteRepositorio clienteRepositorio = null;

    public boolean comprobarDNI(String dn) {
        return true;
    }

    public boolean comprobarEdad(int edad) {
        return !(edad>=18);
    }

    public void comprobarTelefono(Cliente cli, String t){
        if (t.isEmpty() || (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(t) && c.getIdC() != cli.getIdC())))
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        cli.setTelefono(t);
    }

}
