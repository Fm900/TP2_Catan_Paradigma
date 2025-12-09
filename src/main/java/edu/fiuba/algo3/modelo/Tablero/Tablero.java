package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Constructores.ConstructorTablero;
import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Puerto;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {
    private List<Terreno> terrenos;
    private List<Vertice> vertices;
    private List<Arista> aristas;
    private List<Puerto> puertos;

    public Tablero(List<Terreno> terrenos, List<Vertice> vertices, List<Arista> aristas, List<Puerto> puertos) {
        this.terrenos = terrenos;
        this.vertices = vertices;
        this.aristas = aristas;
        this.puertos = puertos;
    }

    public Tablero(List<Terreno> terrenos, List<Vertice> vertices, List<Arista> aristas) {
        this(terrenos, vertices,aristas, new ArrayList<>());
    }

    public Tablero(GeneradorDeTerrenos generador) {
        this.terrenos = generador.generar();
    }

    public void colocarCarretera(Jugador jugador, Arista arista) {
        arista.construirCarretera(jugador);
        Juego.getInstancia().calcularCaminoMasLargo(jugador);
    }

    public void colocarPobladoInicial(Jugador jugador, Vertice vertice){
        vertice.construirPobladoInicial(jugador);
    }

    public void colocarPoblado(Jugador jugador, Vertice vertice){
        vertice.construirPoblado(jugador);
    }

    public void mejorarPobladoACiudad(Jugador jugador, Vertice vertice) {
        vertice.mejorarPobladoACiudad(jugador);
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


    public Arista arista(int id) {
        return this.aristas.get(id - 1); // ids 1..72
    }

    public List<Vertice> vertices() {
        return Collections.unmodifiableList(vertices);
    }

    public List<Arista> aristas() {
        return Collections.unmodifiableList(aristas);
    }

    public List<Terreno> terrenos() {
        return Collections.unmodifiableList(terrenos);
    }

    public int calcularCaminoMasLargo(Jugador jugador) {
        int max = 0;
        for (Arista arista : aristas) {
            if (arista.elMismoDueño(jugador)) {
                max = Math.max(max, calcularCaminoMasLargoJugador(arista.extremo1(), jugador, new ArrayList<>()));
                max = Math.max(max, calcularCaminoMasLargoJugador(arista.extremo2(), jugador, new ArrayList<>()));
            }
        }
        return max;
    }

    private int calcularCaminoMasLargoJugador(Vertice actual, Jugador jugador, List<Arista> usadas) {
        int max = 0;

        for (Arista a : actual.aristas()) {

            if (!a.elMismoDueño(jugador)) continue;

            if (usadas.contains(a)) continue;

            Vertice prox = a.otroExtremo(actual);
            if (verticesBloquean(actual, prox, jugador)) continue;

            usadas.add(a);

            int largo = 1 + calcularCaminoMasLargoJugador(prox, jugador, usadas);
            max = Math.max(max, largo);

            usadas.remove(a);
        }

        return max;
    }

    private boolean verticesBloquean(Vertice v1, Vertice v2, Jugador jugador) {
        return (v1.getDueño() != null && v1.getDueño() != jugador)
                || (v2.getDueño() != null && v2.getDueño() != jugador);
    }

    public List<Puerto> puertos() {
        return Collections.unmodifiableList(puertos);
    }
}
