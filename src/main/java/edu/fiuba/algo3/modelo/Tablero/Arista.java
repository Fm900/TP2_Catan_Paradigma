package edu.fiuba.algo3.modelo.Tablero;

public class Arista {
    private final Vertice a;
    private final Vertice b;

    public Arista(Vertice a, Vertice b){
        this.a = a;
        this.b = b;
    }

    public Vertice otroExtremo(Vertice vertice) {
        if (vertice == a) return b;
        if (vertice == b) return a;
        throw new IllegalStateException("El vertice no pertenece a la arista");
    }
}
