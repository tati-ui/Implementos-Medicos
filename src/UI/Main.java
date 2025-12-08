package UI;

import Entidad.*;
import java.util.Scanner;

/**
 * Programa principal del sistema de Implementos Médicos.
 * Maneja:
 *  - Lista enlazada de productos
 *  - Árbol binario de búsqueda (inventario)
 *  - Cola de prioridad de clientes
 *  - Grafo de ubicaciones con Dijkstra
 */
public class Main {

    private static Tienda tienda = new Tienda();   // Contiene árbol, cola y grafo

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaProductos lista = new ListaProductos();     // Lista enlazada simple
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1 -> insertarProductoLista(sc, lista);
                case 2 -> insertarProductoLista(sc, lista);
                case 3 -> modificarProductoLista(sc, lista);
                case 4 -> lista.mostrarProductos();

                // Árbol binario
                case 5 -> agregarProductoArbol(sc);

                // Cola de prioridad
                case 6 -> agregarClienteCola(sc);
                case 7 -> atenderCliente();

                // Árbol
                case 8 -> tienda.getInventario().mostrarInventario();

                // Grafo
                case 10 -> agregarUbicacion(sc);
                case 11 -> agregarDistancia(sc);
                case 12 -> tienda.getGrafo().mostrarGrafo();
                case 13 -> calcularRuta(sc);

                // Datos precargados
                case 14 -> cargarDatosPrecargados(lista);

                case 9 -> System.out.println("Saliendo del sistema...");

