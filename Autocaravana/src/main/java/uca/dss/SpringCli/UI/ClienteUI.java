package uca.dss.SpringCli.UI;
import jdk.jshell.JShell;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.boot.SpringShellProperties;
import org.springframework.shell.standard.commands.Clear;
import uca.core.Cliente;
import uca.core.ClienteServicio;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uca.dss.SpringCli.SpringCliApplication;

import java.io.IOException;
import java.util.Collection;

@ShellComponent
public class ClienteUI {

    private final ClienteControlador clienteControlador;
    private final ClienteServicio clienteServicio;


    public ClienteUI() {
        this.clienteControlador = new ClienteControlador();
        this.clienteServicio = new ClienteServicio(new ClienteRepositorioJackson(), new ClienteReglasBasicas());
    }

    @ShellMethod(key = "crear-cliente", value = "Crea un nuevo cliente")
    public String crearCliente(@ShellOption String nom, @ShellOption String ape, @ShellOption String tel, @ShellOption String fecha, @ShellOption String dni, @ShellOption String email) {
        clienteServicio.altaCliente(nom, ape, tel, fecha, dni, email);
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
    public String modificarTelefono(@ShellOption String dni, @ShellOption String email) {
        Cliente cliente = clienteServicio.buscarCliente(dni);
        if (cliente != null) {
            clienteServicio.setEmail(cliente, email);
            return "Email modificado con éxito";
        } else {
            return "No se ha encontrado ningún cliente con ese dni";
        }
    }
}



