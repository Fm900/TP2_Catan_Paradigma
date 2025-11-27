package edu.fiuba.algo3.modelo.Recurso;


import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Ladrillo extends Recurso {
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarLadrillo(cantidad, this);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerLadrillo(this);
    }


}
