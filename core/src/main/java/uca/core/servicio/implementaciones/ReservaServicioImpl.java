package uca.core.servicio.implementaciones;

import org.springframework.stereotype.Service;
import uca.core.dao.iReservaRepositorio;
import uca.core.dao.iEstadoRepositorio;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Estado;
import uca.core.dominio.Reserva;
import uca.core.servicio.interfaces.iReservaServicio;
import uca.core.servicio.implementaciones.reglas.ReservaReglas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReservaServicioImpl implements iReservaServicio {
    private final ReservaReglas reservaReglas;
    private final iReservaRepositorio reservaRepositorio;
    private final iEstadoRepositorio reservaEstadoRepositorio;
    private final AutocaravanaServicioImpl autocaravanaServicio;
    private final ClienteServicioImpl clienteServicio;

    public ReservaServicioImpl(iReservaRepositorio reservaRepositorio, iEstadoRepositorio reservaEstadoRepositorio, AutocaravanaServicioImpl autocaravanaServicio, ClienteServicioImpl clienteServicio) {
        this.reservaReglas = new ReservaReglas(reservaRepositorio,clienteServicio,autocaravanaServicio);
        this.reservaRepositorio = reservaRepositorio;
        this.reservaEstadoRepositorio = reservaEstadoRepositorio;
        this.autocaravanaServicio = autocaravanaServicio;
        this.clienteServicio = clienteServicio;
    }

    @Override
    public void altaReserva(int idA, Long idC, String fechI, String fechF)
    {
        LocalDate fechaIni;
        LocalDate fechaFin;
        Autocaravana A;
        Cliente C;
        try
        {
            A = autocaravanaServicio.buscarAutocaravana(idA);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error no se encontro la autocaravana");
        }
        if (!reservaReglas.comprobarAutocaravana(A)) {
            throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
        }
        try
        {
            C = clienteServicio.buscarCliente(idC);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error no se encontro el cliente");
        }

        try
        {
            fechaIni = LocalDate.parse(fechI);
            fechaFin = LocalDate.parse(fechF);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error en el formato de las fechas");
        }

        //Comprobaciones del cliente
        if (!reservaReglas.comprobarCliente(C)) {
            throw new IllegalArgumentException("El cliente no cumple las condiciones");
        }

       if ( !reservaReglas.comprobarReserva(fechaIni, fechaFin, A, C)){
              throw new IllegalArgumentException("La reserva ya existe");
            }
        int idR = reservaRepositorio.cargarReserva().stream().mapToInt(Reserva::getIdR).max().orElse(0) +1 ;
        Reserva reserva = new Reserva(idR, fechI, fechF,reservaReglas.calculaPrecioTotal(A,C,fechaIni,fechaFin), BigDecimal.ZERO, C.getIdC(), A.getIdA(), "Pendiente");
        reservaRepositorio.guardarReserva(reserva);
    }

    @Override
    public String checkout(int idR) {
        Reserva R;
        try{
            R = reservaRepositorio.buscarReserva(idR);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error no se encontro la reserva");
        }

        switch (R.getEstadoR()) {
            case "Cancelada":
                return ("La reserva está cancelada");
            case "Finalizada":
                return ("La reserva ya está finalizada");
            case "En curso":
                if (LocalDate.now().isAfter(R.fechaFinF())
                        || LocalDate.now().isEqual(R.fechaFinF())) {
                    R.setPrecioTotal((reservaReglas.calcularTasaFinalizacion(R)));
                    R.setEstadoR("Finalizada");
                    reservaRepositorio.guardarReserva(R);
                    String mensaje = "La reserva ha sido finalizada.";
                } else {
                    if (LocalDate.now().isBefore(R.fechaFinF())) {
                        R.setPrecioTotal((reservaReglas.calcularTasaCancelacion(R)));
                        R.setEstadoR("Cancelada");
                        reservaRepositorio.guardarReserva(R);
                        String mensaje = "La reserva ha sido cancelada por haber finalizado antes de la fecha prevista.";
                        if (R.getPrecioTotal().compareTo( R.getPagado()) < 0)
                            mensaje += "Queda pendiente de pagar " + (R.getPrecioTotal().subtract(R.getPagado())) + "€";
                        if (R.getPrecioTotal().compareTo( R.getPagado()) > 0)
                            mensaje += "Se le devuelve " + (R.getPagado().subtract(R.getPrecioTotal())) + "€";
                        return mensaje;
                    } else
                        return ("La reserva no ha acabado");
                }
                break;
            case "Pendiente":
                return ("La reserva no ha empezado");
            default:
                return ("El estado de la reserva es " + R.getEstadoR());
        }
        return ("El estado de la reserva es " + R.getEstadoR());    }

    @Override
    public String checkin(int  idR) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            return ("No se ha encontrado la reserva");
        switch (R.getEstadoR()) {
        case "Cancelada":
            return ("La reserva está cancelada");
        case "Finalizada":
            return ("La reserva ya está finalizada");
        case "En curso":
            return ("La reserva ya está en curso");
        case "Pendiente":
            if ((LocalDate.now().isAfter(R.fechaIniF())
                    || LocalDate.now().isEqual(R.fechaIniF()))
                    & LocalDate.now().isBefore(R.fechaFinF())) {
                R.setEstadoR("En curso");
                reservaRepositorio.guardarReserva(R);
            } else {
                if (LocalDate.now().isAfter(R.fechaFinF())) {
                    R.setEstadoR("Finalizada");
                    R.setPrecioTotal(reservaReglas.calcularTasaAcabadaSinCheckOut(R));
                    reservaRepositorio.guardarReserva(R);
                } else
                    return ("La reserva no ha empezado");
            }
            break;
        default:
            return ("El estado de la reserva es " + R.getEstadoR());
    }
        return ("El estado de la reserva es " + R.getEstadoR());
    }



    //‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧ Manejo de la lista‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧

    @Override
    public Reserva buscarReserva(int idR) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("No se ha encontrado la reserva");
        return R;
    }

    @Override
    public Collection<Reserva> buscarReserva(String tipo, String info) {
        return reservaRepositorio.buscarReserva(tipo, info);
    }

    @Override
    public int getCantidadReservas() {return reservaRepositorio.cargarReserva().size();}

    @Override
    public void eliminarReserva(int idR) {
        reservaRepositorio.eliminarReserva(idR);
    }

    @Override
    public void cancelarReserva(int idR)
    {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");
        if (R.getEstadoR().equals("Cancelada"))
            throw new IllegalArgumentException("La reserva ya está cancelada");
        if (R.getEstadoR().equals("Finalizada"))
            throw new IllegalArgumentException("La reserva ya está finalizada");
        if(reservaReglas.condicionesCancelacion(R))
            R.setEstadoR("Cancelada");
        R.setPrecioTotal(R.getPrecioTotal().subtract(reservaReglas.calcularTasaCancelacion(R)));
        reservaRepositorio.guardarReserva(R);
    }


    @Override
    public void setEstadoReserva(int idR, String estado) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");
        R.setEstadoR(estado);
        reservaRepositorio.guardarReserva(R);
    }

    @Override
    public void setPrecioTotal(int idR, float precioTotal)
    {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");
        R.setPrecioTotal(BigDecimal.valueOf(precioTotal));
        reservaRepositorio.guardarReserva(R);
    }

    @Override
    public void setAutocaravana(int idR, int idA) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");
        if (reservaRepositorio.buscarReserva(idA) == null)
            throw new IllegalArgumentException("La autocaravana no existe");
        Autocaravana A = autocaravanaServicio.buscarAutocaravana(idA);
        Cliente C = clienteServicio.buscarCliente(R.getIdCliente());
        if (!reservaReglas.comprobarReserva(R.fechaIniF(), R.fechaFinF(),A, C))
            throw new IllegalArgumentException("La autocaravana no puede reservarse");
        R.setIdAutocaravana(A.getIdA());
        reservaRepositorio.guardarReserva(R);

    }

    @Override
    public void setCliente(int idR, Long idC) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");
        if (reservaRepositorio.buscarReserva(idR) == null)
            throw new IllegalArgumentException("El cliente no existe");
            Cliente C = clienteServicio.buscarCliente(idC);
        if (!reservaReglas.comprobarReserva(R.fechaIniF(), R.fechaFinF(),autocaravanaServicio.buscarAutocaravana(idR), C))
            throw new IllegalArgumentException("El cliente no puede reservar la autocaravana");
        R.setIdCliente(C.getIdC());
        reservaRepositorio.guardarReserva(R);
    }

    @Override
    public void setFechaIni(int  idR, String fechaIni) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");

        try {
            LocalDate.parse(fechaIni);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de las fechas.");
        }
        R.setFechaIni(fechaIni);
        reservaRepositorio.guardarReserva(R);
    }

    @Override
    public void setFechaFin(int idR, String fechaFin) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");
        try {
            LocalDate.parse(fechaFin);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de las fechas.");
        }
        R.setFechaFin(fechaFin);
        reservaRepositorio.guardarReserva(R);}

    @Override
    public void setPrecioTotal(int idR, BigDecimal precioTotal) {
        Reserva R = reservaRepositorio.buscarReserva(idR);
        if (R == null)
            throw new IllegalArgumentException("Error no se encontro la reserva");

        if (precioTotal.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        R.setPrecioTotal(precioTotal);
        reservaRepositorio.guardarReserva(R);
    }


    @Override
    public void modificarReservaEnCurso(int idR, String fechF) {
        Reserva reserva = reservaRepositorio.buscarReserva(idR);
        if (reserva == null)
            throw new IllegalArgumentException("No se ha encontrado la reserva.");
        LocalDate fechaFin;
        if (reserva.getEstadoR().equals("Cancelada")) {
            throw new IllegalArgumentException("La reserva está cancelada.");
        }
        if (reserva.getEstadoR().equals("Finalizada")) {
            throw new IllegalArgumentException("La reserva está finalizada.");
        }
        if (reserva.getEstadoR().equals("En curso")) {

            try {
                fechaFin = LocalDate.parse(fechF);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error en el formato de las fechas.");
            }

            if (reservaReglas.comprobarReserva(reserva.fechaIniF(), fechaFin,autocaravanaServicio.buscarAutocaravana(idR), clienteServicio.buscarCliente(reserva.getIdCliente()))) {
                throw new IllegalArgumentException("La autocaravana no está disponible en las fechas seleccionadas.");
            }
            if(!reservaReglas.condicionesModificacion(reserva))
                throw new IllegalArgumentException("No se puede modificar la reserva.");
            reserva.setFechaFin(fechF);
            reserva.setPrecioTotal(reservaReglas.calculaPrecioTotal(autocaravanaServicio.buscarAutocaravana(idR),clienteServicio.buscarCliente(reserva.getIdCliente()), reserva.fechaIniF(), fechaFin).add( reservaReglas.calcularTasaModificacion(reserva)));
            reservaRepositorio.guardarReserva(reserva);
        }
    }

    @Override
    public void modificarReserva(int idR, int idA, String fechI, String fechF){ //todavia no esta en curso
        Reserva reserva = reservaRepositorio.buscarReserva(idR);
        Autocaravana a = autocaravanaServicio.buscarAutocaravana(idA);
        if (reserva == null)
            throw new IllegalArgumentException("No se ha encontrado la reserva");
        if (a == null)
            throw new IllegalArgumentException("No se ha encontrado la autocaravana");
        LocalDate fechaIni;
        LocalDate fechaFin;
        if (reserva.getEstadoR().equals("Cancelada")) {
            throw new IllegalArgumentException("La reserva está cancelada");
        }
        if (reserva.getEstadoR().equals("Finalizada")) {
            throw new IllegalArgumentException("La reserva está finalizada");
        }
        if (!reserva.getEstadoR().equals("En curso")) {

            try {
                fechaIni = LocalDate.parse(fechI);
                fechaFin = LocalDate.parse(fechF);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error en el formato de las fechas");
            }

            if (!reservaReglas.comprobarAutocaravana(a)) {
                throw new IllegalArgumentException("La caravana seleccionada no cumple las condiciones");
            }
            if (fechaIni.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha inicial no puede ser anterior a la fecha actual");
            }
            if (reservaReglas.comprobarReserva(fechaIni, fechaFin, a, clienteServicio.buscarCliente(reserva.getIdCliente()))) {
                throw new IllegalArgumentException("La autocaravana no está disponible en las fechas seleccionadas");
            }

            reserva.setFechaIni(fechI);
            reserva.setFechaFin(fechF);
            reserva.setPrecioTotal(reservaReglas.calculaPrecioTotal(a, clienteServicio.buscarCliente(reserva.getIdCliente()), fechaIni, fechaFin));
        } else
            throw new IllegalArgumentException("La reserva no está en curso");
    }


    @Override
    public Collection<Reserva> getListaReservas() {
        return reservaRepositorio.cargarReserva();
    }

    @Override
    public String crearEstado(String estado)
    {
        if(reservaEstadoRepositorio.cargarEstado("Reserva").contains(estado)) return "Ya existe el estado en la lista de estados de reserva";
        reservaEstadoRepositorio.guardarEstado(new Estado("Reserva",estado));
        return "Estado creado correctamente";
    }

    @Override
    public String eliminarEstado(String estado)
    {
        if(reservaEstadoRepositorio.cargarEstado("Reserva").contains(estado)) return "No existe el estado en la lista de estados de reserva";
        reservaEstadoRepositorio.eliminarEstado(new Estado("Reserva",estado));
        return "Estado eliminado correctamente";
    }


}



