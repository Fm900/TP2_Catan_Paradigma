package edu.fiuba.algo3.modelo.Exception;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class NoTieneRecursosSuficientesParaDescartar extends RuntimeException {
    public NoTieneRecursosSuficientesParaDescartar(String message, Recurso recurso) {
        super(message);
    }
}
