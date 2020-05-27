package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrescriptionDetailsWindowMain extends Application {

    public void start(Stage primaryStage)throws IOException
    {
        AnchorPane root = FXMLLoader.load(getClass().getResource("PrescriptionDetailsWindow.fxml"));
        primaryStage.setTitle("Prescription Details");
        primaryStage.setScene(new Scene(root,400,414));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
