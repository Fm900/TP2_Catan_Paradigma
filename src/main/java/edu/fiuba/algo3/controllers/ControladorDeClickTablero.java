package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public interface ControladorDeClickTablero {
    void onSeleccionCancelada();
    void onVerticeSeleccionado(Vertice v, Circle c);
    void onAristaSeleccionada(Arista a, Line l);
    void construirBoton();
    void tirarDados();
    void terminarFase();
    void terminarTurno();
    void moverLadron();
}
