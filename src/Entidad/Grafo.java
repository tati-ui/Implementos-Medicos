package Entidad;

import java.util.*;

public class Grafo {

    private final Map<String, List<Arista>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarVertice(String nuevaVertice) {
        listaAdyacencia.putIfAbsent(nuevaVertice, new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, int peso) {
        listaAdyacencia.putIfAbsent(origen, new ArrayList<>());
        listaAdyacencia.putIfAbsent(destino, new ArrayList<>());

        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    public void mostrarGrafo() {
        System.out.println("\n--- Grafo de Ubicaciones ---");
        for (Map.Entry<String, List<Arista>> entry : listaAdyacencia.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Arista arista : entry.getValue()) {
                System.out.print(arista.getDestino() + "(" + arista.getPeso() + ")  ");
            }
            System.out.println();
        }
    }


    public void algoritmoDijkstra(String inicio,
                                  Map<String, Integer> distancias,
                                  Map<String, String> predecesores) {

        PriorityQueue<Vertice> colaVertices =
                new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        for (String v : listaAdyacencia.keySet()) {
            distancias.put(v, Integer.MAX_VALUE);
            predecesores.put(v, null);
        }

        distancias.put(inicio, 0);
        colaVertices.add(new Vertice(inicio, 0));

        while (!colaVertices.isEmpty()) {
            Vertice actualVertice = colaVertices.poll();
            String actual = actualVertice.getNombre();

            for (Arista arista : listaAdyacencia.get(actual)) {
                String vecino = arista.getDestino();
                int nuevaDistancia = distancias.get(actual) + arista.getPeso();

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, actual);
                    colaVertices.add(new Vertice(vecino, nuevaDistancia));
                }
            }
        }
    }

    public List<String> reconstruirCamino(String inicio, String destino,
                                          Map<String, String> predecesores) {

        List<String> camino = new ArrayList<>();

        for (String actual = destino; actual != null; actual = predecesores.get(actual)) {
            camino.add(actual);
        }

        Collections.reverse(camino);

        if (!camino.isEmpty() && camino.get(0).equals(inicio))
            return camino;

        return new ArrayList<>();
    }


    public DijkstraResult dijkstraAdaptado(String inicio, String destino) {

        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();

        algoritmoDijkstra(inicio, distancias, predecesores);

        if (!distancias.containsKey(destino) ||
                distancias.get(destino) == Integer.MAX_VALUE) {
            return new DijkstraResult(new ArrayList<>(), Double.POSITIVE_INFINITY);
        }

        List<String> camino = reconstruirCamino(inicio, destino, predecesores);
        int distancia = distancias.get(destino);

        return new DijkstraResult(camino, distancia);
    }
}
