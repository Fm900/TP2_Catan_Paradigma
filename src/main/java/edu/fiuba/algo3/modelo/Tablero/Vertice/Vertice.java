package edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

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

    public void entregarRecursosPorConstruccion(Recurso recurso) {
        estado.entregarRecursosPorConstruccion(recurso);
    }

    public List<Jugador> agregarPropietario(List<Jugador> propietarios) {
        return ((this.estado).agregarPropietario(propietarios));
    }

    public boolean validarConstruccionEnVecino() {
        return estado.validarConstruccionEnVecino();
    }

    public void mejorarPobladoACiudad(Jugador jugador){
        estado.mejorarPobladoACiudad(this, jugador);
    }
}
