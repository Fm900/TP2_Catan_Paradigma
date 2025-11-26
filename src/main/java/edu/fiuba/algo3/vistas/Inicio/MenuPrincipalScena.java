package edu.fiuba.algo3.vistas.Inicio;

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
        return "/imagenes/FondoDeMenuPrincipalCatan.jpg";
    }

    @Override
    protected Pane createLayout() {

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setFillWidth(false);
        vbox.setStyle("-fx-alignment: center;");
        vbox.setMinHeight(Region.USE_PREF_SIZE);  // <--- clave para evitar que se expanda

        jugar = new Button("Jugar");
        vbox.getChildren().add(jugar);

        StackPane root = new StackPane(vbox);

        // Alinear abajo
        StackPane.setAlignment(vbox, Pos.BOTTOM_CENTER);

        // Moverlo bien abajo (probá 250, 300, 350...)
        StackPane.setMargin(vbox, new Insets(0, 0, -100, 0));

        return root;
    }

    @Override
    protected void createControllers(Stage stage) {

        jugar.setOnAction(e -> {
            SeleccionarJugadores escena = new SeleccionarJugadores(stage);
            stage.setScene(escena.getScene());
            stage.setMaximized(true);
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
