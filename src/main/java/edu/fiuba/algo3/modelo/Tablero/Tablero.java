package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<Terreno> terrenos = new ArrayList<>();

    public Tablero() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        this.terrenos = generador.generar();
    }

    public void colocarCarretera(Jugador jugador, Arista arista) {
        arista.construirCarretera(jugador);
    }

    public Tablero(GeneradorDeTerrenos generador) {
        this.terrenos = generador.generar();
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
}
