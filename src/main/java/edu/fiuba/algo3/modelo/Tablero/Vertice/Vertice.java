package edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirPorFaltaDeConexion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;

import java.util.ArrayList;
import java.util.List;


public class Vertice {
    private int id;
    private double x;
    private double y;
    private EstadoVertice estado = new Libre();
    private final List<Arista> aristas = new ArrayList<>();
    private Jugador dueño;
    public Vertice(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    protected void cambiarAOcupado(Construccion construccion) {
        this.estado = new Ocupado(construccion);
    }

    public void registrarArista(Arista arista) {
        this.aristas.add(arista);
    }

    public void conectarConVertice(Vertice otro){
        Arista arista = new Arista(this, otro, new Vacia());
        this.registrarArista(arista);
        otro.registrarArista(arista);
    }

    public Jugador getDueño(){
        return dueño;
    }

    public List<Arista> aristas() {
        return List.copyOf(aristas);
    }

    public void construirPobladoInicial(Jugador jugador)  {
        estado.construirPobladoInicial(this, jugador, aristas);
    }

    public void construirPoblado(Jugador jugador)  {
        estado.construirPoblado(this, jugador, aristas);
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

    public void setDueño(Jugador jugador) {
        this.dueño = jugador;
    }

    public boolean validarConexion(Jugador jugador) {
        if (this.dueño == jugador) {
            return true;
        }
        for (Arista a : aristas) {
            if (a.elMismoDueño(jugador)) {
                return true;
            }
        }
        return false;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getId() {
        return id;
    }
}