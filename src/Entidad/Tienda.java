package Entidad;

/**
 * Clase principal del sistema.
 * Administra:
 * - Árbol de productos (inventario)
 * - Cola de clientes por prioridad
 * - Grafo de ubicaciones
 * - Ubicación de la tienda
 */
public class Tienda {

    private ArbolProductos inventario;
    private ColaClientes colaClientes;
    private Grafo grafo;
    private String ubicacion;

    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
        this.grafo = new Grafo();
        this.ubicacion = "Tienda Central";

        grafo.agregarVertice(ubicacion);
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Inserta un producto en el árbol binario.
     */
    public void agregarProducto(Producto producto) {
        inventario.insertar(producto);
    }

    /**
     * Agrega un cliente a la cola y registra su ubicación en el grafo.
     */
    public void agregarCliente(Cliente cliente) {
        colaClientes.encolar(cliente);
        grafo.agregarVertice(cliente.getUbicacion());
    }

    public Cliente atenderCliente() {
        return colaClientes.atenderSiguiente();
    }
}
