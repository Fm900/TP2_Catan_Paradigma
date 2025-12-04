package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turnos.Normal;
import edu.fiuba.algo3.modelo.Turnos.Turno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManejoTurnos {
    public static List<Jugador> jugadores;
    public static List<Jugador> jugadoresInv;
    private Turno turnoActual;

    public ManejoTurnos(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.jugadoresInv = new ArrayList<>(jugadores);
        Collections.reverse(this.jugadoresInv);

        this.turnoActual = new Primer();
    }
    public Turno getTurnoActual() {
        return turnoActual;
    }

    public Jugador jugadorActual() {
        return turnoActual.jugadorActual(this);
    }

    public void siguiente() {
        turnoActual.siguiente(this);
    }

    public void cambiarTurno(Turno nuevo) {
        this.turnoActual = nuevo;
    }
    public void pasarSiguienteFase() {
        ((Normal) turnoActual).pasarSiguienteFase();
    }
    public void esperarMovimientoLadron(Jugador jugador) {
        // Guardar referencia a la fase de dados para llamar a moverLadron después
        // O cambiar a un estado especial "EsperandoLadron"

        // La UI debe capturar esta señal y mostrar la interfaz para mover el ladrón
    }
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    public List<Jugador> getJugadoresInv() {
        return jugadoresInv;
    }
}
