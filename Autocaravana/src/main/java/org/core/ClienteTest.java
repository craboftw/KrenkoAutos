package org.core;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ClienteTest {
    //TEST DEL CONSTRUCTOR DE CLIENTE//////////////////////////////////////////////////////////////


    @BeforeEach
    public void setUp() {
        //Borrar todos los clientes existentes
        Cliente.getListaClientes().clear();
        Autocaravana.getListaAutocaravanas().clear();
        Reserva.getListaReservas().clear() ;
    }

    @Test
    void testCliente() {
        String campo = "1,John,Doe,555-555-5555,1990-01-01,12345678,john.doe@example.com,5,0";
        Cliente cliente = new Cliente(campo);
        assertEquals(1, cliente.getIdC());
        assertEquals("John", cliente.getNombre());
        assertEquals("Doe", cliente.getApellido());
        assertEquals("555-555-5555", cliente.getTelefono());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getFechaNacimiento());
        assertEquals("12345678", cliente.getDni());
        assertEquals("john.doe@example.com", cliente.getEmail());
        assertEquals(0, cliente.getMultas());
        cliente.eliminarCliente();
    }

    @Test
    public void testNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        cliente.eliminarCliente();
        });
    }

    @Test
    public void testApellidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
            cliente.eliminarCliente();
        });
    }

    @Test
    public void testFechaIncorrecta() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2-12-21","77172375W", "Culebrainvisible@gmail.com");
            cliente.eliminarCliente();
        });
    }


    @Test
    public void testDniVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","", "Culebrainvisible@gmail.com");
            cliente.eliminarCliente();
        });
        //cliente.eliminarCliente();
    }

    @Test
    public void testDniExistente() {
        Cliente cliente = new Cliente("Culebre", "invisibla", "91209129","2000-12-21","77172375W", "Culebrainvisible");
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente2 = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
            cliente.eliminarCliente();
        });
        cliente.eliminarCliente();

    }

    @Test
    public void testTelefonoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        cliente.eliminarCliente();
        });
    }

    @Test
    public void testTelefonoIncorrecto() {
        Cliente sujetodepruebas = new Cliente("Culebra", "invisible", "a","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "a","2000-12-21","7717375W", "Culebrainvisible@gmail.com");
            cliente.eliminarCliente();
            sujetodepruebas.eliminarCliente();
        });
    }

    @Test
    public void testTelefonoExistente() {
        Cliente cliente = new Cliente("Culebra", "invisible", "87654321","2000-12-21","72172375W", "sandra@si.com");

        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente2 = new Cliente("Sara", "Castillo", "87654321","2000-12-21","77172370W", "sandra@gmail.com");
        cliente2.eliminarCliente();
        });
    }

    @Test
    public void testEmailVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "");
        cliente.eliminarCliente();
        });
    }


    //TEST DE LOS METODOS GET//////////////////////////////////////////////////////////////////////
    @Test
    public void testGetNombre() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertEquals("Culebra", cliente.getNombre());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetApellido() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertEquals("invisible", cliente.getApellido());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetTelefono() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertEquals("77172375W", cliente.getDni());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetEdad() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertEquals(22, cliente.getEdad());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetDni() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertEquals("77172375W", cliente.getDni());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetEmail() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","771723756W", "Culebrainvisible@gmail.com");
        assertEquals("Culebrainvisible@gmail.com", cliente.getEmail());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetId(){
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","71772375W", "Culebrainvisible@gmail.com");
        assertEquals(0,cliente.getIdC());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetNumeroMultas(){
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        assertEquals(0, cliente.getMultas());
        cliente.setMultas();
        assertEquals(1, cliente.getMultas());
        cliente.eliminarCliente();
    }

    @Test
    public void testGetListaCliente(){
        List<Cliente> lista2 = new ArrayList<>();
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Sara", "Castillo", "87654321","2000-12-21","77172370W", "sandra@gmail.com");
        Cliente cliente3 = new Cliente("Borja", "Ruano", "87694321","2000-12-21","77172330W", "ruano@gmail.com");

        lista2.add(cliente);
        lista2.add(cliente2);
        lista2.add(cliente3);
        assertEquals(lista2.size(), Cliente.getListaClientes().size());

        for (Cliente C : lista2) {
            assert (Cliente.getListaClientes().contains(C));
        }
            cliente.eliminarCliente();
            cliente2.eliminarCliente();
            cliente3.eliminarCliente();
    }

    @Test
    public void testGetReservasRealizas(){
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        Autocaravana autocaravana = new Autocaravana("Coche robado", 0.1f, 8, "8675GDS",1230);
        Reserva reserva = new Reserva(autocaravana, cliente, "2023-04-21", "2023-05-21");
        assertEquals(cliente.getReservasRealizadas(),1);

    }

    @Test
    public void testgetNumeroClientes(){
        Cliente cliente1 = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro","Alvarez","666777999","2000-10-03","44065455w","maraya@sirenas.existen");

        assertEquals(2, Cliente.getNumeroClientes());
        cliente1.eliminarCliente();
        cliente2.eliminarCliente();

    }


    //RESTO DE METODOS
    @Test
    public void testBuscarCliente() {
        Cliente cliente1 = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro","Alvarez","666777999","2000-10-03","44065455w","maraya@sirenas.existen");
        // Buscar un cliente que existe en la lista
        Cliente clienteEncontrado = Cliente.buscarCliente("77172375W");
        Assertions.assertNotNull(clienteEncontrado);
        Assertions.assertEquals("Culebra", clienteEncontrado.getNombre());

        // Buscar un cliente que no existe en la lista
        Cliente clienteNoEncontrado = Cliente.buscarCliente("12345");
        Assertions.assertNull(clienteNoEncontrado);

        cliente1.eliminarCliente();
        cliente2.eliminarCliente();
    }

    @Test
    public void testBuscarCliente2() {
        Cliente cliente1 = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro","Alvarez","666777999","2000-10-03","44065455w","maraya@sirenas.existen");
        // Buscar un cliente que existe en la lista
        Cliente clienteEncontrado = Cliente.buscarCliente(0);
        Assertions.assertNotNull(clienteEncontrado);
        Assertions.assertEquals("Culebra", clienteEncontrado.getNombre());

        // Buscar un cliente que no existe en la lista
        Cliente clienteNoEncontrado = Cliente.buscarCliente(10);
        Assertions.assertNull(clienteNoEncontrado);

        cliente1.eliminarCliente();
        cliente2.eliminarCliente();

    }

    @Test
    public void testModificarCliente() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        cliente.modificarCliente("Pedro","Alvarez","666777999","2000-10-03","44065455w","maraya@sirenas.existen");
        assertEquals("Pedro", cliente.getNombre());
        cliente.eliminarCliente();
    }

    @Test
    public void testEliminarCliente(){
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678","2000-12-21","77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro","Alvarez","666777999","2000-10-03","44065455w","maraya@sirenas.existen");

        assertEquals(Cliente.getListaClientes().contains(cliente), true);
        assertEquals(Cliente.getListaClientes().contains(cliente2), true);
        cliente.eliminarCliente();
        assertEquals(Cliente.getListaClientes().contains(cliente), false);
        assertEquals(Cliente.getListaClientes().contains(cliente2), true);
        cliente2.eliminarCliente();
        assertEquals(Cliente.getListaClientes().contains(cliente2), false);

    }

}
