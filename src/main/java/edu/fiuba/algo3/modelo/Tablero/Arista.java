package edu.fiuba.algo3.modelo.Tablero;

public class Arista {
    private final Vertice extremo1;
    private final Vertice extremo2;

    public Arista(Vertice a, Vertice b){
        this.extremo1 = a;
        this.extremo2 = b;
    }

    public Vertice otroExtremo(Vertice vertice) {
        if (vertice == extremo1) return extremo2;
        if (vertice == extremo2) return extremo1;
        throw new IllegalStateException("El vertice no pertenece a la arista");
    }
}
