
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Estado;
import uca.core.dominio.Reserva;
import uca.core.servicio.implementaciones.ClienteServicioImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
public class ClienteTest {

   @Autowired
    ClienteServicioImpl clienteservicio;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
        clienteservicio.eliminarCliente(0L);
    }

    @Test
    public void testAltaCliente(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c =  clienteservicio.buscarCliente(0L);
        Assertions.assertEquals("John", c.getNombre());
        Assertions.assertEquals("Doe", c.getApellido());
        Assertions.assertEquals("555-555-5555", c.getTelefono());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), c.getFechaNacimiento());
        Assertions.assertEquals("12345678", c.getDni());
        Assertions.assertEquals("john@gmail.com", c.getEmail());
    }

    @Test
    public void testGetNumeroCliente(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        Assertions.assertEquals(0L, c.getIdC());
    }

    @Test
    public void testBucarClienteId(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        clienteservicio.altaCliente("Mary", "Joe",  "999-999-999", "1990-01-11", "87654321", "mary@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        Cliente d = clienteservicio.buscarCliente(1L);

        Assertions.assertEquals(c, clienteservicio.buscarCliente(0L));
        Assertions.assertEquals(d, clienteservicio.buscarCliente(1L));
        clienteservicio.eliminarCliente(1L);
    }

    @Test
    public void testBucarClienteDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        clienteservicio.altaCliente("Mary", "Joe",  "999-999-999", "1990-01-11", "87654321", "mary@gmail.com");
        Cliente c = clienteservicio.buscarCliente("12345678");
        Cliente d = clienteservicio.buscarCliente("87654321");

        Assertions.assertEquals(c, clienteservicio.buscarCliente("12345678"));
        Assertions.assertEquals(d, clienteservicio.buscarCliente("87654321"));
        clienteservicio.eliminarCliente(1L);
    }

    @Test
    public void testBuscarClienteLista(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        assertTrue(((List<Cliente>)(clienteservicio.buscarCliente("Nombre", "John"))).contains(c));
        assertTrue(((List<Cliente>)(clienteservicio.buscarCliente("Apellido", "Doe"))).contains(c));
        assertTrue(((List<Cliente>)(clienteservicio.buscarCliente("Telefono", "555-555-5555"))).contains(c));
        assertTrue(((List<Cliente>)(clienteservicio.buscarCliente("FechaNacimiento", "1990-01-01"))).contains(c));
        assertTrue(((List<Cliente>)(clienteservicio.buscarCliente("Dni", "12345678"))).contains(c));
        assertTrue(((List<Cliente>)(clienteservicio.buscarCliente("Email", "john@gmail.com"))).contains(c));
    }

    @Test
    public void testEliminarClienteId(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        clienteservicio.eliminarCliente(1L);
        Assertions.assertEquals(null, clienteservicio.buscarCliente(1L));
    }

    @Test
    public void testEliminarClienteDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        clienteservicio.eliminarCliente("12345678");
        Assertions.assertEquals(null, clienteservicio.buscarCliente("12345678"));
    }

    @Test
    public void testComprobarCliente(){
        clienteservicio.altaCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
        clienteservicio.comprobarCliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john@gmail.com");
    }

    @Test
    public void testSetNombreIdc(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setNombre(0L, "Mary");
        Assertions.assertEquals("Mary", c.getNombre());
    }

    @Test
    public void testSetNombreDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setNombre("12345678", "Mary");
        Assertions.assertEquals("Mary", c.getNombre());
    }

    @Test
    public void testSetApellidoIdc(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setApellido(0L, "Joe");
        Assertions.assertEquals("Joe", c.getApellido());
    }

    @Test
    public void testSetApellidoDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setApellido("12345678", "Joe");
        Assertions.assertEquals("Joe", c.getApellido());
    }

    @Test
    public void testSetEmailIdc(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setEmail(0L, "mary@gmail.com");
        Assertions.assertEquals("mary@gmail.com", c.getEmail());
    }

    @Test
    public void testSetEmailDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setEmail("12345678", "mary@gmail.com");
        Assertions.assertEquals("mary@gmail.com", c.getEmail());
    }

    @Test
    public void testSetDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setDni(0L, "87654321");
        Assertions.assertEquals("87654321", c.getDni());
    }

    @Test
    public void testSetFechaNacimientoIdc(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setFechaNacimiento(0L, "1990-01-02");
        Assertions.assertEquals(LocalDate.of(1990, 1, 2), c.getFechaNacimiento());
    }

    @Test
    public void testSetFechaNacimientoDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setFechaNacimiento("12345678", "1990-01-02");
        Assertions.assertEquals(LocalDate.of(1990, 1, 2), c.getFechaNacimiento());
    }

    @Test
    public void testSetTelefonoIdc(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setTelefono(0L, "666-666-666");
        Assertions.assertEquals("666-666-666", c.getTelefono());
    }

    @Test
    public void testSetTelefonoDNI(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.coom");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.setTelefono("12345678", "666-666-666");
        Assertions.assertEquals("666-666-666", c.getTelefono());
    }

    @Test
    public void testEliminarEstado(){
        clienteservicio.crearEstado("Chulo");
        var listaEstados = clienteservicio.getListaEstadoclientes();
        assertTrue(listaEstados.contains("Chulo"));
        clienteservicio.eliminarEstado("Chulo");
        listaEstados = clienteservicio.getListaEstadoclientes();
        Assertions.assertTrue(!listaEstados.contains("Chulo"));
    }


    @Test
    public void testGetListaEstadoCliente(){
        clienteservicio.crearEstado("Chulo");
        clienteservicio.crearEstado("Feo");
        clienteservicio.crearEstado("Guapo");
        var listaEstados = clienteservicio.getListaEstadoclientes();
        Assertions.assertTrue(listaEstados.contains("Chulo"));
        Assertions.assertTrue(listaEstados.contains("Feo"));
        Assertions.assertTrue(listaEstados.contains("Guapo"));


    }

    @Test
    public void testGetListaCliente(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        clienteservicio.altaCliente("Mary", "Doe", "555-555-555", "1990-01-01", "87654321", "money@money.money");
        clienteservicio.altaCliente("Joe", "Doe", "555-555-555", "1990-01-01", "11111111", "joe@maria.jose");
        var listaClientes = clienteservicio.getListaClientes();
        for (Cliente cliente : listaClientes) {
            Assertions.assertTrue(cliente.getNombre().equals("John") || cliente.getNombre().equals("Mary") || cliente.getNombre().equals("Joe"));
        }
    }

    @Test
    public void testGuardarCliente(){
        clienteservicio.altaCliente("John", "Doe", "555-555-555", "1990-01-01", "12345678", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.guardarCliente(c);
        Assertions.assertTrue(clienteservicio.getListaClientes().contains(c));
        Assertions.assertTrue(clienteservicio.buscarCliente("12345678").equals(c));
    }

}
