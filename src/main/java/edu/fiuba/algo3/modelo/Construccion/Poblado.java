package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

import java.util.List;
import java.util.ArrayList;

public class Poblado extends Construccion {

    public Poblado(Integer puntosDeVictoria, int coeficienteDeProduccion, Jugador dueño) {
        super(puntosDeVictoria, coeficienteDeProduccion, dueño, new ArrayList<Recurso>(List.of(new Madera(), new Ladrillo(), new Grano(), new Lana())));
    }

    public void construir(){
        this.dueño.consumirRecursos(this.precio);
    }
}
