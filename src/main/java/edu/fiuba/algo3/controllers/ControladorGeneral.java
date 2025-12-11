package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Exception.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.*;
import edu.fiuba.algo3.modelo.Turnos.Normal;
import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Turnos.Segundo;
import edu.fiuba.algo3.modelo.Turnos.Turno;
import edu.fiuba.algo3.vistas.Otros.AnimacionDados;
import edu.fiuba.algo3.vistas.Otros.MostrarVictoria;
import edu.fiuba.algo3.vistas.Tablero.GeneradorVistaTablero;
import edu.fiuba.algo3.vistas.Tablero.GenerarRecuYBotones;
import edu.fiuba.algo3.vistas.Tablero.VerticeVista;
import edu.fiuba.algo3.vistas.Tablero.VistaTablero;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.List;

public class ControladorGeneral implements ControladorDeClickTablero{
    private final List<Jugador> jugadores;
    private Jugador jugadorActual;
    private final Juego juego;
    private final Tablero tablero;
    private final Stage stage;
    private final ManejoTurnos manejoTurnos;
    private VistaTablero vistaTablero;
    private GeneradorVistaTablero generadorVista;
    private GenerarRecuYBotones generarRecuYBotones;
    private Vertice verticeSeleccionado;
    private Arista aristaSeleccionada;
    private VerticeVista circuloSeleccionado;
    private Line lineaSeleccionada;
    private Terreno terrenoSeleccionado;
    private Polygon polygonSeleccionado;

    public ControladorGeneral(Stage stage, Juego juego) {
        this.juego = juego;
        this.jugadores = juego.getJugadores();
        this.tablero = juego.getTablero();
        this.stage = stage;
        this.manejoTurnos = new ManejoTurnos(jugadores);
        this.jugadorActual = manejoTurnos.jugadorActual();

        inicializarVista();
        iniciarPrimerTurno();
    }

    private void inicializarVista() {
        boolean estabaFullScreen = stage.isFullScreen();
        boolean estabaMaximized = stage.isMaximized();

        vistaTablero = new VistaTablero(tablero, stage, jugadores, jugadorActual, manejoTurnos);
        stage.setScene(vistaTablero.getScene());

        generadorVista = vistaTablero.getGeneradorVista();
        if (generadorVista != null) {
            generadorVista.setListener(this);
        }
        generarRecuYBotones = vistaTablero.getGenerarRecuYBotones();
        if(generarRecuYBotones != null) {
            generarRecuYBotones.setListener(this);
        }


        if (estabaFullScreen) {
            stage.setFullScreen(true);
        } else {
            stage.setMaximized(estabaMaximized);
        }
    }

    private void iniciarPrimerTurno() {
        actualizarVista();
        System.out.println(jugadorActual.obtenerNombre());
        mostrarMensaje("Turno de colocación inicial - Primera ronda");
    }

    public void siguienteTurno() {
        manejoTurnos.siguiente();
        jugadorActual = manejoTurnos.jugadorActual();
        System.out.println(jugadorActual.obtenerNombre());
        actualizarVista();
        notificarCambioTurno();
    }

    public void siguienteFase() {
        manejoTurnos.pasarSiguienteFase();
        actualizarVista();
        notificarCambioFase();
    }

    private void notificarCambioTurno() {
        Turno turnoActual = manejoTurnos.getTurnoActual();

        if (turnoActual instanceof Primer) {
            mostrarMensaje("Primera ronda - Turno de " + jugadorActual.obtenerNombre());
        } else if (turnoActual instanceof Segundo) {
            mostrarMensaje("Segunda ronda - Turno de " + jugadorActual.obtenerNombre());
        } else if (turnoActual instanceof Normal) {
            Normal normal = (Normal) turnoActual;
            mostrarMensaje("Turno de " + jugadorActual.obtenerNombre() + " - Fase: " + normal.nombreFaseActual());
        }
    }

