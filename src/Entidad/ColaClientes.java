package Entidad;

import java.util.LinkedList;
import java.util.Queue;

public class ColaClientes {
    private Queue<Cliente> colaNormal;
    private Queue<Cliente> colaAfiliados;
    private Queue<Cliente> colaPremium;

    public ColaClientes() {
        this.colaNormal = new LinkedList<>();
        this.colaAfiliados = new LinkedList<>();
        this.colaPremium = new LinkedList<>();
    }

    // Encolar cliente según prioridad
    public void encolar(Cliente cliente) {
        switch (cliente.getPrioridad()) {
            case 1: colaNormal.offer(cliente); break;
            case 2: colaAfiliados.offer(cliente); break;
            case 3: colaPremium.offer(cliente); break;
        }
    }

    // Atender siguiente cliente (mayor prioridad primero)
    public Cliente atenderSiguiente() {
        if (!colaPremium.isEmpty()) return colaPremium.poll();
        if (!colaAfiliados.isEmpty()) return colaAfiliados.poll();
        if (!colaNormal.isEmpty()) return colaNormal.poll();
        return null; // No hay clientes
    }

    // Verificar si hay clientes
    public boolean hayClientes() {
        return !colaNormal.isEmpty() || !colaAfiliados.isEmpty() || !colaPremium.isEmpty();
    }
}