package edu.fiuba.algo3.modelo.Exception;

public class NoTieneRecursos extends RuntimeException {
    public NoTieneRecursos(String mensaje) {
        super(mensaje);
    }
}