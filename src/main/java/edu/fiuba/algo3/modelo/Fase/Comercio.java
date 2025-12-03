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

    public void iniciarFase(Jugador jugadorActual){
        this.jugadorActual = jugadorActual;
        this.banca = Banca.getInstance();
    }

    public Oferta crearOfertaJugador(Jugador receptor, List<Recurso> recursosOfrecidos, List<Recurso> recursosRequeridos){
        Intercambio tipoIntercambio = new EntreJugadores(jugadorActual,receptor,recursosOfrecidos,recursosRequeridos);
        return new Oferta(tipoIntercambio);
    }
    public void crearOfertaBanca(Tasa tasaDeComercio, List<Recurso> ofrecidos, Recurso requerido){
        Intercambio tipoDeIntercambio = new Bancario(jugadorActual, banca, ofrecidos, requerido, tasaDeComercio);
        tipoDeIntercambio.intercambio();
    }
    public void comprarCartas(List<Carta> cartas) {
        for (Carta carta : cartas) {
            carta.agregarse(jugadorActual);
        }
    }
}
