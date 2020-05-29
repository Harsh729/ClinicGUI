package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatePatientMain extends Application {

    public void start(Stage primaryStage)throws IOException
    {
        AnchorPane root= FXMLLoader.load(getClass().getResource("CreatePatient.fxml"));
        primaryStage.setTitle("Create Record");
        primaryStage.setScene(new Scene(root,400,375));
        primaryStage.show();
    }

    public void setObj(AddScheduleWindowController obj)
    {
        CreatePatientController.obj=obj;
    }

    public void setMainWindowController(MainWindowController obj) { CreatePatientController.setMainWindowController(obj); }

    public static void main(String args[])
    {
        launch(args);
    }
}
