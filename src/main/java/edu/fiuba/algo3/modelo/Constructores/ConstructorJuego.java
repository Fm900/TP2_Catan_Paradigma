package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Turnos.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.Comercio;
import edu.fiuba.algo3.modelo.Turnos.Fase.Construccion;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Turnos.Fase.Fase;


import java.util.List;

public class ConstructorJuego {
    public Juego construirJuegoCon(List<String> nombreJugadores){
        List<Jugador> jugadores = new ConstructorJugadores(nombreJugadores).crearJugadores();

        Banca banca = new ConstructorBanca().crearBanca();

        ConstructorTablero ctor = new ConstructorTablero();
        List<Terreno> terrenos = ctor.generarTerrenos();
        List<Vertice> vertices = ctor.generarVertices();
        List<Arista> aristas = ctor.generarAristas();
        Tablero tablero = new Tablero(terrenos, vertices, aristas);
        return Juego.crearInstancia(jugadores,tablero,banca);
    }
}
