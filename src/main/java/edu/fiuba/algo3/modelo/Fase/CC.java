package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Banca;
import edu.fiuba.algo3.modelo.Intercambio.Bancario;
import edu.fiuba.algo3.modelo.Intercambio.Intercambiar;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public class CC implements FasePrincipal {
    private Jugador jugadorActual;
    private Oferta ofertaActual;
    private Bancario bancarioActual;
    private Banca banca;
    private Vertice vertice;
    private Arista arista;


    public void iniciarFase(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
        ofertaActual = null;
        bancarioActual = null;
    }
    public void crearOferta(List<Recurso>recursosRequeridos){
        this.ofertaActual = new Oferta(this.jugadorActual, recursosRequeridos);
    }
    public boolean realizarIntercambio(List<Recurso> recursosOfrecidos, Jugador receptor) {
        if (ofertaActual == null || receptor == null) {
            return false;
        }
        return ofertaActual.recibirOrferta(receptor, recursosOfrecidos);
    }
    public void intercambioConBanca(Recurso recursoRequerido ,Recurso recursoOfrecido, int taza, Banca banca){
        this.bancarioActual = new Bancario(jugadorActual, recursoRequerido, recursoOfrecido, taza, banca);
        this.bancarioActual.intercambio();
    }

    public void comprarCartas(List<Carta> cartas) {
        for (Carta carta : cartas) {
            carta.agregarse(jugadorActual);
        }
    }

    public void construirEnArista(Arista arista, Jugador jugador){

    };

    public void construirEnVertice(Vertice vertice, Jugador jugador){

    };

}
