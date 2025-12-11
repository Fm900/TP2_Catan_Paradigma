package edu.fiuba.algo3.vistas.Otros;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MostrarVictoria {

    private final String rutaCss;

    public MostrarVictoria(String rutaCss) {
        this.rutaCss = rutaCss;
    }

    public void mostrar(String nombreJugador) {

        Stage miniVentana = new Stage();
        miniVentana.initStyle(StageStyle.UNDECORATED);
        miniVentana.initModality(Modality.APPLICATION_MODAL);


        ImageView icono = new ImageView(
                new Image(getClass().getResourceAsStream("/Imagenes/trofeo.png"))
        );
        icono.setFitWidth(80);
        icono.setFitHeight(80);
        icono.setPreserveRatio(true);

        ScaleTransition iconBounce = new ScaleTransition(Duration.millis(700), icono);
        iconBounce.setFromX(0.8);
        iconBounce.setFromY(0.8);
        iconBounce.setToX(1);
        iconBounce.setToY(1);
        iconBounce.setCycleCount(1);
        iconBounce.setInterpolator(Interpolator.EASE_OUT);


        Label titulo = new Label("Victoria!");
        titulo.getStyleClass().add("victoria-titulo");


        Label mensaje = new Label("El ganador de la partida es:\n" + nombreJugador);
        mensaje.getStyleClass().add("victoria-label");
        mensaje.setWrapText(true);


        Button cerrar = new Button("Finalizar");
        cerrar.getStyleClass().add("victoria-boton");
        cerrar.setOnAction(e -> Platform.exit());
        miniVentana.setOnCloseRequest(e -> Platform.exit());

        VBox layout = new VBox(20, icono, titulo, mensaje, cerrar);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("victoria-root");

        Scene scene = new Scene(layout);
        miniVentana.sizeToScene();

        scene.getStylesheets().add(
                getClass().getResource(rutaCss).toExternalForm()
        );

        miniVentana.setScene(scene);

        layout.setOpacity(0);
        layout.setScaleX(0.85);
        layout.setScaleY(0.85);

        FadeTransition fade = new FadeTransition(Duration.millis(400), layout);
        fade.setFromValue(0);
        fade.setToValue(1);

        ScaleTransition scale = new ScaleTransition(Duration.millis(400), layout);
        scale.setFromX(0.85);
        scale.setFromY(0.85);
        scale.setToX(1);
        scale.setToY(1);
        scale.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition entrada = new ParallelTransition(fade, scale);

        miniVentana.show();
        entrada.play();
        iconBounce.play();
    }
}
