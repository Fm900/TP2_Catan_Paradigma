package edu.fiuba.algo3.entrega_2;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class VerificarElConsumoDeRecursosYLaCorrectaColocaciónDeUnaCarretera {


    @Test
    public void test01VerificarConsumoDeRecursosAlConstruirUnaCarretera(){
        List<Recurso> recursosIniciales = new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo()));
        List<Recurso> precioCarretera = List.of(new Madera(), new Ladrillo());

        MazoDeRecursos gestor = new MazoDeRecursos(recursosIniciales);
        Mano mano = new Mano();
        Jugador jugador = new Jugador(gestor, mano, "El chaqueño palavecino");
        Vertice vertice1 = new Vertice(1,0,0);
        Vertice vertice2 = new Vertice(2,0,0);
        Arista arista = new Arista(vertice1, vertice2, new Vacia());
        List<Carta> cartas = new ArrayList<>();
        Juego.crearInstancia(List.of(jugador), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));
        assertDoesNotThrow(() -> vertice1.construirPobladoInicial(jugador), "Deberia poder construir un poblado inicial con los recursos disponibles");
        assertDoesNotThrow(() -> arista.construirCarretera(jugador), "Deberia poder construirse una carretera conectada a un vertice del jugador");
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precioCarretera), "Tras construir una carretera no deberia poder pagar otra");
        assertThrows(RuntimeException.class, () -> arista.construirCarretera(jugador), "No se puede construir otra carretera sobre una arista ya ocupada");



    }
}