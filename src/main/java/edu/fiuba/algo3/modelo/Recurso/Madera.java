package edu.fiuba.algo3.modelo.Recurso;


public class Madera extends Recurso{
    public Madera(String tipo){
        super(tipo);
    }

    @Override
    public boolean esMismoTipo(Recurso otraRecurso) {
        return super.esMismoTipo(otraRecurso);
    }
}
