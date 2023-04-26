package uca.core.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uca.core.dao.iClienteRepositorio;
import uca.core.dao.iEstadoRepositorio;
import uca.core.dominio.Cliente;
import uca.core.dominio.Estado;
import uca.core.servicio.reglas.ClienteReglas;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;



import static java.util.Collections.emptyList;

@Service
public class ClienteServicioImpl implements iClienteServicio {

    private iClienteRepositorio clienteRepositorio;

    private iEstadoRepositorio clienteEstadoRepositorio;
    private final ClienteReglas clienteReglas = new ClienteReglas(clienteRepositorio);

    @Autowired
    public ClienteServicioImpl(iClienteRepositorio clienteRepositorio, iEstadoRepositorio clienteEstadoRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
        this.clienteEstadoRepositorio = clienteEstadoRepositorio;
    }
    
    //crear-cliente miriam armario 666777666 2000-12-21 440999333R armaricon@gmail.com


    @Override
    public void altaCliente(String nom, String ape, String telef, String fecha, String dn, String ema){
        comprobarCliente(nom, ape, telef, fecha, dn, ema);
        String estado = clienteEstadoRepositorio.cargarEstadoDefault("Cliente").getValor();
        int idC = clienteRepositorio.cargarCliente().stream().mapToInt(Cliente::getIdC).max().orElse(0) +1 ;
        if (idC == -1) idC = 1;
        Cliente c = new Cliente(idC, nom, ape, telef, fecha, dn, ema, estado);
        clienteRepositorio.guardarCliente(c);    }

    @Override
    public int getNumeroClientes() { return clienteRepositorio.cargarCliente().size(); }

    @Override
    public Cliente buscarCliente(int idC) {
        return clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC() == idC).findFirst().orElse(Cliente.ClienteNulo);
    }

    @Override
    public Cliente buscarCliente(String dni) {
        return clienteRepositorio.cargarCliente().stream().filter(c -> c.getDni().equals(dni)).findFirst().orElse(Cliente.ClienteNulo);
    }

    @Override
    public Collection<Cliente> buscarCliente(String campo, String dato) {
            switch (campo) {
                case "nombre":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getNombre().equals(dato)).toList();
                case "apellido":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getApellido().equals(dato)).toList();
                case "telefono":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getTelefono().equals(dato)).toList();
                case "fecha":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getFechaNacimiento().equals(dato)).toList();
                case "dni":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getDni().equals(dato)).toList();
                case "email":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getEmail().equals(dato)).toList();
                case "idC":
                    return clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC() == Integer.parseInt(dato)).toList();
            }
        return emptyList();
    }

    @Override
    public void eliminarCliente(String dni) {
            clienteRepositorio.eliminarCliente(dni);

    }

    @Override
    public void eliminarCliente(int idC) {
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getIdC() == idC))
            clienteRepositorio.guardarCliente(clienteRepositorio.cargarCliente().stream().filter((c -> c.getIdC() != idC)).toList());
        else
            throw new IllegalArgumentException("El cliente con ese idC no existe");
    }


    @Override
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


    @Override
    public void setNombre(int idC, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getIdC() == idC).findFirst().ifPresentOrElse(c -> c.setNombre(nombre), () -> {
            throw new IllegalArgumentException("El cliente no existe");
        });
        clienteRepositorio.guardarCliente(clientes);
    }

    @Override
    public void setNombre(String dni, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresentOrElse(c -> c.setNombre(nombre), () -> {
            throw new IllegalArgumentException("El cliente no existe");
        });
        clienteRepositorio.guardarCliente(clientes);
    }

    @Override
    public void setApellido(int idC, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getIdC() == idC).findFirst().ifPresentOrElse(c -> c.setApellido(apellido), () -> {
            throw new IllegalArgumentException("El cliente no existe");
        });
        clienteRepositorio.guardarCliente(clientes);
    }

    @Override
    public void setApellido(String dni, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setApellido(apellido));
        clienteRepositorio.guardarCliente(clientes);
    }

    @Override
    public void setEmail(String dni, String email) {

        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getEmail().equals(email) && !c.getDni().equals(dni)))
            throw new IllegalArgumentException("El email ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setEmail(email));
        clienteRepositorio.guardarCliente(clientes);}

    @Override
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

    @Override
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


    @Override
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


    @Override
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

    @Override
    public void setTelefono(int idC, String telefono) {
        if (telefono.isEmpty())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(telefono) && c.getIdC() != c.getIdC()))
            throw new IllegalArgumentException("El telefono ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clienteRepositorio.cargarCliente().stream().filter(c -> c.getIdC()==idC).findFirst().ifPresent(c -> c.setTelefono(telefono));
        clienteRepositorio.guardarCliente(clientes);
    }

    @Override
    public void setTelefono(String dni, String telefono) {
        if (telefono.isEmpty())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (clienteRepositorio.cargarCliente().stream().anyMatch(c -> c.getTelefono().equals(telefono) && !c.getDni().equals(dni)))
            throw new IllegalArgumentException("El telefono ya existe");
        List<Cliente> clientes = clienteRepositorio.cargarCliente().stream().toList();
        clientes.stream().filter(c -> c.getDni().equals(dni)).findFirst().ifPresent(c -> c.setTelefono(telefono));
        clienteRepositorio.guardarCliente(clientes);
    }


    @Override
    public void eliminarEstadoCliente(String estado)
    {
        if (!estado.isEmpty() & clienteEstadoRepositorio.cargarEstado("Cliente").stream().anyMatch(e -> e.equals(estado))) {
            clienteEstadoRepositorio.eliminarEstado(new Estado("Cliente",estado));
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
    }

    @Override
    public String addEstadocliente(String estado) {
        if (!estado.isEmpty() & clienteEstadoRepositorio.cargarEstado("Cliente").stream().noneMatch(e -> e.equals(estado))) {
            clienteEstadoRepositorio.guardarEstado(new Estado("Cliente",estado));
            return ("El estado se ha añadido correctamente");
        } else {
            return ("El estado no es correcto");
        }
    }

    @Override
    public  Collection<String> getListaEstadoclientes() {
        return clienteEstadoRepositorio.cargarEstado("Cliente");
    }
    @Override
    public  Collection<Cliente> getListaClientes() {
        return clienteRepositorio.cargarCliente();
    }
    @Override
    public void guardarCliente(Cliente c) {
        clienteRepositorio.guardarCliente(c);
    }


}
