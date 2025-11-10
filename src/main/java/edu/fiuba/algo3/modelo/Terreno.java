package edu.fiuba.algo3.modelo;

import java.util.List;

public class Terreno {
    private String recurso;
    private Integer fichaNumero;
    private EstadoProductivo estadoProductivo;
    private List<Vertice> vertices_adyacentes;

    public Terreno(String recurso, Integer fichaNumero, EstadoProductivo estadoProductivo){
        this.recurso = recurso;
        this.fichaNumero = fichaNumero;
        this.estadoProductivo = estadoProductivo;
    }

    public void producirSiCorresponde(int numero) {
        if (this.correspondeA(numero)) {
            this.estadoProductivo.producir(this.recurso,this.vertices_adyacentes);
        }
    }

    public void asignarVerticesAdyacentes(List<Vertice> vertices_adyacentes) {
        this.vertices_adyacentes = vertices_adyacentes;
    }

    public boolean correspondeA(int valor) {
         return fichaNumero == valor;
    }

}
