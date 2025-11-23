package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.List;

public class Generico implements Intercambio {
    private Jugador jugador;
    private List<Recurso> recursosOfrecidos;
    private Recurso recursoRequerido;
    private Banca banca;

    @Override
    public void intercambio() {
        jugador.consumirRecursos(recursosOfrecidos);
        banca.agregarRecurso(recursosOfrecidos);
        banca.consumirRecursos(recursoRequerido);
        jugador.agregarRecurso(recursoRequerido,1);
    }
    public Generico(Jugador jugador, Banca banca, List<Recurso> recursosOfrecidos, Recurso recursosRequeridos) {
        this.jugador = jugador;
        this.banca = banca;
        this.recursosOfrecidos = recursosOfrecidos;
        this.recursoRequerido = recursosRequeridos;
    }
}
