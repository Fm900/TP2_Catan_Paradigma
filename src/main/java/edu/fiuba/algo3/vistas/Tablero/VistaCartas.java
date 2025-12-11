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

public class VistaCartas {

    private final ControladorJugarCartas controlador;

    public VistaCartas(ControladorJugarCartas controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {

        Stage stage = new Stage();
        stage.setTitle("Cartas del jugador");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Cartas del jugador:");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(titulo);

        VBox lista = new VBox(20);
        lista.setAlignment(Pos.TOP_CENTER);

        for (Carta carta : controlador.obtenerCartasJugador()) {

            HBox contenedorCarta = new HBox(20);
            contenedorCarta.setAlignment(Pos.CENTER);

            // --- Imagen correspondiente según el nombre de la clase ---
            String nombre = carta.getClass().getSimpleName();
            String ruta = "/imagenes/" + nombre + ".png";
            System.out.println(ruta);

            ImageView img = new ImageView(new Image(ruta));
            img.setFitWidth(100);
            img.setFitHeight(140);

            Button boton = new Button("Jugar " + nombre);
            boton.setPrefWidth(160);

            boton.setOnAction(e -> {
                controlador.jugarCarta(carta);
                stage.close();
            });

            contenedorCarta.getChildren().addAll(img, boton);
            lista.getChildren().add(contenedorCarta);
        }

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(400);

        layout.getChildren().add(scroll);

        Button cerrar = new Button("Cerrar");
        cerrar.setOnAction(e -> stage.close());
        layout.getChildren().add(cerrar);

        Scene scene = new Scene(layout, 380, 550);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
