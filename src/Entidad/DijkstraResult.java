package Entidad;

import java.util.List;

/**
 * Clase auxiliar usada como resultado del algoritmo Dijkstra.
 * Contiene:
 * - El camino más corto desde origen hasta destino.
 * - La distancia total del recorrido.
 */
public class DijkstraResult {
    private List<String> camino;
    private double distanciaTotal;

    public DijkstraResult(List<String> camino, double distanciaTotal) {
        this.camino = camino;
        this.distanciaTotal = distanciaTotal;
    }

    public List<String> getCamino() {
        return camino;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }
}
