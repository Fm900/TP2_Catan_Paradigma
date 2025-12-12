package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;


public class Vertice {
    EstadoVertice estado = new Libre();
    private final List<Arista> aristas = new ArrayList<>();


    protected void cambiarAOcupado(Construccion construccion) {
        this.estado = new Ocupado(construccion);
    }

    public void conectarConVertice(Vertice otro){
        Arista arista = new Arista(this, otro);
        this.aristas.add(arista);
        otro.aristas.add(arista);
    }

    public List<Arista> aristas() {
        return List.copyOf(aristas);
    }

    public void construirPoblado(Jugador jugador)  {
        estado.construirPoblado(this, jugador);
    }

    public void entregarRecursosPorConstruccion(String recurso) {
        estado.entregarRecursosPorConstruccion(recurso);
    }

    public List<Jugador> agregarPropietario(List<Jugador> propietarios) {
        return ((this.estado).agregarPropietario(propietarios));
    }
}
