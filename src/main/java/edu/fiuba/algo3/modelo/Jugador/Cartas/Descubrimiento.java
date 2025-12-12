package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;

import java.util.List;

public class Descubrimiento extends Carta{

    public Descubrimiento(Efecto activacion) {
        super((List.of(new Lana(), new Grano(), new Mineral())));
    }

    public void activarEfecto(Jugador jugador) {
    }

}

