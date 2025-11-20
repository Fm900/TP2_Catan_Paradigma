package edu.fiuba.algo3.modelo.Recurso;


public class Ladrillo extends Recurso {
    public Ladrillo(String tipo){
        super(tipo);
    }

    @Override
    public boolean esMismoTipo(Recurso otraRecurso) {
        return super.esMismoTipo(otraRecurso);
    }
}
