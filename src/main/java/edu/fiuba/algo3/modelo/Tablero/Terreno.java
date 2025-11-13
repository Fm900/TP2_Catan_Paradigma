package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class Terreno {
    private String recurso;
    private Integer fichaNumero;
    private EstadoProductivo estadoProductivo;
    private List<Vertice> verticesAdyacentes;

    public Terreno(String recurso, Integer fichaNumero, EstadoProductivo estadoProductivo){
        this.recurso = recurso;
        this.fichaNumero = fichaNumero;
        this.estadoProductivo = estadoProductivo;
    }

    public void producirSiCorresponde(int numero) {
        if (this.correspondeA(numero)) {
            this.estadoProductivo.producir(this.recurso,this.verticesAdyacentes);
        }
    }

    public void asignarVerticesAdyacentes(List<Vertice> verticesAdyacentes) {
        this.verticesAdyacentes = verticesAdyacentes;
    }

    public boolean correspondeA(int valor) {
         return fichaNumero == valor;
    }

}
