package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Estandar implements Intercambio {
    private Jugador jugador;
    private List<Recurso> recursosOfrecidos;
    private Recurso recursoRequerido;
    private Banca banca;

    public Estandar(Jugador unJugador, Banca banca, Recurso recursoOfrecido,int cantidad, Recurso recursoRequerido) {
        this.jugador = unJugador;
        this.recursoRequerido = recursoRequerido;
        this.recursosOfrecidos = new ArrayList<Recurso>();
        for(int i = 0; i < cantidad; i++) {
            this.recursosOfrecidos.add(recursoOfrecido);
        }
        this.banca = banca;
    }

    @Override
    public void intercambio() {
        jugador.consumirRecursos(recursosOfrecidos);
        banca.agregarRecurso(recursosOfrecidos);
        banca.consumirRecursos(recursoRequerido);
        jugador.agregarRecurso(recursoRequerido,1);

    }
}
