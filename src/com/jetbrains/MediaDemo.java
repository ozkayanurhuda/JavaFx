package com.jetbrains;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaDemo extends Application {
    private static final String MEDIA_URL =
            "https://liveexample-ppe.pearsoncmg.com/common/sample.mp4";

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //media obj is created
        Media media = new Media(MEDIA_URL);
        //media player obj is created by using the media obj
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        //mediaview obj is created from the med player
        MediaView mediaView = new MediaView(mediaPlayer);

        //play pause the video
        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });

        //rewind başa al geriye alma seek
        Button rewindButton = new Button("<<");
        rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));

        //Volume prop
        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        //volume prop is binded to the value prop of the slider class
        mediaPlayer.volumeProperty().bind(
                slVolume.valueProperty().divide(100));

        //hboxa düğmeleri yerleştir
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton, rewindButton,
                new Label("Volume"), slVolume);

        //hboxu bottoma yerleştir
        //media centera
        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 650, 500);
        primaryStage.setTitle("MediaDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
