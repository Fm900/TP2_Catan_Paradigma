package edu.fiuba.algo3.vistas.Inicio;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public abstract  class EscenaGeneral {

    protected Scene scene;
    protected Pane root;

    public EscenaGeneral(Stage stage) {

        // 1. Layout específico de la escena
        root = createLayout();

        // 2. Controladores (acciones y eventos)
        createControllers(stage);

        // 3. Estilos generales de la escena
        createStyles();

        // 4. Fondo personalizado por escena
        loadBackgroundImage(root);

        // 5. Crear la escena respetando tamaño del Stage actual
        Scene oldScene = stage.getScene();
        if (oldScene != null) {
            scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight());
        } else {
            scene = new Scene(root);
        }
    }

    // ============================
    // MÉTODOS QUE CADA ESCENA IMPLEMENTA
    // ============================

    // Layout principal (VBox, StackPane, BorderPane, etc.)
    protected abstract Pane createLayout();

    // Controladores: listeners, eventos, lógica UI
    protected abstract void createControllers(Stage stage);

    // Estilos CSS específicos de la escena
    protected abstract void createStyles();

    // ============================
    // FONDO PERSONALIZABLE POR ESCENA
    // ============================

    protected abstract String getBackgroundImagePath();
    // Ejemplo: return "/images/menu_principal.png";


    private void loadBackgroundImage(Pane root) {
        String path = getBackgroundImagePath();
        if (path == null) return;

        URL url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            throw new RuntimeException("No se encontró la imagen: " + path);
        }

        Image image = new Image(url.toString());

        BackgroundSize backgroundSize = new BackgroundSize(
                BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, true, true
        );

        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        root.setBackground(new Background(backgroundImage));
    }


    // Obtener la Scene final para colocar en el Stage
    public Scene getScene() {
        return scene;
    }
}
