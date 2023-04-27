package uca.springCli.shell;

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//PATRON TEMPLATE

public class PrintShell {
    static String imprimir(Cliente cli)
    {
        var idC = cli.getIdC();
        var nombre = cli.getNombre();
        var apellido = cli.getApellido();
        var email = cli.getEmail();
        var dni = cli.getDni();
        var fechaNacimiento = cli.getFechaNacimiento();
        var cantidadReservasRealizadas = cli.getCantidadReservasRealizadas();
        var multas = cli.getMultas();
        var estado = cli.getEstado();


        String bold = "\033[1m";
        String red = "\033[31m";
        String green = "\033[32m";
        String white = "\033[37m";
        String reset = "\033[0m";

        String output = String.format(red + "╔═%s═\n"
                        + red+"║ CLIENTE "+reset+"%d"+red+"\n"
                        + red+"║═%s═"+reset+"\n"
                        + red+"║ Nombre: %s%s %s%s\n"
                        + red+"║ Email: %s%s%s\n"
                        + red+"║ DNI: %s%s%s\n"
                        + red+"║ Fecha de nacimiento: %s%s%s\n"
                        + red+"║ Reservas realizadas: %s%d%s\n",
                "═".repeat(33+ String.valueOf(idC).length()),
                idC,
                "═".repeat(33 + String.valueOf(idC).length()),
                reset, nombre, apellido, red,
                reset, email, red,
                reset, dni, red,
                reset, fechaNacimiento, red,
                reset,cantidadReservasRealizadas, red);

        if (multas > 0) {
            output += String.format("║ Multas: %s%d%s\n", green, multas, reset);
        }
        output += String.format(red + "╚═%s═%s\n", "═".repeat(33 + String.valueOf(idC).length()), reset);

        String finalOutput = "";
        for (int i = 0; i < output.lines().toList().size(); i++){
            finalOutput += output.lines().toList().get(i);
            if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 1)

                //add a " " until every line has the same length
                for (int j = 0; j < 51 - output.lines().toList().get(i).length(); j++){
                    finalOutput += " ";
                }
            if (i == 0)
                finalOutput += red+"╗"+reset+"\n";
            else if (i == output.lines().toList().size() - 1)
                finalOutput += red+"╝"+reset+"\n";
            else
                finalOutput += red+"║"+reset+"\n";}

