package uca.dss.SpringCli.UI;

import uca.core.Cliente;
import uca.core.ClienteReglas;
import uca.core.ClienteRepositorio;

public class ClienteReglasBasicas implements ClienteReglas {
        ClienteRepositorio clienteRepositorio = null;
    @Override

        public boolean comprobarDNI(String dn) {
            return true;
        }

        @Override
        public boolean comprobarEdad(int edad) {
            return !(edad>=18);
        }

    @Override
    public void comprobarTelefono(Cliente cli, String t){
            if (t.isEmpty() || (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(t) && c.getIdC() != cli.getIdC())))
                throw new IllegalArgumentException("El telefono no puede estar vacio");
            cli.setTelefono(t);
        }

    }

