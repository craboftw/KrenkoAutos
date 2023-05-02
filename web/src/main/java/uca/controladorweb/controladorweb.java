package uca.controladorweb;

import lombok.extern.slf4j.Slf4j;
import uca.core.servicio.interfaces.iClienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class controladorweb {

    public controladorweb(iClienteServicio personaService) {
        this.personaService = personaService;
    }

    @Autowired
    private iClienteServicio personaService;

    @GetMapping("/")
    public String inicio(Model model){
        var personas = personaService.getListaClientes();
        log.info("ejecutando el controlador Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }
}
