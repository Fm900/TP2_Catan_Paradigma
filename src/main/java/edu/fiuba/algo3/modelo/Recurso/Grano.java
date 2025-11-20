package edu.fiuba.algo3.modelo.Recurso;

public class Grano extends Recurso {
    public Grano(String tipo){
        super(tipo);
    }

    @Override
    public boolean esMismoTipo(Recurso otraRecurso) {
        return super.esMismoTipo(otraRecurso);
    }
}