    private void notificarCambioFase() {
        Turno turnoActual = manejoTurnos.getTurnoActual();

        if (turnoActual instanceof Normal) {
            Normal normal = (Normal) turnoActual;
            mostrarMensaje("Fase: " + normal.nombreFaseActual());
        }
    }

    public ManejoTurnos getManejoTurnos() {
        return manejoTurnos;
    }

    private void actualizarVista() {
        vistaTablero.actualizarJugadorActual(manejoTurnos.jugadorActual());
        vistaTablero.actualizarInfoTurno(getNombreFaseActual());
        vistaTablero.actualizarInfoTablero();
    }

    private void mostrarMensaje(String mensaje) {
        ControladorDeAlerta.mostrarInfo(mensaje, stage);
    }

    public void verificarVictoria() {
        for (Jugador jugador : jugadores) {
            if (this.juego.chequearVictoria(jugador)){
                mostrarVictoria(jugador.obtenerNombre());
            }

        }
    }

    public void mostrarVictoria(String nombreJugador) {
        MostrarVictoria mini = new MostrarVictoria("/estilos/victoria.css");
        mini.mostrar(nombreJugador);
    }


    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Turno getTurnoActual() {
        return manejoTurnos.getTurnoActual();
    }

    public Fase getFaseActual() {
        Turno turno = manejoTurnos.getTurnoActual();
        if (turno instanceof Normal) {
            return ((Normal) turno).faseActual();
        }
        return null;
    }

