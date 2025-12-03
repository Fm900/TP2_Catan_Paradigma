package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class BancarioSinEntregarRecursos implements Intercambio {

    private final List<Recurso> recursoAEntregar;
    private final Jugador jugadorAEntregar;
    private final Banca banca;

    public BancarioSinEntregarRecursos(List<Recurso> recurso,  Jugador jugador) {
        this.recursoAEntregar = recurso;
        this.jugadorAEntregar = jugador;
        this.banca = Banca.getInstance();
    }

    @Override
    public void intercambio() {
        for (Recurso recurso : recursoAEntregar) {
            banca.consumirRecursos(recurso);
            jugadorAEntregar.agregarRecurso(recurso, 1);
        }
    }
}
