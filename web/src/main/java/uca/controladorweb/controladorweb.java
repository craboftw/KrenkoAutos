package uca.controladorweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controladorweb {
    @GetMapping("/sample")
    public String showForm() {
        return "sample";
    }

}