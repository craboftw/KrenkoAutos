# dss2022--2023-MACFLG

### El equipo está compuesto por Francisco López y Miriam Armario.
Para sistema de control de versiones usaremos GitHub, para seguimiento de proyecto usaremos también GitHub, dado que la opción de Projects es muy útil. La comunicación se realizará en su mayoría cara a cara, y como IDE usaremos Visual Studio Code.
Nos reuniremos semanalmente para ver los avances del proyecto, siempre teniendo de objetivo entregar cada Hito antes de la fecha límite. Las herramientas a utilizar han sido comentadas anteriormente, a medida que avancemos en los Hitos, iremos describiendo las herramientas que añadamos.

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
        - reservas: List<Reserva>
        + agregarAutocaravana(ac: Autocaravana): void
        + removerAutocaravana(ac: Autocaravana): void
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
