package ar.edu.unju.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;

public class Main {
    private static final List<Producto> productos = new ArrayList<>();
    private static final Scanner scanner;
    static {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) throws Exception{
        precargarLista();
        int opcion; int opcionPago;
        String opcionDeCarga;
        String codigo;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    try {
                        mostrarProductos();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    List<Producto> productosAPagar = new ArrayList<>();
                    do {
                        System.out.print("Ingrese codigo: ");
                        codigo = scanner.next();
                        if (buscarProducto(codigo) == null) {
                            System.out.println("El código del producto no existe.");
                        }else{
                            productosAPagar.add(buscarProducto(codigo));
                        }
                        System.out.print("Desea seguir cargando datos s/n? ");
                        opcionDeCarga = scanner.next();
                    }while(!Objects.equals(opcionDeCarga, "n"));
                    if(!productosAPagar.isEmpty()) {
                        do {
                            opcionPago = menuPago();
                            switch(opcionPago) {
                                case 1:
                                    System.out.println("Ingrese numero de tarjeta: ");
                                    String numeroTarjeta = scanner.next();
                                    PagoTarjeta pagoTarjeta = new PagoTarjeta(numeroTarjeta);
                                    pagoTarjeta.realizarPago(obtenerTotalMonto(productosAPagar));
                                    pagoTarjeta.setFechaDePago(LocalDate.now());
                                    pagoTarjeta.imprimirRecibo();
                                    break;
                                case 2:
                                    PagoEfectivo pagoEfectivo = new PagoEfectivo();
                                    pagoEfectivo.realizarPago(obtenerTotalMonto(productosAPagar));
                                    pagoEfectivo.setFechaPago(LocalDate.now());
                                    pagoEfectivo.imprimirRecibo();
                                    break;
                                default:
                                    System.out.println("Selecciona una opcion valida");
                            }
                        }while(opcionPago!=1 && opcionPago!=2);
                    }
                    vaciarListaProductos(productosAPagar);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);

    }

    /**
     * Vacía la lista de productos a pagar
     */
    private static void vaciarListaProductos(List<Producto> productosAPagar) {
        productosAPagar.clear();
    }

    /**
     * Obtiene el total del monto a pagar
     */
    private static Double obtenerTotalMonto(List<Producto> productosAPagar) {
        double suma=0.0;
        for (Producto producto : productosAPagar) {
            suma=suma+producto.getPrecioUnitario();
        }
        return suma;
    }

    /**
     * Muestra el menú de pago
     */
    private static Integer menuPago() {
        System.out.println("1 – Pago Tarjeta");
        System.out.println("2 - Pago Efectivo");
        System.out.print("Ingrese valor ");
        return scanner.nextInt();
    }

    /**
     * Muestra el menú
     */
    private static Integer menu() {
        System.out.println("1 – Mostrar productos");
        System.out.println("2 - Realizar compra");
        System.out.println("3 - Salir");
        System.out.print("Ingrese valor ");
        return scanner.nextInt();
    }

    /**
     * Precarga la lista de productos
     */
    private static void precargarLista() {
        productos.add(new Producto("01","Celular",569.8,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.TELEFONIA,true));
        productos.add(new Producto("02","Microndas",18610.8,Producto.OrigenFabricacion.CHINA,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("03","Televisor",899,Producto.OrigenFabricacion.BRASIL,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("04","Radio",16190.1,Producto.OrigenFabricacion.URUGUAY,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("05","Equipo de Musica",4819.6,Producto.OrigenFabricacion.ARGENTINA,Producto.Categoria.ELECTROHOGAR,true));
        productos.add(new Producto("06","Calculadora",419,Producto.OrigenFabricacion.CHINA,Producto.Categoria.HERRAMIENTAS,true));
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

    /**
     * Muestra los productos
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
     * Busca un producto
     */
    private static Producto buscarProducto(String codigo){
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
}
