package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Recurso.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class Jugador {
    private MazoDeRecursos recursos;
    private Collection<Carta> cartas;

    public Jugador() {
        cartas =  new ArrayList<>();
        recursos = new MazoDeRecursos();
    }

    public void agregarRecurso(Recurso recurso, int cantidad) {
        recurso.agregar(cantidad, recursos);
    }

    public void consumirRecursos(List<Recurso> precio) {
        recursos.verificarCumplimiento(precio);
        for(Recurso recurso: precio){
            recurso.eliminar(recursos);
        }
    }
}