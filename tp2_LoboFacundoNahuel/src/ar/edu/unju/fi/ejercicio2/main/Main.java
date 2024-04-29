package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {
	private static final List<Efemeride> efemerides = new ArrayList<>();
    private static final Scanner scanner;
    static {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) throws Exception {
        int opcion;
        do{
            opcion = menuPrincipal();
            switch (opcion){
                case 1:
                    efemerides.add(crearEfemeride());
                    System.out.println("Se ha creado la efemeride.");
                    break;
                case 2:
                    try {
                        mostrarEfemerides();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        eliminarEfemeride();
                        System.out.println("Se ha eliminado la efemeride.");
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        modificarEfemeride();
                        System.out.println("Se ha modificado la efemeride.");
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }while (opcion != 5);
    }

    /**
     * Crea una efemeride
     */
    private static Efemeride crearEfemeride() {
        Efemeride efemeride = new Efemeride();
        verificarCodigoRepetido(efemeride);
        efemeride.setMes(obtenerMes());
        efemeride.setDia(obtenerDia());
        System.out.print("Ingrese detalle: ");
        efemeride.setDetalle(scanner.next());
        scanner.nextLine();
        return efemeride;
    }

    /**
     * Verifica que el código no esté repetido
     */
    private static void verificarCodigoRepetido(Efemeride efemeride) {
        boolean repetido;
        do {
            repetido = false;
            System.out.print("Ingrese código: ");
            String codigo = scanner.next();
            for (Efemeride e : efemerides) {
                if (e.getCodigo().equals(codigo)) {
                    System.out.println("Código repetido. Por favor, intente de nuevo.");
                    repetido = true;
                    break;
                }
            }
            efemeride.setCodigo(codigo);
        } while (repetido);
    }

    /**
     * Obtiene el día
     */
    private static Integer obtenerDia() {
        int dia;
        do {
            System.out.print("Ingrese día: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Eso no es un número. Por favor, intente de nuevo.");
                scanner.next(); // descarta la entrada incorrecta
            }
            dia = scanner.nextInt();
            scanner.nextLine(); // consume el carácter de línea restante
            if (dia < 1 || dia > 31) {
                System.out.println("Día inválido. Por favor, intente de nuevo.");
            }
        } while (dia < 1 || dia > 31);
        return dia;
    }

    /**
     * Obtiene el mes
     */
    private static Mes obtenerMes() {
        int opcion;
        do {
            opcion = menuMes();
            if (opcion < 1 || opcion > 12) {
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion < 1 || opcion > 12);
        return switch (opcion) {
            case 1 -> Mes.ENERO;
            case 2 -> Mes.FEBRERO;
            case 3 -> Mes.MARZO;
            case 4 -> Mes.ABRIL;
            case 5 -> Mes.MAYO;
            case 6 -> Mes.JUNIO;
            case 7 -> Mes.JULIO;
            case 8 -> Mes.AGOSTO;
            case 9 -> Mes.SEPTIEMBRE;
            case 10 -> Mes.OCTUBRE;
            case 11 -> Mes.NOVIEMBRE;
            case 12 -> Mes.DICIEMBRE;
            default -> null;
        };
    }

    /**
     * Muestra el menú de meses
     */
    private static Integer menuMes() {
        System.out.println("Seleccione el mes: ");
        System.out.println("1 – Enero");
        System.out.println("2 – Febrero");
        System.out.println("3 – Marzo");
        System.out.println("4 – Abril");
        System.out.println("5 – Mayo");
        System.out.println("6 – Junio");
        System.out.println("7 – Julio");
        System.out.println("8 – Agosto");
        System.out.println("9 – Septiembre");
        System.out.println("10 – Octubre");
        System.out.println("11 – Noviembre");
        System.out.println("12 – Diciembre");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    /**
     * Modifica una efemeride
     */
    private static void modificarEfemeride() throws Exception {
        Efemeride efemerideModificar = buscarEfemeride();
        Integer opcion = menuModificar();
        switch (opcion){
            case 1:
                System.out.print("Ingrese nuevo código: ");
                efemerideModificar.setCodigo(scanner.next());
                break;
            case 2:
                efemerideModificar.setMes(obtenerMes());
                break;
            case 3:
                efemerideModificar.setDia(obtenerDia());
                break;
            case 4:
                System.out.print("Ingrese nuevo detalle: ");
                efemerideModificar.setDetalle(scanner.next());
                break;
            case 5:
                System.out.println("Saliendo del menú de modificación...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    /**
     * Muestra el menú de modificación
     */
    private static Integer menuModificar() {
        System.out.println("1) Modificar código");
        System.out.println("2) Modificar mes");
        System.out.println("3) Modificar día");
        System.out.println("4) Modificar detalle");
        System.out.println("5) Salir");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    /**
     * Elimina una efemeride
     */
    private static void eliminarEfemeride() throws Exception {
        Efemeride efemerideEliminar = buscarEfemeride();
        efemerides.remove(efemerideEliminar);
    }

    /**
     * Busca una efemeride
     */
    private static Efemeride buscarEfemeride() throws Exception{
        if (efemerides.isEmpty()) {
            throw new Exception("No hay efemerides");
        }
        System.out.print("Ingrese código: ");
        String codigo = scanner.next();
        for (Efemeride e : efemerides) {
            if (e.getCodigo().equals(codigo)){
                return e;
            }
        }
        throw new Exception("No se encontró la efemeride.");
    }

    /**
     * Muestra las efemerides
     */
    private static void mostrarEfemerides() throws Exception{
        if (efemerides.isEmpty()) {
            throw new Exception("No hay efemerides");
        }
        for (Efemeride efemeride : efemerides) {
            System.out.println(efemeride);
        }
    }

    /**
     * Muestra el menú principal
     */
    private static Integer menuPrincipal(){
        System.out.println("MENU");
        System.out.println("1) Crear efemeride");
        System.out.println("2) Mostrar efemerides");
        System.out.println("3) Eliminar efemeride");
        System.out.println("4) Modificar efemeride");
        System.out.println("5) Salir");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }
}
