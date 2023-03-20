import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AutocaravanaTest {

    private Autocaravana autocaravana;

    @BeforeEach
    public void setUp() {
        autocaravana = new Autocaravana("Modelo 1", 100, 4, "1234ABC", 1000);
    }

    @Test
    public void testCrearAutocaravana() {
        Assertions.assertNotNull(autocaravana);
    }

    @Test
    public void testComprobarMatricula() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Autocaravana("Modelo 1", 100, 4, "123", 1000));
    }

    @Test
    public void testBuscarAutocaravana() {
        Autocaravana encontrada = Autocaravana.buscarAutocaravana(autocaravana.getIdA());
        Assertions.assertEquals(encontrada, autocaravana);
    }

    @Test
    public void testGetListaAutocaravanas() {
        List<Autocaravana> lista = Autocaravana.getListaAutocaravanas();
        Assertions.assertTrue(lista.contains(autocaravana));
    }

    @Test
    public void testModificarAutocaravana() {
        autocaravana.modificarAutocaravana("Modelo 2", 200);
        Assertions.assertEquals("Modelo 2", autocaravana.getModelo());
        Assertions.assertEquals(200, autocaravana.getPrecioPorDia());
    }

    @Test
    public void testActualizarKilometraje() {
        autocaravana.actualizarkilometraje(1500);
        Assertions.assertEquals(1500, autocaravana.getKilometraje());
        Assertions.assertThrows(IllegalArgumentException.class, () -> autocaravana.actualizarkilometraje(500));
    }

    @Test
    public void testModificarPrecio() {
        autocaravana.modificarPrecio(150);
        Assertions.assertEquals(150, autocaravana.getPrecioPorDia());
    }

    @Test
    public void testCambiarEstado() {
        autocaravana.cambiarEstado("Alquilada");
        Assertions.assertEquals("Disponible", autocaravana.getEstado());
        autocaravana.cambiarEstado("Reservada");
        Assertions.assertEquals("Reservada", autocaravana.getEstado());
    }
}
