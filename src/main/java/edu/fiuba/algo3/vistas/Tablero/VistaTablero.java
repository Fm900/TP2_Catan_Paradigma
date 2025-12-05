package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.vistas.Principales.EscenaGeneral;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class VistaTablero extends EscenaGeneral {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Jugador actual;

    private StackPane tableroPane;

    private HBox panelArriba;

    private HBox barraAbajo;
    private HBox espacioCentro;
    private HBox espacioDerecha;
    private HBox espacioIzquierda;


    public VistaTablero(Tablero tablero, Stage stage, List<Jugador> jugadores, Jugador jugador) {
        super(stage);
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.actual = jugador;
        iniciar();
    }
    public void iniciar() {
        new GenerarVistaDeJugadores(jugadores,actual, panelArriba).construir();

        new GeneradorVistaTablero(tableroPane).crearTablero(tablero.terrenos(), tablero.vertices(),tablero.aristas());

        new GenerarRecuYBotones(espacioCentro, espacioDerecha,espacioIzquierda,actual).construir();
    }

    @Override
    protected Pane createLayout() {
        BorderPane root = new BorderPane();

        //PARTE DE ARRIBA
        panelArriba = new HBox();
        root.setTop(panelArriba);
        panelArriba.setAlignment(Pos.CENTER);


        //PARTE DEL CENTRO
        tableroPane = new StackPane();
        root.setCenter(tableroPane);


        barraAbajo = new HBox();
        barraAbajo.setPadding(new Insets(10));
        barraAbajo.setSpacing(50);
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
        // acá van listeners futuros si querés clickear aristas, vértices, etc.
    }

    @Override
    protected void createStyles() {
    }

    @Override
    protected String getBackgroundImagePath() {
        return "Imagenes/FondoSeleccionarJugadoresCatan.png";
    }

    public void actualizarJugadorActual(Jugador jugadorActual) {
    }

    public void actualizarInfoTurno(String nombreFaseActual) {

    }
}
