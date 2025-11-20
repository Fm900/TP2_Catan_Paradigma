package edu.fiuba.algo3.modelo.Recurso;


public class Mineral extends Recurso {
    public Mineral(String tipo) {
        super(tipo);
    }

    @Override
    public boolean esMismoTipo(Recurso otraRecurso) {
        return super.esMismoTipo(otraRecurso);
    }
}
