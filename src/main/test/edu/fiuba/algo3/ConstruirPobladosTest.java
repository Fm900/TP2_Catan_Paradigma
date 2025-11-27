package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente;
import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Exception.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstruirPobladosTest {

    Vertice vertice1;
    Vertice vertice2;
    Jugador jugador1;
    Jugador jugador2;
    Arista arista;
    MazoDeRecursos recursos;
    Mano  mano;


    @BeforeEach
    public void setUp() {
        List<Recurso> listaRecursos = (List.of(new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo(), new Madera(), new Ladrillo(), new Grano(), new Lana()));
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        recursos = new MazoDeRecursos(listaRecursos);
        jugador1 = new Jugador(recursos, mano, "Hola");
        jugador2 = new Jugador(recursos, mano, "Hola");
        vertice1.conectarConVertice(vertice2);
    }

    @Test
    public void noSePuedeConstruirUnPobladoSiNingunCaminoAdyacente() {
        assertThrows(NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente.class, () -> vertice1.construirPoblado(jugador1), "No se puede construir poblado: ninguna arista habilita");

    }
    @Test
    public void noSePuedeConstruirSiOtroJugadorConstruyoEnUnVerticeAdyacente(){
        vertice2.construirPobladoInicial(jugador2);
        assertThrows(ReglaDeDistanciaNoValida.class, () -> vertice1.construirPobladoInicial(jugador1), "Vértice adyacente ocupado");
    }

    @Test
    public void sePuedeConstruirUnPobladoSiSeCumplenLasCondiciones(){
        List<Arista> aristas = vertice1.aristas();
        for(Arista arista: aristas){
            arista.construirCarretera(jugador1);

        }

        assertDoesNotThrow(() -> vertice1.construirPoblado(jugador1));
    }

    @Test
    public void noSePuedeConstruirSiEstaOcupado(){
        vertice2.construirPobladoInicial(jugador2);
        assertThrows(VerticeOcupadoNoPuedeConstruir.class, ()-> vertice2.construirPobladoInicial(jugador2), "No puedes construir, el vertice ya esta ocupado");
    }

}
