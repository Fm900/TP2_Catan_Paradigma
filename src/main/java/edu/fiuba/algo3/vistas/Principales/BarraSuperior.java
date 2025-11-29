package edu.fiuba.algo3.vistas.Principales;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarraSuperior {

    public static VBox crearLayoutConBarra(javafx.scene.Node contenido, Stage stage) {
        VBox layoutPrincipal = new VBox();
        layoutPrincipal.getStyleClass().add("layout-principal-con-barra");

        // Crear la barra de menú
        MenuBar menuBar = crearBarraMenuDiscreta(stage);

        // Layout general
        layoutPrincipal.getChildren().addAll(menuBar, contenido);
        VBox.setVgrow(contenido, Priority.ALWAYS);

        return layoutPrincipal;
    }

    private static MenuBar crearBarraMenuDiscreta(Stage stage) {
        MenuBar menuBar = new MenuBar();

        // ✔ Cargar CSS externo
        menuBar.getStylesheets().add(
                BarraSuperior.class.getResource("/estilos/barra-superior.css").toExternalForm()
        );

        /* -----------------------
           Menú JUEGO
        ------------------------- */
        Menu menuJuego = new Menu("Juego");

        MenuItem pantallaCompleta = new MenuItem("Pantalla Completa");
        MenuItem ventanaCompleta = new MenuItem("Modo Ventana");
        MenuItem salir = new MenuItem("Salir");

// Función para actualizar disponibilidad
        Runnable actualizarOpciones = () -> {
            boolean esMaximized = stage.isMaximized();

            pantallaCompleta.setDisable(!esMaximized);   // Deshabilitar si ya está en fullscreen
            ventanaCompleta.setDisable(esMaximized);   // Deshabilitar si está en ventana
        };

// Acción: activar pantalla completa
        pantallaCompleta.setOnAction(e -> {
            stage.setMaximized(false);
            stage.setFullScreen(true);
            actualizarOpciones.run();
        });

// Acción: salir de pantalla completa (modo ventana)
        ventanaCompleta.setOnAction(e -> {
            stage.setFullScreen(false);
            stage.setMaximized(true);
            actualizarOpciones.run();
        });

// Acción salir
        salir.setOnAction(e -> javafx.application.Platform.exit());

// Registrar listener por si el usuario sale con ESC

// Agregar items
        menuJuego.getItems().addAll(
                pantallaCompleta,
                ventanaCompleta,
                new SeparatorMenuItem(),
                salir
        );

// Inicializar estado
        actualizarOpciones.run();


        /* -----------------------
           Menú AYUDA
        ------------------------- */
        Menu menuAyuda = new Menu("Ayuda");
        MenuItem instrucciones = new MenuItem("Instrucciones");
        MenuItem acercaDe = new MenuItem("Acerca de");

        instrucciones.setOnAction(e -> System.out.println("Instrucciones"));
        acercaDe.setOnAction(e -> System.out.println("Acerca de"));

        menuAyuda.getItems().addAll(instrucciones, acercaDe);

        menuBar.getMenus().addAll(menuJuego, menuAyuda);

        return menuBar;
    }
}
