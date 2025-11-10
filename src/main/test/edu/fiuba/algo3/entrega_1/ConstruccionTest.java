package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstruccionTest{
    @Test
    public void test01ElPobladoGeneraUnSoloRecurso() {
        Jugador jugador = new Jugador();
        Producir producir = new ProducirX1();
        Poblado pobladotest = new Poblado(1, producir, jugador);
        String recurso = "madera";
        pobladotest.producirRecurso(recurso);
        int valorObtenido = jugador.getCantidadRecurso(recurso);

        Assertions.assertEquals(1, valorObtenido);
    }

    @Test
    public void test02LaCiudadGeneraDosRecursos() {
        Jugador jugador = new Jugador();
        Producir producir = new ProducirX2();
        Poblado pobladotest = new Poblado(2, producir, jugador);
        String recurso = "madera";
        pobladotest.producirRecurso(recurso);
        int valorObtenido = jugador.getCantidadRecurso(recurso);

        Assertions.assertEquals(2, valorObtenido);
    }

    @Test
    public void test03LaCarreteraNoGeneraRecursos() {
        Jugador jugador = new Jugador();
        Producir producir = new NoProducir();
        Poblado pobladotest = new Poblado(0, producir, jugador);
        String recurso = "madera";
        pobladotest.producirRecurso(recurso);
        int valorObtenido = jugador.getCantidadRecurso(recurso);

        Assertions.assertEquals(0, valorObtenido);
    }
}