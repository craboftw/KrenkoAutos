package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AutocaravanaTest {

    @BeforeEach
    public void setUp() {
        //Borrar todos los clientes existentes
        Cliente.getListaClientes().clear();
        ServicioAutocaravana.getListaAutocaravanas().clear();
        Reserva.getListaReservas().clear();
    }


    @Test
    public void testConstructor() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals(0, a1.getIdA());
        assertEquals("Modelo1", a1.getModelo());
        assertEquals(100, a1.getPrecioPorDia(), 0.01);
        assertEquals("4029PKT", a1.getMatricula());
        assertEquals(0, a1.getKilometraje());
        assertEquals("Disponible", a1.getEstado());
    }

    /*@Test
    void testConstructorCampo() {
        String campo = "1,Citroen,50,555-555-5555,8,12345678,Nueva,0";
        Autocaravana autocaravana = new Autocaravana(campo);
        assertEquals(1, autocaravana.getIdA());
        assertEquals("Citroen", autocaravana.getModelo());
        assertEquals(50f, autocaravana.getPrecioPorDia());
        assertEquals("555-555-5555", autocaravana.getMatricula());
        assertEquals(8, autocaravana.getPlazas());
        assertEquals(12345678, autocaravana.getKilometraje());
        assertEquals("Nueva", autocaravana.getEstado());
        assertEquals(0, autocaravana.getVecesReservada());
    }
*/
    @Test
    public void testModificarPrecio() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        a1.setPrecioPorDia(200);
        assertEquals(200, a1.getPrecioPorDia(), 0.01);
    }

    @Test
    public void testModificarKilometraje() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        a1.setKilometraje(1000);
        assertEquals(1000, a1.getKilometraje());
    }

    @Test
    public void testModificarKilometrajeMenos() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        a1.setKilometraje(1000);
        assertEquals(1000, a1.getKilometraje());
        Assertions.assertThrows(IllegalArgumentException.class, () -> a1.setKilometraje(500));
    }

    @Test
    public void testAnadirQuitarCaravanaReservada() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals(1, ServicioAutocaravana.getCantidadCaravanas());
        Autocaravana a2 = new Autocaravana(1,"Modelo2", 200, 6, "8008SSS", 0, "Alquilada");
        assertEquals(2, ServicioAutocaravana.getCantidadCaravanas());
        a1.eliminarAutocaravana();
        a2.eliminarAutocaravana();
        assertEquals(0, ServicioAutocaravana.getCantidadCaravanas());
    }

    @Test
    public void testBuscarAutocaravana() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Autocaravana a2 = new Autocaravana(1,"Modelo2", 200, 6, "8008SSS", 0, "Alquilada");
        assertEquals(a1, ServicioAutocaravana.buscarAutocaravana(a1.getIdA()));
        assertEquals(a2, ServicioAutocaravana.buscarAutocaravana(a2.getIdA()));
        Assertions.assertNull(ServicioAutocaravana.buscarAutocaravana(999));


    }

    @Test
    public void crearAutocaravanaConMatriculaIncorrectaLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");

        });
    }

    @Test
    public void crearAutocaravanaConMatriculaYaExistenteLanzaExcepcion() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana a2 = new Autocaravana(1,"Modelo2", 200, 6, "8008SSS", 0, "Alquilada");
                }
        );
    }

    @Test
    public void crearAutocaravanaConPrecioNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        });
    }

    @Test
    public void crearAutocaravanaConPlazasNegativasLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        });
    }

    @Test
    public void crearAutocaravanaConKilometrajeNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        });
    }

    @Test
    public void testModificarPrecioNegativo() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Assertions.assertThrows(IllegalArgumentException.class, () -> a1.setPrecioPorDia(-200));
    }

    @Test
    public void testModificarPrecioCero() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Assertions.assertThrows(IllegalArgumentException.class, () -> a1.setPrecioPorDia(0));
    }

    @Test
    public void testModificarKilometrajeNegativo() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Assertions.assertThrows(IllegalArgumentException.class, () -> a1.setKilometraje(-1000));
    }

    @Test
    public void testModificarKilometrajeCero() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Assertions.assertThrows(IllegalArgumentException.class, () -> a1.setKilometraje(0));
    }

    //TESTS DE GET

    @Test
    public void testGetIdA(){
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals(0, a1.getIdA());
    }

    @Test
    public void testGetModelo(){
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals("Modelo1", a1.getModelo());
        a1.setModelo("Modelo2");
        assertEquals("Modelo2", a1.getModelo());
    }
    
    @Test
    public void testGetPrecioPorDia(){
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals(100, a1.getPrecioPorDia());
        a1.setPrecioPorDia(20);
        assertEquals(20, a1.getPrecioPorDia());

    }

    @Test
    public void testGetMatricula(){
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals ("4022PKT", a1.getMatricula());
    }
    @Test
    public void testGetKilometraje(){
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals(0, a1.getKilometraje());
        a1.setKilometraje(1);
        assertEquals(1, a1.getKilometraje());

    }
    
    @Test
    public void testGetEstado() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        a1.setEstado("Sucio");
        assertEquals("Sucio", a1.getEstado());
        a1.setEstado("EstadoInventado");
        assertEquals("Sucio", a1.getEstado());

    }

    @Test
    public void testGetPlazas(){
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        assertEquals(4, a1.getPlazas());
    }

    @Test
    public void testGetListaAutocaravanas() {
        Autocaravana a1 = new Autocaravana(0,"Modelo1", 100, 4, "4025PKT", 0,"Alquilada");
        Autocaravana a2 = new Autocaravana(1,"Modelo2", 200, 6, "8008SSS", 0, "Alquilada");
        List<Autocaravana> lista = ServicioAutocaravana.getListaAutocaravanas();
        assertEquals(2, lista.size());
        Assertions.assertTrue(lista.contains(a1));
        Assertions.assertTrue(lista.contains(a2));

    }


}
