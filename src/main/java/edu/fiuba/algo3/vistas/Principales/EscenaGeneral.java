package edu.fiuba.algo3.vistas.Principales;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public abstract  class EscenaGeneral {

    protected Scene scene;
    protected Pane root;
    protected Stage stage;

    public EscenaGeneral(Stage stage) {
        this.stage = stage;

        // Crear el layout específico de la escena hija
        Pane contenidoEscena = createLayout();

        // Envolver el contenido con la barra superior
        VBox layoutConBarra = BarraSuperior.crearLayoutConBarra(contenidoEscena, stage);
        BarraSuperior.inicializarControladorMusica();
        this.root = layoutConBarra;

        createControllers(stage);
        createStyles();
        loadBackgroundImage(root);

        Scene oldScene = stage.getScene();
        if (oldScene != null) {
            scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight());
        } else {
            scene = new Scene(root);
        }

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (stage.isMaximized() || stage.isFullScreen()) return;
            stage.setWidth(newVal.doubleValue());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (stage.isMaximized() || stage.isFullScreen()) return;
            stage.setHeight(newVal.doubleValue());
        });
    }

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