    public String getNombreFaseActual() {
        Turno turno = manejoTurnos.getTurnoActual();
        if (turno instanceof Normal) {
            return ((Normal) turno).nombreFaseActual();
        } else if (turno instanceof Primer) {
            return "Colocación Inicial - Primera Ronda";
        } else if (turno instanceof Segundo) {
            return "Colocación Inicial - Segunda Ronda";
        }
        return "Desconocido";
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Juego getJuego() {
        return juego;
    }

    @Override
    public void onSeleccionCancelada() {
        this.verticeSeleccionado = null;
        this.aristaSeleccionada = null;
        this.circuloSeleccionado = null;
        this.lineaSeleccionada = null;
        System.out.println("Selección cancelada");
    }

    @Override
    public void onVerticeSeleccionado(Vertice v, VerticeVista vista) {
        this.verticeSeleccionado = v;
        this.circuloSeleccionado = vista;
    }

    @Override
    public void onAristaSeleccionada(Arista a, Line l) {
        this.aristaSeleccionada = a;
        this.lineaSeleccionada = l;
    }

    @Override
    public void obTerrenoSeleccionado(Terreno t, Polygon p) {
        this.terrenoSeleccionado = t;
        this.polygonSeleccionado = p;
    }

    @Override
    public void construirBoton(){
        Turno turno = getTurnoActual();
        if (turno instanceof Primer) {
            construirPirmerTurno((Primer) turno);
        } else if (turno instanceof Segundo) {
            construirSegundoTurno((Segundo) turno);
        } else if (turno instanceof Normal && getFaseActual() instanceof Construccion) {
            construirFaseConstruccion((Normal) turno);
        }
    }

    @Override
    public void tirarDados() {
        Dados fase = (Dados) getFaseActual();
        fase.ejecutar(getJugadorActual(), getManejoTurnos());
        int tirada = fase.getTirada();
        AnimacionDados.mostrarAnimacion(stage,fase.getDado1(),fase.getDado2());

        if (tirada != 7){
            fase.producirRecursos(getManejoTurnos());
        } else{
            generarRecuYBotones.crearBotonMoverLadron();
        }
        generarRecuYBotones.desactivarBoton();
    }
    @Override
    public void moverLadron(){
        Dados fase = (Dados) getFaseActual();
        Jugador victima = verticeSeleccionado.getDueño();
        if (victima == null){
            mostrarMensaje("El vertice sleccionado tiene que tener dueño");
        }
        fase.moverLadron(getJugadorActual(), terrenoSeleccionado,victima,getManejoTurnos());
        generadorVista.dibujarLadron();
        limpiarSeleccion();
        mostrarMensaje("¡Se movio el Ladron!");
        siguienteTurno();

    }

    @Override
    public void terminarFase() {
        siguienteFase();
    }

    @Override
    public void terminarTurno() {
        verificarVictoria();
        siguienteTurno();
    }

    private void construirPirmerTurno(Primer turno){
        try {
            turno.construir(manejoTurnos, verticeSeleccionado, aristaSeleccionada);

            Color colorJugador = jugadorActual.color();
            generadorVista.colocarCasa(circuloSeleccionado, colorJugador);
            generadorVista.colorearArista(lineaSeleccionada, colorJugador);
            actualizarVista();
            limpiarSeleccion();
            mostrarMensaje("¡Construcción exitosa!");

            siguienteTurno();

        } catch (AristaOcupadaNoSePuedeConstruir | NoAlcanzanLosRecursos |
                 NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente |
                 NoSePuedeConstruirPorFaltaDeConexion |
                 NoSePuedeMejorarACiudad |
                 ReglaDeDistanciaNoValida |
                 VerticeOcupadoNoPuedeConstruir e) {
            mostrarMensaje("Error: " + e.getMessage());
            generadorVista.resetearSeleccion();
            actualizarVista();
            limpiarSeleccion();
        }
    }
    private void construirSegundoTurno(Segundo turno){
        try {
            turno.construir(manejoTurnos, verticeSeleccionado, aristaSeleccionada);

            Color colorJugador = jugadorActual.color();
            generadorVista.colocarCasa(circuloSeleccionado, colorJugador);
            generadorVista.colorearArista(lineaSeleccionada, colorJugador);

            limpiarSeleccion();
            mostrarMensaje("¡Construcción exitosa! Recibiste recursos iniciales.");

            siguienteTurno();

        } catch (AristaOcupadaNoSePuedeConstruir | NoAlcanzanLosRecursos |
                 NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente |
                 NoSePuedeConstruirPorFaltaDeConexion |
                 NoSePuedeMejorarACiudad |
                 ReglaDeDistanciaNoValida |
                 VerticeOcupadoNoPuedeConstruir e) {
            mostrarMensaje("Error: " + e.getMessage());
            generadorVista.resetearSeleccion();
            limpiarSeleccion();
        }
    }
    private void construirFaseConstruccion(Normal turno){
        Construccion fase = (Construccion) turno.faseActual();
        Jugador jugadorActual = fase.getJugadorActual();
        Color color = jugadorActual.color();
        try {
            if (verticeSeleccionado != null && verticeSeleccionado.getDueño() != null) {
                fase.construirCiudad(verticeSeleccionado);
                generadorVista.colocarCiudad(circuloSeleccionado, color);
            }
            if (verticeSeleccionado != null) {
                fase.construirPoblado(verticeSeleccionado);
                generadorVista.colocarCiudad(circuloSeleccionado, color);
            }
            if (aristaSeleccionada != null) {
                fase.construirCarretera(aristaSeleccionada);
                generadorVista.colorearArista(lineaSeleccionada, color);
            }
        } catch (AristaOcupadaNoSePuedeConstruir | NoAlcanzanLosRecursos |
                 NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente |
                 NoSePuedeConstruirPorFaltaDeConexion |
                 NoSePuedeMejorarACiudad |
                 ReglaDeDistanciaNoValida |
                 VerticeOcupadoNoPuedeConstruir e){
            mostrarMensaje("Error: " + e.getMessage());
        }
    }
    private void limpiarSeleccion() {
        this.verticeSeleccionado = null;
        this.aristaSeleccionada = null;
        this.circuloSeleccionado = null;
        this.terrenoSeleccionado = null;
        this.polygonSeleccionado = null;
    }



}