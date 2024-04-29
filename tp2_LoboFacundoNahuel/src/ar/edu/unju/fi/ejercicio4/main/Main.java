package ar.edu.unju.fi.ejercicio4.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Jugador> jugadores = new ArrayList<>();

    /**
     * Muestra el menú de opciones
     */
    private static Integer menu() {
        System.out.println("MENU");
        System.out.println("1 – Alta de jugador");
        System.out.println("2 – Mostrar todos los jugadores");
        System.out.println("3 – Modificar la posición de un jugador");
        System.out.println("4 – Eliminar un jugador");
        System.out.println("5 – Salir");
        System.out.print("Ingrese una opción: ");
        Integer opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    /**
     * Crea un jugador
     */
    private static Jugador crearJugador() {
        Jugador jugador = new Jugador();
        int posicionJugador;
        System.out.print("Ingrese el nombre del jugador: ");
        jugador.setNombre(scanner.next());
        System.out.print("Ingrese el apellido del jugador: ");
        jugador.setApellido(scanner.next());
        System.out.println("Ingrese la fecha de nacimiento del jugador: ");
        System.out.print("Día: ");
        int dia = scanner.nextInt();
        System.out.print("Mes: ");
        int mes = scanner.nextInt();
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        jugador.setFechaNacimiento(LocalDate.of(anio, mes, dia));
        System.out.print("Ingrese la nacionalidad del jugador: ");
        jugador.setNacionalidad(scanner.next());
        System.out.print("Ingrese la estatura del jugador: ");
        jugador.setEstatura(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese el peso del jugador: ");
        jugador.setPeso(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese la posición del jugador: ");
        System.out.println();
        posicionJugador = menuPosicionJugador();
        setearPosicion(jugador, posicionJugador);
        scanner.nextLine();
        return jugador;
    }

    /**
     * Setea la posición del jugador
     */
    private static void setearPosicion(Jugador jugador, int posicionJugador) {
        switch (posicionJugador) {
            case 1:
                jugador.setPosicion(Posicion.DELANTERO);
                break;
            case 2:
                jugador.setPosicion(Posicion.MEDIO);
                break;
            case 3:
                jugador.setPosicion(Posicion.DEFENSA);
                break;
            case 4:
                jugador.setPosicion(Posicion.ARQUERO);
                break;
            default:
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
        }
    }

    /**
     * Muestra el menú de posiciones
     */
    private static Integer menuPosicionJugador() {
        System.out.println("Posiciones:");
        System.out.println("1 – Delantero");
        System.out.println("2 – Medio");
        System.out.println("3 – Defensa");
        System.out.println("4 – Arquero");
        System.out.print("Ingrese la posición del jugador: ");
        return scanner.nextInt();
    }

    /**
     * Muestra los jugadores
     */
    private static void mostrarJugadores() throws Exception {
        if (jugadores.isEmpty()) {
            throw new Exception("No hay jugadores");
        }
        jugadores.forEach(jugador -> System.out.println(jugador.toString()));
    }

    /**
     * Busca un jugador
     */
    private static Jugador buscarJugador(String nombre, String apellido) throws Exception {
        if (jugadores.isEmpty()) {
            throw new Exception("No hay jugadores");
        }
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equals(nombre) && jugador.getApellido().equals(apellido)) {
                return jugador;
            }
        }
        throw new Exception("No se encontró el jugador.");
    }

    /**
     * Modifica la posición de un jugador
     */
    private static void modificarPosicionJugador() throws Exception {
    	if (jugadores.isEmpty()) {
            throw new Exception("No hay jugadores");
        }
        String nombre;
        String apellido;
        // Modificar la posición de un jugador
        System.out.print("Ingrese el nombre del jugador: ");
        nombre = scanner.next();
        System.out.print("Ingrese el apellido del jugador: ");
        apellido = scanner.next();
        Jugador jugadorModificado = buscarJugador(nombre,apellido);
        System.out.print("Ingrese la nueva posición del jugador: ");
        System.out.println();
        int posicionJugador = menuPosicionJugador();
        setearPosicion(jugadorModificado, posicionJugador);
    }

    /**
     * Elimina un jugador
     */
    private static boolean eliminarJugador(boolean jugadorEncontrado) throws Exception {
        String nombre;
        String apellido;
        // Eliminar un jugador
        System.out.print("Ingrese el nombre del jugador: ");
        nombre = scanner.next();
        System.out.print("Ingrese el apellido del jugador: ");
        apellido = scanner.next();
        Iterator<Jugador> iterador = jugadores.iterator();
        while (iterador.hasNext()) {
            Jugador jugadorActual = iterador.next();
            if (jugadorActual.getNombre().equals(nombre) && jugadorActual.getApellido().equals(apellido)) {
                iterador.remove();
                jugadorEncontrado = true;
                break;
            }
        }
        if (!jugadorEncontrado) {
            throw new Exception("No se encontró el jugador.");
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        int opcion;
        boolean jugadorEncontrado = false;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    jugadores.add(crearJugador());
                    break;
                case 2:
                    try{
                        mostrarJugadores();
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        modificarPosicionJugador();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        jugadorEncontrado = eliminarJugador(jugadorEncontrado);
                        if (jugadorEncontrado) {
                            System.out.println("Jugador eliminado.");
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    // Salir
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }
}
