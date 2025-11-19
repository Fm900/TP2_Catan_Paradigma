package edu.fiuba.algo3.modelo.Recurso.Visitator;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

public class ConsumirRecursoVisitor implements RecursoVisitor {
    private Jugador dueño;
    public ConsumirRecursoVisitor(Jugador dueño) {
        this.dueño = dueño;
    }
    @Override public void visitar(Madera m)   { dueño.remover(m, 1); }
    @Override public void visitar(Ladrillo l) { dueño.remover(l, 1); }
    @Override public void visitar(Lana l)     { dueño.remover(l, 1); }
    @Override public void visitar(Grano t)    { dueño.remover(t, 1); }
    @Override public void visitar(Mineral p)   { dueño.remover(p, 1); }
}
