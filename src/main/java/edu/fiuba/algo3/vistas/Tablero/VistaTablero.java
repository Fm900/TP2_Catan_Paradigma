package edu.fiuba.algo3.vistas.Tablero;


import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.vistas.Principales.EscenaGeneral;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class VistaTablero extends EscenaGeneral {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Jugador actual;

    private StackPane tableroPane;

    private VBox panelArriba;

    private HBox barraAbajo;
    private HBox espacioCentro;
    private HBox espacioDerecha;
    private HBox espacioIzquierda;

    private GenerarVistaDeJugadores genVistaDeJugadores ;
    private GeneradorVistaTablero genVistaTablero;
    private GenerarRecuYBotones genRecuYBotones;
    private ManejoTurnos manejoTurnos;


    public VistaTablero(Tablero tablero, Stage stage, List<Jugador> jugadores, Jugador jugador, ManejoTurnos manejoTurnos) {
        super(stage);
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.actual = jugador;
        this.manejoTurnos = manejoTurnos;
        iniciar();
    }
    public void iniciar() {
        genVistaDeJugadores = new GenerarVistaDeJugadores(jugadores,actual, panelArriba);
        genVistaDeJugadores.construir();

        genVistaTablero = new GeneradorVistaTablero(tableroPane);
        genVistaTablero.crearTablero(tablero.terrenos(), tablero.vertices(),tablero.aristas(), tablero.puertos());

        genRecuYBotones = new GenerarRecuYBotones(espacioCentro, espacioDerecha,espacioIzquierda,actual, manejoTurnos);
        genRecuYBotones.construir();
    }

    @Override
    protected Pane createLayout() {
        BorderPane root = new BorderPane();

        panelArriba = new VBox();
        root.setTop(panelArriba);

        tableroPane = new StackPane();
        root.setCenter(tableroPane);

        barraAbajo = new HBox();
        barraAbajo.setAlignment(Pos.CENTER);

        espacioIzquierda = new HBox();
        espacioIzquierda.setAlignment(Pos.CENTER_LEFT);

        espacioCentro = new HBox();
        espacioCentro.setAlignment(Pos.CENTER);

        espacioDerecha = new HBox();
        espacioDerecha.setAlignment(Pos.CENTER_RIGHT);

        barraAbajo.getChildren().addAll(espacioIzquierda, espacioCentro, espacioDerecha);
        HBox.setHgrow(espacioCentro, Priority.ALWAYS);

        root.setBottom(barraAbajo);



        return root;
    }

    @Override
    protected void createControllers(Stage stage) {
    }

    @Override
    protected void createStyles() {
    }

    @Override
    protected String getBackgroundImagePath() {
        return "Imagenes/FondoSeleccionarJugadoresCatan.png";
    }

    public void actualizarJugadorActual(Jugador jugadorActual) {
        genVistaDeJugadores.actualizarJugador(jugadorActual);
        genRecuYBotones.actualizarJugador(jugadorActual);
    }

    public void actualizarInfoTurno(String nombreFase) {
        genVistaDeJugadores.actualizarInfo(nombreFase);
        genRecuYBotones.actualizarBotonesSegunFase(nombreFase);
    }
    public void actualizarInfoTablero() {
        genVistaTablero.actualizarReferencias();
    }

    public GeneradorVistaTablero getGeneradorVista() {
        return genVistaTablero;
    }

    public GenerarRecuYBotones getGenerarRecuYBotones() {
        return genRecuYBotones;
    }


}
