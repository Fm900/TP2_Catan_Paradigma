package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class GenerarVistaDeJugadores {
    private List<Jugador> jugadores;
    private Jugador jugadorActual;
    private static final Color[] COLORES_JUGADORES = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.PURPLE, Color.ORANGE, Color.CYAN, Color.MAGENTA
    };
    private HBox root;

    public GenerarVistaDeJugadores(List<Jugador> jugadores, Jugador jugadorActual, HBox root) {
        this.jugadores = jugadores;
        this.jugadorActual = jugadorActual;
        this.root = root;
    }
    public void construir(){
        crearPanelSuperior();
    }
    private void crearPanelSuperior() {
        configurarPanelArriba();

        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println(jugadores.get(i).obtenerNombre());
            HBox tarjetaJugador = crearTarjetaJugador(jugadores.get(i), i);
            root.getChildren().add(tarjetaJugador);
        }
    }

    private void configurarPanelArriba() {
        root.setPadding(new Insets(15, 0, 15, 0));
        root.setStyle("-fx-background-color: transparent;");
        root.setAlignment(Pos.CENTER);
        root.setSpacing(250);
    }

    private HBox crearTarjetaJugador(Jugador jugador, int indiceColor) {
        HBox tarjeta = new HBox();
        configurarTarjetaJugador(tarjeta, jugador);

        Circle icono = crearIconoJugador(indiceColor);
        VBox infoJugador = crearInfoJugador(jugador);

        tarjeta.getChildren().addAll(icono, infoJugador);
        return tarjeta;
    }
    private void configurarTarjetaJugador(HBox tarjeta, Jugador jugador) {
        tarjeta.setAlignment(Pos.CENTER_LEFT);
        tarjeta.setPadding(new Insets(10, 20, 10, 20));
        tarjeta.setSpacing(15);
        tarjeta.setMinHeight(70);
        tarjeta.setMinWidth(200);
        tarjeta.setMaxWidth(220);

        String estilo = jugador == jugadorActual
                ? "-fx-background-color: rgba(76, 86, 106, 0.8); " +
                "-fx-border-color: #88C0D0; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 10;"
                : "-fx-background-color: rgba(67, 76, 94, 0.7); " +
                "-fx-background-radius: 10; " +
                "-fx-border-color: rgba(136, 192, 208, 0.3); " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 10;";

        tarjeta.setStyle(estilo);
    }

    private Circle crearIconoJugador(int indiceColor) {
        Circle icono = new Circle(20);
        icono.setFill(COLORES_JUGADORES[indiceColor % COLORES_JUGADORES.length]);
        return icono;
    }

    private VBox crearInfoJugador(Jugador jugador) {
        VBox contenedor = new VBox();
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setSpacing(8);
        contenedor.setPrefWidth(130);

        Label nombre = new Label(jugador.obtenerNombre());
        nombre.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-font-weight: bold;");
        nombre.setMaxWidth(Double.MAX_VALUE);

        Label puntos = new Label(String.valueOf(jugador.calcularPuntosTotales()));
        puntos.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-weight: bold;");
        VBox.setMargin(puntos, new Insets(5, 0, 0, 0));

        contenedor.getChildren().addAll(nombre, puntos);
        return contenedor;
    }

}





