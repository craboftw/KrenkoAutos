package uca.controladorweb;

import lombok.extern.slf4j.Slf4j;
import uca.core.dominio.Cliente;
import uca.core.dao.iClienteRepositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class controladorweb {

    @Autowired
    public controladorweb(iClienteRepositorio personaService) {
        this.clienteServicio = personaService;
    }
    
    private iClienteRepositorio clienteServicio;



    @GetMapping("/")
    public String inicio(Model model){
        var clientes = (List<Cliente>) clienteServicio.findAll();
        log.info("ejecutando el controlador Spring MVC");
        model.addAttribute("clientes", clientes);
        return "index";
    }
}
