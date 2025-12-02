package edu.fiuba.algo3.entrega_2;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
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
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Arista arista = new Arista(vertice1, vertice2, new Vacia());

        assertDoesNotThrow(() -> vertice1.construirPobladoInicial(jugador), "Deberia poder construir un poblado inicial con los recursos disponibles");
        assertDoesNotThrow(() -> arista.construirCarretera(jugador), "Deberia poder construirse una carretera conectada a un vertice del jugador");
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precioCarretera), "Tras construir una carretera no deberia poder pagar otra");
        assertThrows(RuntimeException.class, () -> arista.construirCarretera(jugador), "No se puede construir otra carretera sobre una arista ya ocupada");



    }
}