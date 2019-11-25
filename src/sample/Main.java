package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media ;
import javafx.scene.media.MediaPlayer ;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.getIcons().add(new Image(new FileInputStream("src/images/Plants-vs-Zombies-icon.png")));
        primaryStage.setTitle("Plant Vs Zombie");
        Scene firstScene = new Scene(root) ;
        primaryStage.setScene(firstScene);

        Media media = new Media(new File("src/audio/PVZ_Intro.wav").toURI().toString());
        AudioClip mediaPlayer = new AudioClip(media.getSource());
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        //TODO: The lines bellow this are just for testing
//        Player P = new Player() ;
//        System.out.println(P.getName());
    }
}
