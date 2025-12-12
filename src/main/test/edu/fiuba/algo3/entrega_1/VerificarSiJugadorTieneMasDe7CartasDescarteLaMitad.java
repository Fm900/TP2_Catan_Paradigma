package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Exception.NoTieneRecursosSuficientesParaDescartar;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VerificarSiJugadorTieneMasDe7CartasDescarteLaMitad {

    private Jugador jugador;
    private MazoDeRecursos gestor;
    private List<Recurso> recursos;

    @Test
    public void test01seVerificaQueJugadorConMenosDe7CartasNoDescarte() {
        recursos = new ArrayList<Recurso>(List.of(new Madera(), new Grano(), new Lana()));
        jugador = new Jugador(new MazoDeRecursos(recursos), new Mano());

        assertThrows(NoTieneRecursosSuficientesParaDescartar.class, () -> jugador.descarteMayoria(), "No llega a la cantidad de 7 recursos");
    }


    @Test
    public void test02seVerificaQueJugadorConMasDe7CartasDescarte() {
        recursos = new ArrayList<Recurso>(List.of(new Madera(), new Grano(), new Lana(),new Madera(), new Grano(), new Lana(), new Madera(), new Grano(), new Lana()));
        jugador = new Jugador(new MazoDeRecursos(recursos), new Mano());

        assertDoesNotThrow(() -> jugador.descarteMayoria(), "No llega a la cantidad de 7 recursos");
    }

}
