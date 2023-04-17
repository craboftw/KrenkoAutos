package Uca.SpringCli.UI;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import Uca.Core.Autocaravana;
import Uca.Core.AutocaravanaServicio;
import Uca.SpringCli.Implementaciones.AutocaravanaEstadoRepositorioJackson;
import Uca.SpringCli.Implementaciones.AutocaravanaReglasBasicas;
import Uca.SpringCli.Implementaciones.AutocaravanaRepositorioJackson;
import Uca.Core.AutocaravanaReglas;

import java.math.BigDecimal;

@ShellComponent
public class AutocaravanaUI {
  // Igual que ClienteUI pero con Autocaravana
    AutocaravanaUI() {
        this.autocaravanaReglas = new AutocaravanaReglasBasicas();
        this.autocaravanaServicio = new AutocaravanaServicio(new AutocaravanaRepositorioJackson(), new AutocaravanaReglasBasicas(), new AutocaravanaEstadoRepositorioJackson());
    }

    private final AutocaravanaServicio autocaravanaServicio;
    private final AutocaravanaReglas autocaravanaReglas;

    @ShellMethod(key = "crear-autocaravana", value = "Crea una nueva autocaravana")
    public String crearAutocaravana(@ShellOption String modelo, @ShellOption String matricula, @ShellOption BigDecimal precio, @ShellOption int plazas, @ShellOption int kilometraje) {
            try{
            autocaravanaServicio.altaAutocaravana(modelo, precio, plazas, matricula, kilometraje);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Autocaravana creada con éxito";
    }

    @ShellMethod(key = "listar-autocaravanas", value = "Lista todas las autocaravanas")
    public void listarAutocaravanas() {
        autocaravanaServicio.getListaAutocaravanas().forEach(System.out::println);
    }

    @ShellMethod(key = "buscar-autocaravana", value = "Busca una autocaravana por un dato")
    public void buscarAutocaravana(@ShellOption (help = "Tipo de dato [matricula|idA|modelo|plazas|estado|") String tipo,
                                   @ShellOption String dato) {
        var autocaravana = autocaravanaServicio.buscarAutocaravana(tipo, dato);
        if (!autocaravana.isEmpty()) {
            autocaravana.stream().forEach(System.out::println);
        } else {
            System.out.println("No se ha encontrado ninguna autocaravana con esa matricula");
        }
    }

    @ShellMethod(key = "modificar-modelo", value = "Modifica el modelo de una autocaravana.")
    public String modificarEmail(@ShellOption(defaultValue = "matricula", value = "-t", help = "Tipo de dato [matricula|idA]") String type,
                                 @ShellOption(help = "Valor del dato") String dato,
                                 @ShellOption(help = "Nuevo modelo") String modelo) {
        try {
            if (type.equals("matricula")) {
                autocaravanaServicio.setModelo(dato, modelo);
            } else if (type.equals("idA")) {
                int idC = Integer.parseInt(dato);
                autocaravanaServicio.setModelo(idC, modelo);
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Modelo modificado con éxito";
    }

    @ShellMethod(key = "modificar-precio", value = "Modifica el precio de una autocaravana.")
    public String modificarPrecio(          @ShellOption(help = "Dato identificador") String dato,
                                            @ShellOption(help = "Nuevo precio por dia", value = { "-m", "--modelo" }) BigDecimal precio,
                                            @ShellOption(defaultValue = "matricula", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

        try {
            if (type.equals("matricula")) {
                autocaravanaServicio.setPrecio(dato, precio);
            } else if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.setPrecio(idC, precio);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Precio modificado con éxito";
    }



}
