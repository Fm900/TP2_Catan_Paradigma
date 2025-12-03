package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente;
import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Exception.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Monopolio;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
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
    Banca banca;
    Juego juego;
    List<Carta> cartas;

    @BeforeEach
    public void setUp() {
        Juego.reset();
        Banca.reset();
        List<Recurso> listaRecursos = (List.of(new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo(), new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo(), new Madera(), new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo(), new Madera()));
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        recursos = new MazoDeRecursos(listaRecursos);
        jugador1 = new Jugador(recursos, mano, "Hola");
        jugador2 = new Jugador(recursos, mano, "Hola");
        vertice1.conectarConVertice(vertice2);
        cartas = List.of(new Monopolio(new Deshabilitado()));

        banca = Banca.crearBanca(listaRecursos ,cartas);
        juego = Juego.crearInstancia(List.of(jugador1, jugador2), List.of(new Dados()), List.of(new PrimerTurno()), new Tablero(), banca);

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
        Vertice vertice3 = new Vertice();
        vertice1.construirPobladoInicial(jugador1);
        vertice1.conectarConVertice(vertice2);
        List<Arista> aristas = vertice1.aristas();
        Arista arista = aristas.get(0);
        arista.construirCarretera(jugador1);
        vertice3.conectarConVertice(vertice2);
        List<Arista> aristas2 = vertice3.aristas();
        Arista arista2 = aristas2.get(0);
        arista2.construirCarretera(jugador1);

        assertDoesNotThrow(() -> vertice3.construirPoblado(jugador1));
    }
    @Test
    public void noSePuedeConstruirSiEstaOcupado(){
        vertice2.construirPobladoInicial(jugador2);
        assertThrows(VerticeOcupadoNoPuedeConstruir.class, ()-> vertice2.construirPobladoInicial(jugador2), "No puedes construir, el vertice ya esta ocupado");
    }
}