package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LabWorkReminderMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        AnchorPane root =  FXMLLoader.load(getClass().getResource("LabWorkReminder.fxml"));
        primaryStage.setTitle("Lab Work Reminder");
        primaryStage.setScene(new Scene(root,400,200));
        primaryStage.show();
    }

}
