package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Recurso.Visitator.RecursoVisitor;

public abstract class Recurso {
    public abstract void aceptar(RecursoVisitor visitor);
}
