package uca.springCli.UI;



import uca.core.servicio.ClienteServicio;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uca.core.servicio.iClienteServicio;
import uca.core.servicio.reglas.ClienteReglas;
import uca.springCli.Implementaciones.ClienteEstadoRepositorioImpl;
import uca.springCli.Implementaciones.ClienteRepositorioImpl;


@ShellComponent
public class ClienteShell {

   // @Autowired
    private final iClienteServicio clienteServicio = new ClienteServicio (new ClienteRepositorioImpl(),new ClienteEstadoRepositorioImpl());



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
        //transform every uca.core.cliente to a uca.springCli.UI.cliente
        clienteServicio.getListaClientes().forEach(cli -> System.out.println(Print.imprimir(cli)));
    }

    @ShellMethod(key = "buscar-cliente", value = "Busca un cliente por un dato")
    public void buscarCliente(@ShellOption(help = "Valor del identificador") String identificador,
                              @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC|edad|nombre]") String type) {

        var cliente = clienteServicio.buscarCliente(type, identificador);
        if (!cliente.isEmpty()) {
            cliente.stream().forEach(System.out::println);
        } else {
            System.out.println("No se ha encontrado ningún cliente con ese identificador");
        }

    }

    @ShellMethod(key = "modificar-email", value = "Modifica el email de un cliente")
    public String modificarEmail(
            @ShellOption(help = "Valor del identificador") String identificador,
            @ShellOption(help = "Nuevo email", value = { "-e", "--email" }) String email,
            @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC]") String type) {
        try {
            if (type.equals("dni")) {
                clienteServicio.setEmail(identificador, email);
            } else if (type.equals("idC")) {
                try {
                    int idC = Integer.parseInt(identificador);
                    clienteServicio.setEmail(idC, email);
                }catch (Exception e){
                    return "El idC debe ser un número";
                }
            } else {
                return "Tipo de identificador no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Email modificado con éxito";
    }

    @ShellMethod(key = "modificar-telefono", value = "Modifica el teléfono de un cliente")
    public String modificarTelefono(@ShellOption(help = "Valor del identificador") String identificador,
                                    @ShellOption(help = "Nuevo teléfono", value = { "-t", "--telefono" }) String telefono,
                                    @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC]") String type) {
        try {
            if (type.equals("dni")) {
                clienteServicio.setTelefono(identificador, telefono);
            } else if (type.equals("idC")) {
                try {
                    int idC = Integer.parseInt(identificador);
                    clienteServicio.setTelefono(idC, telefono);
                }catch (Exception e){
                    return "El idC debe ser un número";
                }
            } else {
                return "Tipo de identificador no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Teléfono modificado con éxito";
    }



    @ShellMethod(key = "modificar-nombre", value = "Modifica el nombre de un cliente")
    public String modificarNombre(
            @ShellOption(help = "Valor del identificador") String identificador,
            @ShellOption(help = "Nuevo nombre", value = { "-n", "--nombre" }) String nombre,
            @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC]") String type

            ) {
        try {
            if (type.equals("dni")) {
                clienteServicio.setNombre(identificador, nombre);
            } else if (type.equals("idC")) {
                try{
                    int idC = Integer.parseInt(identificador);
                    clienteServicio.setNombre(idC, nombre);
                }catch (Exception e){
                    return "El idC debe ser un número";
                }

            } else {
                return "Tipo de identificador no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Nombre modificado con éxito";
    }








    @ShellMethod(key = "modificar-apellido", value = "Modifica el apellido de un cliente")
    public String modificarapellido(@ShellOption(help = "Valor del identificador") String identificador,
                                    @ShellOption(help = "Nuevo apellido", value = { "-a", "--apellido" }) String nombre,
                                    @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC]") String type)
    {
        try {
            if (type.equals("dni")) {
                clienteServicio.setApellido(identificador, nombre);
            } else if (type.equals("idC")) {
                try {
                    int idC = Integer.parseInt(identificador);
                    clienteServicio.setApellido(idC, nombre);
                }catch (Exception e){
                    return "El idC debe ser un número";
                }
            } else {
                return "Tipo de identificador no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Apellido modificado con éxito";
    }

    @ShellMethod(key = "modificar-fecha", value = "Modifica la fecha de nacimiento de un cliente")
    public String modificarfecha(@ShellOption(help = "Valor del identificador") String identificador,
                                 @ShellOption(help = "Nueva fecha de nacimiento", value = { "-f", "--fecha" }) String nombre,
                                 @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC]") String type)
    {
        try {
            if (type.equals("dni")) {
                clienteServicio.setFechaNacimiento(identificador , nombre);
            } else if (type.equals("idC")) {
                try {
                    int idC = Integer.parseInt(identificador);
                    clienteServicio.setFechaNacimiento(idC, nombre);
                }catch (Exception e){
                    return "El idC debe ser un número";
                }
            } else {
                return "Tipo de identificador no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Fecha modificado con éxito";
    }

    @ShellMethod(key = "borrar-cliente", value = "Modifica la fecha de nacimiento de un cliente")
    public String borrarcliente(@ShellOption(help = "Valor del identificador") String identificador,
                                @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC]") String type){
        try {
            if (type.equals("dni")) {
                clienteServicio.eliminarCliente(identificador);            }
            else if (type.equals("idC")) {
                try {
                    int idC = Integer.parseInt(identificador);
                    clienteServicio.eliminarCliente(idC);
                }catch (Exception e){
                    return "El idC debe ser un número";
                }}
            else {
                return "Tipo de identificador no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Cliente eliminado con éxito";
    }

}



