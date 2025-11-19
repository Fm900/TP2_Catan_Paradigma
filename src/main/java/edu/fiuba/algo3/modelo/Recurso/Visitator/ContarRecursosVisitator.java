package edu.fiuba.algo3.modelo.Recurso.Visitator;

import edu.fiuba.algo3.modelo.Recurso.*;

public class ContarRecursosVisitator implements RecursoVisitor{
    Recurso recurso;
    int contador = 0;
    public ContarRecursosVisitator(Recurso recurso) {
        this.recurso = recurso;
    }
    @Override
    public void visitar(Madera madera) {
        if (recurso.equals(Madera.class)) contador++;
    }

    @Override
    public void visitar(Ladrillo ladrillo) {
        if (recurso.equals(Madera.class)) contador++;
    }

    @Override
    public void visitar(Lana lana) {
        if (recurso.equals(Madera.class)) contador++;
    }

    @Override
    public void visitar(Grano grano) {
        if (recurso.equals(Madera.class)) contador++;
    }

    @Override
    public void visitar(Mineral mineral) {
        if (recurso.equals(Madera.class)) contador++;
    }
    public int getResultado() {return contador;}
}
