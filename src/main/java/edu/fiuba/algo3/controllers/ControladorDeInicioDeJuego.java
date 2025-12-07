package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Constructores.ConstructorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.scene.paint.Color;

import java.nio.file.Paths;
import java.util.List;

public class ControladorDeInicioDeJuego {

    public Juego iniciarJuegoPara(List<String> jugadores, List<Color> colores) {
        ConstructorJuego constJuego = new ConstructorJuego();
        Juego juego = constJuego.construirJuegoCon(jugadores, colores);
        return juego;
    }
}
