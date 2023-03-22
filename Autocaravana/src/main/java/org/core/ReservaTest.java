package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservaTest {
    @BeforeEach
    public void setUp() {
        //Borrar todos los clientes existentes
        Cliente.getListaClientes().clear();
        Autocaravana.getListaAutocaravanas().clear();
        Reserva.getListaReservas().clear();
    }

    //TESTS CONSTRUCTORES
    @Test
    public void testConstructorReserva() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);

        // Verificar que los atributos de la reserva son correctos
        Assertions.assertEquals(a, r.getAutocaravana());
        Assertions.assertEquals(c, r.getCliente());
        Assertions.assertEquals(LocalDate.parse(fechaInicio), r.getFechaIni());
        Assertions.assertEquals(LocalDate.parse(fechaFin), r.getFechaFin());
        Assertions.assertEquals(0, r.getIdR());
        Assertions.assertEquals("Pendiente", r.getEstadoReserva());

        // Verificar que el precio total es el esperado
        double precioEsperado = a.getPrecioPorDia() * 6; // Precio base de la autocaravana por 6 días
        Assertions.assertEquals(precioEsperado, r.getPrecioTotal(), 0.001);

        // Verificar que la reserva se ha añadido a la lista de reservas
        Assertions.assertTrue(Reserva.getListaReservas().contains(r));

        // Verificar que se ha actualizado la cantidad de reservas realizadas para el cliente y la autocaravana
        Assertions.assertEquals(1, c.getCantidadReservasRealizadas());
        Assertions.assertEquals(1, a.getVecesReservada());
    }


    @Test
    public void testReservaCadena() {
        Cliente cli = new Cliente("John", "Doe", "12345678A", "1990-01-01", "555-555-5555", "a@a.com");
        Autocaravana car = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        String campo = "0,0,0,2024-01-10,2024-01-20,Pendiente";
        Reserva reserva = new Reserva(campo);

        Assertions.assertEquals(0, reserva.getIdR());
        Assertions.assertEquals(0, reserva.getAutocaravana().getIdA());
        Assertions.assertEquals(0, reserva.getCliente().getIdC());
        Assertions.assertEquals("Modelo1", reserva.getAutocaravana().getModelo());
        Assertions.assertEquals("Doe", reserva.getCliente().getApellido());
        Assertions.assertEquals("2024-01-10", reserva.getFechaIni().toString());
        Assertions.assertEquals("2024-01-20", reserva.getFechaFin().toString());
        Assertions.assertEquals("Pendiente", reserva.getEstadoReserva());

    }

    //TESTS GET
    @Test
    public void testGetIdr() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);

        Assertions.assertEquals(0, r.getIdR());
    }

    @Test
    public void testGetListaReservas() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio1 = "2023-04-01";
        String fechaFin1 = "2023-04-07";
        String fechaInicio2 = "2023-05-01";
        String fechaFin2 = "2023-05-07";

        Reserva r1 = new Reserva(a, c, fechaInicio1, fechaFin1);
        Reserva r2 = new Reserva(a, c, fechaInicio2, fechaFin2);

        List<Reserva> lista = Reserva.getListaReservas();
        Assertions.assertEquals(2, lista.size());
        Assertions.assertTrue(lista.contains(r1));
        Assertions.assertTrue(lista.contains(r2));
    }

    @Test
    public void testGetAutocaravana() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);
        Assertions.assertEquals(a, r.getAutocaravana());
    }

    @Test
    public void testGetCliente() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);
        Assertions.assertEquals(c, r.getCliente());
    }

    @Test
    public void testGetFechaIni() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);
        Assertions.assertEquals(LocalDate.parse("2023-04-01"), r.getFechaIni());
    }

    @Test
    public void testGetFechaFin() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);
        Assertions.assertEquals(LocalDate.parse("2023-04-07"), r.getFechaFin());
    }

    @Test
    public void testGetPrecioTotal() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);

        Assertions.assertEquals(a.getPrecioPorDia() * 6, r.getPrecioTotal());
    }

    @Test
    public void testGetEstadoReserva() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);

        r.setEstadoReserva("Pendiente");
        Assertions.assertEquals("Pendiente", r.getEstadoReserva());
    }

    @Test
    public void testGetCantidadReservas() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r = new Reserva(a, c, fechaInicio, fechaFin);

        Assertions.assertEquals(1, Reserva.getListaReservas().size());

    }

    //CHECKIN Y CHECKOUT
    @Test
    public void testCheckIn(){
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r1 = new Reserva(a, c, fechaInicio, fechaFin);
        Reserva r2 = new Reserva(a, c, "2023-02-19", "2023-03-19");
        Reserva r3 = new Reserva(a, c, "2023-03-21", "2023-04-21");
        r1.setEstadoReserva("Cancelada");
        r1.checkIn();
        Assertions.assertEquals("Cancelada", r1.getEstadoReserva());

        r2.setEstadoReserva("En curso");
        r2.setEstadoReserva("Finalizada");
        r2.checkIn();
        Assertions.assertEquals("Finalizada", r2.getEstadoReserva());

        r3.setEstadoReserva("En curso");
        r3.checkIn();
        Assertions.assertEquals("En curso", r3.getEstadoReserva());

        Autocaravana b = new Autocaravana("Modelo1", 100, 4, "4024PKT", 0);
        Reserva r4 = new Reserva(b, c, "2023-03-22", "2023-04-21");
        r4.setEstadoReserva("Pendiente");
        r4.checkIn();
        Assertions.assertEquals("En curso", r4.getEstadoReserva());

        Autocaravana d = new Autocaravana("Modelo1", 100, 4, "4566PKT", 0);
        Reserva r5 = new Reserva(b, c, "2023-03-19", "2023-03-20");
        r5.setEstadoReserva("Pendiente");
        r5.checkIn();
        Assertions.assertEquals("Cancelada", r5.getEstadoReserva());

        Autocaravana e = new Autocaravana("Modelo1", 100, 4, "4776PKT", 0);
        Reserva r6 = new Reserva(e, c, "2023-04-19", "2023-04-20");
        r6.setEstadoReserva("Pendiente");
        r6.checkIn();
        Assertions.assertEquals("Pendiente", r6.getEstadoReserva());


    }

    @Test
    public void testCheckOut()
    {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "Culebrainvisible@gmail.com");
        Autocaravana b = new Autocaravana("Modelo1", 100, 4, "4024PKT", 0);
        Autocaravana d = new Autocaravana("Modelo1", 100, 4, "4034PKT", 0);

        String fechaInicio = "2023-04-01";
        String fechaFin = "2023-04-07";
        Reserva r1 = new Reserva(a, c, fechaInicio, fechaFin);
        Reserva r2 = new Reserva(a, c, "2023-02-19", "2023-03-19");
        Reserva r3 = new Reserva(b, c, "2023-01-21", "2023-02-21");
        Reserva r4 = new Reserva(b, c, "2022-03-19", "2023-03-24");
        Reserva r5 = new Reserva(d, c, "2022-03-19", "2024-03-21");

        r1.setEstadoReserva("Cancelada");
        r1.checkOut();
        Assertions.assertEquals("Cancelada", r1.getEstadoReserva());

        r2.setEstadoReserva("En curso");
        r2.setEstadoReserva("Finalizada");
        r2.checkOut();
        Assertions.assertEquals("Finalizada", r2.getEstadoReserva());

        r3.setEstadoReserva("En curso");
        float precio = r3.getPrecioTotal();
        r3.checkOut(); //Le va a poner una multa por pasarse de la fecha
        Assertions.assertTrue(r3.getPrecioTotal() > precio);
        Assertions.assertEquals(r3.getEstadoReserva(),"Finalizada");

        r4.setEstadoReserva("En curso");
        float precio2 = r4.getPrecioTotal();
        r4.checkOut();
        Assertions.assertTrue(r4.getPrecioTotal() < precio2);

        r5.setEstadoReserva("En curso");
        r5.checkOut();
        Assertions.assertEquals(r5.getEstadoReserva(),"En curso");



    }


    //Excepcion
    @Test
    public void testFechaErroneaExcepcion() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "a@b.c");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Reserva r = new Reserva(a, c, "pakito", "2023-04-07");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Reserva r = new Reserva(a, c, "pakito", "2023-04-07");
        });
    }

    @Test
    public void testCaravanaReserva() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 10000001);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2000-12-21", "77172375W", "a@b.c");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Reserva r = new Reserva(a, c, "2023-04-01", "2023-04-07");
        });
        Assertions.assertFalse(ReglasReserva.comprobarAutocaravana(a));
    }

    @Test
    public void testClienteReserva() {
        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "2009-12-21", "77172375W", "a@b.c");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Reserva r = new Reserva(a, c, "2023-04-01", "2023-04-07");
        });
        Assertions.assertFalse(ReglasReserva.comprobarCliente(c));
    }

    @Test
    public void testFechaCoincidentes() {

        Autocaravana a = new Autocaravana("Modelo1", 100, 4, "4023PKT", 0);
        Cliente c = new Cliente("Culebra", "Invisible", "12345678", "1999-12-21", "77172375W", "a@b.c");
        Cliente c2 = new Cliente("John", "Doe", "555-555-5555", "1990-01-01", "12345678", "john.doe@example.com");
        Reserva r = new Reserva(a, c, "2023-04-01", "2023-04-07");
        Assertions.assertThrows(IllegalArgumentException.class, ()->
        {
            new Reserva(a, c2, "2023-03-01", "2023-04-07");
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->
        {
            new Reserva(a, c2, "2023-03-01", "2023-04-02");
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->
        {
            new Reserva(a, c2, "2023-03-01", "2023-04-01");
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->
        {
            new Reserva(a, c2, "2023-04-07", "2023-04-09");
        });

    }
}
