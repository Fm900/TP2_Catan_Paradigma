package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.vistas.Principales.EscenaGeneral;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class VistaTablero extends EscenaGeneral {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Jugador actual;
    private StackPane tableroPane;
    private HBox panelArriba;
    private StackPane contenedorAbajo;
    private HBox panelAbajo;
    private HBox buttonContainer;


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

        new GenerarRecuYBotones(panelAbajo,buttonContainer,actual).construir();
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


        //PARTE DE ABAJO
        contenedorAbajo = new StackPane();
        panelAbajo = new HBox();
        buttonContainer = new HBox(10);
        contenedorAbajo.setAlignment(panelAbajo, Pos.CENTER);
        contenedorAbajo.setMargin(panelAbajo, new Insets(0, 0, 20, 0));
        contenedorAbajo.setAlignment(buttonContainer, Pos.BOTTOM_RIGHT);
        contenedorAbajo.getChildren().addAll(panelAbajo, buttonContainer);
        root.setBottom(contenedorAbajo);


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
}
