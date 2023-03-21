package org.core;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ClienteTests {
    //TEST DEL CONSTRUCTOR DE CLIENTE//////////////////////////////////////////////////////////////
    @Test
    public void testNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("", "Apellido", "111111111", "1990-01-01", "12345678A", "correo@ejemplo.com");
        });
    }

    @Test
    public void testApellidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nombre", "", "111111111", "1990-01-01", "12345678A", "correo@ejemplo.com");
        });
    }

    @Test
    public void testFechaIncorrecta() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nombre", "Apellido", "111111111", "1990-13-01", "12345678A", "correo@ejemplo.com");
        });
    }


    @Test
    public void testDniVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nombre", "Apellido", "111111111", "1990-01-01", "", "correo@ejemplo.com");
        });
    }

    @Test
    public void testDniExistente() {
        Cliente cliente1 = new Cliente("Nombre1", "Apellido1", "111111111", "1990-01-01", "12345678A", "correo1@ejemplo.com");
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente2 = new Cliente("Nombre2", "Apellido2", "222222222", "1990-01-01", "12345678A", "correo2@ejemplo.com");
        });
    }

    @Test
    public void testTelefonoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nombre", "Apellido", "", "1990-01-01", "12345678A", "correo@ejemplo.com");
        });
    }

    @Test
    public void testTelefonoIncorrecto() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nombre", "Apellido", "1111111111a", "1990-01-01", "12345678A", "correo@ejemplo.com");
        });
    }

    @Test
    public void testTelefonoExistente() {
        Cliente cliente1 = new Cliente("Nombre1", "Apellido1", "111111111", "1990-01-01", "12345678A", "correo1@ejemplo.com");
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente2 = new Cliente("Nombre2", "Apellido2", "111111111", "1990-01-01", "87654321B", "correo2@ejemplo.com");
        });
    }

    @Test
    public void testEmailVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nombre", "Apellido", "111111111", "1990-01-01", "12345678A", "");
        });
    }


    //TEST DE LOS METODOS GET//////////////////////////////////////////////////////////////////////
    @Test
    public void testGetNombre() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        assertEquals("Juan", cliente.getNombre());
    }

    @Test
    public void testGetApellido() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        assertEquals("Perez", cliente.getApellido());
    }

    @Test
    public void testGetTelefono() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        assertEquals("12345678", cliente.getDni());
    }

    @Test
    public void testGetEdad() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        assertEquals(22, cliente.getEdad());
    }

    @Test
    public void testGetDni() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        assertEquals("77172375W", cliente.getDni());
    }

    @Test
    public void testGetEmail() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        assertEquals("juanperez@gmail.com", cliente.getDni());
    }

    //AQUIII
    @Test
    public void testModificarCliente() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        cliente.modificarCliente("Pedro","Alvarez","666777999","2000-10-03","44065455w","maraya@sirenas.existen");
        assertEquals("Pedro", cliente.getNombre());
    }

    @Test
    public void testSetApellido() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        cliente.setApellido("Gonzalez");
        assertEquals("Gonzalez", cliente.getApellido());
    }

    @Test
    public void testSetDni() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        cliente.setDni("87654321B");
        assertEquals("87654321B", cliente.getDni());
    }

    @Test
    public void testSetEdad() {
        Cliente cliente = new Cliente("Juan", "Perez", "12345678","2000-12-21","77172375W", "juanperez@gmail.com");
        cliente.setEdad(25);
        assertEquals(25, cliente.getEdad());
    }
}
