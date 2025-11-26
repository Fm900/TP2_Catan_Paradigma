package edu.fiuba.algo3.vistas.Inicio;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

    public SeleccionarJugadores(Stage stage) {
        super(stage);
    }

    @Override
    protected String getBackgroundImagePath() {
        return "/imagenes/FondoSeleccionarJugadoresCatan.png";
    }

    @Override
    protected Pane createLayout() {

        VBox layout = new VBox(25);
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.TOP_CENTER);
        
        // Elegir cantidad de jugadores
        Label labelCantidad = new Label("Cantidad de jugadores:");
        labelCantidad.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        tresJugadores = new RadioButton("3 jugadores");
        cuatroJugadores = new RadioButton("4 jugadores");
        
        grupoCantidad = new ToggleGroup();
        tresJugadores.setToggleGroup(grupoCantidad);
        cuatroJugadores.setToggleGroup(grupoCantidad);

        tresJugadores.setSelected(true);

        VBox opcionesCantidad = new VBox(10, labelCantidad, tresJugadores, cuatroJugadores);
        opcionesCantidad.setStyle(
                "-fx-background-color: #ffcc66;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 15;"
        );
        opcionesCantidad.setAlignment(Pos.CENTER_LEFT);

        
        // Nombres
        Label labelNombres = new Label("Nombres de los jugadores:");
        labelNombres.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        nombre1 = new TextField();
        nombre1.setPromptText("Jugador 1");

        nombre2 = new TextField();
        nombre2.setPromptText("Jugador 2");

        nombre3 = new TextField();
        nombre3.setPromptText("Jugador 3");

        nombre4 = new TextField();
        nombre4.setPromptText("Jugador 4");
        nombre4.setVisible(false);
        nombre4.setManaged(false);

        VBox camposNombres = new VBox(10, labelNombres, nombre1, nombre2, nombre3, nombre4);
        camposNombres.setAlignment(Pos.CENTER_LEFT);
        
        // Botones
        comenzar = new Button("Comenzar partida");
        volver = new Button("Volver al menú");

        VBox botones = new VBox(15, comenzar);
        botones.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(opcionesCantidad, camposNombres, botones, volver);

        return new StackPane(layout);
    }

    @Override
    protected void createControllers(Stage stage) {

        // Comenzar partida
        comenzar.setOnAction(e -> {

            int cantidad = tresJugadores.isSelected() ? 3 : 4;

            System.out.println("Cantidad de jugadores: " + cantidad);
            System.out.println("Jugador 1: " + nombre1.getText());
            System.out.println("Jugador 2: " + nombre2.getText());
            System.out.println("Jugador 3: " + nombre3.getText());

            if (cantidad == 4) {
                System.out.println("Jugador 4: " + nombre4.getText());
            }
            // Aca más adelante cambias a la escena del juego
        });
        volver.setOnAction(e -> {
            stage.setScene(new MenuPrincipalScena(stage).getScene());
            stage.setMaximized(true);   
        });
        grupoCantidad.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            boolean cuatro = cuatroJugadores.isSelected();
            nombre4.setVisible(cuatro);
            nombre4.setManaged(cuatro); // IMPORTANTE para que no deje espacios
        });

    }

    @Override
    protected void createStyles() {

        comenzar.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-background-color: #ffcc66;" +
                        "-fx-padding: 12 30;" +
                        "-fx-background-radius: 15;"
        );
        volver.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-background-color: #ffcc66;" +
                        "-fx-padding: 8 20;" +
                        "-fx-background-radius: 15;"
        );
        String estiloCampos =
                "-fx-background-color: #ffcc66;" +
                        "-fx-background-radius: 15;" +
                        "-fx-padding: 8 10;" +
                        "-fx-font-size: 18px;" +
                        "-fx-border-color: #8a6d3b;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15;";

        nombre1.setStyle(estiloCampos);
        nombre2.setStyle(estiloCampos);
        nombre3.setStyle(estiloCampos);
        nombre4.setStyle(estiloCampos);
        tresJugadores.setStyle("-fx-font-size: 20px; -fx-text-fill: #4a3b2a;");
        cuatroJugadores.setStyle("-fx-font-size: 20px; -fx-text-fill: #4a3b2a;");


    }
}
