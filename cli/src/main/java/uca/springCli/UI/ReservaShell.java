package uca.springCli.UI;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uca.core.dao.ReservaRepositorio;
import uca.core.servicio.AutocaravanaServicio;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;
import uca.core.servicio.ClienteServicio;
import uca.core.servicio.ReservaServicio;
import uca.springCli.Implementaciones.*;

@ShellComponent
public class ReservaShell {

    //@Autowired
    private final ClienteServicio clienteServicio = new ClienteServicio(new ClienteRepositorioImpl(),new ClienteEstadoRepositorioImpl());
    private final AutocaravanaServicio autocaravanaServicio = new AutocaravanaServicio(new AutocaravanaRepositorioImpl(), new AutocaravanaEstadoRepositorioImpl());
    private final ReservaServicio reservaServicio = new ReservaServicio(new ReservaRepositorioImpl(), new ReservaEstadoRepositorioImpl(), autocaravanaServicio, clienteServicio);






    //    @ShellMethod(key = "buscar-cliente", value = "Busca un cliente por un dato")
    //    public void buscarCliente(@ShellOption(help = "Valor del identificador") String identificador,
    //                              @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC|edad|nombre]") String type) {



    @ShellMethod(key = "crear-reserva", value = "Crea una nueva reserva")
    public String crearReserva(
            @ShellOption(help = "Valor del identificador") int datoA,
            @ShellOption(help = "Valor del identificador") int datoC,
            @ShellOption(help = "Fecha de inicio") String fechaInicio,
            @ShellOption(help = "Fecha de fin") String fechaFin) {
        try {
            reservaServicio.altaReserva(datoA, datoC, fechaInicio, fechaFin);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva creada con éxito";
    }

    //Realizar el checkin a una reserva dada
    @ShellMethod(key = "checkin", value = "Realiza el checkin a una reserva")
    public String checkin(@ShellOption(help = "Valor del identificador") String dato,
                          @ShellOption(help = "Tipo de identificador") String tipo) {
        try {
            return reservaServicio.checkin(reserva);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Realizar el checkout a una reserva dada
    @ShellMethod(key = "checkout", value = "Realiza el checkout a una reserva")
    public String checkout(@ShellOption(help = "Valor del identificador") String dato,
                           @ShellOption(help = "Tipo de identificador") String tipo) {
        try {
            return reservaServicio.checkout(reserva);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @ShellMethod(key = "listar-reservas", value = "Lista todas las reservas")
    public void listarReservas() {
        reservaServicio.getListaReservas().forEach(reserva -> System.out.println(Print.imprimir(reserva)));
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
            var cliente = clienteServicio.buscarCliente(reserva.getIdCliente());
            var Stringcliente = Print.imprimir(cliente);
            List<String> printcliente= Stringcliente.lines().toList();
            var autocaravana = autocaravanaServicio.buscarAutocarvana(reserva.getIdAutocaravana());
            var Stringautocaravana = Print.imprimir(autocaravana);
            List<String> printautocaravana= Stringautocaravana.lines().toList();
            List<String> printreserva= Print.imprimir(reserva,autocaravana,cliente).lines().collect(Collectors.toList());

            String bold = "\033[0;1m";

            System.out.println(bold+"═".repeat(117));
            System.out.println(bold+" ".repeat(15) + "Reserva" + " ".repeat(35) + "Cliente" + " ".repeat(29) + "Autocaravana");
            for (int i = 0; i < printautocaravana.size()-1; i++) {
                System.out.println(printreserva.get(i) + " " + printcliente.get(i) + " " + printautocaravana.get(i));
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
                if(reserva.getIdAutocaravana() == (autocaravana.getIdA()))
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
                if(reserva.getIdCliente() == (cliente.getIdC()))
                {
                    reservaServicio.setCliente(reserva, cliente);

                }
            }
        }
    }
}





