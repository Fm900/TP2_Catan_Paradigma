package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Lana extends Recurso {

    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo){
        mazo.agregarLana(this ,cantidad);
    }
    @Override
    public void eliminar(MazoDeRecursos mazo){
        mazo.removerLana(this);
    }
}
