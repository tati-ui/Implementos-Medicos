package Entidad;

/**
 * Implementa una lista enlazada simple para manejar los productos.
 */
public class ListaProductos {
    private NodoProducto cabeza;

    public ListaProductos() {
        this.cabeza = null;
    }

    /** Inserta un producto al inicio de la lista */
    public void insertarInicio(Producto producto) {
        NodoProducto nuevo = new NodoProducto(producto);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    /** Inserta un producto al final de la lista */
    public void insertarFinal(Producto producto) {
        NodoProducto nuevo = new NodoProducto(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    /** Modifica un producto por nombre */
    public boolean modificarProducto(String nombre, Producto nuevoProducto) {
        NodoProducto aux = cabeza;
        while (aux != null) {
            if (aux.dato.getNombre().equalsIgnoreCase(nombre)) {
                aux.dato = nuevoProducto;
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    /** Recorre la lista y muestra los productos */
    public void mostrarProductos() {
        NodoProducto aux = cabeza;
        double total = 0;
        while (aux != null) {
            Producto p = aux.dato;
            double subtotal = p.getPrecio() * p.getCantidad();
            System.out.println(p);
            System.out.println("Subtotal: ₡" + subtotal + "\n");
            total += subtotal;
            aux = aux.siguiente;
        }
        System.out.println("Costo total del inventario: ₡" + total);
    }
}
