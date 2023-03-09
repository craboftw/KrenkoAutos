# dss2022--2023-MACFLG

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
        - reservas: List<Reserva>
        + agregarAutocaravana(ac: Autocaravana): void
        + removerAutocaravana(id Autocaravana: int): void
        + getAutocaravanas(): List<Autocaravana>
        c+ buscarAutocaravana(id: int): Autocaravana
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
