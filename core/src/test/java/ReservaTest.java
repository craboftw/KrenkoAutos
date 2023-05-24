

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;
import uca.core.servicio.implementaciones.ClienteServicioImpl;
import uca.core.servicio.implementaciones.AutocaravanaServicioImpl;
import uca.core.servicio.implementaciones.ReservaServicioImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class ReservaTest {
    
    @Autowired
    ReservaServicioImpl reservaservicio;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
        reservaservicio.eliminarReserva(0L);
    }

    @Test   
    public void testAltaReserva(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("2023-25-05", r.getFechaIni());
        Assertions.assertEquals("2023-30-05", r.getFechaFin());
        Assertions.assertEquals(0L, r.getIdR());
        Assertions.assertEquals(0L, r.getIdCliente());
        Assertions.assertEquals(0L, r.getIdAutocaravana());
        Assertions.assertEquals(BigDecimal.valueOf(500), r.getPrecioTotal());
        Assertions.assertEquals("Pendiente", r.getEstadoR());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);

    }


    @Test
    public void testCheckout(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.checkout(0L);
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("Finalizada", r.getEstadoR());
        Assertions.assertEquals(BigDecimal.valueOf(500), r.getPrecioTotal());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L); 

    }

    @Test
    public void testCheckin(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.checkin(0L);

        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("En curso", r.getEstadoR());
        Assertions.assertEquals(BigDecimal.valueOf(500), r.getPrecioTotal());

        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);

    }

    @Test
    public void testBuscarReserva(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);

        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals(r, reservaservicio.buscarReserva(0L));
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }

    @Test
    public void testBuscarReservaCampo(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("idR", "0L"))).contains(r));
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("idC", "0L"))).contains(r));
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("idA", "0L"))).contains(r));
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("fechaIni", "2023-25-05"))).contains(r));
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("fechaFin", "2023-30-05"))).contains(r));
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("precioTotal", "500"))).contains(r));
        Assertions.assertTrue(((List<Reserva>)(reservaservicio.buscarReserva("estadoR", "Pendiente"))).contains(r));
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);

    }

    @Test
    public void testGetCantidadReserva(){
        Assertions.assertEquals(0, reservaservicio.getCantidadReservas());
    }

    @Test
    public void testEliminarReserva(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        var lista = reservaservicio.getListaReservas();
        reservaservicio.eliminarReserva(0L);
        Assertions.assertTrue(!lista.contains(r));
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }

    @Test
    public void testCancelarReserva(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.cancelarReserva(0L);
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("Cancelada", r.getEstadoR());
        Assertions.assertEquals(BigDecimal.valueOf(0), r.getPrecioTotal());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);

    }

    @Test
    public void testSetEstadoReserva(){

        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.setEstadoReserva(0L, "En curso");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("En curso", r.getEstadoR());
        Assertions.assertEquals(BigDecimal.valueOf(500), r.getPrecioTotal());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);

    }

    @Test
    public void testSetPrecioTotal(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.setPrecioTotal(0L, BigDecimal.valueOf(1000));
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals(BigDecimal.valueOf(1000), r.getPrecioTotal());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);

    }

    @Test
    public void testSetAutocaravana(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        autocaravanaservicio.altaAutocaravana("Modelo2", BigDecimal.valueOf(100), 4, "4023PKT", 1);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.setAutocaravana(0L, 1L);
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals(1L, r.getIdAutocaravana());
        autocaravanaservicio.eliminarAutocaravana(0L);
        autocaravanaservicio.eliminarAutocaravana(1L);
        clienteservicio.eliminarCliente(0L);

    }

    @Test
    public void testSetCliente(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "666-666-666", "1990-01-01", "12345678A", "john@gmail.com");
        Cliente c = clienteservicio.buscarCliente(0L);
        clienteservicio.altaCliente("Mary", "Doe", "999-999-999", "1990-01-11", "87654321B", "mary@gmail.com");
        Cliente c2 = clienteservicio.buscarCliente(1L);
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.setCliente(0L, 1L);
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals(1L, r.getIdCliente());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
        clienteservicio.eliminarCliente(1L);
    }

    @Test
    public void testSetFechaInicio(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.setFechaIni(0L, "2023-26-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("2023-26-05", r.getFechaIni());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }


    @Test
    public void testSetFechaFin(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.setFechaFin(0L, "2023-31-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("2023-31-05", r.getFechaFin());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }


    @Test
    public void testModificarReservaEnCurso(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.modificarReservaEnCurso(0L, "2023-31-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("2023-31-05", r.getFechaFin());
        Assertions.assertEquals("En curso", r.getEstadoR());
        Assertions.assertEquals(BigDecimal.valueOf(500), r.getPrecioTotal());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }


    @Test
    public void testModificarReserva(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.modificarReserva(0L, 0L, "2023-26-05", "2023-31-05");
        Reserva r = reservaservicio.buscarReserva(0L);
        Assertions.assertEquals("2023-26-05", r.getFechaIni());
        Assertions.assertEquals("2023-31-05", r.getFechaFin());
        Assertions.assertEquals("En curso", r.getEstadoR());
        Assertions.assertEquals(BigDecimal.valueOf(600), r.getPrecioTotal());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }


    @Test
    public void testGetListaReservas(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        clienteservicio.altaCliente("Mary", "Doe", "999-999-999", "1990-01-11", "87654321B", "mary@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        autocaravanaservicio.altaAutocaravana("Modelo2", BigDecimal.valueOf(200), 8, "8058PKT", 1);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.altaReserva(1L, 1L, "2023-25-05", "2023-30-05");
        List<Reserva> lista = (List<Reserva>) (reservaservicio.getListaReservas());
        Assertions.assertEquals(2, lista.size());
        Assertions.assertTrue(lista.contains(reservaservicio.buscarReserva(0L)));
        Assertions.assertTrue(lista.contains(reservaservicio.buscarReserva(1L)));
        autocaravanaservicio.eliminarAutocaravana(0L);
        autocaravanaservicio.eliminarAutocaravana(1L);
        clienteservicio.eliminarCliente(0L);
        clienteservicio.eliminarCliente(1L);
    }

    @Test
    public void testCrearEstado(){

        String estado = "En curso";
        String estado2 = "Cancelada";
        String estado3 = "Finalizada";
        String estado4 = "En curso";
        String estado5 = "Cancelada";
        String estado6 = "Finalizada";

        Assertions.assertEquals("Estado creado correctamente", reservaservicio.crearEstado(estado));
        Assertions.assertEquals("Estado creado correctamente", reservaservicio.crearEstado(estado2));
        Assertions.assertEquals("Estado creado correctamente", reservaservicio.crearEstado(estado3));
        Assertions.assertEquals("Ya existe el estado en la lista de estados de reserva", reservaservicio.crearEstado(estado4));
        Assertions.assertEquals("Ya existe el estado en la lista de estados de reserva", reservaservicio.crearEstado(estado5));
        Assertions.assertEquals("Ya existe el estado en la lista de estados de reserva", reservaservicio.crearEstado(estado6));

        reservaservicio.eliminarEstado(estado);
        reservaservicio.eliminarEstado(estado2);
        reservaservicio.eliminarEstado(estado3);
    }

  
    @Test
    public void testGetListaAutocaravanasDisponibles(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        autocaravanaservicio.altaAutocaravana("Modelo2", BigDecimal.valueOf(200), 8, "8058PKT", 1);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        reservaservicio.altaReserva(0L, 1L, "2023-25-05", "2023-30-05");
        List<Autocaravana> lista = (List<Autocaravana>) (reservaservicio.getListaAutocaravanasDisponibles(LocalDate.parse("2023-26-05")));
        Assertions.assertEquals(1, lista.size());
        Assertions.assertTrue(lista.contains(autocaravanaservicio.buscarAutocaravana(1L)));
        autocaravanaservicio.eliminarAutocaravana(0L);
        autocaravanaservicio.eliminarAutocaravana(1L);
        clienteservicio.eliminarCliente(0L);
    }



    @Test
    public void testGuardarReserva(){
        ClienteServicioImpl clienteservicio;
        clienteservicio.altaCliente("John", "Doe", "12345678", "1990-01-01", "12345678A", "john@gmail.com");
        AutocaravanaServicioImpl autocaravanaservicio;
        autocaravanaservicio.altaAutocaravana("Modelo1", BigDecimal.valueOf(100), 4, "4023PKT", 0);
        reservaservicio.altaReserva(0L, 0L, "2023-25-05", "2023-30-05");
        Reserva r = reservaservicio.buscarReserva(0L);

        reservaservicio.guardarReserva(r);
        Assertions.assertEquals("2023-25-05", reservaservicio.buscarReserva(0L).getFechaIni());
        Assertions.assertEquals("2023-30-05", reservaservicio.buscarReserva(0L).getFechaFin());
        Assertions.assertEquals(0L, reservaservicio.buscarReserva(0L).getIdCliente());
        Assertions.assertEquals(0L, reservaservicio.buscarReserva(0L).getIdAutocaravana());
        autocaravanaservicio.eliminarAutocaravana(0L);
        clienteservicio.eliminarCliente(0L);
    }

}