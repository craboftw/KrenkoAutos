package uca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.*;
import uca.NotificacionEmail;
import uca.core.dao.iReservaRepositorio;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;
import uca.core.servicio.interfaces.iAutocaravanaServicio;
import uca.core.servicio.interfaces.iClienteServicio;
import uca.core.servicio.interfaces.iReservaServicio;


import uca.dto.ReservaDTO;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private iReservaServicio reservaServicio;
    private iAutocaravanaServicio autocaravanaServicio;
    private iClienteServicio clienteServicio;
    private iReservaRepositorio iReservaRepositorio;
    private NotificacionEmail notificacionEmail;

    @Autowired
    public ReservaController(iReservaServicio reservaServicio, iAutocaravanaServicio autocaravanaServicio, iClienteServicio clienteServicio, iReservaRepositorio iReservaRepositorio,NotificacionEmail mailSender) {
        this.reservaServicio = reservaServicio;
        this.autocaravanaServicio = autocaravanaServicio;
        this.clienteServicio = clienteServicio;
        this.iReservaRepositorio = iReservaRepositorio;
        this.notificacionEmail = mailSender;
    }

    @GetMapping("/")
    public Collection<Reserva> listarReservas() {
        return reservaServicio.getListaReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable("id") Long id) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reserva);
        }
    }

    @GetMapping("/cantidad-reservas")
    public ResponseEntity<Integer> getCantidadReservas() {
        int cantidadReservas = reservaServicio.getCantidadReservas();
        return ResponseEntity.ok(cantidadReservas);
    }


    @PostMapping("/")
    public ResponseEntity<Reserva> altaReserva(@RequestBody ReservaDTO intento) {
        reservaServicio.altaReserva(intento.getIdAutocaravana(), intento.getIdCliente(), intento.getFechaIni(), intento.getFechaFin());
        //get the last reserva
        Reserva R = reservaServicio.getListaReservas().stream().toList().get(reservaServicio.getListaReservas().size() - 1);
        Cliente cliente = clienteServicio.buscarCliente(intento.getIdCliente());
        Autocaravana autocaravana = autocaravanaServicio.buscarAutocaravana(intento.getIdAutocaravana());
        //send email
        notificacionEmail.enviarReservaRealizadaNotifiacion(R, cliente, autocaravana);
        return ResponseEntity.ok(R);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReservaPorId(@PathVariable("id") Long id) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/Cliente/{id}")
    public ResponseEntity<Reserva> modificarReservaCliente(@PathVariable("id") Long id, @RequestBody Long idCliente) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.setCliente(id, idCliente);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/Autocaravana/{id}")
    public ResponseEntity<Reserva> modificarReservaAutocaravana(@PathVariable("id") Long id, @RequestBody Long idAutocaravana) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.setAutocaravana(id, idAutocaravana);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/FechaIni/{id}")
    public ResponseEntity<Reserva> modificarReservaFechaIni(@PathVariable("id") Long id, @RequestBody String fechaIni) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.setFechaIni(id, fechaIni);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/FechaFin/{id}")
    public ResponseEntity<Reserva> modificarReservaFechaFin(@PathVariable("id") Long id, @RequestBody String fechaFin) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.setFechaFin(id, fechaFin);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/EstadoR/{id}")
    public ResponseEntity<Reserva> modificarReservaEstadoR(@PathVariable("id") Long id, @RequestBody String estadoR) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.setEstadoReserva(id, estadoR);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/PrecioTotal/{id}")
    public ResponseEntity<Reserva> modificarReservaPrecioTotal(@PathVariable("id") Long id, @RequestBody BigDecimal precioTotal) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.setPrecioTotal(id, precioTotal);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/checkin/{id}")
    public ResponseEntity<Reserva> modificarReservaCheckin(@PathVariable("id") Long id) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.checkin(id);
            return ResponseEntity.ok(reserva);
        }
    }

    @PutMapping("/checkout/{id}")
    public ResponseEntity<Reserva> modificarReservaCheckout(@PathVariable("id") Long id) {
        Reserva reserva = reservaServicio.buscarReserva(id);
        if (reserva == Reserva.ReservaNulo) {
            return ResponseEntity.notFound().build();
        } else {
            reservaServicio.checkout(id);
            return ResponseEntity.ok(reserva);
        }
    }

}
