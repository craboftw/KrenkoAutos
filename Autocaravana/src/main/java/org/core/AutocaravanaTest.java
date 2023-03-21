package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AutocaravanaTest {

    @BeforeEach
    public void setUp() {
        //Borrar todos los clientes existentes
        Cliente.getListaClientes().clear();
        Autocaravana.getListaAutocaravanas().clear();
        Reserva.getListaReservas().clear();
    }


    @Test
    public void testConstructor() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4029PKT", 0);
        Assertions.assertEquals("Modelo1", a.getModelo());
        Assertions.assertEquals(100, a.getPrecioPorDia(), 0.01);
        Assertions.assertEquals("4029PKT", a.getMatricula());
        Assertions.assertEquals(0, a.getKilometraje());
        Assertions.assertEquals("Disponible", a.getEstado());
    }

    @Test
    public void testModificarPrecio() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4021PKT", 0);
        a.modificarPrecio(200);
        Assertions.assertEquals(200, a.getPrecioPorDia(), 0.01);
    }

    @Test
    public void testModificarKilometraje() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        a.actualizarkilometraje(1000);
        Assertions.assertEquals(1000, a.getKilometraje());
    }

    @Test
    public void testActualizarKilometraje() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        a.actualizarkilometraje(1000);
        Assertions.assertEquals(1000, a.getKilometraje());
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.actualizarkilometraje(500));
    }

    @Test
    public void testAnadirQuitarCaravanaReservada() {
        Autocaravana a1 = new Autocaravana("Modelo1", 100, 4, "4024PKT", 0);
        Assertions.assertEquals(1, Autocaravana.getCantidadCaravanas());
        Autocaravana a2 = new Autocaravana("Modelo2", 200, 6, "8008SSS", 0);
        Assertions.assertEquals(2, Autocaravana.getCantidadCaravanas());
        a1.eliminarAutocaravana();
        a2.eliminarAutocaravana();
        Assertions.assertEquals(0, Autocaravana.getCantidadCaravanas());
    }

    @Test
    public void testBuscarAutocaravana() {
        Autocaravana a1 = new Autocaravana("Modelo1", 100, 4, "4025PKT", 0);
        Autocaravana a2 = new Autocaravana("Modelo2", 200, 6, "8008SSS", 0);
        Assertions.assertEquals(a1, Autocaravana.buscarAutocaravana(a1.getIdA()));
        Assertions.assertEquals(a2, Autocaravana.buscarAutocaravana(a2.getIdA()));
        Assertions.assertNull(Autocaravana.buscarAutocaravana(999));


    }

    @Test
    public void testListaAutocaravanas() {
        Autocaravana a1 = new Autocaravana("Modelo1", 100, 4, "4026PKT", 0);
        Autocaravana a2 = new Autocaravana("Modelo2", 200, 6, "8008SSS", 0);
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
        Autocaravana autocaravana1 = new Autocaravana("VW California", 80.0f, 4, "4020PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Autocaravana autocaravana2 = new Autocaravana("VW California", 80.0f, 4, "4020PKT", 0);
                }
        );
    }

    @Test
    public void crearAutocaravanaConPrecioNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", -10.0f, 4, "4020PKT", 0);
        });
    }

    @Test
    public void crearAutocaravanaConPlazasNegativasLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", 80.0f, -4, "4020PKT", 0);
        });
    }

    @Test
    public void crearAutocaravanaConKilometrajeNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana autocaravana = new Autocaravana("VW California", 80.0f, 4, "4020PKT", -1);
        });
    }

    @Test
    public void testModificarPrecioNegativo() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4021PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.modificarPrecio(-200));
    }

    @Test
    public void testModificarPrecioCero() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4021PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.modificarPrecio(0));
    }

    @Test
    public void testModificarKilometrajeNegativo() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.actualizarkilometraje(-1000));
    }

    @Test
    public void testModificarKilometrajeCero() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.actualizarkilometraje(0));
    }


}
