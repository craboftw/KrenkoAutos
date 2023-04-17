package uca.springCli.UI;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uca.core.*;
import uca.springCli.Implementaciones.*;

@ShellComponent
public class ReservaUI {

    private final ReservaServicio reservaServicio;
    private final ClienteServicio clienteServicio;
    private final AutocaravanaServicio autocaravanaServicio;
    private final ClienteRepositorio clienteRepositorio;
    private final ReservaEstadoRepositorioJackson reservaEstadoRepositorio;


    public ReservaUI() {
        this.clienteRepositorio = new ClienteRepositorioJackson();
        this.reservaServicio = new ReservaServicio(new ReservaReglasBasicas(new ReservaRepositorioJackson()), new ReservaRepositorioJackson(), new ReservaEstadoRepositorioJackson());
        this.clienteServicio = new ClienteServicio(new ClienteReglasBasicas(), new ClienteRepositorioJackson(), new ClienteEstadoRepositorioJackson());
        this.autocaravanaServicio = new AutocaravanaServicio(new AutocaravanaRepositorioJackson(), new AutocaravanaReglasBasicas(), new AutocaravanaEstadoRepositorioJackson());
        this.reservaEstadoRepositorio = new ReservaEstadoRepositorioJackson();
    }


    //    @ShellMethod(key = "buscar-cliente", value = "Busca un cliente por un dato")
    //    public void buscarCliente(@ShellOption(help = "Valor del identificador") String identificador,
    //                              @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC|edad|nombre]") String type) {


    @ShellMethod(key = "crear-reserva", value = "Crea una nueva reserva")
    public String crearReserva(
            @ShellOption(help = "Valor del identificador") String datoA,
            @ShellOption(help = "Valor del identificador") String datoC,
            @ShellOption(help = "Fecha de inicio") String fechaInicio,
            @ShellOption(help = "Fecha de fin") String fechaFin,
            @ShellOption(help = "Tipo de identificador") String tipoA,
            @ShellOption(help = "Tipo de identificador") String tipoC) {

        try {
            Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(tipoA, datoA).stream().toList().get(0);
            Cliente cliente = clienteServicio.buscarCliente(tipoC, datoC).stream().toList().get(0);
            reservaServicio.altaReserva(autocaravana, cliente, fechaInicio, fechaFin);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva creada con éxito";
    }



    @ShellMethod(key = "listar-reservas", value = "Lista todas las reservas")
    public String listarReservas() {
        return reservaServicio.getListaReservas().stream().toString();
    }

}


