package UI;

import Entidad.*;
import java.util.Scanner;

/**
 * Clase principal que contiene el menú de la aplicación.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaProductos lista = new ListaProductos();

        int opcion;
        do {
            System.out.println("\n=== MENÚ DE IMPLEMENTOS MÉDICOS ===");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Modificar producto");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1, 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();
                    System.out.print("Fecha de vencimiento (opcional): ");
                    String fecha = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    Producto p = new Producto(nombre, precio, categoria, fecha, cantidad);
                    if (opcion == 1) lista.insertarInicio(p);
                    else lista.insertarFinal(p);
                }
                case 3 -> {
                    System.out.print("Ingrese el nombre del producto a modificar: ");
                    String nombreMod = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Nueva categoría: ");
                    String nuevaCategoria = sc.nextLine();
                    System.out.print("Nueva fecha de vencimiento: ");
                    String nuevaFecha = sc.nextLine();
                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = sc.nextInt();
                    sc.nextLine();

                    Producto nuevo = new Producto(nuevoNombre, nuevoPrecio, nuevaCategoria, nuevaFecha, nuevaCantidad);
                    if (lista.modificarProducto(nombreMod, nuevo))
                        System.out.println("Producto modificado correctamente.");
                    else
                        System.out.println("No se encontró el producto.");
                }
                case 4 -> lista.mostrarProductos();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }
}
