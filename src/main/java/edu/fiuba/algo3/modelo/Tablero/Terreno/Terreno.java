package edu.fiuba.algo3.modelo.Tablero.Terreno;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Terreno {
    private final Recurso recurso;
    private final Integer fichaNumero;
    private EstadoProductivo estadoProductivo;
    private List<Vertice> verticesAdyacentes;

    public Terreno(Recurso recurso, Integer fichaNumero, EstadoProductivo estadoProductivo){
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

    public void cambiarEstado() {
        EstadoProductivo nuevoEstadoProductino = this.estadoProductivo.alterarEstado();
        this.estadoProductivo = nuevoEstadoProductino;

    }

    public boolean tieneVertice(Vertice v) {
        return this.verticesAdyacentes.contains(v);
    }

    public void producirInicial(Vertice vertice){
        vertice.entregarRecursosPorConstruccion(this.recurso);
    }

    public Recurso recursoInicial(){
        return this.recurso;
    }
    public List<Vertice> verticesAdyacentes() {
        return List.copyOf(verticesAdyacentes);
    }

    public int numeroFicha() {
        return fichaNumero;
    }

    public Recurso recurso() {
        return this.recurso;
    }

}

