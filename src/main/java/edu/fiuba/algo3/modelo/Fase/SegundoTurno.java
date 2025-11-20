package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public class SegundoTurno implements FaseInicial{
    private Vertice verticeParaConstruir;

    @Override
    public void iniciarFase(List<Jugador> jugadores, Tablero tablero) {
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador jugador = jugadores.get(i);

            tablero.colocarPoblado(jugador, this.verticeParaConstruir);

            for (Terreno terreno : tablero.obtenerTerrenosAdy(verticeParaConstruir)){
                // el jugador recibe 1 recurso por cada terreno adyacente al que construyo
                jugador.agregarRecurso(terreno.recursoInicial(),1);
            }
        }
    }

    public void setVerticeParaConstruir(Vertice vertice){
        this.verticeParaConstruir = vertice;
    }
}
