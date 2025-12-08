package Entidad;

/**
 * Nodo que contiene un objeto Producto y una referencia al siguiente nodo.
 */
public class NodoProducto {
    Producto dato;
    NodoProducto siguiente;

    public NodoProducto(Producto dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
