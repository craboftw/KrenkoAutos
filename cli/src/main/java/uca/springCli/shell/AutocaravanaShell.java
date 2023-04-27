package uca.springCli.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import uca.core.dominio.Autocaravana;
import uca.core.servicio.interfaces.iAutocaravanaServicio;
import uca.core.servicio.implementaciones.AutocaravanaServicioImpl;
import uca.springCli.repositorio.Implementaciones.AutocaravanaRepositorioImpl;
import uca.springCli.repositorio.Implementaciones.EstadoRepositorio;

import java.math.BigDecimal;
import java.util.List;

import static uca.springCli.shell.PrintShell.imprimir;

@ShellComponent
public class AutocaravanaShell {
  // Igual que ClienteUI pero con Autocaravana

    @Autowired
    AutocaravanaShell() {
        this.autocaravanaServicio = new AutocaravanaServicioImpl(new AutocaravanaRepositorioImpl(), new EstadoRepositorio());
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
        List<Autocaravana> autocaravanas = autocaravanaServicio.getListaAutocaravanas().stream().toList();
        if (autocaravanas.size() != 0)
        PrintShell.imprimir(autocaravanas);
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
    public String modificarmodelo(@ShellOption(help = "Valor del dato") String dato,
                                  @ShellOption(help = "Nuevo modelo") String modelo,
                                  @ShellOption(defaultValue = "idA", value = "-t", help = "Tipo de dato [matricula|idA]") String type) {
        try {
            if (type.equals("matricula"))
                autocaravanaServicio.setModelo(dato, modelo);
            if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.setModelo(idC, modelo);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
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
                                            @ShellOption(help = "Nuevo precio por dia") BigDecimal precio,
                                            @ShellOption(defaultValue = "idA", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

        try {
            if (type.equals("matricula"))
                autocaravanaServicio.setPrecio(dato, precio);
            if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.setPrecio(idC, precio);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
            }
            return "Tipo de dato no válido";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "modificar-plazas", value = "Modifica el número de plazas de una autocaravana.")
    public String modificarPlazas(          @ShellOption(help = "Dato identificador") String dato,
                                            @ShellOption(help = "Nuevo número de plazas") int plazas,
                                            @ShellOption(defaultValue = "idA", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

        try {
            if (type.equals("matricula"))
                autocaravanaServicio.setPlazas(dato, plazas);
                if (type.equals("idA")) {
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
    public String modificarKilometraje(     @ShellOption(help = "Dato identificador") String dato,
                                            @ShellOption(help = "Nuevo kilometraje") int kilometraje,
                                            @ShellOption(defaultValue = "idA", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

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
                                            @ShellOption(help = "Nuevo estado") String estado,
                                            @ShellOption(defaultValue = "idA", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {

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

    //borrar autocaravana
    @ShellMethod(key = "borrar-autocaravana", value = "Borra una autocaravana")
    public String borrarAutocaravana(@ShellOption(help = "Dato identificador") String dato,
                                     @ShellOption(defaultValue = "idA", value = "-t", help = "Tipo de dato identificador [matricula|idA]") String type) {
        try {
            if (type.equals("matricula")) {
                autocaravanaServicio.eliminarAutocaravana(dato);
            } else if (type.equals("idA")) {
                try {
                    int idC = Integer.parseInt(dato);
                    autocaravanaServicio.eliminarAutocaravana(idC);
                } catch (Exception e) {
                    return "El id debe ser un número";
                }
            } else {
                return "Tipo de dato no válido";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Autocaravana borrada con éxito";
    }

    @ShellMethod(key = "crear-estado-autocaravana", value = "Crea un estado de autocaravana")
    public String crearEstadoautocaravana(@ShellOption(help = "Valor del estado") String valor) {
        try {
            return autocaravanaServicio.crearEstado(valor);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "eliminar-estado-autocaravana", value = "Eliminar un estado de autocaravana")
    public String eliminarEstadoautocaravana(@ShellOption(help = "Valor del estado") String valor) {
        try {
            return autocaravanaServicio.eliminarEstado(valor);
        } catch (Exception e) {
            return e.getMessage();
        }
    }



}
