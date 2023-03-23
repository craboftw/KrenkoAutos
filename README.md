# dss2022--2023-MACFLG

# HITO 0
### El equipo está compuesto por Francisco López y Miriam Armario.
## Equipo
El equipo está compuesto por Francisco López y Miriam Armario.

## Herramientas
Para el sistema de control de versiones utilizaremos GitHub, y para el seguimiento del proyecto también utilizaremos GitHub, aprovechando la utilidad de su opción de Projects. Como IDE usaremos Visual Studio Code.

## Comunicación
La comunicación entre los miembros del equipo se realizará en su mayoría de forma presencial.

## Reuniones
Nos reuniremos semanalmente para revisar el avance del proyecto, con el objetivo de entregar cada hito antes de la fecha límite.

## Desarrollo
A medida que avancemos en los hitos, describiremos las herramientas que se vayan incorporando al proyecto.


```mermaid
classDiagram
    class Autocaravana {
        - id: int
        - tipo: TipoAutocaravana
        - modelo: string
        - precioPorDia: float
        + getId(): int
        + getTipo(): TipoAutocaravana
        + getModelo(): string
        + getPrecioPorDia(): float
    }

    class TipoAutocaravana {
        - nombre: string
        + getNombre(): string
    }

    class Cliente {
        - id: int
        - nombre: string
        - apellido: string
        - email: string
        + getId(): int
        + getNombre(): string
        + getApellido(): string
        + getEmail(): string
    }

    class Reserva {
        - id: int
        - autocaravana: Autocaravana
        - cliente: Cliente
        - fechaInicio: DateTime
        - fechaFin: DateTime
        + getId(): int
        + getAutocaravana(): Autocaravana
        + getCliente(): Cliente
        + getFechaInicio(): DateTime
        + getFechaFin(): DateTime


    }

    class GestorAlquilerAutocaravanas {
        - autocaravanas: List<Autocaravana>
        - clientes: List<Cliente>
        - reservas: List<Reserva>
        - cantidadClientes: int
        - cantidadAutocaravanas: int
        - cantidadReservas: int
        + crearCliente(nombre: string, apellido: string, email: string): Cliente
        + cancelarCliente(c: Cliente): void
        + getClientes(): List<Cliente>
        + buscarCliente(id: int): Cliente
        + agregarAutocaravana(ac: Autocaravana): void
        + removerAutocaravana(id Autocaravana: int): void
        + getAutocaravanas(): List<Autocaravana>
        + buscarAutocaravana(id: int): Autocaravana
        + crearReserva(ac: Autocaravana, cl: Cliente, fechaInicio: DateTime, fechaFin: DateTime): Reserva
        + cancelarReserva(r: Reserva): void
        + getReservas(): List<Reserva>
        + buscarReserva(id: int): Reserva
    }

    Autocaravana --|> TipoAutocaravana
    Reserva --|> Autocaravana
    Reserva --|> Cliente
    GestorAlquilerAutocaravanas --> Autocaravana
    GestorAlquilerAutocaravanas --> Reserva
    GestorAlquilerAutocaravanas --> Cliente
```

# HITO 1
## Diagrama de secuencia

```mermaid
sequenceDiagram
    participant Cliente
    participant Autocaravana
    participant ReglasReserva
    participant servidor
    participant ServicioReserva
    participant listaReservas

    Cliente->>ReglasReserva: comprobarCliente(C)
    ReglasReserva-->>Cliente: resultado

    Autocaravana->>ReglasReserva: comprobarAutocaravana(A)
    ReglasReserva-->>Autocaravana: resultado

    Cliente->>Reserva: Reserva(A, C, fechI, fechF)
    Autocaravana->>Reserva: Reserva(A, C, fechI, fechF)

    Reserva->>ReglasReserva: comprobarAutocaravana(A)
    Reserva->>ReglasReserva: comprobarCliente(C)
    ReglasReserva-->>Reserva: resultado
    Reserva->>servidor: comprobarReserva(fechaIni, fechaFin, A, C)
    servidor-->>Reserva: resultado

    alt las fechas son compatibles
        Reserva->>C: setNuevaReservaRealizada()
        C-->>Reserva: resultado
        Reserva->>A: setNuevaReservaRealizada()
        A-->>Reserva: resultado
        Reserva->>servidor: calculaPrecioTotal(A, C, fechaIni, fechaFin)
        servidor-->>Reserva: precioTotal
        Reserva->>ServicioReserva: getListaEstadoReservas().get(0)
        ServicioReserva-->>Reserva: estadoReserva
        Reserva->>Reserva: getCantidadReservas()
        Reserva-->>Reserva: idR
        Reserva->>listaReservas: add(this)
    else las fechas no son compatibles
        Reserva->>Reserva: IllegalArgumentException("Las fechas no son compatibles")
    end
```

## Diagrama de Clases

