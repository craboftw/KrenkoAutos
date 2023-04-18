package uca.springCli.UI;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public void listarReservas() {
        reservaServicio.getListaReservas().forEach(System.out::println);
    }

    //mostrar-reserva muestra todo de una reserva
    @ShellMethod(key = "mostrar-reserva", value = "Muestra una reserva con la informacion del cliente y el coche")
    public void mostrarReserva(@ShellOption(help = "Valor del identificador") String dato,
                               @ShellOption(defaultValue = "id", value = "-t", help = "Tipo de identificador [dni|idC|edad|nombre]") String type) {
        try {
            Reserva reserva = reservaServicio.buscarReserva(type, dato).stream().toList().get(0);
            //muestra la reserva con el cliente y la autocaravana uno al lao del otro.
            //divido ambos en lineas y sumo cada linea de cada uno en una lista;
            //luego muestro la lista
            List<String> printreserva= reserva.toString().lines().collect(Collectors.toList());
            List<String> printcliente= reserva.getCliente().toString().lines().collect(Collectors.toList());
            List<String> printautocaravana= reserva.getAutocaravana().toString().lines().collect(Collectors.toList());
            String bold = "\033[0;1m";

            System.out.println(bold+"═".repeat(115));
            System.out.println(bold+" ".repeat(15) + "Reserva" + " ".repeat(35) + "Cliente" + " ".repeat(29) + "Autocaravana");
            for (int i = 0; i < printautocaravana.size(); i++) {
                System.out.println(printreserva.get(i) + " " + printcliente.get(i) + " " + printautocaravana.get(i));
            }
            for (int i = printautocaravana.size(); i < printcliente.size(); i++) {
                System.out.println(printreserva.get(i) + " " + printcliente.get(i));
            }
            for (int i = printcliente.size(); i < printreserva.size(); i++) {
                System.out.println(printreserva.get(i));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(key = "modificar-cliente", value = "Modifica una reserva")
    public String modificarCliente (
            @ShellOption(help = "Valor del identificador de la reserva") String dato,
            @ShellOption(help = "Valor del identificador del cliente") String datoC,
            @ShellOption(help = "Tipo de identificador del cliente idC|dni ") String tipoC) {
        try {
            Cliente cliente = clienteServicio.buscarCliente(tipoC, datoC).stream().toList().get(0);
            Reserva reserva = reservaServicio.buscarReserva("id", dato).stream().toList().get(0);
            reservaServicio.setCliente(reserva, cliente);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-autocaravana", value = "Modifica una reserva")
    public String modificarAutocaravana (
            @ShellOption(help = "Valor del identificador de la reserva") String dato,
            @ShellOption(help = "Valor del identificador de la autocaravana") String datoA,
            @ShellOption(help = "Tipo de identificador de la autocaravana idA|matricula ") String tipoA) {
        try {
            Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(tipoA, datoA).stream().toList().get(0);
            Reserva reserva = reservaServicio.buscarReserva("id", dato).stream().toList().get(0);
            reservaServicio.setAutocaravana(reserva, autocaravana);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-fechaInicio", value = "Modifica una reserva")
    public String modificarFechaInicio (
            @ShellOption(help = "Valor del identificador de la reserva") String dato,
            @ShellOption(help = "Fecha de inicio") String fechaInicio) {
        try {
            LocalDate.parse(fechaInicio);
            Reserva reserva = reservaServicio.buscarReserva("id", dato).stream().toList().get(0);
            reservaServicio.setFechaIni(reserva, fechaInicio);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-fechaFin", value = "Modifica una reserva")
    public String modificarFechaFin (
            @ShellOption(help = "Valor del identificador de la reserva") String dato,
            @ShellOption(help = "Fecha de fin") String fechaFin) {
        try {
            LocalDate.parse(fechaFin);
            Reserva reserva = reservaServicio.buscarReserva("id", dato).stream().toList().get(0);
            reservaServicio.setFechaFin(reserva, fechaFin);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-precio", value = "Modifica una reserva")
    public String modificarPrecio (
            @ShellOption(help = "Valor del identificador de la reserva") String dato,
            @ShellOption(help = "Precio") String precio) {
        try {
            Reserva reserva = reservaServicio.buscarReserva("id", dato).stream().toList().get(0);
            reservaServicio.setPrecioTotal(reserva, BigDecimal.valueOf(Double.parseDouble(precio)));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "eliminar-reserva", value = "Elimina una reserva")
    public String eliminarReserva(@ShellOption(help = "Valor del identificador") String dato,
                                  @ShellOption(defaultValue = "id", value = "-t", help = "Tipo de identificador [dni|idC|edad|nombre]") String type) {
        try {
            Reserva reserva = reservaServicio.buscarReserva(type, dato).stream().toList().get(0);
            reservaServicio.eliminarReserva(reserva);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva eliminada con éxito";

    }

    public void arreglarAutocaravana(Autocaravana autocaravana)
    {
        if(autocaravana.getVecesReservada() > 0)
        {
           var reservas = reservaServicio.getListaReservas();
            if (!reservas.isEmpty()) {
              for (Reserva reserva : reservas) {
                if(reserva.getAutocaravana().getIdA() == (autocaravana.getIdA()))
                {
                reservaServicio.setAutocaravana(reserva, autocaravana);
                }
              }
        }
    }
}

    public void arreglarCliente(Cliente cliente)
    {
        if(cliente.getCantidadReservasRealizadas() > 0)
        {
            var reservas = reservaServicio.getListaReservas();
            if (reservas.isEmpty()) {
                return;
            }
            for (Reserva reserva : reservas) {
                if(reserva.getCliente().getIdC() == (cliente.getIdC()))
                {
                    reservaServicio.setCliente(reserva, cliente);

                }
            }
        }
    }
}





