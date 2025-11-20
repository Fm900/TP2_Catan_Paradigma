package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.NoTieneRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumoDeRecursosYCambioDePVAlMejorarACiudad {

    private Jugador nuevoJugador() {
        GestorDeRecursos gestor = new GestorDeRecursos(new ArrayList<>());
        Mano mano = new Mano();
        return new Jugador(gestor, mano);
    }

    private void darRecursosParaUnPobladoYCiudad(Jugador jugador) {
        jugador.agregarRecurso(new Madera(), 1);
        jugador.agregarRecurso(new Ladrillo(), 1);
        jugador.agregarRecurso(new Grano(), 1);
        jugador.agregarRecurso(new Lana(), 1);

        jugador.agregarRecurso(new Grano(), 2);
        jugador.agregarRecurso(new Mineral(), 3);
    }



    @Test
    void mejorarPobladoACiudadConsumeRecursos() {
        Jugador jugador = nuevoJugador();
        darRecursosParaUnPobladoYCiudad(jugador);

        Vertice vertice = new Vertice();

        // Construyo el poblado
        assertDoesNotThrow(() -> vertice.construirPoblado(jugador));

        // Mejoro a ciudad
        assertDoesNotThrow(() -> vertice.mejorarPobladoACiudad(jugador));

        // si intento mejorar otra vez, no debería poder por falta de recursos
        assertThrows(RuntimeException.class, () -> vertice.mejorarPobladoACiudad(jugador));
    }

}
    //me falta probar los PV