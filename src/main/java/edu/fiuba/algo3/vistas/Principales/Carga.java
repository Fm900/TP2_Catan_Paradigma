package edu.fiuba.algo3.vistas.Principales;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Carga extends EscenaGeneral {

    private ProgressBar progressBar;
    private Label loadingLabel;
    private Timeline loadingTimeline;
    private Runnable onLoadingComplete;

    public Carga(Stage stage, Runnable onLoadingComplete) {
        super(stage);
        this.onLoadingComplete = onLoadingComplete;
    }

    @Override
    protected VBox createLayout() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Etiqueta de carga
        loadingLabel = new Label("CARGANDO JUEGO...");
        loadingLabel.setTextFill(Color.WHITE);
        loadingLabel.setFont(Font.font("Arial", 24));

        // Barra de progreso
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);
        progressBar.setPrefHeight(20);

        layout.getChildren().addAll(loadingLabel, progressBar);
        return layout;
    }

    @Override
    protected void createControllers(Stage stage) {
        // Simular progreso de carga
        startLoadingAnimation();
    }

    @Override
    protected void createStyles() {
        progressBar.setStyle(
                "-fx-accent: #ffcc66;" +  // Color de la barra de progreso llena
                        "-fx-background-color: #2C3E50;" +  // Color de fondo de la barra
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #8a6d3b;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15;" +
                        "-fx-pref-width: 400px;" +
                        "-fx-pref-height: 25px;"
        );

        // Estilo para la etiqueta de carga
        loadingLabel.setStyle(
                "-fx-text-fill: #ffcc66;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0.1, 2, 2);"
        );
    }

    @Override
    protected String getBackgroundImagePath() {
        return "Imagenes/FondoDeCargaCatan.jpg";
    }

    private void startLoadingAnimation() {
        double duration = 2.0; // 2 segundos de carga
        int frames = 50; // 50 actualizaciones

        loadingTimeline = new Timeline();

        for (int i = 0; i <= frames; i++) {
            final int progress = i;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds((duration / frames) * progress),
                    e -> updateProgress(progress, frames)
            );
            loadingTimeline.getKeyFrames().add(keyFrame);
        }

        // Al completar la animación
        loadingTimeline.setOnFinished(e -> {
            if (onLoadingComplete != null) {
                onLoadingComplete.run();
            }
        });

        loadingTimeline.play();
    }

    private void updateProgress(int currentFrame, int totalFrames) {
        double progress = (double) currentFrame / totalFrames;
        progressBar.setProgress(progress);
        loadingLabel.setText("CARGANDO JUEGO... " + (int)(progress * 100) + "%");
    }

}