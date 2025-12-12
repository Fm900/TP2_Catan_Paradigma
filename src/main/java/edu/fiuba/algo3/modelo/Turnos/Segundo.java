package edu.fiuba.algo3.modelo.Turnos;

import edu.fiuba.algo3.controllers.ControladorGeneral;
import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Segundo implements Turno {

    private int indice;

    public Segundo() {
        this.indice = 0;
    }

    @Override
    public Jugador jugadorActual(ManejoTurnos manejador) {
        return ManejoTurnos.jugadoresInv.get(indice);
    }

    @Override
    public void siguiente(ManejoTurnos manejador) {
        indice++;

        if (indice == manejador.getJugadoresInv().size()) {
            manejador.cambiarTurno(new Normal());
        }
    }

    @Override
    public String obtenerTexto(ManejoTurnos manejador) {
        return "Segunda ronda - Turno de " + jugadorActual(manejador);
    }

    @Override
    public void ejecutarConstruccion(ControladorGeneral controlador) {
        if (!controlador.validarSeleccion()) return;

        this.construir(controlador.getManejoTurnos(),
                controlador.getVerticeSeleccionado(),
                controlador.getAristaSeleccionada());

        controlador.actualizarVistaConConstruccion();
        controlador.mostrarMensaje("¡Construcción exitosa! Recibiste recursos iniciales.");
        controlador.siguienteTurno();
    }

    @Override
    public String obtenerNombre() {
        return "Colocación Inicial - Segunda Ronda";
    }


    public void construir(ManejoTurnos manejador, Vertice vertice, Arista arista){
        Tablero tablero = Juego.getInstancia().getTablero();
        Jugador jugador = jugadorActual(manejador);

        tablero.colocarPobladoInicial(jugador, vertice);
        tablero.colocarCarretera(jugador, arista);

        for (Terreno terreno : tablero.obtenerTerrenosAdy(vertice)){
            jugador.agregarRecurso(terreno.recursoInicial(), 1);
        }
    }
}
