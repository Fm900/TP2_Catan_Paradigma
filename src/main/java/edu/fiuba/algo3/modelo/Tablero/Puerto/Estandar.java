package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Exception.RecursoInvalido;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Estandar implements Tasa{
    private int cantidad = 4;
    @Override
    public List<Recurso> aplicarTasa(List<Recurso> recursos, Jugador jugador) {
        Recurso recurso = recursos.get(0);
        List<Recurso> subRecursos = new ArrayList<>();
        for(int i = 0; i < cantidad; i++){
            subRecursos.add(recurso);
        }
        return subRecursos;
    }
}