        return finalOutput;
    }

    static String imprimir(Reserva reserva, Autocaravana autocaravana, Cliente cliente){
        String green = "\033[32m";
        String reset = "\033[0m";
        var idR = reserva.getIdR();
        var autocaravanaR = reserva.getIdAutocaravana();
        var clienteR = reserva.getIdCliente();
        var fechaIni = reserva.getFechaIni();
        var fechaFin = reserva.getFechaFin();
        var estadoR = reserva.getEstadoR();
        var precioTotal = reserva.getPrecioTotal();
        var precioPagado = reserva.getPagado();


        // usar String.format() para dar formato a la salida
        String output = String.format(green +"╔═%s═"+reset +"\n"
                        + green +"║ RESERVA "+reset+"%d"+reset+"\n"
                        + green +"║══%s═" +reset+ "\n"
                        + green +"║ Fecha Inicio: "+reset+"%s"+reset+"\n"
                        + green +"║ Fecha Fin: "+reset+"%s"+reset+"\n"
                        + green +"║ Precio Total: "+reset+"%.2f"+reset+"\n"
                        + green +"║ Estado: "+reset+"%s"+reset+"\n"
                        + green +"║ Precio pagado: "+reset+"%s"+reset+"\n"
                        + green +"╚═%s═"+reset+"\n",
                "═".repeat(Math.max(0, 36 + String.valueOf(idR).length() - 2)),
                idR,
                "═".repeat(Math.max(0, 37 + String.valueOf(idR).length() - 4)),

                fechaIni,
                fechaFin,
                precioTotal,
                estadoR,
                precioPagado,
                "═".repeat(Math.max(0, 36 + String.valueOf(idR).length() - 2)));


        //add ║ to each line at the end
        String finalOutput = "";
        for (int i = 0; i < output.lines().toList().size(); i++){
            finalOutput += output.lines().toList().get(i);
            if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 1)

                //add a " " until every line has the same length
                for (int j = 0; j < 51 - output.lines().toList().get(i).length(); j++){
                    finalOutput += " ";
                }
            if (i == 0)
                finalOutput += green+"╗"+reset+"\n";
            else if (i == output.lines().toList().size() - 1)
                finalOutput += green+"╝"+reset+"\n";
            else
                finalOutput += green+"║"+reset+"\n";}

        return finalOutput;
    }

    static String imprimir(Reserva reserva){
        String green = "\033[32m";
        String reset = "\033[0m";
        var idR = reserva.getIdR();
        var autocaravanaR = reserva.getIdAutocaravana();
        var clienteR = reserva.getIdCliente();
        var fechaIni = reserva.getFechaIni();
        var fechaFin = reserva.getFechaFin();
        var estadoR = reserva.getEstadoR();
        var precioTotal = reserva.getPrecioTotal();


        // usar String.format() para dar formato a la salida
        String output = String.format(green +"╔═%s═"+reset +"\n"
                        + green +"║ RESERVA "+reset+"%d"+reset+"\n"
                        + green +"║══%s═" +reset+ "\n"
                        + green +"║ Caravana: " +reset+ "%s"+reset+"\n"
                        + green +"║ Cliente: "+reset+ "%s"+reset+"\n"
                        + green +"║ Fecha Inicio: "+reset+"%s"+reset+"\n"
                        + green +"║ Fecha Fin: "+reset+"%s"+reset+"\n"
                        + green +"║ Precio Total: "+reset+"%.2f"+reset+"\n"
                        + green +"║ Estado: "+reset+"%s"+reset+"\n"
                        + green +"╚═%s═"+reset+"\n",
                "═".repeat(Math.max(0, 36 + String.valueOf(idR).length() - 2)),
                idR,
                "═".repeat(Math.max(0, 37 + String.valueOf(idR).length() - 4)),
                autocaravanaR,
                clienteR,
                fechaIni,
                fechaFin,
                precioTotal,
                estadoR,
                "═".repeat(Math.max(0, 36 + String.valueOf(idR).length() - 2)));


        //add ║ to each line at the end
        String finalOutput = "";
        for (int i = 0; i < output.lines().toList().size(); i++){
            finalOutput += output.lines().toList().get(i);
            if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 1)

                //add a " " until every line has the same length
                for (int j = 0; j < 51 - output.lines().toList().get(i).length(); j++){
                    finalOutput += " ";
                }
            if (i == 0)
                finalOutput += green+"╗"+reset+"\n";
            else if (i == output.lines().toList().size() - 1)
                finalOutput += green+"╝"+reset+"\n";
            else
                finalOutput += green+"║"+reset+"\n";}

        return finalOutput;
    }







    static String imprimir(Autocaravana autocaravana)
    {
        int idA = autocaravana.getIdA();
        String modelo = autocaravana.getModelo();
        BigDecimal precioPorDia = autocaravana.getPrecioPorDia();
        String matricula = autocaravana.getMatricula();
        int kilometraje = autocaravana.getKilometraje();
        int plazas = autocaravana.getPlazas();

        if (!(listObject.get(0) instanceof Autocaravana) & !(listObject.get(0) instanceof Reserva) & !(listObject.get(0) instanceof Cliente))

        String yellow = "\033[33m";
        String reset = "\033[0m";
        // usar String.format() para dar formato a la salida
        String output = String.format(yellow + "╔═%s═\n"
                        + yellow+"║ AUTOCARAVANA "+reset+"%d\n"
                        + yellow+"║═%s═\n"
                        + yellow+"║ Modelo: "+reset+"%s\n"
                        + yellow+"║ Precio por día: "+reset+"%.2f €\n"
                        + yellow+"║ Matrícula: "+reset+"%s\n"
                        + yellow+"║ Kilometraje: "+reset+"%d km\n"
                        + yellow+"║ Plazas: "+reset+"%d\n"

                        + yellow+"╚═%s═\n" + reset,
                "═".repeat(27 + String.valueOf(idA).length()),
                idA,
                "═".repeat(27 + String.valueOf(idA).length()),
                modelo,
                precioPorDia,
                matricula,
                kilometraje,
                plazas,
                "═".repeat(27 + String.valueOf(idA).length()));

        //add ║ to each line at the end
        String finalOutput = "";
        for (int i = 0; i < output.lines().toList().size(); i++){
            finalOutput += output.lines().toList().get(i);
            if (i != 0 & i!= 2 & i!= output.lines().toList().size() - 2)
                //add a " " until every line has the same length
                for (int j = 0; j < 40 - output.lines().toList().get(i).length(); j++){
                    finalOutput += " ";
                }

            if (i == 0)
                finalOutput += yellow+"╗"+reset+"\n";
            else if (i == output.lines().toList().size() - 2)
                finalOutput += yellow+"╝"+reset+"\n";
            else if (i < output.lines().toList().size() - 2)
                finalOutput += yellow+"║"+reset+"\n";}

        return finalOutput;

    }

    //List can be only of Autocaravana or Reserva or Cliente


    //List of Reserva or Autocaravana or Cliente ONLY ONE TYPE
    static void imprimir(List<?> listObject)
    {
        //transform the listObject into a list of autocaravanas or reservas or clientes
        if (!(listObject.get(0) instanceof Autocaravana) & !(listObject.get(0) instanceof Reserva) & !(listObject.get(0) instanceof Cliente))
            throw new IllegalArgumentException("The list must be of Autocaravana, Reserva or Cliente");

        List<String> print = new ArrayList<>();
        int lineas = imprimir(listObject.get(0)).lines().toList().size();
        for (int i = 0; i < lineas; i++) {
            print.add("");
        }
        //get the three autocaravanas and print them one next to the other. Add a print line every three autocaravanas
        for (int i = 0; i < listObject.size(); i++) {
            for (int j=0; j< PrintShell.imprimir(listObject.get(i)).lines().toList().size(); j++) {
                print.set(j,print.get(j)+PrintShell.imprimir(listObject.get(i)).lines().toList().get(j));
            }
            if ((i + 1) % 3 == 0) {
                for (String s : print) {
                    System.out.println(s);
                    print.set(print.indexOf(s), "");
                }
            }
        }
        if (!print.get(0).equals("")) {
            for (String s : print) {
                System.out.println(s);
                print.set(print.indexOf(s), "");
            }
        }
    }

    static String imprimir(Object object)
    {
        if (!(object instanceof Autocaravana) & !(object instanceof Reserva) & !(object instanceof Cliente))
            throw new IllegalArgumentException("The object must be of Autocaravana, Reserva or Cliente");
        if (object instanceof Autocaravana)
            return imprimir((Autocaravana) object);
        else if (object instanceof Reserva)
            return imprimir((Reserva) object);
        else
            return imprimir((Cliente) object);
    }
}
