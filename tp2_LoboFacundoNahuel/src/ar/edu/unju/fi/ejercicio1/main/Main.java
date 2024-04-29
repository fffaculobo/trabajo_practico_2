package ar.edu.unju.fi.ejercicio1.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Producto;

public class Main {
	private static final List<Producto> productos = new ArrayList<>();
    private static final Scanner scanner;
    static {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) throws Exception {
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    productos.add(crearProducto());
                    break;
                case 2:
                    try {
                        mostrarProductos();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try{
                        modificarProducto();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    /**
     * Muestra el menú de opciones
     * @return
     */
    private static int menu() {
        System.out.println("MENU");
        System.out.println("1 – Crear Producto");
        System.out.println("2 – Mostrar productos");
        System.out.println("3 – Modificar producto");
        System.out.println("4 – Salir");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    /**
     * Crea un producto
     * @return
     */
    private static Producto crearProducto(){
        Producto producto = new Producto();
        verificarCodigoRepetido(producto);
        System.out.print("Ingrese descripcion: ");
        producto.setDescripcion(scanner.next());
        elegirPrecioUnitario(producto);
        scanner.nextLine();
        producto.setOrigenFabricacion(obtenerOrigenFabricacion());
        producto.setCategoria(obtenerCategoria());
        return producto;
    }

    /**
     * Verifica que el código del producto no esté repetido
     * @param producto
     */
    private static void verificarCodigoRepetido(Producto producto) {
        String codigo;
        do {
            try {
                System.out.print("Ingrese codigo: ");
                codigo = scanner.next();
                if (buscarProducto(codigo) != null) {
                    throw new Exception("El código del producto ya existe.");
                }
                producto.setCodigo(codigo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                codigo = null;
            }
        } while (codigo == null);
    }

    /**
     * Elegir el precio unitario del producto
     * @param producto
     */
    private static void elegirPrecioUnitario(Producto producto) {
        double precioUnitario;
        do {
            System.out.print("Ingrese precio unitario: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Eso no es un número. Por favor, intente de nuevo.");
                scanner.next(); // descarta la entrada incorrecta
            }
            precioUnitario = scanner.nextDouble();
        } while (precioUnitario <= 0);
        producto.setPrecioUnitario(precioUnitario);
    }

    /**
     * Busca un producto por su código
     * @param codigo
     * @return
     */
    private static Producto buscarProducto(String codigo){
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Obtiene el origen de fabricación del producto
     * @return
     */
    private static Producto.OrigenFabricacion obtenerOrigenFabricacion(){
        int opcion;
        do {
            opcion = menuOrigenFabricacion();
            if (opcion < 1 || opcion > 4) {
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion < 1 || opcion > 4);

        return switch (opcion) {
            case 1 -> Producto.OrigenFabricacion.ARGENTINA;
            case 2 -> Producto.OrigenFabricacion.CHINA;
            case 3 -> Producto.OrigenFabricacion.BRASIL;
            case 4 -> Producto.OrigenFabricacion.URUGUAY;
            default -> null;
        };
    }

    /**
     * Muestra el menú de origen de fabricación
     * @return
     */
    private static Integer menuOrigenFabricacion(){
        System.out.println("Seleccione el origen de fabricación del producto: ");
        System.out.println("1 – Argentina");
        System.out.println("2 – China");
        System.out.println("3 – Brasil");
        System.out.println("4-  Uruguay");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    /**
     * Obtiene la categoría del producto
     * @return
     */
    private static Producto.Categoria obtenerCategoria(){
        int opcion;
        do {
            opcion = menuCategoria();
            if (opcion < 1 || opcion > 4) {
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion < 1 || opcion > 4);

        return switch (opcion) {
            case 1 -> Producto.Categoria.TELEFONIA;
            case 2 -> Producto.Categoria.INFORMATICA;
            case 3 -> Producto.Categoria.ELECTROHOGAR;
            case 4 -> Producto.Categoria.HERRAMIENTAS;
            default -> null;
        };
    }

    /**
     * Muestra el menú de categorías
     * @return
     */
    private static Integer menuCategoria(){
        System.out.println("Seleccione la categoría del producto: ");
        System.out.println("1 – Telefonia");
        System.out.println("2 – Informatica");
        System.out.println("3 – Electrohogar");
        System.out.println("4 – Herramientas");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    /**
     * Muestra los productos
     * @throws Exception
     */
    private static void mostrarProductos() throws Exception {
        if (Main.productos.isEmpty()) {
            throw new Exception("No hay productos");
        }
        for (Producto producto : Main.productos) {
            System.out.println(producto);
        }
    }

    /**
     * Modifica un producto
     * @throws Exception
     */
    private static void modificarProducto() throws Exception{
        System.out.print("Ingrese el código del producto a modificar: ");
        String codigo = scanner.next();
        int opcion;
        Producto producto = buscarProducto(codigo);
        if (producto == null) throw new Exception("El producto no existe.");
        do {
            opcion = menuModificacion();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la nueva descripción: ");
                    producto.setDescripcion(scanner.next());
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo precio unitario: ");
                    producto.setPrecioUnitario(scanner.nextDouble());
                    break;
                case 3:
                    producto.setOrigenFabricacion(obtenerOrigenFabricacion());
                    break;
                case 4:
                    producto.setCategoria(obtenerCategoria());
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion < 1 || opcion > 4);
    }

    /**
     * Muestra el menú de modificación
     * @return
     */
    private static Integer menuModificacion(){
        System.out.println("Seleccione el producto a modificar: ");
        System.out.println("1 – Modificar descripción");
        System.out.println("2 – Modificar precio unitario");
        System.out.println("3 – Modificar origen de fabricación");
        System.out.println("4 – Modificar categoría");
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

}
