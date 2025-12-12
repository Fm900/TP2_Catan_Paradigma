package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;
import java.util.ArrayList;

public class Ciudad extends Construccion{
    public Ciudad(Integer puntosDeVictoria, int coeficienteDeProduccion, Jugador dueño) {
        super(puntosDeVictoria, coeficienteDeProduccion, dueño,new  ArrayList<Recurso>(List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral())));
    }

    @Override
    public void construir() {
        this.dueño.consumirRecursos(this.precio);
        this.dueño.sumarPuntos(this.puntosDeVictoria);
    }
}
