package uca.dss.SpringCli.UI;
import uca.core.Cliente;
import uca.core.ClienteReglas;
import uca.core.ClienteServicio;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class ClienteUI {

    private final ClienteControlador clienteControlador;
    private final ClienteServicio clienteServicio;


    public ClienteUI() {
        this.clienteControlador = new ClienteControlador();
        this.clienteServicio = new ClienteServicio(new ClienteRepositorioJackson(),new ClienteReglasBasicas() );
    }

    @ShellMethod(key = "crear-cliente", value = "Crea un nuevo cliente")
    public String crearCliente(@ShellOption String nom, @ShellOption String ape, @ShellOption String tel, @ShellOption String fecha, @ShellOption String dni, @ShellOption String email) {
        try{
            clienteServicio.altaCliente(nom, ape, tel, fecha, dni, email);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Cliente creado con éxito";
    }

    @ShellMethod(key = "listar-clientes", value = "Lista todos los clientes")
    public void listarClientes() {
        clienteServicio.getListaClientes().forEach(System.out::println);
    }

    @ShellMethod(key = "buscar-cliente", value = "Busca un cliente por su dni")
    public void buscarCliente(@ShellOption String dni) {
        Cliente cliente = clienteServicio.buscarCliente(dni);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("No se ha encontrado ningún cliente con ese dni");
        }
    }

    @ShellMethod(key = "modificar-email", value = "Modifica el email de un cliente")
    public String modificarEmail(@ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de dato [dni|idC]") String type,
                                 @ShellOption(help = "Valor del dato") String dato,
                                 @ShellOption(help = "Nuevo email") String email) {
        try {
            if (type.equals("dni")) {
                clienteServicio.setEmail(dato, email);
            } else if (type.equals("idC")) {
                int idC = Integer.parseInt(dato);
                clienteServicio.setEmail(idC, email);
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Email modificado con éxito";
    }

    @ShellMethod(key = "modificar-telefono", value = "Modifica el teléfono de un cliente")
    public String modificarTelefono(@ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de dato [dni|idC]") String type,
                                    @ShellOption(help = "Valor del dato") String dato,
                                    @ShellOption(help = "Nuevo teléfono") String telefono) {
        try {
            if (type.equals("dni")) {
                clienteServicio.setTelefono(dato, telefono);
            } else if (type.equals("idC")) {
                int idC = Integer.parseInt(dato);
                clienteServicio.setTelefono(idC, telefono);
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Teléfono modificado con éxito";
    }



    @ShellMethod(key = "modificar-nombre", value = "Modifica el nombre de un cliente")
    public String modificarNombre(
            @ShellOption(help = "Valor del dato") String dato,
            @ShellOption(help = "Nuevo nombre", value = { "-n", "--nombre" }) String nombre,
            @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de dato [dni|idC]") String type

            ) {
        try {
            if (type.equals("dni")) {
                clienteServicio.setNombre(dato, nombre);
            } else if (type.equals("idC")) {
                int idC = Integer.parseInt(dato);
                clienteServicio.setNombre(idC, nombre);
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Nombre modificado con éxito";
    }








    @ShellMethod(key = "modificar-apellido", value = "Modifica el apellido de un cliente")
    public String modificarapellido(@ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de dato [dni|idC]") String type,
                                    @ShellOption(help = "Valor del dato") String dato,
                                    @ShellOption(help = "Nuevo apellido") String nombre)
    {
        try {
            if (type.equals("dni")) {
                clienteServicio.setApellido(dato, nombre);
            } else if (type.equals("idC")) {
                int idC = Integer.parseInt(dato);
                clienteServicio.setApellido(idC, nombre);
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Apellido modificado con éxito";
    }

    @ShellMethod(key = "modificar-fecha", value = "Modifica la fecha de nacimiento de un cliente")
    public String modificarfecha(@ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de dato [dni|idC]") String type,
                                 @ShellOption(help = "Valor del dato") String dato,
                                 @ShellOption(help = "Nuevo apellido") String nombre)
    {
        try {
            if (type.equals("dni")) {
                clienteServicio.setFechaNacimiento(dato , nombre);
            } else if (type.equals("idC")) {
                int idC = Integer.parseInt(dato);
                clienteServicio.setFechaNacimiento(idC, nombre);
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Fecha modificado con éxito";
    }

    @ShellMethod(key = "borrar-cliente", value = "Modifica la fecha de nacimiento de un cliente")
    public String borrarcliente(@ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de dato [dni|idC]") String type,
                                @ShellOption(help = "Valor del dato") String dato){
        try {
            if (type.equals("dni")) {
                clienteServicio.eliminarCliente(dato);            }
            else if (type.equals("idC")) {
                int idC = Integer.parseInt(dato);
                clienteServicio.eliminarCliente(idC);            }
            else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Cliente eliminado con éxito";
    }


}



