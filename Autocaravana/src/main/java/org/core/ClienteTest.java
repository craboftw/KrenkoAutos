package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ClienteTest {
    //TEST DEL CONSTRUCTOR DE CLIENTE//////////////////////////////////////////////////////////////


    @BeforeEach
    public void setUp() {
        //Borrar todos los clientes existentes
        Cliente.getListaClientes().clear();
        Autocaravana.getListaAutocaravanas().clear();
        Reserva.getListaReservas().clear();
    }

    @Test
    void testClienteCadena() {
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
    }

    @Test
    void testConstructorCliente() {
        Cliente cliente = new Cliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john.doe@example.com");
        assertEquals(0, cliente.getIdC());
        assertEquals("John", cliente.getNombre());
        assertEquals("Doe", cliente.getApellido());
        assertEquals("555-555-5555", cliente.getTelefono());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getFechaNacimiento());
        assertEquals("12345678", cliente.getDni());
        assertEquals("john.doe@example.com", cliente.getEmail());
        assertEquals(0, cliente.getMultas());
    }



    @Test
    public void testNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        });
    }

    @Test
    public void testApellidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        });
    }

    @Test
    public void testFechaIncorrecta() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2-12-21", "77172375W", "Culebrainvisible@gmail.com");
        });
    }


    @Test
    public void testDniVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "", "Culebrainvisible@gmail.com");
        });
    }

    @Test
    public void testDniExistente() {
        Cliente cliente = new Cliente("Culebre", "invisibla", "91209129", "2000-12-21", "77172375W", "Culebra@invisi.ble");
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente2 = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        });
    }

    @Test
    public void testTelefonoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        });
    }

    @Test
    public void testTelefonoIncorrecto() {
        Cliente sujetodepruebas = new Cliente("Culebra", "invisible", "a", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "a", "2000-12-21", "7717375W", "Culebrainvisible@gmail.com");

        });
    }

    @Test
    public void testTelefonoExistente() {
        Cliente cliente = new Cliente("Culebra", "invisible", "87654321", "2000-12-21", "72172375W", "sandra@si.com");

        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente2 = new Cliente("Sara", "Castillo", "87654321", "2000-12-21", "77172370W", "sandra@gmail.com");
        });
    }

    @Test
    public void testEmailVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "");
        });
    }

    @Test
    public void testEmailSincaracteres(){
        assertThrows(IllegalArgumentException.class, () -> {
                    Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "culebra#invisi.ble");});
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "culebra@invisi,ble");});
        }


            //TEST DE LOS METODOS GET//////////////////////////////////////////////////////////////////////
    @Test
    public void testGetNombre() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertEquals("Culebra", cliente.getNombre());
    }

    @Test
    public void testGetApellido() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertEquals("invisible", cliente.getApellido());
    }

    @Test
    public void testGetTelefono() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertEquals("77172375W", cliente.getDni());
    }

    @Test
    public void testGetEdad() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertEquals(22, cliente.getEdad());
    }

    @Test
    public void testGetDni() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertEquals("77172375W", cliente.getDni());
    }

    @Test
    public void testGetEmail() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "771723756W", "Culebrainvisible@gmail.com");
        assertEquals("Culebrainvisible@gmail.com", cliente.getEmail());
    }

    @Test
    public void testGetId() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "71772375W", "Culebrainvisible@gmail.com");
        assertEquals(0, cliente.getIdC());
    }

    @Test
    public void testGetNumeroMultas() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        assertEquals(0, cliente.getMultas());
        cliente.setNuevaMulta();
        assertEquals(1, cliente.getMultas());
    }

    @Test
    public void testGetListaCliente() {
        List<Cliente> lista2 = new ArrayList<>();
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Sara", "Castillo", "87654321", "2000-12-21", "77172370W", "sandra@gmail.com");
        Cliente cliente3 = new Cliente("Borja", "Ruano", "87694321", "2000-12-21", "77172330W", "ruano@gmail.com");

        lista2.add(cliente);
        lista2.add(cliente2);
        lista2.add(cliente3);
        assertEquals(lista2.size(), Cliente.getListaClientes().size());

        for (Cliente C : lista2) {
            assert (Cliente.getListaClientes().contains(C));
        }
    }

    @Test
    public void testGetReservasRealizas() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Autocaravana autocaravana = new Autocaravana("Coche robado", 0.1f, 8, "8675GDS", 1230);
        Reserva reserva = new Reserva(autocaravana, cliente, "2023-04-21", "2023-05-21");
        assertEquals(cliente.getCantidadReservasRealizadas(), 1);

    }

    @Test
    public void testgetNumeroClientes() {
        Cliente cliente1 = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro", "Alvarez", "666777999", "2000-10-03", "44065455w", "maraya@sirenas.existen");

        assertEquals(2, Cliente.getNumeroClientes());
    }


    //RESTO DE METODOS
    @Test
    public void testBuscarCliente() {
        Cliente cliente1 = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro", "Alvarez", "666777999", "2000-10-03", "44065455w", "maraya@sirenas.existen");
        // Buscar un cliente que existe en la lista
        Cliente clienteEncontrado = Cliente.buscarCliente("77172375W");
        Assertions.assertNotNull(clienteEncontrado);
        Assertions.assertEquals("Culebra", clienteEncontrado.getNombre());

        // Buscar un cliente que no existe en la lista
        Cliente clienteNoEncontrado = Cliente.buscarCliente("12345");
        Assertions.assertNull(clienteNoEncontrado);
    }

    @Test
    public void testBuscarCliente2() {
        Cliente cliente1 = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro", "Alvarez", "666777999", "2000-10-03", "44065455w", "maraya@sirenas.existen");
        // Buscar un cliente que existe en la lista
        Cliente clienteEncontrado = Cliente.buscarCliente(0);
        Assertions.assertNotNull(clienteEncontrado);
        Assertions.assertEquals("Culebra", clienteEncontrado.getNombre());

        // Buscar un cliente que no existe en la lista
        Cliente clienteNoEncontrado = Cliente.buscarCliente(10);
        Assertions.assertNull(clienteNoEncontrado);
    }

    @Test
    //ANTES ERA MODIFICAR CLIENTE AHORA ES SOLO TEST MODIFICAR NOMBRE
    public void testModificarCliente() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        cliente.setNombre("Pedro");
        assertEquals("Pedro", cliente.getNombre());
    }

    @Test
    public void testEliminarCliente() {
        Cliente cliente = new Cliente("Culebra", "invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Cliente cliente2 = new Cliente("Pedro", "Alvarez", "666777999", "2000-10-03", "44065455w", "maraya@sirenas.existen");

        assertTrue(Cliente.getListaClientes().contains(cliente));
        assertTrue(Cliente.getListaClientes().contains(cliente2));
        cliente.eliminarCliente();
        assertFalse(Cliente.getListaClientes().contains(cliente));
        assertTrue(Cliente.getListaClientes().contains(cliente2));
        cliente2.eliminarCliente();
        assertFalse(Cliente.getListaClientes().contains(cliente2));
    }

}
