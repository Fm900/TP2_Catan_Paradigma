package edu.fiuba.algo3.modelo.Turnos;

import edu.fiuba.algo3.controllers.ControladorGeneral;
import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.*;

import java.util.ArrayList;
import java.util.List;

public class Normal implements Turno {

    private final List<Fase> fases;
    private int faseActual;
    private int indiceJugador;
    private ManejoTurnos manejador;

    public Normal() {
        this.fases = new ArrayList<>();
        this.faseActual = 0;
        this.indiceJugador = 0;
        fases.add(new Dados());
        fases.add(new Comercio());
        fases.add(new Construccion());
        fases.add(new JugarCartas());
    }

    @Override
    public Jugador jugadorActual(ManejoTurnos manejador) {
        this.manejador = manejador;
        return manejador.getJugadores().get(indiceJugador);
    }

    @Override
    public void siguiente(ManejoTurnos manejador) {
        this.manejador = manejador;
        ejecutarFaseActual();
    }

    @Override
    public String obtenerTexto(ManejoTurnos manejador) {
        return "Turno de" + jugadorActual(manejador) + " - Fase: " + nombreFaseActual();
    }

    @Override
    public void ejecutarConstruccion(ControladorGeneral controlador) throws Exception {
        Construccion fase = (Construccion) this.faseActual();
        controlador.construirEnFaseConstruccion(fase);
    }

    @Override
    public String obtenerNombre() {
        return nombreFaseActual();
    }

    private void ejecutarFaseActual() {
        Jugador jugador = manejador.getJugadores().get(indiceJugador);
        fases.get(faseActual).ejecutar(jugador, manejador);
    }

    public void pasarSiguienteFase() {
        faseActual++;

        if (faseActual >= fases.size()) {
            avanzarSiguienteJugador();
        }
        ejecutarFaseActual();
    }

    private void avanzarSiguienteJugador() {
        faseActual = 0;
        if (manejador != null) {
            indiceJugador = (indiceJugador + 1) % manejador.getJugadores().size();
        }
    }

    public Fase faseActual() {
        return fases.get(faseActual);
    }

    public String nombreFaseActual() {
        return fases.get(faseActual).getClass().getSimpleName();
    }

    public Jugador getJugadorActual() {
        return manejador != null ? manejador.getJugadores().get(indiceJugador) : null;
    }
}