package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.Inicio.MenuPrincipalScena;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // Crear la escena de menú
        MenuPrincipalScena menu = new MenuPrincipalScena(stage);
        Scene scene = menu.getScene();

        // Colocar la escena en el stage
        stage.setScene(menu.getScene());

        // Título de la ventana
        stage.setTitle("Catan");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setFullScreen(false);
                stage.setMaximized(true);
            }
        });
        // Mostrar ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
