package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Constructores.ConstructorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.nio.file.Paths;
import java.util.List;

public class ControladorDeInicioDeJuego {

    public Tablero iniciarJuegoPara(List<String> jugadores){
        //aca se pone el path, para el parser
        ConstructorJuego constJuego = new ConstructorJuego();
        Juego juego = constJuego.construirJuegoCon(jugadores);
//        juego.iniciarJuego();
        return juego.getTablero();
    }
}
