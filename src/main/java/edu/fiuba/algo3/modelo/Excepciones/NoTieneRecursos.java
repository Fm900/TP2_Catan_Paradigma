package edu.fiuba.algo3.modelo.Excepciones;

public class NoTieneRecursos extends RuntimeException {
    public NoTieneRecursos(String mensaje) {
        super(mensaje);
    }
}