package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;

import java.util.List;

public class Bancario implements Intercambio{
    private final Tasa tasa;
    private final Jugador jugador;
    private final List<Recurso> ofrecidos;
    private final Recurso requerdio;

    public Bancario(Jugador jugadorActual, Banca banca, List<Recurso> ofrecidos, Recurso requerido, Tasa tasa){
        this.jugador = jugadorActual;
        this.ofrecidos = ofrecidos;
        this.requerdio = requerido;
        this.tasa = tasa;
    }
    @Override
    public void intercambio() {
        List<Recurso> recursosOfrecidos = tasa.aplicarTasa(ofrecidos, jugador);
        jugador.consumirRecursos(recursosOfrecidos);
        Banca.getInstance().agregarRecurso(recursosOfrecidos);
        Banca.getInstance().consumirRecursos(requerdio);
        jugador.agregarRecurso(requerdio,1);
    }
}
