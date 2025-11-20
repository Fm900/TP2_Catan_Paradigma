package edu.fiuba.algo3.modelo.Exception;

public class MovimientoInvalido extends RuntimeException {
    public MovimientoInvalido(String mensaje) {
        super(mensaje);
    }
}