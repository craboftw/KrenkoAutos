package uca.core;

public interface ClienteReglas {
    ClienteRepositorio clienteRepositorio = null;

    default boolean comprobarDNI(String dn) {
        return true;
    }

    default boolean comprobarEdad(int edad) {
        return !(edad>=18);
    }

    default void comprobarTelefono(Cliente cli, String t){
        if (t.isEmpty() || (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(t) && c.getIdC() != cli.getIdC())))
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        cli.setTelefono(t);
    }

}
