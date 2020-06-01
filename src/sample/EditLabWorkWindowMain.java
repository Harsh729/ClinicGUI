package sample;

import ClinicSoftware.LabWork;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EditLabWorkWindowMain extends Application {

    public void start(Stage primaryStage) throws IOException
    {
        AnchorPane root = FXMLLoader.load(getClass().getResource("EditLabWorkWindow.fxml"));
        primaryStage.setTitle("Edit Lab Work");
        primaryStage.setScene(new Scene(root,400,376));
        primaryStage.show();
    }

    public void setMainWindowControllerObject(MainWindowController obj) { EditLabWorkWindowController.setMainWindowController(obj); }

    public void setLabWork(LabWork labWork) { EditLabWorkWindowController.setLabWork(labWork); }

    public static void main(String[] args)
    {
        launch(args);
    }
}
