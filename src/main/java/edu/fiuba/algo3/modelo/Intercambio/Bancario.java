package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;

import java.util.List;

public class Bancario implements Intercambio{
    private Tasa tasa;
    private Jugador jugador;
    private Banca banca;
    private List<Recurso> ofrecidos;
    private Recurso requerdio;

    public Bancario(Jugador jugadorActual, Banca banca, List<Recurso> ofrecidos, Recurso requerido, Tasa tasa){
        this.jugador = jugadorActual;
        this.banca = banca;
        this.ofrecidos = ofrecidos;
        this.requerdio = requerido;
        this.tasa = tasa;
    }
    @Override
    public void intercambio() {
        List<Recurso> recursosOfrecidos = tasa.aplicarTasa(ofrecidos, jugador);
        jugador.consumirRecursos(recursosOfrecidos);
        banca.agregarRecurso(recursosOfrecidos);
        banca.consumirRecursos(requerdio);
        jugador.agregarRecurso(requerdio,1);
    }
}
