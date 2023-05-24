
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;
import uca.core.servicio.implementaciones.AutocaravanaServicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class AutocaravanaTest {


    AutocaravanaServicioImpl autocaravanaServicio;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
        autocaravanaServicio.eliminarAutocaravana(0L);
    }


    @Test
    public void testAltaAutocaravana() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        assertEquals("Modelo1", a.getModelo());
        assertEquals(100, a.getPrecioPorDia());
        assertEquals(8, a.getPlazas());
        assertEquals("4029PKT", a.getMatricula());
        assertEquals(420, a.getKilometraje());
    }

    @Test
    public void testComprobarAutocaravana() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        autocaravanaServicio.comprobarAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
    }

    @Test
    public void testBuscarAutocaravana() {

        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        autocaravanaServicio.altaAutocaravana("Modelo2", BigDecimal.valueOf(200), 16, "8058PKT", 840);

        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        Autocaravana b = autocaravanaServicio.buscarAutocaravana(1L);

        assertEquals(a, autocaravanaServicio.buscarAutocaravana(a.getIdA()));
        assertEquals(b, autocaravanaServicio.buscarAutocaravana(b.getIdA()));
        Assertions.assertNull(autocaravanaServicio.buscarAutocaravana(999L));
        autocaravanaServicio.eliminarAutocaravana(1L);
    }

    @Test
    public void testBuscarAutocaravana2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);

        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        assertTrue(((List<Autocaravana>) (autocaravanaServicio.buscarAutocaravana("Modelo", "Modelo1"))).contains(a));
        assertTrue(((List<Autocaravana>) (autocaravanaServicio.buscarAutocaravana("Precio", "100"))).contains(a));
        assertTrue(((List<Autocaravana>) (autocaravanaServicio.buscarAutocaravana("Plazas", "8"))).contains(a));
        assertTrue(((List<Autocaravana>) (autocaravanaServicio.buscarAutocaravana("Matriicula", "4029PKT"))).contains(a));
        assertTrue(((List<Autocaravana>) (autocaravanaServicio.buscarAutocaravana("Kilometraje", "420"))).contains(a));

    }

    @Test
    public void getListaAutocaravanas() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        autocaravanaServicio.altaAutocaravana("Modelo2", BigDecimal.valueOf(200), 16, "8058PKT", 840);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        Autocaravana b = autocaravanaServicio.buscarAutocaravana(1L);

        List<Autocaravana> lista = (List<Autocaravana>) (autocaravanaServicio.getListaAutocaravanas());
        assertEquals(2, lista.size());
        Assertions.assertTrue(lista.contains(a));
        Assertions.assertTrue(lista.contains(b));

    }

    @Test
    public void testGetListaEstadosAutocaravanas() {

        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        autocaravanaServicio.crearEstado("Chulo");

        List<String> lista = (List < String >) autocaravanaServicio.getListaEstadoAutocaravana();

        assertTrue(lista.contains("Chulo"));

    }

    @Test
    public void testSetModelo() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setModelo(0L, "Modelo2");
        assertEquals("Modelo2", a.getModelo());
    }

    @Test
    public void testSetModelo2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setModelo("4029PKT", "Modelo2");
        assertEquals("Modelo2", a.getModelo());
    }

    @Test
    public void testSetPrecioPorDia() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setPrecioPorDia(0L, BigDecimal.valueOf(200));
        assertEquals(200, a.getPrecioPorDia());
    }

    @Test
    public void testSetPrecioPorDia2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setPrecioPorDia("4029PKT", BigDecimal.valueOf(200));
        assertEquals(200, a.getPrecioPorDia());
    }

    @Test
    public void testSetPlazas() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setPlazas(0L, 10);
        assertEquals(10, a.getPlazas());
    }

    @Test
    public void testSetPlazas2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setPlazas("4029PKT", 10);
        assertEquals(10, a.getPlazas());
    }

    @Test
    public void testSetEstado() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        autocaravanaServicio.crearEstado("Chulo");
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setEstado(0L, "Chulo");
        assertEquals("Chulo", a.getEstadoA());
    }

    @Test
    public void testSetEstado2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        autocaravanaServicio.crearEstado("Chulo");
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setEstado("4029PKT", "Chulo");
        assertEquals("Chulo", a.getEstadoA());
    }

    @Test
    public void testSetKilometraje() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setKilometraje(0L, 1000);
        assertEquals(1000, a.getKilometraje());
    }

    @Test
    public void testSetKilometraje2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setKilometraje("4029PKT", 1000);
        assertEquals(1000, a.getKilometraje());
    }

    @Test
    public void testEliminarAutocaravana() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        var lista = autocaravanaServicio.getListaAutocaravanas();
        autocaravanaServicio.eliminarAutocaravana(0L);
        assertTrue(!lista.contains(a));

    }

    @Test
    public void testEliminarAutocaravana2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        var lista = autocaravanaServicio.getListaAutocaravanas();
        autocaravanaServicio.eliminarAutocaravana("4029PKT");
        assertTrue(!lista.contains(a));
    }

    @Test
    public void testEliminarEstadoAutocaravana() {
        autocaravanaServicio.crearEstado("Chulo");
        var lista = autocaravanaServicio.getListaEstadoAutocaravana();
        assertTrue(lista.contains("Chulo"));
        autocaravanaServicio.eliminarEstadoAutocaravana("Chulo");
        lista = autocaravanaServicio.getListaEstadoAutocaravana();
        assertTrue(!lista.contains("Chulo"));

    }


    @Test
    public void testSetPrecio() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setPrecio(0L, BigDecimal.valueOf(200));
        assertEquals(200, a.getPrecioPorDia());
    }

    @Test
    public void testSetPrecio2() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);

        autocaravanaServicio.setPrecio("4029PKT", BigDecimal.valueOf(200));
        assertEquals(200, a.getPrecioPorDia());
    }

    @Test
    public void testCrearEstado() {
        autocaravanaServicio.crearEstado("Chulo");
        assertEquals("Chulo", autocaravanaServicio.getListaEstadoAutocaravana().contains("Chulo"));
    }

    @Test
    public void testEliminarEstado() {
        autocaravanaServicio.crearEstado("Chulo");
        autocaravanaServicio.eliminarEstado("Chulo");
        assertTrue(!autocaravanaServicio.getListaEstadoAutocaravana().contains("Chulo"));
    }

    @Test
    public void testGuardarAutocaravana() {
        autocaravanaServicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 8, "4029PKT", 420);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(0L);
        autocaravanaServicio.guardarAutocaravana(a);
        assertEquals("4029PKT", autocaravanaServicio.buscarAutocaravana(0L).getMatricula());
    }
}


