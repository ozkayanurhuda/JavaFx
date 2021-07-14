package com.jetbrains;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class ListViewDemo extends Application {
    // Declare an array of Strings for flag titles
    private String[] flagTitles = {"Canada", "China", "Denmark"};

    // Declare an ImageView array for the national flags of 9 countries
    private ImageView[] ImageViews = {
            new ImageView("com/jetbrains/image/canada.png"),
            new ImageView("com/jetbrains/image/china.png"),
            new ImageView("com/jetbrains/image/denmark.jpg"),

    };

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //create listview
        ListView<String> lv = new ListView<>
                (FXCollections.observableArrayList(flagTitles));
        lv.setPrefSize(400, 400);
        //multiple selection
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create a pane to hold image views
        FlowPane imagePane = new FlowPane(10, 10);
        //main pane
        BorderPane pane = new BorderPane();
        //listview sola
        pane.setLeft(new ScrollPane(lv));
        pane.setCenter(imagePane);

        //added listener
        lv.getSelectionModel().selectedItemProperty().addListener(
                ov -> {
                    //resimleri temizle
                    imagePane.getChildren().clear();
                    //tek tek resimleri getir
                    for (Integer i: lv.getSelectionModel().getSelectedIndices()) {
                        imagePane.getChildren().add(ImageViews[i]);
                    }
                });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ListViewDemo"); // Set the stage title
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
