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

import edu.fiuba.algo3.modelo.Turnos.Turno;
import edu.fiuba.algo3.vistas.Otros.AnimacionDados;
import edu.fiuba.algo3.vistas.Otros.MostrarVictoria;
import edu.fiuba.algo3.vistas.Tablero.GeneradorVistaTablero;
import edu.fiuba.algo3.vistas.Tablero.GenerarRecuYBotones;
import edu.fiuba.algo3.vistas.Tablero.VerticeVista;
import edu.fiuba.algo3.vistas.Tablero.VistaTablero;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

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
        generadorVista.setListener(this);

        generarRecuYBotones = vistaTablero.getGenerarRecuYBotones();
        generarRecuYBotones.setListener(this);


        if (estabaFullScreen) {
            stage.setFullScreen(true);
        } else {
            stage.setMaximized(estabaMaximized);
        }
    }

    private void iniciarPrimerTurno() {
        actualizarVista();
        mostrarMensaje("Turno de colocación inicial - Primera ronda");
    }

    public void siguienteTurno() {
        manejoTurnos.siguiente();
        jugadorActual = manejoTurnos.jugadorActual();

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
        String datosTurnoActual = turnoActual.obtenerTexto(manejoTurnos);
        mostrarMensaje(datosTurnoActual);
    }

    private void notificarCambioFase() {
        Turno turnoActual = manejoTurnos.getTurnoActual();
        String fase = turnoActual.obtenerTexto(manejoTurnos);
        if (!fase.isEmpty()){
            mostrarMensaje(fase);
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

    public void mostrarMensaje(String mensaje) {
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
        return turno.obtenerNombre();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Juego getJuego() {
        return juego;
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
    public void construirBoton(){
        try{
            getTurnoActual().ejecutarConstruccion(this);
        } catch (Exception e){
            manejarErrorConstruccion(e);
        }
    }
    public boolean validarSeleccion() {
        if (verticeSeleccionado == null || aristaSeleccionada == null) {
            mostrarMensaje("se debe seleccionar un vertice y arista");
            return false;
        }
        return true;
    }
    private void manejarErrorConstruccion(Exception e) {
        mostrarMensaje("Error: " + e.getMessage());
        generadorVista.resetearSeleccion();
        actualizarVista();
        limpiarSeleccion();
    }
    public void actualizarVistaConConstruccion() {
        Color colorJugador = jugadorActual.color();
        generadorVista.colocarCasa(circuloSeleccionado, colorJugador);
        generadorVista.colorearArista(lineaSeleccionada, colorJugador);
        actualizarVista();
        limpiarSeleccion();
    }
    public Vertice getVerticeSeleccionado() {
        return verticeSeleccionado;
    }
    public Arista getAristaSeleccionada() {
        return aristaSeleccionada;
    }
    public void construirEnFaseConstruccion(Construccion fase) throws Exception {
        Jugador jugadorActual = fase.getJugadorActual();
        Color color = jugadorActual.color();

        if (verticeSeleccionado != null) {
            if (verticeSeleccionado.getDueño() != null) {
                fase.construirCiudad(verticeSeleccionado);
                generadorVista.colocarCiudad(circuloSeleccionado, color);
            } else {
                fase.construirPoblado(verticeSeleccionado);
                generadorVista.colocarCasa(circuloSeleccionado, color);
            }
        }

        if (aristaSeleccionada != null) {
            fase.construirCarretera(aristaSeleccionada);
            generadorVista.colorearArista(lineaSeleccionada, color);
        }

        actualizarVista();
        limpiarSeleccion();
    }

    private void limpiarSeleccion() {
        this.verticeSeleccionado = null;
        this.aristaSeleccionada = null;
        this.circuloSeleccionado = null;
        this.terrenoSeleccionado = null;
        this.polygonSeleccionado = null;
    }



}