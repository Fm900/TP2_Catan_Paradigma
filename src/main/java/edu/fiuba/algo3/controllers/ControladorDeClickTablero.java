package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.vistas.Tablero.VerticeVista;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public interface ControladorDeClickTablero {
    void onSeleccionCancelada();
    void onVerticeSeleccionado(Vertice v, VerticeVista vista);
    void onAristaSeleccionada(Arista a, Line l);
    void obTerrenoSeleccionado(Terreno t, Polygon p);
    void construirBoton();
    void tirarDados();
    void terminarFase();
    void terminarTurno();
    void moverLadron();
}
