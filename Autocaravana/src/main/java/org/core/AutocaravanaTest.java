package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AutocaravanaTest {

    @Test
    public void testConstructor() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        Assertions.assertEquals("Modelo1", a.getModelo());
        Assertions.assertEquals(100, a.getPrecioPorDia(), 0.01);
        Assertions.assertEquals("1234567", a.getMatricula());
        Assertions.assertEquals(0, a.getKilometraje());
        Assertions.assertEquals("Disponible", a.getEstado());
    }

    @Test
    public void testModificarPrecio() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        a.modificarPrecio(200);
        Assertions.assertEquals(200, a.getPrecioPorDia(), 0.01);
    }

    @Test
    public void testModificarKilometraje() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        a.modificarKilometraje(1000);
        Assertions.assertEquals(1000, a.getKilometraje());
    }

    @Test
    public void testActualizarKilometraje() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        a.actualizarkilometraje(1000);
        Assertions.assertEquals(1000, a.getKilometraje());
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.actualizarkilometraje(500));
    }

    @Test
    public void testAnadirQuitarCaravanaReservada() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        Assertions.assertFalse(a.QuedanCaravanas());
        a.AnadirCaravanaReservada();
        Assertions.assertTrue(a.QuedanCaravanas());
        a.QuitarCaravanaReservada();
        Assertions.assertFalse(a.QuedanCaravanas());
    }

    @Test
    public void testBuscarAutocaravana() {
        Autocaravana a1 = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        Autocaravana a2 = new Autocaravana("Modelo2", 200, 6, "2345678", 0);
        Assertions.assertEquals(a1, Autocaravana.buscarAutocaravana(a1.getIdA()));
        Assertions.assertEquals(a2, Autocaravana.buscarAutocaravana(a2.getIdA()));
        Assertions.assertNull(Autocaravana.buscarAutocaravana(999));
    }

    @Test
    public void testListaAutocaravanas() {
        Autocaravana a1 = new Autocaravana("Modelo1", 100, 4, "1234567", 0);
        Autocaravana a2 = new Autocaravana("Modelo2", 200, 6, "2345678", 0);
        List<Autocaravana> lista = Autocaravana.getListaAutocaravanas();
        Assertions.assertEquals(2, lista.size());
        Assertions.assertTrue(lista.contains(a1));
        Assertions.assertTrue(lista.contains(a2));
        }

    @Test
    public void crearAutocaravanaConMatriculaIncorrectaLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", 80.0f, 4, "123456", 0);
        });
    }

    @Test
    public void crearAutocaravanaConMatriculaYaExistenteLanzaExcepcion() {
        Autocaravana autocaravana1 = new Autocaravana("VW California", 80.0f, 4, "1234567", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana2 = new Autocaravana("VW California", 80.0f, 4, "1234567", 0);
        });
    }

    @Test
    public void crearAutocaravanaConPrecioNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", -10.0f, 4, "1234567", 0);
        });
    }

    @Test
    public void crearAutocaravanaConPlazasNegativasLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", 80.0f, -4, "1234567", 0);
        });
    }

    @Test
    public void crearAutocaravanaConKilometrajeNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", 80.0f, 4, "1234567", -1);
        });
    }


}