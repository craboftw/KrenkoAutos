package uca.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uca.core.dominio.Cliente;
import uca.core.servicio.interfaces.iClienteServicio;
import uca.dto.ClienteDTO;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private iClienteServicio clienteServicio;

	@ApiOperation(value = "Listar todos los clientes.")
	@GetMapping("/")
	public Collection<Cliente> listarClientes() {
		return clienteServicio.getListaClientes();
	}

	@ApiOperation(value = "Buscar un cliente por su id.")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("id") Long id) {
		Cliente cliente = clienteServicio.buscarCliente(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cliente);
		}
	}

	@ApiOperation(value = "Dar de alta un cliente.")
	@PostMapping("/")
	public ResponseEntity<Cliente> altaCliente(@RequestBody ClienteDTO intento) {
		clienteServicio.altaCliente(intento.getNombre(), intento.getApellido(), intento.getTelefono(), intento.getFechaNacimiento(), intento.getDni(), intento.getEmail());
		Cliente cliente = clienteServicio.buscarCliente(intento.getDni());
		return ResponseEntity.created(URI.create("/clientes/" + cliente.getIdC())).body(cliente);
	}

	@ApiOperation(value = "Obtener el número de clientes.")
	@GetMapping("/numero-clientes")
	public ResponseEntity<Integer> getNumeroClientes() {
		int numeroClientes = clienteServicio.getNumeroClientes();
		return ResponseEntity.ok(numeroClientes);
	}

	@ApiOperation(value = "Eliminar un cliente por su id.")
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


	@ApiOperation(value = "Buscar un cliente indicando un campo [nombre|apellido|telefono|fecha|dni|email|idC] y un valor de este.")
	@GetMapping("/buscar{campo}/{dato}")
	public ResponseEntity<Collection<Cliente>> buscarClientePorDni(@RequestParam(required = false) String campo,
																   @RequestParam(required = false) String dato) {
		return ResponseEntity.ok(clienteServicio.buscarCliente(campo, dato));
	}

	@ApiOperation(value = "Actualizar todos los campos de un cliente.")
	@PutMapping("/dni/{id}")
	public ResponseEntity<Cliente> modificarDni(@PathVariable("id") Long id, @RequestBody String dni) {
			clienteServicio.setDni(id, dni);
			return ResponseEntity.ok(clienteServicio.buscarCliente(id));
		}


	@ApiOperation(value = "Actualizar el nombre de un cliente.")
	@PutMapping("/nombre/{id}")
		public ResponseEntity<Cliente> modificarNombre(@PathVariable("id") Long id, @RequestBody String nombre) {
			clienteServicio.setNombre(id, nombre);
			return ResponseEntity.ok(clienteServicio.buscarCliente(id));
		}

	@ApiOperation(value = "Actualizar los apellidos de un cliente.")
	@PutMapping("/apellidos/{id}")
		public ResponseEntity<Cliente> modificarApellidos(@PathVariable("id") Long id, @RequestBody String apellidos) {
			clienteServicio.setApellido(id, apellidos);
			return ResponseEntity.ok(clienteServicio.buscarCliente(id));
		}

	@ApiOperation(value = "Actualizar el teléfono de un cliente.")
	@PutMapping("/telefono/{id}")
		public ResponseEntity<Cliente> modificarTelefono(@PathVariable("id") Long id, @RequestBody String telefono) {
			clienteServicio.setTelefono(id, telefono);
			return ResponseEntity.ok(clienteServicio.buscarCliente(id));
		}

	@ApiOperation(value = "Actualizar el email de un cliente.")
	@PutMapping("/email/{id}")
		public ResponseEntity<Cliente> modificarEmail(@PathVariable("id") Long id, @RequestBody String email) {
			clienteServicio.setEmail(id, email);
			return ResponseEntity.ok(clienteServicio.buscarCliente(id));
		}

	@ApiOperation(value = "Actualizar la fecha de nacimiento de un cliente.")
	@PutMapping("/fechaNacimiento/{id}")
		public ResponseEntity<Cliente> modificarFechaNacimiento(@PathVariable("id") Long id, @RequestBody String fechaNacimiento) {
			clienteServicio.setFechaNacimiento(id, fechaNacimiento);
			return ResponseEntity.ok(clienteServicio.buscarCliente(id));
		}


}
