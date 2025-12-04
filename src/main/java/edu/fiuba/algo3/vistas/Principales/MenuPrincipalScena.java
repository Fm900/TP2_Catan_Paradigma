package edu.fiuba.algo3.vistas.Principales;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipalScena extends EscenaGeneral {

    private Button jugar;

    public MenuPrincipalScena(Stage stage) {
        super(stage);
    }

    @Override
    protected String getBackgroundImagePath() {
        return "Imagenes/FondoDeMenuPrincipalCatan.jpg";
    }

    @Override
    protected Pane createLayout() {

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setFillWidth(false);
        vbox.setStyle("-fx-alignment: center;");
        vbox.setMinHeight(Region.USE_PREF_SIZE);

        jugar = new Button("Jugar");
        vbox.getChildren().add(jugar);

        StackPane root = new StackPane(vbox);

        StackPane.setAlignment(vbox, Pos.BOTTOM_CENTER);

        StackPane.setMargin(vbox, new Insets(0, 0, -100, 0));

        return root;
    }

    @Override
    protected void createControllers(Stage stage) {

        jugar.setOnAction(e -> {
            boolean estabaFullScreen = stage.isFullScreen();
            boolean estabaMaximized = stage.isMaximized();


            SeleccionarJugadores escena = new SeleccionarJugadores(stage);
            stage.setScene(escena.getScene());

            Platform.runLater(() -> {
                if (estabaFullScreen) {
                    stage.setFullScreen(true);
                } else if (estabaMaximized) {
                    stage.setMaximized(true);
                }
            });

            System.out.println("Iniciar juego...");
        });

    }

    @Override
    protected void createStyles() {
        jugar.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-background-color: #ffcc66;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 30;"
        );
    }
}
