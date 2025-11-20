package edu.fiuba.algo3.modelo.Tablero.Terreno;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Terreno {
    private Recurso recurso;
    private Integer fichaNumero;
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
    public List<Jugador> obtenerHabitantes(){
        List<Jugador> propietarios = new ArrayList<>();
        if (this.verticesAdyacentes == null) { return propietarios; }

        for (Vertice v : this.verticesAdyacentes) {
            propietarios = v.agregarPropietario(propietarios);
        }

        return propietarios;
    }
    public String obtenerRecurso() {
        return this.recurso;
    }
    public boolean tieneVertice(Vertice v) {
        return verticesAdyacentes != null && this.verticesAdyacentes.contains(v);
    }
}

