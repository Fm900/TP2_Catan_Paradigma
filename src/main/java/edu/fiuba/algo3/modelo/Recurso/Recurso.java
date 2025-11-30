package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public abstract class Recurso {

    public abstract void agregar(int cantidad, MazoDeRecursos mazo);

    public abstract void eliminar(MazoDeRecursos mazo);

    public abstract int getCantidad(MazoDeRecursos mazo);

    public abstract boolean mismaClaseQue(Recurso otro);
}
