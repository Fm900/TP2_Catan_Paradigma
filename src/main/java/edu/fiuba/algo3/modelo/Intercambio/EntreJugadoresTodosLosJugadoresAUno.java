package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.List;

public class EntreJugadoresTodosLosJugadoresAUno implements Intercambio {
    private final Jugador victimario;
    private final List<Jugador> victimas;
    private final Recurso recursoElegido;

    public EntreJugadoresTodosLosJugadoresAUno(Jugador victimario, List<Jugador> victimas,  Recurso recursoElegido) {
        this.victimario = victimario;
        victimas.remove(victimario);
        this.victimas = victimas;
        this.recursoElegido = recursoElegido;
    }

    @Override
    public void intercambio() {
        for(Jugador jugador: victimas){
            jugador.consumirRecursos(List.of(recursoElegido));
            victimario.agregarRecurso(recursoElegido, 1);
        }
    }
}
