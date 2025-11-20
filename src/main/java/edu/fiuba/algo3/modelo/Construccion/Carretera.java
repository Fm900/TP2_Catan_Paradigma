package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import java.util.List;
import java.util.ArrayList;

public class Carretera extends  Construccion{

    public Carretera(Integer puntosDeVictoria, int coeficienteDeProduccion, Jugador dueño) {
        super(puntosDeVictoria, coeficienteDeProduccion, dueño, new ArrayList<>(List.of(new Madera(), new Ladrillo())));
    }

    public void construir() {
        this.dueño.consumirRecursos(this.precio);
    }
}
