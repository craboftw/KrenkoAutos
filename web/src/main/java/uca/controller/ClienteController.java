package uca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uca.core.dominio.Cliente;
import uca.core.servicio.interfaces.iClienteServicio;

@Controller
@RequestMapping("/clientes/")
public class ClienteController {

	@Autowired
	private iClienteServicio clienteServicio;

	@GetMapping("list")
	public String students(Model model) {
		model.addAttribute("clientes", this.clienteServicio.getListaClientes());
		clienteServicio.getListaClientes().forEach(System.out::println);
		return "index";
	}
}
