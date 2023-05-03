package uca.core.servicio.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uca.core.dao.iClienteRepositorio;
import uca.core.dao.iEstadoRepositorio;
import uca.core.dominio.Cliente;
import uca.core.dominio.Estado;
import uca.core.servicio.interfaces.iClienteServicio;
import uca.core.servicio.implementaciones.reglas.ClienteReglas;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;



import static java.util.Collections.emptyList;

@Service
public class ClienteServicioImpl implements iClienteServicio {

    private iClienteRepositorio clienteRepositorio;
    private final iEstadoRepositorio clienteEstadoRepositorio;
    private  ClienteReglas clienteReglas = new ClienteReglas(clienteRepositorio);

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
        Long idC = clienteRepositorio.count() ;
        if (idC == -1) idC = (long) 1;
        Cliente c = new Cliente(idC, nom, ape, telef, fecha, dn, ema, estado);
        clienteRepositorio.save(c);    }

    @Override
    public int getNumeroClientes() { return (int) clienteRepositorio.count(); }

    @Override
    public Cliente buscarCliente(Long idC) {
        return clienteRepositorio.findById(idC).orElse(Cliente.ClienteNulo);
    }

    @Override
    public Cliente buscarCliente(String dni) {
        //use method findAll in repository and find the one with the dni passed as parameter
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getDni().equals(dni)) {
                return c;
            }
        }
        return Cliente.ClienteNulo;


    }

    @Override
    public Collection<Cliente> buscarCliente(String campo, String dato) {
        List<Cliente> clientes = emptyList();
        for (Cliente c : clienteRepositorio.findAll()) {
            clientes.add(c);
        }
        if (clientes.isEmpty()) return clientes;

        return switch (campo) {
            case "nombre" -> clientes.stream().filter(c -> c.getNombre().equals(dato)).toList();
            case "apellido" -> clientes.stream().filter(c -> c.getApellido().equals(dato)).toList();
            case "telefono" -> clientes.stream().filter(c -> c.getTelefono().equals(dato)).toList();
            case "fecha" -> clientes.stream().filter(c -> c.getFechaNacimiento().equals(dato)).toList();
            case "dni" -> clientes.stream().filter(c -> c.getDni().equals(dato)).toList();
            case "email" -> clientes.stream().filter(c -> c.getEmail().equals(dato)).toList();
            case "idC" -> clientes.stream().filter(c -> c.getIdC().equals(dato)).toList();
            default -> emptyList();
        };
    }

    @Override
    public void eliminarCliente(String dni) {
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getDni().equals(dni)) {
                clienteRepositorio.delete(c);
                break;
            }
        }

    }

    @Override
    public void eliminarCliente(Long idC) {
        Cliente c = clienteRepositorio.findById(idC).orElse(Cliente.ClienteNulo);
        if (c != Cliente.ClienteNulo) {
            clienteRepositorio.delete(c);
        } else {
            throw new IllegalArgumentException("El cliente con ese idC no existe");
        }
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
        List<Cliente> clientes = (List<Cliente>) clienteRepositorio.findAll();

        if (clienteReglas.comprobarEdad(edad)) throw new IllegalArgumentException("La edad no es valida");
        if (nom.isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (ape.isEmpty()) throw new IllegalArgumentException("El apellido no puede estar vacio");
        if (dn.isEmpty()) throw new IllegalArgumentException("El DNI no puede estar vacio");
        if (telef.isEmpty()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (ema.isEmpty()) throw new IllegalArgumentException("El email no puede estar vacio");
        if (!(ema.contains("@") & ema.contains("."))) throw new IllegalArgumentException("El email no es correcto");
        if (!clientes.isEmpty()) {
            if (clientes.stream().anyMatch(c -> c.getDni().equals(dn)))
                throw new IllegalArgumentException("El DNI ya existe");
            if (clientes.stream().anyMatch(c -> c.getEmail().equals(ema)))
                throw new IllegalArgumentException("El email ya existe");
            if (clientes.stream().anyMatch(c -> c.getTelefono().equals(telef)))
                throw new IllegalArgumentException("El telefono ya existe");
        }
        if (!clienteReglas.comprobarDNI(dn)) throw new IllegalArgumentException("El DNI no es correcto");
    }


    @Override
    public void setNombre(Long idC, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        Cliente c = clienteRepositorio.findById(idC).orElse(Cliente.ClienteNulo);
        if (c != Cliente.ClienteNulo) {
            c.setNombre(nombre);
            clienteRepositorio.save(c);
        } else {
            throw new IllegalArgumentException("El cliente con ese idC no existe");
        }
    }

    @Override
    public void setNombre(String dni, String nombre) {
        if (nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getDni().equals(dni)) {
                c.setNombre(nombre);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese dni no existe");
        }
    }

    @Override
    public void setApellido(Long idC, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        Cliente c = clienteRepositorio.findById(idC).orElse(Cliente.ClienteNulo);
        if (c != Cliente.ClienteNulo) {
            c.setApellido(apellido);
            clienteRepositorio.save(c);
        } else {
            throw new IllegalArgumentException("El cliente con ese idC no existe");
        }
    }

    @Override
    public void setApellido(String dni, String apellido) {
        if (apellido.isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getDni().equals(dni)) {
                c.setApellido(apellido);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese dni no existe");
        }
    }

    @Override
    public void setEmail(String dni, String email) {
        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getDni().equals(dni)) {
                c.setEmail(email);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese dni no existe");
        }
    }

    @Override
    public void setEmail(Long idC, String email)
    {
        if (email.isEmpty())
            throw new IllegalArgumentException("El email no puede estar vacio");
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getIdC()==idC) {
                c.setEmail(email);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese idC no existe");
        }
    }

    @Override
    public void setDni(Long idC, String dni) {
        if (dni.isEmpty())
            throw new IllegalArgumentException("El dni no puede estar vacio");
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getIdC()==idC) {
                c.setDni(dni);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese idC no existe");
        }
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
        List<Cliente> clientes = (List<Cliente>) clienteRepositorio.findAll();
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                c.setFechaNacimiento(fechaNacimiento);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese dni no existe");
        }
    }


    @Override
    public void setFechaNacimiento(Long idC, String fechaNacimiento) {
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
        Cliente c = clienteRepositorio.findById(idC).orElse(Cliente.ClienteNulo);
        if (c != Cliente.ClienteNulo) {
            throw new IllegalArgumentException("El cliente con ese idC no existe");}
        c.setFechaNacimiento(fechaNacimiento);
        clienteRepositorio.save(c);
        }


    @Override
    public void setTelefono(Long idC, String telefono) {
        if(telefono.isEmpty())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        Cliente c = clienteRepositorio.findById(idC).orElse(Cliente.ClienteNulo);
        if (c != Cliente.ClienteNulo) {
            c.setTelefono(telefono);
            clienteRepositorio.save(c);
        } else {
            throw new IllegalArgumentException("El cliente con ese idC no existe");
        }
    }

    @Override
    public void setTelefono(String dni, String telefono) {
        if(telefono.isEmpty())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        for (Cliente c : clienteRepositorio.findAll()) {
            if (c.getDni().equals(dni)) {
                c.setTelefono(telefono);
                clienteRepositorio.save(c);
                break;
            }
            throw new IllegalArgumentException("El cliente con ese dni no existe");
        }
    }


    @Override
    public String eliminarEstado(String estado)
    {
        if (!estado.isEmpty() & clienteEstadoRepositorio.cargarEstado("Cliente").stream().anyMatch(e -> e.equals(estado))) {
            clienteEstadoRepositorio.eliminarEstado(new Estado("Cliente",estado));
        } else {
            throw new IllegalArgumentException("El estado no es correcto");
        }
        return ("El estado se ha eliminado correctamente");
    }

    @Override
    public String crearEstado(String estado) {
        if (!estado.isEmpty() & clienteEstadoRepositorio.cargarEstado("Cliente").stream().noneMatch(e -> e.equals(estado))) {
            clienteEstadoRepositorio.guardarEstado(new Estado("Cliente",estado));
            return ("El estado se ha añadido correctamente");
        } else {
            return ("El estado no es correcto");
        }
    }

    @Override
    public Collection<String> getListaEstadoclientes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListaEstadoclientes'");
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Cliente> getListaClientes() {
        return (List<Cliente>) clienteRepositorio.findAll();

    }

    @Override
    public void guardarCliente(Cliente c) {
        clienteRepositorio.save(c);
    }


}
