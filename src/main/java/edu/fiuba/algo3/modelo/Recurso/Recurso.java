package edu.fiuba.algo3.modelo.Recurso;

public abstract class Recurso {
    protected String tipo;
    public Recurso(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public boolean esMismoTipo(Recurso otraRecurso) {
        return this.tipo.equals(otraRecurso.getTipo());
    }
}
