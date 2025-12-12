package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class EntreJugadores implements Intercambio {
    private final Jugador ofertante;
    private final Jugador receptor;
    private final List<Recurso> recursosOfrecidos;
    private final List<Recurso> recursosRequeridos;
    public EntreJugadores(Jugador j1, Jugador j2,List<Recurso> recursosOfrecidos, List<Recurso> recursosRequeridos){
        this.ofertante = j1;
        this.receptor = j2;
        this.recursosOfrecidos = recursosOfrecidos;
        this.recursosRequeridos = recursosRequeridos;
    }
    @Override
    public void intercambio() {
        // Transferir recursos requeridos del receptor al ofertante

        // Transferir recursos ofrecidos del ofertante al receptor
        ofertante.consumirRecursos(recursosOfrecidos);
        for (Recurso recurso : recursosOfrecidos) {
            receptor.agregarRecurso(recurso, 1);
        }
        receptor.consumirRecursos(recursosRequeridos);
        for (Recurso recurso : recursosRequeridos) {
            ofertante.agregarRecurso(recurso, 1);
        }
    }

}
