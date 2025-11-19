package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Recurso.Visitator.RecursoVisitor;

public class Lana extends Recurso {
    @Override
    public void aceptar(RecursoVisitor visitor) {
        visitor.visitar(this);
    }
}