```mermaid
classDiagram
    class Autocaravana {
        + servidor : ServicioAutocaravana
        - estado : String
        - idA : int
        - kilometraje : int
        - listaAutocaravanas : List<Autocaravana>
        - matricula : String
        - modelo : String
        - plazas : int
        - precioPorDia : float
        - vecesReservada : int
        + Autocaravana()
        + Autocaravana()
        + buscarAutocaravana()
        + eliminarAutocaravana()
        + getCantidadCaravanas()
        + getEstado()
        + getIdA()
        + getKilometraje()
        + getMatricula()
        + getModelo()
        + getPlazas()
        + getPrecioPorDia()
        + getVecesReservada()
        + quedanCaravanas()
        + setEstado()
        + setKilometraje()
        + setModelo()
        + setPrecioPorDia()
        + toString()
        ~ getListaAutocaravanas()
        ~ setNuevaReservaRealizada()
    }




    class AutocaravanaTest {
         + setUp()
        + test()
    }




    class Cliente {
        - apellido : String
        - dni : String
        - email : String
        - fechaNacimiento : LocalDate
        - idC : int
        - listaClientes : List<Cliente>
        - multas : int
        - nombre : String
        - reservasRealizadas : int
        - servidor : ServicioCliente
        - telefono : String
        + Cliente()
        + Cliente()
        + buscarCliente()
        + buscarCliente()
        + eliminarCliente()
        + getApellido()
        + getCantidadReservasRealizadas()
        + getDni()
        + getEdad()
        + getEmail()
        + getFechaNacimiento()
        + getIdC()
        + getMultas()
        + getNombre()
        + getNumeroClientes()
        + getTelefono()
        + setApellido()
        + setDni()
        + setEmail()
        + setFechaNacimiento()
        + setNombre()
        + setNuevaMulta()
        + setNuevaReservaRealizada()
        + setTelefono()
        + toString()
        ~ getListaClientes()
    }




    class ClienteTest {
        + setUp()
        + test()
    }


    class ReglasAutocaravana {
        + comprobarMatricula()
    }


    class ReglasCliente {
        + comprobarCliente()
        + comprobarDNI()
        + comprobarEdad()
    }




    class ReglasReserva {
        + listaEstadosReserva : List<String>
        + addEstadoReserva()
        + borrarEstado()
        + calculaPrecioTotal()
        + calcularMulta()
        + calcularTasaCancelacion()
        + calcularTasaFinalizacion()
        + calcularTasaModificacion()
        + comprobarAutocaravana()
        + comprobarCambioEstado()
        + comprobarCliente()
        + comprobarEstadoReserva()
        + comprobarReserva()
        + condicionesCancelacion()
        + condicionesFinalizacion()
        + condicionesModificacion()
        + eliminarEstadoReserva()
    }




    class RepositorioAutocaravana {
        + cargarAutocaravana()
        + cargarEstadosAutocaravana()
        + guardarAutocaravana()
        + guardarEstadoAutocaravana()
    }




    class RepositorioCliente {
        + cargarClientes()
        + cargarEstadosCliente()
        + guardarCliente()
        + guardarEstadosCliente()
    }




    class RepositorioReserva {
        + cargarEstadosReserva()
        + cargarReservas()
        + guardarEstadosReserva()
        + guardarReservas()
    }




    class Reserva {
        - cantidadReservas : int
        - estadoReserva : String
        - fechaFin : LocalDate
        - fechaIni : LocalDate
        - idR : int
        - listaReservas : List<Reserva>
        - precioTotal : float
        - servidor : ServicioReserva
        + Reserva()
        + Reserva()
        + buscarReserva()
        + buscarReserva()
        + checkIn()
        + checkOut()
        + eliminarReserva()
        + getAutocaravana()
        + getCantidadReservas()
        + getCliente()
        + getEstadoReserva()
        + getFechaFin()
        + getFechaIni()
        + getIdR()
        + getPrecioTotal()
        + setEstadoReserva()
        + toString()
        ~ getListaReservas()
        ~ setPrecioTotal()
    }




    class ReservaTest {
        + setUp()
        + test()
    }




    class ServicioAutocaravana {
        - AUTOCARAVANAS_FILE : String
        - ESTADOSAUTOCARAVANA_FILE : String
        - listaEstadosAutocaravana : List<String>
        + ServicioAutocaravana()
        + cargarAutocaravana()
        + cargarEstadosAutocaravana()
        + comprobarEstadoAutocaravana()
        + guardarAutocaravana()
        + guardarEstadoAutocaravana()
        ~ getListaEstadoAutocaravana()

  }



    class ServicioCliente {
        - CLIENTES_FILE : String
        - ESTADOSCLIENTE_FILE : String
        - listaEstadosCliente : List<String>
        + cargarClientes()
        + cargarEstadosCliente()
        + getListaEstadoClientes()
        + guardarCliente()
        + guardarEstadosCliente()
    }




    class ServicioReserva {
        - ESTADOSRESERVAS_FILE : String
        - RESERVAS_FILE : String
        - listaEstadoReserva : List<String>
        + cargarEstadosReserva()
        + cargarReservas()
        + checkIn()
        + checkOut()
        + comprobarEstadoReserva()
        + eliminarEstadoReserva()
        + getListaEstadoReservas()
        + guardarEstadosReserva()
        + guardarReservas()
        + nuevoestado()

        Autocaravana *-- ReglasAutocaravana
    }

    Autocaravana *--ReglasAutocaravana
    Reserva *--ReglasReserva
    Cliente *--ReglasCliente

    ServicioCliente .. ReglasCliente
    ServicioAutocaravana .. ReglasAutocaravana
    ServicioReserva .. ReglasReserva

    ServicioCliente .. RepositorioCliente
    ServicioReserva .. RepositorioReserva
    ServicioAutocaravana .. RepositorioAutocaravana

    Autocaravana -- RepositorioAutocaravana
    Cliente -- RepositorioCliente
    Reserva -- RepositorioReserva
```