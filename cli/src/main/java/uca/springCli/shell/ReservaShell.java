package uca.springCli.shell;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uca.core.servicio.*;
import uca.core.dominio.Reserva;

@ShellComponent
public class ReservaShell {
    private final iClienteServicio clienteServicio;
    private final iAutocaravanaServicio autocaravanaServicio;
    private final iReservaServicio reservaServicio ;

    @Autowired
    public ReservaShell(iClienteServicio clienteServicio, iAutocaravanaServicio autocaravanaServicio, iReservaServicio reservaServicio) {
        this.clienteServicio = clienteServicio;
        this.autocaravanaServicio = autocaravanaServicio;
        this.reservaServicio = reservaServicio;
    }


    //    @ShellMethod(key = "buscar-cliente", value = "Busca un cliente por un id")
    //    public void buscarCliente(@ShellOption(help = "Valor del identificador") String identificador,
    //                              @ShellOption(defaultValue = "dni", value = "-t", help = "Tipo de identificador [dni|idC|edad|nombre]") String type) {



    @ShellMethod(key = "crear-reserva", value = "Crea una nueva reserva")
    public String crearReserva(
            @ShellOption(help = "Valor del identificador") int idA,
            @ShellOption(help = "Valor del identificador") int idC,
            @ShellOption(help = "Fecha de inicio") String fechaInicio,
            @ShellOption(help = "Fecha de fin") String fechaFin) {
        try {
            reservaServicio.altaReserva(idA, idC, fechaInicio, fechaFin);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva creada con éxito";
    }

    //Realizar el checkin a una reserva dada
    @ShellMethod(key = "checkin", value = "Realiza el checkin a una reserva")
    public String checkin(@ShellOption(help = "Valor del identificador") int id) {
        try {
            return reservaServicio.checkin(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Realizar el checkout a una reserva dada
    @ShellMethod(key = "checkout", value = "Realiza el checkout a una reserva")
    public String checkout(@ShellOption(help = "Valor del identificador") int id){
        try {
            return reservaServicio.checkout(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @ShellMethod(key = "listar-reservas", value = "Lista todas las reservas")
    public void listarReservas() {
        try {
            List<Reserva> reservas = reservaServicio.getListaReservas().stream().toList();
            if (!reservas.isEmpty())
                PrintShell.imprimir(reservas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(key = "mostrar-reserva", value = "Muestra una reserva con la informacion del cliente y el coche")
        public void mostrarReserva(@ShellOption(help = "Valor del identificador") int id) {
        try {
            Reserva reserva = reservaServicio.buscarReserva(id);
            //muestra la reserva con el cliente y la autocaravana uno al lao del otro.
            //divido ambos en lineas y sumo cada linea de cada uno en una lista;
            //luego muestro la lista
            var cliente = clienteServicio.buscarCliente(reserva.getIdCliente());
            var Stringcliente = PrintShell.imprimir(cliente);
            List<String> printcliente= Stringcliente.lines().toList();
            var autocaravana = autocaravanaServicio.buscarAutocaravana(reserva.getIdAutocaravana());
            var Stringautocaravana = PrintShell.imprimir(autocaravana);
            List<String> printautocaravana= Stringautocaravana.lines().toList();
            List<String> printreserva= PrintShell.imprimir(reserva,autocaravana,cliente).lines().collect(Collectors.toList());
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
            @ShellOption(help = "Valor del identificador de la reserva") int id,
            @ShellOption(help = "Valor del identificador del cliente") int idC) {
        try {
            reservaServicio.setCliente(id, idC);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-autocaravana", value = "Modifica una reserva")
    public String modificarAutocaravana (
            @ShellOption(help = "Valor del identificador de la reserva") int id,
            @ShellOption(help = "Valor del identificador de la autocaravana") int idA) {
        try {
            reservaServicio.setAutocaravana(id,idA);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-fechaInicio", value = "Modifica una reserva")
    public String modificarFechaInicio (
            @ShellOption(help = "Valor del identificador de la reserva") int id,
            @ShellOption(help = "Fecha de inicio") String fechaInicio) {
        try {
            LocalDate.parse(fechaInicio);
            reservaServicio.setFechaIni(id, fechaInicio);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-fechaFin", value = "Modifica una reserva")
    public String modificarFechaFin (
            @ShellOption(help = "Valor del identificador de la reserva") int id,
            @ShellOption(help = "Fecha de fin") String fechaFin) {
        try {
            LocalDate.parse(fechaFin);
            reservaServicio.setFechaFin(id, fechaFin);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "modificar-precio", value = "Modifica una reserva")
    public String modificarPrecio (
            @ShellOption(help = "Valor del identificador de la reserva") int id,
            @ShellOption(help = "Precio") String precio) {
        try {
            reservaServicio.setPrecioTotal(id, BigDecimal.valueOf(Double.parseDouble(precio)));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva modificada con éxito";
    }

    @ShellMethod(key = "eliminar-reserva", value = "Elimina una reserva")
    public String eliminarReserva(@ShellOption(help = "Valor del identificador") int id){
        try {
            reservaServicio.eliminarReserva(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Reserva eliminada con éxito";
    }


}





