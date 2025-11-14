package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ConstruccionTest{
    @Test
    public void test01ElPobladoGeneraUnSoloRecurso() {
        Jugador jugador = mock(Jugador.class);
        Producir producir = new ProducirX1();
        Poblado poblado = new Poblado(1, producir, jugador);

        poblado.producirRecurso("madera");
        verify(jugador).agregarRecursos("madera", 1);
    }

    @Test
    public void test02LaCiudadGeneraDosRecursos() {
        Jugador jugador = mock(Jugador.class);
        Producir producir = new ProducirX2();
        Ciudad ciudad = new Ciudad(2, producir, jugador);

        ciudad.producirRecurso("madera");
        verify(jugador).agregarRecursos("madera", 2);
    }

    @Test
    public void test03LaCarreteraNoGeneraRecursos() {
        Jugador jugador = mock(Jugador.class);
        Producir producir = new NoProducir();
        Carretera carretera = new Carretera(0, producir, jugador);

        carretera.producirRecurso("madera");
        verify(jugador, never()).agregarRecursos(anyString(), anyInt());
        }
}