package ar.edu.unju.fi.ejercicio7.main;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import ar.edu.unju.fi.ejercicio1.model.Producto;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        precargarLista(productos);
        int opcion;
        do {
            System.out.println("1 – Mostrar productos");
            System.out.println("2 – Mostrar los productos faltantes");
            System.out.println("3 – Incrementar los precios de los productos en un 20%");
            System.out.println("4 – Mostrar los productos que corresponden a la categoría Electrohogar y estén disponibles para la venta");
            System.out.println("5 – Ordenar los productos por precio de forma descendente");
            System.out.println("6 - Mostrar los productos con los nombres en mayúsculas");
            System.out.println("7 - Salir");
            System.out.print("Ingrese una opción: ");
            Scanner scanner = new Scanner(System.in);
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Consumer mostrarProducto = producto -> {
                        if (producto instanceof Producto) {
                            System.out.println(producto);
                        }
                    };
                    productos.forEach(mostrarProducto);
                    break;
                case 2:
                    Predicate<Producto> isFaltante = producto -> !producto.getEstado().equals(true);
                    productos.stream().filter(isFaltante).forEach(System.out::println);
                    break;
                case 3:
                    Function<Producto, Producto> incrementarPrecio = producto -> {
                        producto.setPrecioUnitario(producto.getPrecioUnitario() * 1.2);
                        return producto;
                    };
                    List<Producto> productosIncrementados = new ArrayList<>();
                    productos.stream().map(incrementarPrecio).forEach(productosIncrementados::add);
                    break;
                case 4:
                    Predicate<Producto> isElectrohogarYDisponible = producto -> producto.getCategoria().equals(Producto.Categoria.ELECTROHOGAR) && producto.getEstado();
                    productos.stream().filter(isElectrohogarYDisponible).forEach(System.out::println);
                    break;
                case 5:
                    productos.sort(Comparator.comparing(Producto::getPrecioUnitario).reversed());
                    break;
                case 6:
                    Function<Producto, Producto> toUpperCase = producto -> {
                        producto.setDescripcion(producto.getDescripcion().toUpperCase());
                        return producto;
                    };
                    productos.stream().map(toUpperCase).forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 7);
    }
    private static void precargarLista(List<Producto> productos) {
        productos.add(new Producto("01","Celular",569.8,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.TELEFONIA,false));
        productos.add(new Producto("02","Microndas",18610.8,Producto.OrigenFabricacion.CHINA,Producto.Categoria.ELECTROHOGAR,false));
        productos.add(new Producto("03","Televisor",899,Producto.OrigenFabricacion.BRASIL,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("04","Radio",16190.1,Producto.OrigenFabricacion.URUGUAY,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("05","Equipo de Musica",4819.6,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("06","Calculadora",419,Producto.OrigenFabricacion.CHINA,Producto.Categoria.HERRAMIENTAS,false));
        productos.add(new Producto("07","Escoba Electrica",5690.8,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.HERRAMIENTAS,true));
        productos.add(new Producto("08","Mouse",899.8,Producto.OrigenFabricacion.CHINA,Producto.Categoria.INFORMATICA,true));
        productos.add(new Producto("09","Cargador",109.8,Producto.OrigenFabricacion.URUGUAY,Producto.Categoria.TELEFONIA,true));
        productos.add(new Producto("10","CD",6929.8,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("11","Regla",451.8,Producto.OrigenFabricacion.CHINA,Producto.Categoria.HERRAMIENTAS,true));
        productos.add(new Producto("12","Auriculares",4958.8,Producto.OrigenFabricacion.URUGUAY,Producto.Categoria.TELEFONIA,true));
        productos.add(new Producto("13","Funda",891.8,Producto.OrigenFabricacion.BRASIL,Producto.Categoria.TELEFONIA,true));
        productos.add(new Producto("14","Vidrio Templado",480.8,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.TELEFONIA,true));
        productos.add(new Producto("15","Modulo",4912.8,Producto.OrigenFabricacion.BRASIL,Producto.Categoria.TELEFONIA,true));
    }
}