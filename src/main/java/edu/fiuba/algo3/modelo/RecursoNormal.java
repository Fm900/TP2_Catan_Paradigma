package edu.fiuba.algo3.modelo;

public class RecursoNormal implements Recurso{
    private final String tipo;

    public RecursoNormal(String tipo) {
        this.tipo = tipo;
    }

    public String tipo() {
        return tipo;
    }
    public boolean esNulo(){
        return false;
    }
}
