package edu.fiuba.algo3.modelo.Turnos.Fase;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;

import java.util.List;

public class Comercio implements Fase {
    private Jugador jugadorActual;
    private final Banca banca;
    private Intercambio intercambioActual;

    public Comercio() {
        this.banca = Banca.getInstance();
    }

    @Override
    public void ejecutar(Jugador jugador, ManejoTurnos manejador) {
        this.jugadorActual = jugador;
    }

    public Oferta crearOfertaJugador(Jugador receptor, List<Recurso> recursosOfrecidos, List<Recurso> recursosRequeridos) {
        if (jugadorActual == null) {
            throw new IllegalStateException("La fase de comercio no ha sido inicializada");
        }

        intercambioActual = new EntreJugadores(jugadorActual, receptor, recursosOfrecidos, recursosRequeridos);
        intercambioActual.intercambio();
        return new Oferta(intercambioActual);
    }

    public void aceptarOferta(Oferta oferta) {
        if (oferta != null) {
            oferta.aceptar();
        }
    }

    public void rechazarOferta(Oferta oferta) {
        if (oferta != null) {
            oferta.declinar();
        }
    }

    public void comerciarConBanca(Tasa tasaDeComercio, List<Recurso> ofrecidos, Recurso requerido) {
        intercambioActual = new Bancario(jugadorActual, banca, ofrecidos, requerido, tasaDeComercio);
    }


    public void comprarCarta() {
        intercambioActual = new ComprarCartas(jugadorActual);
        intercambioActual.intercambio();
    }

    public void efectuarComercio(){

    }

    public void terminarFase(ManejoTurnos manejador) {
        manejador.pasarSiguienteFase();
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
}