                default -> System.out.println("⚠ Opción inválida.");
            }

        } while (opcion != 9);
    }


    // ==== MENÚ ===

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ DE IMPLEMENTOS MÉDICOS ===");
        System.out.println("1. Insertar producto al inicio (Lista)");
        System.out.println("2. Insertar producto al final (Lista)");
        System.out.println("3. Modificar producto (Lista)");
        System.out.println("4. Mostrar productos (Lista)");
        System.out.println("5. Agregar producto al Árbol");
        System.out.println("6. Agregar cliente a la cola de prioridad");
        System.out.println("7. Atender siguiente cliente (con Dijkstra)");
        System.out.println("8. Mostrar inventario del Árbol");
        System.out.println("9. Salir");

        System.out.println("\n--- OPCIONES DEL GRAFO ---");
        System.out.println("10. Agregar ubicación (vértice)");
        System.out.println("11. Agregar distancia entre ubicaciones (arista)");
        System.out.println("12. Mostrar grafo de ubicaciones");
        System.out.println("13. Calcular ruta más corta entre dos ubicaciones");

        System.out.println("\n--- UTILIDADES ---");
        System.out.println("14. Cargar datos precargados (productos – clientes – ubicaciones)");
    }


    // == LISTA ENLAZADA =

    private static void insertarProductoLista(Scanner sc, ListaProductos lista) {
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Precio: "); double precio = sc.nextDouble(); sc.nextLine();
        System.out.print("Categoría: "); String categoria = sc.nextLine();
        System.out.print("Fecha vencimiento: "); String fecha = sc.nextLine();
        System.out.print("Cantidad: "); int cantidad = sc.nextInt(); sc.nextLine();

        Producto p = new Producto(nombre, precio, categoria, fecha, cantidad);

        System.out.print("Insertar (1) inicio o (2) final: ");
        int tipo = sc.nextInt(); sc.nextLine();

        if (tipo == 1) lista.insertarInicio(p);
        else lista.insertarFinal(p);

        System.out.println("Producto insertado correctamente.");
    }

    private static void modificarProductoLista(Scanner sc, ListaProductos lista) {
        System.out.print("Producto a modificar: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo nombre: "); String n = sc.nextLine();
        System.out.print("Nuevo precio: "); double p = sc.nextDouble(); sc.nextLine();
        System.out.print("Nueva categoría: "); String c = sc.nextLine();
        System.out.print("Nueva fecha: "); String f = sc.nextLine();
        System.out.print("Nueva cantidad: "); int cant = sc.nextInt(); sc.nextLine();

        Producto nuevo = new Producto(n, p, c, f, cant);

        if (lista.modificarProducto(nombre, nuevo))
            System.out.println("Producto modificado.");
        else
            System.out.println("Producto no encontrado.");
    }


    // ===ÁRBOL ==

    private static void agregarProductoArbol(Scanner sc) {
        System.out.println("\n--- Agregar producto al árbol ---");

        System.out.print("Nombre: "); String n = sc.nextLine();
        System.out.print("Precio: "); double p = sc.nextDouble(); sc.nextLine();
        System.out.print("Categoría: "); String c = sc.nextLine();
        System.out.print("Fecha vencimiento: "); String f = sc.nextLine();
        System.out.print("Cantidad: "); int cant = sc.nextInt(); sc.nextLine();

        tienda.agregarProducto(new Producto(n, p, c, f, cant));

        System.out.println("Producto agregado al árbol.");
    }


    // ======= COLA ======

    private static void agregarClienteCola(Scanner sc) {

        System.out.println("\n--- Agregar cliente ---");
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Ubicación: "); String ubicacion = sc.nextLine();
        System.out.print("Prioridad (1-3): "); int prioridad = sc.nextInt(); sc.nextLine();

        Cliente cliente = new Cliente(nombre, prioridad, ubicacion);

        llenarCarrito(cliente, sc);

        tienda.agregarCliente(cliente);
        System.out.println("Cliente agregado.");
    }

    private static void llenarCarrito(Cliente c, Scanner sc) {
        char seguir;

        do {
            System.out.print("Producto a agregar: ");
            String nombre = sc.nextLine();

            Producto p = tienda.getInventario().buscar(nombre);

            if (p != null) c.agregarAlCarrito(p);
            else System.out.println("Producto no encontrado.");

            System.out.print("Añadir otro? (s/n): ");
            seguir = sc.nextLine().charAt(0);

        } while (seguir == 's' || seguir == 'S');
    }

    private static void atenderCliente() {

        if (!tienda.getColaClientes().hayClientes()) {
            System.out.println("No hay clientes en cola.");
            return;
        }

        Cliente c = tienda.atenderCliente();
        String origen = c.getUbicacion();
        String destino = tienda.getUbicacion(); // ubicación de la tienda

        DijkstraResult res = tienda.getGrafo().dijkstraAdaptado(origen, destino);

        if (res.getDistanciaTotal() == Double.POSITIVE_INFINITY) {
            System.out.println("No existe ruta hacia la tienda.");
            return;
        }

        System.out.println("\n=== FACTURA ===");
        System.out.println("Cliente: " + c.getNombre());
        System.out.println("Ruta más corta: " + res.getCamino());
        System.out.println("Distancia total: " + res.getDistanciaTotal() + " km");

        System.out.println("\nProductos comprados:");
        c.getCarrito().mostrarProductos();

        System.out.println("TOTAL A PAGAR: " + c.calcularTotal());
    }


    // ===== GRAFO =====

    private static void agregarUbicacion(Scanner sc) {
        System.out.print("Nueva ubicación: ");
        String lugar = sc.nextLine();
        tienda.getGrafo().agregarVertice(lugar);
        System.out.println("Ubicación agregada.");
    }

    private static void agregarDistancia(Scanner sc) {
        System.out.print("Origen: ");
        String o = sc.nextLine();

        System.out.print("Destino: ");
        String d = sc.nextLine();

        System.out.print("Distancia (km): ");
        int km = sc.nextInt();
        sc.nextLine();

        tienda.getGrafo().agregarArista(o, d, km);
        System.out.println("Distancia registrada.");
    }

    private static void calcularRuta(Scanner sc) {
        System.out.println("\n--- Cálculo de ruta ---");

        System.out.print("Origen: ");
        String origen = sc.nextLine();

        System.out.print("Destino: ");
        String destino = sc.nextLine();

        DijkstraResult r = tienda.getGrafo().dijkstraAdaptado(origen, destino);

        if (r.getDistanciaTotal() == Double.POSITIVE_INFINITY) {
            System.out.println("No existe conexión entre esas ubicaciones.");
        } else {
            System.out.println("Camino más corto: " + r.getCamino());
            System.out.println("Distancia: " + r.getDistanciaTotal() + " km");
        }
    }


    // ======================= DATOS PRECARGADOS ========================

    private static void cargarDatosPrecargados(ListaProductos lista) {

        System.out.println("\n=== CARGANDO DATOS PREDEFINIDOS ===");


        // 1. UBICACIONES DEL GRAFO

        tienda.getGrafo().agregarVertice("San José");
        tienda.getGrafo().agregarVertice("Heredia");
        tienda.getGrafo().agregarVertice("Cartago");
        tienda.getGrafo().agregarVertice("Alajuela");

        tienda.getGrafo().agregarArista("San José", "Heredia", 10);
        tienda.getGrafo().agregarArista("San José", "Cartago", 17);
        tienda.getGrafo().agregarArista("Heredia", "Alajuela", 12);
        tienda.getGrafo().agregarArista("Cartago", "Alajuela", 30);


        // 2. PRODUCTOS EN EL ÁRBOL

        tienda.agregarProducto(new Producto("Mascarilla", 350, "Protección", "2026-01-01", 100));
        tienda.agregarProducto(new Producto("Guantes", 500, "Protección", "2026-05-12", 200));
        tienda.agregarProducto(new Producto("Alcohol", 900, "Desinfección", "2027-03-22", 150));


        // 3. PRODUCTOS EN LA LISTA ENLAZADA

        lista.insertarFinal(new Producto("Vendas", 250, "Curación", "2026-04-15", 80));
        lista.insertarFinal(new Producto("Tijeras", 1200, "Herramienta", "2030-07-07", 45));


        // 4. CLIENTES PRECARGADOS

        Cliente c1 = new Cliente("Tatiana", 3, "Heredia");
        Cliente c2 = new Cliente("Esteban", 2, "San José");
        Cliente c3 = new Cliente("Andrey", 1, "Cartago");

        c1.agregarAlCarrito(new Producto("Mascarilla", 350, "Protección", "2026-01-01", 2));
        c2.agregarAlCarrito(new Producto("Alcohol", 900, "Desinfección", "2027-03-22", 1));
        c3.agregarAlCarrito(new Producto("Guantes", 500, "Protección", "2026-05-12", 3));

        tienda.agregarCliente(c1);
        tienda.agregarCliente(c2);
        tienda.agregarCliente(c3);

        System.out.println("✔ Datos precargados correctamente.\n");
    }
}