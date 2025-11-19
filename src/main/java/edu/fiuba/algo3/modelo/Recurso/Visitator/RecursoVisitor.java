package edu.fiuba.algo3.modelo.Recurso.Visitator;

import edu.fiuba.algo3.modelo.Recurso.*;

public interface RecursoVisitor {
    void visitar(Madera madera);
    void visitar(Ladrillo ladrillo);
    void visitar(Lana lana);
    void visitar(Grano grano);
    void visitar(Mineral mineral);
}
