package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Especifico implements Intercambio {
    private Jugador jugador;
    private Banca banca;
    private List<Recurso> recursosOfrecidos;
    private Recurso recursoRequerido;

    @Override
    public void intercambio() {
        jugador.consumirRecursos(recursosOfrecidos);
        banca.agregarRecurso(recursosOfrecidos);
        banca.consumirRecursos(recursoRequerido);
        jugador.agregarRecurso(recursoRequerido,1);
    }
    public Especifico(Jugador jugadorActual, Banca banca, Recurso recursoDelPuerto, Recurso recursoRequerido) {
        this.jugador = jugadorActual;
        this.banca = banca;
        this.recursosOfrecidos = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            this.recursosOfrecidos.add(recursoDelPuerto);
        }
        this.recursoRequerido = recursoRequerido;
    }
}
