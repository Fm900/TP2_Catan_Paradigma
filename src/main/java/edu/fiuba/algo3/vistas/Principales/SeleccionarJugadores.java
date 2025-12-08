package edu.fiuba.algo3.vistas.Principales;

import edu.fiuba.algo3.controllers.ControladorGeneral;
import edu.fiuba.algo3.controllers.ControladorDeAlerta;
import edu.fiuba.algo3.controllers.ControladorDeInicioDeJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class SeleccionarJugadores extends EscenaGeneral {

    private ToggleGroup grupoCantidad;
    private RadioButton tresJugadores;
    private RadioButton cuatroJugadores;

    private TextField nombre1;
    private TextField nombre2;
    private TextField nombre3;
    private TextField nombre4;
    private Button comenzar;
    private Button volver;

    private ControladorDeInicioDeJuego controlador;
    private Tablero tablero;
    private Juego juego;

    private int maxCaracteres = 10;

    private ColorPicker color1;
    private ColorPicker color2;
    private ColorPicker color3;
    private ColorPicker color4;

    public SeleccionarJugadores(Stage stage) {
        super(stage);
        controlador = new ControladorDeInicioDeJuego();
    }

    @Override
    protected String getBackgroundImagePath() {
        return "Imagenes/FondoSeleccionarJugadoresCatan.png";
    }

    @Override
    protected Pane createLayout() {

        VBox layout = new VBox(25);
        layout.setPadding(new Insets(50, 80, 50, 50));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setFillWidth(true);
        layout.getStylesheets().add(
                getClass().getResource("/estilos/selec-jugadores.css").toExternalForm()
        );


        Label labelCantidad = new Label("Cantidad de jugadores:");
        labelCantidad.getStyleClass().add("label-titulo");

        tresJugadores = new RadioButton("3 jugadores");
        cuatroJugadores = new RadioButton("4 jugadores");

        tresJugadores.getStyleClass().add("radio-cantidad");
        cuatroJugadores.getStyleClass().add("radio-cantidad");

        grupoCantidad = new ToggleGroup();
        tresJugadores.setToggleGroup(grupoCantidad);
        cuatroJugadores.setToggleGroup(grupoCantidad);
        tresJugadores.setSelected(true);

        VBox opcionesCantidad = new VBox(10, labelCantidad, tresJugadores, cuatroJugadores);
        opcionesCantidad.getStyleClass().add("opciones-cantidad");
        opcionesCantidad.setAlignment(Pos.CENTER_LEFT);


        Label labelNombres = new Label("Nombres de los jugadores:");
        labelNombres.getStyleClass().add("label-titulo");

        nombre1 = new TextField();
        nombre1.setPromptText("Jugador 1");
        nombre1.getStyleClass().add("textfield-jugador");
        HBox.setHgrow(nombre1, Priority.ALWAYS);
        nombre1.setMaxWidth(Double.MAX_VALUE);

        color1 = new ColorPicker();
        color1.getStyleClass().add("color-jugador");

        nombre2 = new TextField();
        nombre2.setPromptText("Jugador 2");
        nombre2.getStyleClass().add("textfield-jugador");
        HBox.setHgrow(nombre2, Priority.ALWAYS);
        nombre2.setMaxWidth(Double.MAX_VALUE);

        color2 = new ColorPicker();
        color2.getStyleClass().add("color-jugador");

        nombre3 = new TextField();
        nombre3.setPromptText("Jugador 3");
        nombre3.getStyleClass().add("textfield-jugador");
        HBox.setHgrow(nombre3, Priority.ALWAYS);
        nombre3.setMaxWidth(Double.MAX_VALUE);

        color3 = new ColorPicker();
        color3.getStyleClass().add("color-jugador");

        nombre4 = new TextField();
        nombre4.setPromptText("Jugador 4");
        nombre4.getStyleClass().add("textfield-jugador");
        HBox.setHgrow(nombre4, Priority.ALWAYS);
        nombre4.setMaxWidth(Double.MAX_VALUE);

        color4 = new ColorPicker();
        color4.getStyleClass().add("color-jugador");

        nombre4.setVisible(false);
        nombre4.setManaged(false);
        color4.setVisible(false);
        color4.setManaged(false);

        HBox fila1 = new HBox(10, nombre1, color1);
        HBox fila2 = new HBox(10, nombre2, color2);
        HBox fila3 = new HBox(10, nombre3, color3);
        HBox fila4 = new HBox(10, nombre4, color4);

        fila1.setAlignment(Pos.CENTER_LEFT);
        fila2.setAlignment(Pos.CENTER_LEFT);
        fila3.setAlignment(Pos.CENTER_LEFT);
        fila4.setAlignment(Pos.CENTER_LEFT);

        VBox camposNombres = new VBox(10, labelNombres, fila1, fila2, fila3, fila4);
        camposNombres.getStyleClass().add("campos-nombres");


        comenzar = new Button("Comenzar partida");
        volver   = new Button("Volver al menú");

        comenzar.getStyleClass().add("btn-principal");
        volver.getStyleClass().add("btn-secundario");

        VBox botones = new VBox(15, comenzar);
        botones.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(opcionesCantidad, camposNombres, botones, volver);

        return new StackPane(layout);
    }

    @Override
    protected void createControllers(Stage stage) {

        comenzar.setOnAction(e -> {

            int cantidad = tresJugadores.isSelected() ? 3 : 4;

            if (nombre1.getText().isBlank() || nombre1.getText().length() > maxCaracteres ||
                    nombre2.getText().isBlank() || nombre2.getText().length() > maxCaracteres ||
                    nombre3.getText().isBlank() || nombre3.getText().length() > maxCaracteres ||
                    (cantidad == 4 && (nombre4.getText().isBlank() || nombre4.getText().length() > maxCaracteres))) {

                ControladorDeAlerta.mostrarError(
                        "Todos los jugadores deben tener nombre y no pueden exceder " + maxCaracteres + " caracteres.",
                        stage
                );
                return;
            }
            Color c1 = color1.getValue();
            Color c2 = color2.getValue();
            Color c3 = color3.getValue();
            Color c4 = color4.getValue();
            boolean coloresRepetidos =
                    c1.equals(c2) ||
                            c1.equals(c3) ||
                            c2.equals(c3) ||
                            (cantidad == 4 && (c1.equals(c4) || c2.equals(c4) || c3.equals(c4)));

            if (coloresRepetidos) {
                ControladorDeAlerta.mostrarError(
                        "Cada jugador debe elegir un color distinto.",
                        stage
                );
                return;
            }

            String j1 = nombre1.getText();
            String j2 = nombre2.getText();
            String j3 = nombre3.getText();
            String j4 = cantidad == 4 ? nombre4.getText() : null;

            if (cantidad == 3) {
                this.juego = controlador.iniciarJuegoPara(List.of(j1, j2, j3), List.of(c1,c2,c3));
            } else {
                this.juego = controlador.iniciarJuegoPara(List.of(j1, j2, j3, j4),List.of(c1,c2,c3,c4));
            }

            mostrarPantallaDeCarga(stage);
        });

        volver.setOnAction(e -> {
            boolean estabaFullScreen = stage.isFullScreen();
            boolean estabaMaximized = stage.isMaximized();

            stage.setScene(new MenuPrincipalScena(stage).getScene());

            if (estabaFullScreen) stage.setFullScreen(true);
            else stage.setMaximized(estabaMaximized);
        });

        grupoCantidad.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            boolean cuatro = cuatroJugadores.isSelected();
            nombre4.setVisible(cuatro);
            nombre4.setManaged(cuatro);
            color4.setVisible(cuatro);
            color4.setManaged(cuatro);
        });
    }

    private void mostrarPantallaDeCarga(Stage stage) {
        Carga pantallaCarga = new Carga(stage, () -> cambiarATablero(stage));

        boolean estabaFullScreen = stage.isFullScreen();
        boolean estabaMaximized = stage.isMaximized();

        stage.setScene(pantallaCarga.getScene());

        if (estabaFullScreen) stage.setFullScreen(true);
        else stage.setMaximized(estabaMaximized);
    }

    private void cambiarATablero(Stage stage) {
        new ControladorGeneral(stage, juego);
    }

    @Override
    protected void createStyles() {
    }
}
