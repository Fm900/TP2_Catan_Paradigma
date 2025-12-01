package edu.fiuba.algo3.controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ControladorMusica {
    private List<MediaPlayer> reproductores;
    private int cancionActual;
    private boolean musicaActivada;
    private double volumen;

    public ControladorMusica() {
        this.reproductores = new ArrayList<>();
        this.cancionActual = 0;
        this.musicaActivada = true;
        this.volumen = 0.7; // Volumen al 70%
        cargarCanciones();
    }

    private void cargarCanciones() {
        try {
            // Carga las canciones desde la carpeta resources
            String[] canciones = {
                    "/musica/cancion1.mp3",
                    "/musica/cancion2.mp3",
                    "/musica/cancion3.mp3"
            };

            for (String cancion : canciones) {
                URL resource = getClass().getResource(cancion);
                if (resource != null) {
                    Media media = new Media(resource.toString());
                    MediaPlayer player = new MediaPlayer(media);
                    player.setVolume(volumen);
                    player.setCycleCount(MediaPlayer.INDEFINITE); // Repetir indefinidamente
                    reproductores.add(player);
                }
            }

            // Reproducir la primera canción si hay canciones cargadas
            if (!reproductores.isEmpty()) {
                reproductores.get(0).play();
            }

        } catch (Exception e) {
            System.err.println("Error cargando música: " + e.getMessage());
        }
    }

    public void activarMusica() {
        if (musicaActivada) return;

        musicaActivada = true;
        if (!reproductores.isEmpty()) {
            reproductores.get(cancionActual).play();
        }
    }

    public void desactivarMusica() {
        if (!musicaActivada) return;

        musicaActivada = false;
        if (!reproductores.isEmpty()) {
            reproductores.get(cancionActual).pause();
        }
    }

    public void siguienteCancion() {
        if (reproductores.isEmpty()) return;

        // Detener canción actual
        reproductores.get(cancionActual).stop();

        // Avanzar a la siguiente canción
        cancionActual = (cancionActual + 1) % reproductores.size();

        // Reproducir nueva canción si la música está activada
        if (musicaActivada) {
            reproductores.get(cancionActual).play();
        }
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
        for (MediaPlayer player : reproductores) {
            player.setVolume(volumen);
        }
    }

    public boolean isMusicaActivada() {
        return musicaActivada;
    }

    public String getCancionActual() {
        if (reproductores.isEmpty()) return "No hay canciones";
        return "Canción " + (cancionActual + 1);
    }
}