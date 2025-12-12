package edu.fiuba.algo3.modelo.Excepciones;

public class MovimientoInvalido extends RuntimeException {
    public MovimientoInvalido(String mensaje) {
        super(mensaje);
    }
}