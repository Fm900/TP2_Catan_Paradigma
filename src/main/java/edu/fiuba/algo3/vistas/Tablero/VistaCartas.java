package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.controllers.ControladorJugarCartas;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class VistaCartas {

    private final ControladorJugarCartas controlador;

    public VistaCartas(ControladorJugarCartas controlador) {
        this.controlador = controlador;
    }
    public void mostrar() {

        Stage miniStage = new Stage();
        miniStage.setTitle("Cartas del jugador");
        miniStage.initModality(Modality.APPLICATION_MODAL);
        miniStage.setResizable(false);

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-border-radius: 10;" +
                "-fx-background-color: rgba(0,0,0,0.5);" +
                "-fx-padding: 20;");

        Label titulo = new Label("Cartas del jugador");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(titulo);

        // ------------------ Lista Horizontal ------------------
        HBox lista = new HBox(20);  // espacio mayor entre cartas
        lista.setAlignment(Pos.CENTER_LEFT);

        List<Carta> cartas = controlador.obtenerCartasJugador();

        for (Carta carta : cartas) {
            VBox cartaBox = new VBox(10);
            cartaBox.setAlignment(Pos.CENTER);
            cartaBox.setPrefWidth(120);  // ancho fijo
            cartaBox.setPrefHeight(180); // alto fijo

            // Imagen
            String nombre = carta.getClass().getSimpleName();
            String ruta = "/imagenes/" + nombre + ".png";
            ImageView imagen = new ImageView(new Image(ruta));
            imagen.setPreserveRatio(true); // ✅ mantiene proporción original
            imagen.setFitWidth(100);       // ancho máximo
            imagen.setFitHeight(120);      // altura máxima

            // Nombre
            Label nombreCarta = new Label(nombre);
            nombreCarta.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            // Botón jugar
            Button jugar = new Button("Jugar");
            jugar.setMaxWidth(Double.MAX_VALUE); // botón ocupa todo el ancho de la carta
            jugar.setStyle(
                    "-fx-border-radius: 10;" +
                            " -fx-border-width: 2;" +
                            " -fx-border-color: white;" +
                            " -fx-background-radius: 10;" +
                            " -fx-background-color: linear-gradient(to bottom, #2e4d2e, #1f331f);"
            );
            jugar.setOnAction(e -> {
                controlador.jugarCarta(carta);
                miniStage.close();
            });

            cartaBox.getChildren().addAll(imagen, nombreCarta, jugar);
            lista.getChildren().add(cartaBox);
        }

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToHeight(true);
        scroll.setPrefHeight(220);          // altura del scroll
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        layout.getChildren().add(scroll);
    }
}
