package uca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uca.core.dominio.Cliente;
import uca.core.servicio.interfaces.iClienteServicio;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private iClienteServicio clienteServicio;

	@GetMapping("/")
	public Collection<Cliente> listarClientes() {
		return clienteServicio.getListaClientes();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("id") Long id) {
		Cliente cliente = clienteServicio.buscarCliente(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cliente);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Cliente> altaCliente(@RequestBody Cliente cliente) {
		clienteServicio.guardarCliente(cliente);
		return ResponseEntity.created(URI.create("/clientes/" + cliente.getIdC())).body(cliente);
	}

	@GetMapping("/numero-clientes")
	public ResponseEntity<Integer> getNumeroClientes() {
		int numeroClientes = clienteServicio.getNumeroClientes();
		return ResponseEntity.ok(numeroClientes);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarClientePorId(@PathVariable("id") Long id) {
		Cliente cliente = clienteServicio.buscarCliente(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			clienteServicio.eliminarCliente(id);
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> modificarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		Cliente clienteActual = clienteServicio.buscarCliente(id);
		if (clienteActual == null) {
			return ResponseEntity.notFound().build();
		} else {
			cliente.setIdC(id);
			clienteServicio.guardarCliente(cliente);
			return ResponseEntity.ok(cliente);
		}
	}

	@GetMapping
	public ResponseEntity<Collection<Cliente>> buscarClientePorDni(@RequestParam(required = false) String dni,
																   @RequestParam(required = false) String campo,
																   @RequestParam(required = false) String dato) {
		Collection<Cliente> clientes;
		if (dni != null) {
			clientes = (Collection<Cliente>) clienteServicio.buscarCliente(dni);
		} else if (campo != null && dato != null) {
			clientes = clienteServicio.buscarCliente(campo, dato);
		} else {
			clientes = clienteServicio.getListaClientes();
		}
		return ResponseEntity.ok(clientes);
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarClientePorId(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteActual = clienteServicio.buscarCliente(id);
		if (clienteActual != null) {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setDni(cliente.getDni());
			clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
			clienteActual.setMultas(cliente.getMultas());
			clienteActual.setCantidadReservasRealizadas(cliente.getCantidadReservasRealizadas());
			clienteActual.setEstado(cliente.getEstado());
			clienteServicio.guardarCliente(clienteActual);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
