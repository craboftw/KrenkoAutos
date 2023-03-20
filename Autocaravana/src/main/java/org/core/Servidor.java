package org.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Servidor implements Repositorio , ReglasNegocio, ReglasAutocaravana {

    Servidor() {}

        private final String AUTOCARAVANAS_FILE = "autocaravanas.txt";
        private final String CLIENTES_FILE = "clientes.txt";
        private final String RESERVAS_FILE = "reservas.txt";
        private final String ESTADOS_FILE = "estados.txt";
        private final String REGLASNEGOCIO_FILE = "reglasnegocio.txt";

        @Override
        public void cargarAutocaravanas() {
            try (Scanner scanner = new Scanner(new File(AUTOCARAVANAS_FILE))) {
                while (scanner.hasNextLine()) {
                    new Autocaravana(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                // Archivo no encontrado, lista vacia
            }
        }

        @Override
        public void cargarClientes() {
            try (Scanner scanner = new Scanner(new File(CLIENTES_FILE))) {
                while (scanner.hasNextLine()) {
                    new Cliente(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                // Archivo no encontrado, lista vacia
            }
        }

        @Override
        public void cargarReservas() {
            try (Scanner scanner = new Scanner(new File(RESERVAS_FILE))) {
                while (scanner.hasNextLine()) {
                    new Reserva(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                // Archivo no encontrado, lista vacia
            }

        }

        @Override
        public void cargarEstados() {
            try (Scanner scanner = new Scanner(new File(ESTADOS_FILE))) {
                while (scanner.hasNextLine()) {
                    Reserva.nuevoestado(scanner.nextLine().toString());
                }
            } catch (FileNotFoundException e) {
                // Archivo no encontrado, lista vacia
            }
        }

        @Override
        public void cargarTodos() {
           cargarClientes();
              cargarAutocaravanas();
                cargarReservas();
                    cargarEstados();
        }



        @Override
        public void guardar(Object o) {
            if (o instanceof Autocaravana) {
                guardarAutocaravana((Autocaravana) o);
            } else if (o instanceof Cliente) {
                guardarCliente((Cliente) o);
            } else if (o instanceof Reserva) {
                guardarReserva((Reserva) o);
            } else if (o instanceof String) {
                guardarEstado((String) o);
            }
        }

        @Override
        public void guardarColeccionEstados(Collection<String> listaEstados) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(ESTADOS_FILE))) {
                for (String estado : listaEstados) {
                    pw.println(estado);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void guardarEstado(List<String> estado) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(ESTADOS_FILE, true))) {
                pw.println(estado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void guardarAutocaravana(Collection<Autocaravana> caravanas) {
            try {
                FileWriter writer = new FileWriter("autocaravanas.txt");
                for (Autocaravana caravana : caravanas) {
                    String campos = caravana.getIdA() + ";" + caravana.getModelo() + ";" + caravana.getPrecioPorDia()
                            + ";" + caravana.getMatricula() + ";" + caravana.getKilometraje() + ";" + caravana.getEstado();
                    writer.write(campos + "\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al guardar las autocaravanas: " + e.getMessage());
            }
        }


    @Override
    public void guardarAutocaravana(Autocaravana caravana) {
        try {
            FileWriter writer = new FileWriter("autocaravanas.txt", true);
            String campos = caravana.getIdA() + ";" + caravana.getModelo() + ";" + caravana.getPrecioPorDia()
                    + ";" + caravana.getMatricula() + ";" + caravana.getKilometraje() + ";" + caravana.getEstado();
            writer.write(campos + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la autocaravana: " + e.getMessage());
        }
    }


        @Override
        public void guardarCliente(Collection<Cliente> clientes) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(CLIENTES_FILE))) {
                for (Cliente cliente : clientes) {
                    pw.println(cliente.getIdC() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," +
                            cliente.getTelefono() + "," + cliente.getFechaNacimiento() + "," + cliente.getDni() + "," +
                            cliente.getEmail() + "," + cliente.getReservasRealizadas() + "," + cliente.getMultas());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void guardarReservas(Collection<Reserva> R) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(RESERVAS_FILE))) {
                for (Reserva reserva : R) {
                    pw.println(reserva.getIdR() + ";" + reserva.getAutocaravana().getIdA() + ";" + reserva.getCliente().getDni()
                            + ";" + reserva.getFechaIni() + ";" + reserva.getFechaFin() + ";" + reserva.getEstadoReserva());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
        public void guardarReservas(Reserva R) {

        }

        @Override
        public void guardarTodos(List<Object> lista) {

        }


        public void guardarReserva(Reserva reserva) {
            try {
                // leer reservas actuales desde el archivo
                List<Reserva> reservas = new ArrayList<>();
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reservas.txt"))) {
                    while (true) {
                        Reserva r = (Reserva) ois.readObject();
                        reservas.add(r);
                    }
                } catch (EOFException e) {
                    // fin del archivo, no hacer nada
                }

                // añadir la nueva reserva a la lista
                reservas.add(reserva);

                // guardar la lista actualizada en el archivo
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("reservas.txt"))) {
                    for (Reserva r : reservas) {
                        oos.writeObject(r);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public List<Reserva> buscarReservasPorCliente(String dni) {
            List<Reserva> reservas = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reservas.txt"))) {
                while (true) {
                    Reserva r = (Reserva) ois.readObject();
                    if (r.getCliente().getDni().equals(dni)) {
                        reservas.add(r);
                    }
                }
            } catch (EOFException e) {
                // fin del archivo, no hacer nada
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return reservas;
        }

        public List<Reserva> buscarReservasPorAutocaravana(String matricula) {
            List<Reserva> reservas = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reservas.txt"))) {
                while (true) {
                    Reserva r = (Reserva) ois.readObject();
                    if (r.getAutocaravana().getMatricula().equals(matricula)) {
                        reservas.add(r);
                    }
                                    }
            } catch (EOFException e) {
                // fin del archivo, no hacer nada
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return reservas;
        }
    }

