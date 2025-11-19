package edu.fiuba.algo3.modelo.Recurso.Visitator;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

public class AgregarRecursoVisitor implements RecursoVisitor {
    private Jugador dueño;
    private int coeficiente;
    public AgregarRecursoVisitor(Jugador dueño, int coeficiente) {
        this.dueño = dueño;
        this.coeficiente = coeficiente;
    }
    @Override
    public void visitar(Madera madera) {
        dueño.agregarRecurso(new Madera(), this.coeficiente);
    }

    @Override
    public void visitar(Ladrillo ladrillo) {
        dueño.agregarRecurso(new Ladrillo(), this.coeficiente);
    }

    @Override
    public void visitar(Lana lana) {
        dueño.agregarRecurso(new Lana(), this.coeficiente);
    }

    @Override
    public void visitar(Grano trigo) {
        dueño.agregarRecurso(new Grano(), this.coeficiente);
    }

    @Override
    public void visitar(Mineral piedra) {
        dueño.agregarRecurso(new Mineral(), this.coeficiente);
    }
}

