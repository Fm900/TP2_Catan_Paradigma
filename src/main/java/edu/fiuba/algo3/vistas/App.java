package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.Principales.MenuPrincipalScena;
import javafx.application.Application;
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

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
