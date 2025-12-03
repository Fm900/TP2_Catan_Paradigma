package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;
import java.util.List;

public class Comercio implements FasePrincipal{
    private Jugador jugadorActual;
    private Banca banca;
    private Intercambio intercambio;

    public void iniciarFase(Jugador jugadorActual){
        this.jugadorActual = jugadorActual;
        this.banca = Banca.getInstance();
    }

    public Oferta crearOfertaJugador(Jugador receptor, List<Recurso> recursosOfrecidos, List<Recurso> recursosRequeridos){
        intercambio = new EntreJugadores(jugadorActual,receptor,recursosOfrecidos,recursosRequeridos);
        return new Oferta(intercambio);
    }

    public void crearOfertaBanca(Tasa tasaDeComercio, List<Recurso> ofrecidos, Recurso requerido){
        intercambio = new Bancario(jugadorActual, banca, ofrecidos, requerido, tasaDeComercio);
        intercambio.intercambio();
    }

    public void comprarCarta(Carta carta) {
        intercambio = new ComprarCartas(jugadorActual, carta);
        intercambio.intercambio();
    }
}
