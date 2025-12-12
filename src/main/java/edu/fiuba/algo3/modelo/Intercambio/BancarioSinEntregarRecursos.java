package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class BancarioSinEntregarRecursos implements Intercambio {

    private final List<Recurso> recursoAEntregar;
    private final Jugador jugadorAEntregar;

    public BancarioSinEntregarRecursos(List<Recurso> recurso,  Jugador jugador) {
        this.recursoAEntregar = recurso;
        this.jugadorAEntregar = jugador;
    }

    @Override
    public void intercambio() {
        for (Recurso recurso : recursoAEntregar) {
            Banca.getInstance().consumirRecursos(recurso);
            jugadorAEntregar.agregarRecurso(recurso, 1);
        }
    }
}
