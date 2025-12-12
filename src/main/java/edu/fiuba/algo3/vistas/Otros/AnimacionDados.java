package edu.fiuba.algo3.vistas.Otros;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;

public class AnimacionDados {

    public static void mostrarAnimacion(Stage owner, int valor1Final, int valor2Final) {

        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Tirada de dados");


        ImageView dado1 = new ImageView();
        ImageView dado2 = new ImageView();

        dado1.setFitWidth(80);
        dado1.setFitHeight(80);
        dado2.setFitWidth(80);
        dado2.setFitHeight(80);

        // Texto del resultado
        Label resultadoLabel = new Label("");
        resultadoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        resultadoLabel.setWrapText(true);
        HBox boxDados = new HBox(20, dado1, dado2);
        boxDados.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, boxDados, resultadoLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new javafx.geometry.Insets(20));

        root.getStyleClass().add("popup-dados");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                Objects.requireNonNull(
                        AnimacionDados.class.getResource("/estilos/dados.css")
                ).toExternalForm()
        );
        dialog.setScene(scene);
        dialog.setMinWidth(260);
        dialog.setMinHeight(230);

        dialog.show();

        Timeline animacion = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    dado1.setImage(cargarCaraAleatoria());
                    dado2.setImage(cargarCaraAleatoria());
                })
        );
        animacion.setCycleCount(Animation.INDEFINITE);
        animacion.play();

        PauseTransition pausa = new PauseTransition(Duration.seconds(1));
        pausa.setOnFinished(e -> {
            animacion.stop();

            dado1.setImage(cargarCara(valor1Final));
            dado2.setImage(cargarCara(valor2Final));

            int suma = valor1Final + valor2Final;
            if ( valor1Final + valor2Final == 7 ) {
                resultadoLabel.setText("Resultado: " + suma + "selecciona un terreno, un vertice y mueve al ladron");
            }else {
                resultadoLabel.setText("Resultado: " + suma);
            }
            PauseTransition mostrarResultado = new PauseTransition(Duration.seconds(1.5));
            mostrarResultado.setOnFinished(event -> dialog.close());
            mostrarResultado.play();
        });

        pausa.play();
    }

    private static Image cargarCaraAleatoria() {
        int v = (int)(Math.random() * 6) + 1;
        return new Image("/imagenes/dado" + v + ".png");
    }

    private static Image cargarCara(int valor) {
        return new Image("/imagenes/dado" + valor + ".png");
    }
}

