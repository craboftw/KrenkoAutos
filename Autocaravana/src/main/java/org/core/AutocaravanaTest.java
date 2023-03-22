package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    void testConstructorCampo() {
        String campo = "1,Citroen,50,555-555-5555,8,12345678,Nueva,0";
        Autocaravana autocaravana = new Autocaravana(campo);
        Assertions.assertEquals(1, autocaravana.getIdA());
        Assertions.assertEquals("Citroen", autocaravana.getModelo());
        Assertions.assertEquals(50f, autocaravana.getPrecioPorDia());
        Assertions.assertEquals("555-555-5555", autocaravana.getMatricula());
        Assertions.assertEquals(8, autocaravana.getPlazas());
        Assertions.assertEquals(12345678, autocaravana.getKilometraje());
        Assertions.assertEquals("Nueva", autocaravana.getEstado());
        Assertions.assertEquals(0, autocaravana.getVecesReservada());
    }

    @Test
    public void testModificarPrecio() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4021PKT", 0);
        a.setPrecioPorDia(200);
        Assertions.assertEquals(200, a.getPrecioPorDia(), 0.01);
    }

    @Test
    public void testModificarKilometraje() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        a.setKilometraje(1000);
        Assertions.assertEquals(1000, a.getKilometraje());
    }

    @Test
    public void testModificarKilometrajeMenos() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        a.setKilometraje(1000);
        Assertions.assertEquals(1000, a.getKilometraje());
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.setKilometraje(500));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.setPrecioPorDia(-200));
    }

    @Test
    public void testModificarPrecioCero() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4021PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.setPrecioPorDia(0));
    }

    @Test
    public void testModificarKilometrajeNegativo() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.setKilometraje(-1000));
    }

    @Test
    public void testModificarKilometrajeCero() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> a.setKilometraje(0));
    }

    //TESTS DE GET

    @Test
    public void testGetIdA(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertEquals(0, a.getIdA());
    }

    @Test
    public void testGetModelo(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertEquals("Modelo1", a.getModelo());
        a.setModelo("Modelo2");
        Assertions.assertEquals("Modelo2", a.getModelo());
    }
    
    @Test
    public void testGetPrecioPorDia(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        Assertions.assertEquals(100, a.getPrecioPorDia());
        a.setPrecioPorDia(20);
        Assertions.assertEquals(20, a.getPrecioPorDia());

    }

    @Test
    public void testGetMatricula(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        assertEquals ("4022PKT", a.getMatricula());
    }
    @Test
    public void testGetKilometraje(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        assertEquals(0, a.getKilometraje());
        a.setKilometraje(1);
        assertEquals(1, a.getKilometraje());

    }
    
    @Test
    public void testGetEstado() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        a.setEstado("Sucio");
        assertEquals("Sucio", a.getEstado());
        a.setEstado("EstadoInventado");
        assertEquals("Sucio", a.getEstado());

    }

    @Test
    public void testGetPlazas(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4022PKT", 0);
        assertEquals(4, a.getPlazas());
    }

    @Test
    public void testGetListaAutocaravanas() {
        Autocaravana a1 = new Autocaravana("Modelo1", 100, 4, "4026PKT", 0);
        Autocaravana a2 = new Autocaravana("Modelo2", 200, 6, "8008SSS", 0);
        List<Autocaravana> lista = Autocaravana.getListaAutocaravanas();
        Assertions.assertEquals(2, lista.size());
        Assertions.assertTrue(lista.contains(a1));
        Assertions.assertTrue(lista.contains(a2));

    }


}
