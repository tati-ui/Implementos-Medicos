package Entidad;

import java.util.ArrayList;

/**
 * Representa un producto dentro del inventario de implementos médicos.
 */
public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;

    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public ArrayList<String> getListaImagenes() { return listaImagenes; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    /** Agrega una imagen a la lista de imágenes */
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", cantidad=" + cantidad +
                ", imagenes=" + listaImagenes +
                '}';
    }
}
