package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Constructores.ConstructorTablero;
import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<Terreno> terrenos;
    private List<Vertice> vertices;
    private List<Arista> aristas;

    public Tablero() {
        ConstructorTablero constructor = new ConstructorTablero();

        this.terrenos = constructor.generarTerrenos();
        this.vertices = constructor.generarVertices();
        this.aristas  = constructor.generarAristas(vertices);
        constructor.asignarVerticesATerrenos(terrenos, vertices);
    }

    public void colocarCarretera(Jugador jugador, Arista arista) {
        arista.construirCarretera(jugador);
    }

    public Tablero(GeneradorDeTerrenos generador) {
        this.terrenos = generador.generar();
    }

    public void mejorarPobladoACiudad(Jugador jugador, Vertice vertice) {
        vertice.mejorarPobladoACiudad(jugador);
    }

    public void colocarPobladoInicial(Jugador jugador, Vertice vertice){
        vertice.construirPobladoInicial(jugador);
    }

    public void colocarPoblado(Jugador jugador, Vertice vertice){
        vertice.construirPoblado(jugador);
    }

    public void producirPara(int tirada){
        for (Terreno t : terrenos) {
            t.producirSiCorresponde(tirada);
        }
    }
    public List<Terreno> obtenerTerrenosAdy(Vertice vertice){
        List<Terreno> terrenosAdy = new ArrayList<>();
        for (Terreno terreno : terrenos){
            if(terreno.tieneVertice(vertice)){
                terrenosAdy.add(terreno);
            }
        }
        return terrenosAdy;
    }

    public Vertice vertice(int id) {
        return this.vertices.get(id - 1);
    }

    public Arista aristaEntre(Vertice v1, Vertice v2) {
        for (Arista arista : v1.aristas()) {
            if (arista.otroExtremo(v1) == v2) {
                return arista;
            }
        }
        throw new IllegalArgumentException("v1 y v2 no están conectados por una arista");
    }
    public Arista arista(int id) {
        return this.aristas.get(id - 1); // ids 1..72
    }

    public List<Vertice> vertices() {
        return List.copyOf(vertices);
    }

    public List<Arista> aristas() {
        return List.copyOf(aristas);
    }
    public List<Terreno> terrenos() {
        return List.copyOf(terrenos);
    }
}
