package uca.springCli.UI;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import uca.core.servicio.iAutocaravanaServicio;
import uca.core.servicio.reglas.AutocaravanaReglas;
import uca.core.servicio.AutocaravanaServicio;
import uca.springCli.Implementaciones.AutocaravanaEstadoRepositorioImpl;
import uca.springCli.Implementaciones.AutocaravanaRepositorioImpl;

import java.math.BigDecimal;

@ShellComponent
public class AutocaravanaShell {
  // Igual que ClienteUI pero con Autocaravana
    AutocaravanaShell() {
        this.autocaravanaServicio = new AutocaravanaServicio(new AutocaravanaRepositorioImpl(), new AutocaravanaEstadoRepositorioImpl());
    }

    private final iAutocaravanaServicio autocaravanaServicio;

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
        autocaravanaServicio.getListaAutocaravanas().forEach(autocaravana -> System.out.println(Print.imprimir(autocaravana)));
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

    @ShellMethod(key = "modificar-plazas", value = "Modifica el número de plazas de una autocaravana.")
    public String modificarPlazas(          @ShellOption(help = "Dato identificador") String dato,
                                            @ShellOption(help = "Nuevo número de plazas", value = { "-m", "--modelo" }) int plazas,
                                            @ShellOption(defaultValue = "matricula", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

        try {
            if (type.equals("matricula")) {
                autocaravanaServicio.setPlazas(dato, plazas);
            } else if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.setPlazas(idC, plazas);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Número de plazas modificado con éxito";
    }

    @ShellMethod(key = "modificar-kilometraje", value = "Modifica el kilometraje de una autocaravana.")
    public String modificarKilometraje(          @ShellOption(help = "Dato identificador") String dato,
                                            @ShellOption(help = "Nuevo kilometraje", value = { "-m", "--modelo" }) int kilometraje,
                                            @ShellOption(defaultValue = "matricula", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

        try {
            if (type.equals("matricula")) {
                autocaravanaServicio.setKilometraje(dato, kilometraje);
            } else if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.setKilometraje(idC, kilometraje);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Kilometraje modificado con éxito";
    }

    @ShellMethod(key = "modificar-estado", value = "Modifica el estado de una autocaravana.")
    public String modificarEstado(          @ShellOption(help = "Dato identificador") String dato,
                                            @ShellOption(help = "Nuevo estado", value = { "-m", "--modelo" }) String estado,
                                            @ShellOption(defaultValue = "matricula", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

        try {
            if (type.equals("matricula")) {
                autocaravanaServicio.setEstado(dato, estado);
            } else if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.setEstado(idC, estado);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Estado modificado con éxito";
    }

    





}
