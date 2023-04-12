package uca.dss.SpringCli.UI;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uca.core.Autocaravana;
import uca.core.AutocaravanaReglas;
import uca.core.AutocaravanaServicio;
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

    @ShellMethod(key = "modificar-modelo", value = "Modifica el modelo de una autocaravana")
    public String modificarModelo(@ShellOption String matricula, @ShellOption String modelo) {
        try {
            autocaravanaServicio.setModelo(matricula, modelo);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Modelo modificado con éxito";
    }



}
