package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import java.util.ArrayList;
import java.util.List;

public class ConstructorJugadores {
    List<String> nombresJugadores;
    public ConstructorJugadores(List<String> nombresJugadores) {
        this.nombresJugadores = nombresJugadores;
    }
    public List<Jugador> crearJugadores(){
        List<Jugador> jugadores = new ArrayList<>();
        for(String nombreJugador : nombresJugadores){
            //el mazo de recursos se inicia de esta manera para que tenga los recursos necesarios para construir los poblados y carreteras de los primeros turnos
            MazoDeRecursos mazo = new MazoDeRecursos(new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(),new Madera(), new Ladrillo(), new Lana(), new Grano())));
            Mano mano = new Mano();
            jugadores.add(new Jugador(mazo,mano,nombreJugador));
        }
        return jugadores;
    }
}
//