package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Intercambio.Bancario;
import edu.fiuba.algo3.modelo.Intercambio.BancarioSinEntregarRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

import java.util.List;

public class Descubrimiento extends Carta{

    BancarioSinEntregarRecursos intercambio;

    public Descubrimiento(Efecto activacion) {
        super((List.of(new Lana(), new Grano(), new Mineral())));
    }

    public void activarEfecto(Jugador jugador, ParametrosCarta parametros) {
        List<Recurso> recursosElegidos = parametros.getRecursosElegidos();
        intercambio = new BancarioSinEntregarRecursos(recursosElegidos, jugador);
        jugador.descartarCarta(this);
    }

}

