package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Fase.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;


import java.util.List;

public class ConstructorJuego {
    private final Tablero tablero;
    public ConstructorJuego(String path) {
        this.tablero = new Tablero();
    }
    public Juego construirJuegoCon(List<String> nombreJugadores){
        List<Jugador> jugadores = new ConstructorJugadores(nombreJugadores).crearJugadores();
        Banca banca = new ConstructorBanca().crearBanca();
        List<FasePrincipal> principales = List.of(new Dados(), new Comercio(), new Construccion());
        List<FaseInicial > iniciales = List.of(new PrimerTurno(), new SegundoTurno());
        return new Juego(jugadores,principales, iniciales,tablero,banca);
    }
}
