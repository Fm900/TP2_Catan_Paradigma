package edu.fiuba.algo3.modelo.Exception;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class NoSePudoRealizarElIntercambioLaBancaNoTieneSuficientesRecursos extends RuntimeException {
    public NoSePudoRealizarElIntercambioLaBancaNoTieneSuficientesRecursos(String message, Recurso recurso) {
        super(message);
    }
}
