package uca.core;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ClienteServicio {
    public ClienteServicio(ClienteRepositorio clienteRepositorio, ClienteReglas clienteReglas) {
        this.clienteRepositorio = clienteRepositorio;
        this.clienteReglas = clienteReglas;
    }



    //crear-cliente miriam armario 666777666 2000-12-21 440999333R armaricon@gmail.com



    private final ClienteReglas clienteReglas;
    private final ClienteRepositorio clienteRepositorio;

    public void altaCliente(String nom, String ape, String telef, String fecha, String dn, String ema){
        comprobarCliente(nom, ape, telef, fecha, dn, ema);
        String estado = clienteRepositorio.cargarEstadoDefault();
        int idC = clienteRepositorio.getCantidadCliente();
        Cliente c = new Cliente(idC, nom, ape, telef, fecha, dn, ema);
        clienteRepositorio.guardarCliente(c);    }

    public int getNumeroClientes() { return clienteRepositorio.getCantidadCliente(); }

    public Cliente buscarCliente(int idC) {
        return clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC() == idC).findFirst().orElse(Cliente.ClienteNulo);
    }
    
    public Cliente buscarCliente(String dni) {
        return clienteRepositorio.cargarCliente().stream().filter(c -> c.getDni().equals(dni)).findFirst().orElse(Cliente.ClienteNulo);
    }

    public void eliminarCliente(String dni) {
        if (clienteRepositorio.existeCliente(dni))
            clienteRepositorio.eliminarCliente(buscarCliente(dni));
        else
            throw new IllegalArgumentException("El cliente no existe");
    }

    public void eliminarCliente(int idC) {
        if (clienteRepositorio.existeCliente(idC))
            clienteRepositorio.eliminarCliente(buscarCliente(idC));
        else
            throw new IllegalArgumentException("El cliente no existe");
    }


    public void comprobarCliente(String nom, String ape, String telef, String cumpleanos, String dn, String ema) {
        LocalDate diaDeCum;
        try {
            diaDeCum = LocalDate.parse(cumpleanos);
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es correcta");
        }
        //Calculo su edad
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - diaDeCum.getYear();
        if (hoy.getMonthValue() < diaDeCum.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == diaDeCum.getMonthValue() && hoy.getDayOfMonth() < diaDeCum.getDayOfMonth()) {
            edad--;
        }

        if (clienteReglas.comprobarEdad(edad)) throw new IllegalArgumentException("La edad no es valida");
        if (nom.isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (ape.isEmpty()) throw new IllegalArgumentException("El apellido no puede estar vacio");
        if (dn.isEmpty()) throw new IllegalArgumentException("El DNI no puede estar vacio");
        if (telef.isEmpty()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (ema.isEmpty()) throw new IllegalArgumentException("El email no puede estar vacio");
        if (!(ema.contains("@") & ema.contains("."))) throw new IllegalArgumentException("El email no es correcto");
        if (!clienteRepositorio.cargarCliente().isEmpty()) {
            if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getDni().equals(dn)))
                throw new IllegalArgumentException("El DNI ya existe");
            if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getEmail().equals(ema)))
                throw new IllegalArgumentException("El email ya existe");
            if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(telef)))
                throw new IllegalArgumentException("El telefono ya existe");
        }
        if (!clienteReglas.comprobarDNI(dn)) throw new IllegalArgumentException("El DNI no es correcto");

    }


    public void setNombre(int idC, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getIdC() == idC).findFirst().ifPresentOrElse(c -> c.setNombre(nombre), () -> {
            throw new IllegalArgumentException("El cliente no existe");
        });
        clienteRepositorio.guardarCliente(clientes);
    }

    public void setNombre(String dni, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresentOrElse(c -> c.setNombre(nombre), () -> {
            throw new IllegalArgumentException("El cliente no existe");
        });
        clienteRepositorio.guardarCliente(clientes);
    }

    public void setApellido(int idC, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getIdC() == idC).findFirst().ifPresentOrElse(c -> c.setApellido(apellido), () -> {
            throw new IllegalArgumentException("El cliente no existe");
        });
        clienteRepositorio.guardarCliente(clientes);
    }

    public void setApellido(String dni, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setApellido(apellido));
        clienteRepositorio.guardarCliente(clientes);
    }

    public void setEmail(String dni, String email) {

        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getEmail().equals(email) && !c.getDni().equals(dni)))
            throw new IllegalArgumentException("El email ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setEmail(email));
        clienteRepositorio.guardarCliente(clientes);}

    public void setEmail(int idC, String email)
    {
        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getEmail().equals(email) && c.getIdC()!=idC))
            throw new IllegalArgumentException("El email ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC()==idC).findFirst().ifPresent(c -> c.setEmail(email));
        clienteRepositorio.guardarCliente(clientes);
    }

    public void setDni(int idC, String dni) {
        if (dni.isEmpty())
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getDni().equals(dni) && c.getIdC()!=idC))
            throw new IllegalArgumentException("El DNI ya existe");
        if (!clienteReglas.comprobarDNI(dni)) throw new IllegalArgumentException("El DNI no es correcto");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC()==idC).findFirst().ifPresent(c -> c.setDni(dni));
        clienteRepositorio.guardarCliente(clientes);
    }


    public void setFechaNacimiento(String dni, String fechaNacimiento) {
        LocalDate nueva;
        try {
            nueva = LocalDate.parse(fechaNacimiento);
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es correcta");
        }
        //Calculo su edad
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - nueva.getYear();
        if (hoy.getMonthValue() < nueva.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == nueva.getMonthValue() && hoy.getDayOfMonth() < nueva.getDayOfMonth()) {
            edad--;
        }
        if (clienteReglas.comprobarEdad(edad)) throw new IllegalArgumentException("La edad no es valida");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setFechaNacimiento(fechaNacimiento));
        clienteRepositorio.guardarCliente(clientes);
    }


    public void setFechaNacimiento(int idC, String fechaNacimiento) {
        LocalDate nueva;
        try {
            nueva = LocalDate.parse(fechaNacimiento);
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es correcta");
        }
        //Calculo su edad
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - nueva.getYear();
        if (hoy.getMonthValue() < nueva.getMonthValue()) {
            edad--;
        } else if (hoy.getMonthValue() == nueva.getMonthValue() && hoy.getDayOfMonth() < nueva.getDayOfMonth()) {
            edad--;
        }
        if (clienteReglas.comprobarEdad(edad)) throw new IllegalArgumentException("La edad no es valida");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC()==idC).findFirst().ifPresent(c -> c.setFechaNacimiento(fechaNacimiento));
        clienteRepositorio.guardarCliente(clientes);
}

    public void setTelefono(int idC, String telefono) {
        if (telefono.isEmpty())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(telefono) && c.getIdC() != c.getIdC()))
            throw new IllegalArgumentException("El telefono ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC()==idC).findFirst().ifPresent(c -> c.setTelefono(telefono));
        clienteRepositorio.guardarCliente(clientes);
    }

    public void setTelefono(String dni, String telefono) {
        if (telefono.isEmpty())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(telefono) && !c.getDni().equals(dni)))
            throw new IllegalArgumentException("El telefono ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setTelefono(telefono));
        clienteRepositorio.guardarCliente(clientes);
    }


    void eliminarEstadoCliente(String estado)
    {
        if (!estado.isEmpty() & clienteRepositorio.existeEstadoCliente(estado)) {
            clienteRepositorio.eliminarEstadoCliente(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    void addEstadocliente(String estado) {
        if (!estado.isEmpty() & !clienteRepositorio.existeEstadoCliente(estado)) {
            clienteRepositorio.guardarEstadoCliente(estado);
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    public  Collection<String> getListaEstadoclientes() {
        return clienteRepositorio.cargarEstadosCliente();
    }
    public  Collection<Cliente> getListaClientes() {
        return clienteRepositorio.cargarCliente();
    }

    public void guardarCliente(Cliente c) {
        clienteRepositorio.guardarCliente(c);
    }


}
