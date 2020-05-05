package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreatePrescriptionMain extends Application {

    public void setObj(AddScheduleWindowController obj)
    {
        CreatePrescriptionController.obj=obj;
    }

    public void setPatientName(String name)
    {
        CreatePrescriptionController.name=name;
    }

    public void start(Stage primaryStage)
    {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("CreatePrescription.fxml"));
            primaryStage.setTitle("Create Prescription");
            primaryStage.setScene(new Scene(root,400,375));
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public  static void main(String args[])
    {
        launch(args);
    }
}
