package Entidad;

public class ArbolProductos {
    private NodoArbol raiz;

    public ArbolProductos() {
        this.raiz = null;
    }

    // Insertar producto en el árbol (por nombre)
    public void insertar(Producto producto) {
        raiz = insertarRec(raiz, producto);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Producto producto) {
        if (nodo == null) {
            return new NodoArbol(producto);
        }

        // Comparar por nombre
        if (producto.getNombre().compareToIgnoreCase(nodo.getProducto().getNombre()) < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), producto));
        } else if (producto.getNombre().compareToIgnoreCase(nodo.getProducto().getNombre()) > 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), producto));
        }

        return nodo;
    }

    // Buscar producto por nombre
    public Producto buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Producto buscarRec(NodoArbol nodo, String nombre) {
        if (nodo == null) return null;

        if (nombre.equalsIgnoreCase(nodo.getProducto().getNombre())) {
            return nodo.getProducto();
        }

        if (nombre.compareToIgnoreCase(nodo.getProducto().getNombre()) < 0) {
            return buscarRec(nodo.getIzquierdo(), nombre);
        } else {
            return buscarRec(nodo.getDerecho(), nombre);
        }
    }

    // Mostrar todos los productos (in-order)
    public void mostrarInventario() {
        mostrarRec(raiz);
    }

    private void mostrarRec(NodoArbol nodo) {
        if (nodo != null) {
            mostrarRec(nodo.getIzquierdo());
            System.out.println(nodo.getProducto());
            mostrarRec(nodo.getDerecho());
        }
    }
}