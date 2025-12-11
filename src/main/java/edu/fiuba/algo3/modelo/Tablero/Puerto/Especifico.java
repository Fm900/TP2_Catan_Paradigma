package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Exception.RecursoInvalido;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Especifico implements Tasa {

    private final Recurso recursoPuerto;

    public Especifico(Recurso recurso) {
        this.recursoPuerto = recurso;
    }

    @Override
    public List<Recurso> aplicarTasa(List<Recurso> recursos, Jugador jugador) {

        if (recursos.size() != 2)
            throw new RecursoInvalido("Se deben entregar exactamente 2 recursos.");

        for (Recurso r : recursos) {
            if (!r.mismaClaseQue(recursoPuerto))
                throw new RecursoInvalido("Solo se puede comerciar con " + recursoPuerto.getClass().getSimpleName());
        }

        return recursos;
    }
}
