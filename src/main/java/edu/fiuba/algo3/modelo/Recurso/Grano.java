package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Recurso.Visitator.RecursoVisitor;

public class Grano extends Recurso {
    @Override
    public void aceptar(RecursoVisitor visitor) {
        visitor.visitar(this);
    }
}
