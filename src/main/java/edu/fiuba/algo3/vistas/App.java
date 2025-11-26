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


        MenuPrincipalScena menu = new MenuPrincipalScena(stage);


        stage.setScene(menu.getScene());
        stage.setTitle("Catan");


        stage.setFullScreen(true);


        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);


        stage.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (stage.isFullScreen()) {
                    // Salir de fullscreen → pasar a ventana maximizada
                    stage.setFullScreen(false);
                    stage.setMaximized(true);
                } else {
                    stage.setFullScreen(true);
                }
                event.consume();
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
