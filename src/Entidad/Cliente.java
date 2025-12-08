package Entidad;

/**
 * Representa un cliente dentro de la tienda.
 * Cada cliente tiene:
 * - Nombre
 * - Prioridad (1 básico, 2 afiliado, 3 premium)
 * - Ubicación geográfica (necesaria para el grafo)
 * - Carrito con productos (lista enlazada)
 */
public class Cliente {

    private String nombre;
    private int prioridad;
    private String ubicacion;
    private ListaProductos carrito;

    /**
     * Constructor principal.
     * Se usa cuando el cliente se registra y se le asigna una ubicación.
     */
    public Cliente(String nombre, int prioridad, String ubicacion) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.ubicacion = ubicacion;
        this.carrito = new ListaProductos();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    /**
     * Agrega un producto al carrito del cliente.
     * El carrito está implementado como una lista enlazada simple.
     */
    public void agregarAlCarrito(Producto producto) {
        carrito.insertarFinal(producto);
    }

    /**
     * Calcula el total a pagar basado en los productos del carrito.
     */
    public double calcularTotal() {
        return carrito.calcularTotal();
    }
}
